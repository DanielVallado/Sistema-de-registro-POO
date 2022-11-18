package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioAdministrador;
import vista.*;


public class ControlEliminarObra implements ActionListener {
    private VistaEliminarObra vistaEliminarObra;
    
    
    public ControlEliminarObra(VistaEliminarObra vistaEliminarObra){
        this.vistaEliminarObra = vistaEliminarObra;
        this.vistaEliminarObra.getBtnVolver().addActionListener(this);
        this.vistaEliminarObra.getBtnEliminar().addActionListener(this);
        this.vistaEliminarObra.getTxtError().setVisible(false);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaEliminarObra.getBtnEliminar() == evento.getSource()){
            String nombreObra = vistaEliminarObra.getEntradaNombre().getText();
            if(nombreObra.equals("")){
                this.vistaEliminarObra.getTxtError().setVisible(true);
                this.vistaEliminarObra.getTxtError().setText("Entrada vacía");
            } else{
                verificarObra(nombreObra);
            }
        }
        
        if(vistaEliminarObra.getBtnVolver() == evento.getSource()){
            VistaObra vistaObra = new VistaObra();
            ControlObras ControlObra = new ControlObras(vistaObra);
            vistaObra.setVisible(true);
            this.vistaEliminarObra.dispose();
        }  
    }
    
    private void verificarObra(String nombreObra){
        if(UsuarioAdministrador.verificarExistenciaObra(nombreObra)){
            UsuarioAdministrador.eliminarObra(nombreObra);
            JOptionPane.showMessageDialog(null, nombreObra + " ha sido eliminada exitosamente", "Eliminación exitosa", JOptionPane.PLAIN_MESSAGE);
            VistaObra vistaObra = new VistaObra();
            ControlObras controlObras = new ControlObras(vistaObra);
            vistaObra.setVisible(true);
            this.vistaEliminarObra.dispose();
        } else{
            this.vistaEliminarObra.getTxtError().setVisible(true);
            this.vistaEliminarObra.getTxtError().setText("Nombre de obra inexistente");
        }
    }
}

