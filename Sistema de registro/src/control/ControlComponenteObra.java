package control;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Obra;
import modelo.UsuarioAdministrador;
import vista.VistaComponenteObra;
import vista.VistaFunciones;
import vista.VistaModificarObra;
import vista.VistaObras;

public class ControlComponenteObra implements ActionListener{
    private VistaComponenteObra vistaComponenteObra;
    private VistaObras vistaObras;
    private Obra obra;
    
    public ControlComponenteObra(VistaComponenteObra vistaComponenteObra, Obra obra, VistaObras vistaObras){
        this.vistaComponenteObra = vistaComponenteObra;
        this.obra = obra;
        this.vistaObras = vistaObras;
        this.vistaComponenteObra.getNombreObra().setText(this.obra.getNombreObra());
        this.vistaComponenteObra.getBtnVerFunciones().addActionListener(this);
        this.vistaComponenteObra.getBtnModificarObra().addActionListener(this);
        this.vistaComponenteObra.getBtnEliminarObra().addActionListener(this);
        this.vistaComponenteObra.getNombreObra().setText(this.obra.getNombreObra());
        this.vistaComponenteObra.getDuracion().setText(this.obra.getDuracion());
        this.vistaComponenteObra.getGenero().setText(this.obra.getGenero());
        this.vistaComponenteObra.getResumen().setText(this.obra.getResumen());
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        String[] botonesString = {"Sí", "No"};
        Object[] botonesObject = {"1", "2"};
        int verificador;
        
        if(vistaComponenteObra.getBtnVerFunciones()== evento.getSource()){
            VistaFunciones vistaFunciones = new VistaFunciones();
            ControlVistaFunciones controlVistaFunciones = new ControlVistaFunciones(vistaFunciones, obra);
            vistaFunciones.setVisible(true);
            this.vistaObras.dispose();
        }
        
        if(vistaComponenteObra.getBtnModificarObra()== evento.getSource()){
            VistaModificarObra vistaModificarObra = new VistaModificarObra();
            ControlModificarObra controlPreModificarObra = new ControlModificarObra(vistaModificarObra, obra);
            vistaModificarObra.setVisible(true);
            this.vistaObras.dispose();
        }
        
        if(vistaComponenteObra.getBtnEliminarObra() == evento.getSource()){
            verificador = JOptionPane.showOptionDialog (null, "¿Seguro que desea eliminar esta obra?", "Eliminar Obra", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botonesString, botonesObject[0]);
            if(verificador == 0){
                UsuarioAdministrador.eliminarObra(this.obra.getNombreObra());
                JOptionPane.showMessageDialog(null, this.obra.getNombreObra() + " ha sido eliminado exitosamente", "Eliminación exitosa", JOptionPane.PLAIN_MESSAGE);
                this.vistaObras.getPanelObras().removeAll();
                this.vistaObras.getPanelObras().repaint();
                ArrayList<Obra> listaObras = UsuarioAdministrador.obtenerTodasObras();
                for(int i = 0; i < listaObras.size(); i++) {     
                    VistaComponenteObra panelObra = new VistaComponenteObra();
                    ControlComponenteObra controlComponenteObra = new ControlComponenteObra(panelObra, listaObras.get(i), this.vistaObras);
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.gridx = 0; 
                    constraints.gridy = i; 
                    constraints.gridwidth = 1;
                    constraints.gridheight = 1; 
                    this.vistaObras.getPanelObras().add(panelObra, constraints);
                    this.vistaObras.getPanelObras().updateUI();      
                }  
            } 
        }
    }
}
