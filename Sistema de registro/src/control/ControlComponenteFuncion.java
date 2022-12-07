package control;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Funcion;
import modelo.Obra;
import modelo.UsuarioAdministrador;
import vista.VistaComponenteFuncion;
import vista.VistaFunciones;
import vista.VistaModificarFuncion;


public class ControlComponenteFuncion implements ActionListener{
    private VistaComponenteFuncion vistaComponenteFuncion;
    private VistaFunciones vistaFunciones;
    private Obra obra;
    private Funcion funcion;
    
    public ControlComponenteFuncion(VistaComponenteFuncion vistaComponenteFuncion, Funcion funcion, VistaFunciones vistaFunciones, Obra obra){
        this.vistaComponenteFuncion = vistaComponenteFuncion;
        this.funcion = funcion;
        this.obra = obra;
        this.vistaFunciones = vistaFunciones;
        this.vistaComponenteFuncion.getFecha().setText(String.valueOf(this.funcion.getFecha()));
        this.vistaComponenteFuncion.getHora().setText(this.funcion.getHorario());
        this.vistaComponenteFuncion.getBtnModificarFuncion().addActionListener(this);
        this.vistaComponenteFuncion.getBtnEliminarFuncion().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        String[] botonesString = {"Sí", "No"};
        Object[] botonesObject = {"1", "2"};
        int verificador;
        
        if(vistaComponenteFuncion.getBtnModificarFuncion()== evento.getSource()){
            VistaModificarFuncion VistaModificarFuncion = new VistaModificarFuncion();
            ControlModificarFuncion ControlModificarFuncion = new ControlModificarFuncion(VistaModificarFuncion, funcion, obra);
            VistaModificarFuncion.setVisible(true);
            this.vistaFunciones.dispose();
        }
        
        if(vistaComponenteFuncion.getBtnEliminarFuncion()== evento.getSource()){
            verificador = JOptionPane.showOptionDialog (null, "¿Seguro que desea eliminar esta función?", "Eliminar Función", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botonesString, botonesObject[0]);
            if(verificador == 0){
                UsuarioAdministrador.eliminarFuncion(this.funcion.getNombreObra(), this.funcion.getFecha(), this.funcion.getHorario());
                JOptionPane.showMessageDialog(null, "La función del día " + this.funcion.getFecha() + " a las " + this.funcion.getHorario() + " ha sido eliminada exitosamente", "Eliminación exitosa", JOptionPane.PLAIN_MESSAGE);
                this.vistaFunciones.getPanelFunciones().removeAll();
                this.vistaFunciones.getPanelFunciones().repaint();
                ArrayList<Funcion> listaFunciones = UsuarioAdministrador.buscarFuncion(funcion.getNombreObra());
                for(int i = 0; i < listaFunciones.size(); i++) {     
                    VistaComponenteFuncion panelFuncion = new VistaComponenteFuncion();
                    ControlComponenteFuncion controlComponenteFuncion = new ControlComponenteFuncion(panelFuncion, listaFunciones.get(i), vistaFunciones, obra);
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.gridx = 0; 
                    constraints.gridy = i; 
                    constraints.gridwidth = 1;
                    constraints.gridheight = 1; 
                    this.vistaFunciones.getPanelFunciones().add(panelFuncion, constraints);
                    this.vistaFunciones.getPanelFunciones().updateUI();      
                }  
            } 
        }
    }
}
