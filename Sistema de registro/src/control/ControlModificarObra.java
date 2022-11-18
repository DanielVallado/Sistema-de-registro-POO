package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioAdministrador;
import vista.*;

public class ControlModificarObra implements ActionListener{
    private VistaModificarObra VistaModificarObra;
    
    
    public ControlModificarObra(VistaModificarObra VistaModificarObra){
        this.VistaModificarObra = VistaModificarObra;
        this.VistaModificarObra.getBtnVolver().addActionListener(this);
        this.VistaModificarObra.getBtnModificar().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(VistaModificarObra.getBtnModificar() == evento.getSource()){
            
        }
        
        if(VistaModificarObra.getBtnVolver() == evento.getSource()){
            VistaObra vistaObra = new VistaObra();
            ControlObras ControlObra = new ControlObras(vistaObra);
            vistaObra.setVisible(true);
            this.VistaModificarObra.dispose();
        }  
    }
}
