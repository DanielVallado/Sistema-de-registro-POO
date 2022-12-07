package control;
import vista.MenuAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;

public class ControlMenuAdmin implements ActionListener {
    private  MenuAdmin vistaMenuAdmin;
    
    public ControlMenuAdmin (MenuAdmin vistaMenuAdmin){
        this.vistaMenuAdmin = vistaMenuAdmin;
        this.vistaMenuAdmin.getBtnUsuario().addActionListener(this);
        this.vistaMenuAdmin.getBtnObra().addActionListener(this);
        this.vistaMenuAdmin.getBtnReporte().addActionListener(this);
        this.vistaMenuAdmin.getBtnCerrarSesion().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaMenuAdmin.getBtnUsuario() == evento.getSource()){
            VistaUsuarios vistaUsuarios = new VistaUsuarios();
            ControlVistaUsuarios controlVistaUsuario = new ControlVistaUsuarios(vistaUsuarios);
            vistaUsuarios.setVisible(true);
            this.vistaMenuAdmin.dispose();
        }
    
        if (vistaMenuAdmin.getBtnObra() == evento.getSource()){
            VistaObras vistaObras = new VistaObras();
            ControlVistaObras ControlVistaObras = new ControlVistaObras(vistaObras);
            vistaObras.setVisible(true);
            this.vistaMenuAdmin.dispose();
        }
        
        if(vistaMenuAdmin.getBtnReporte() == evento.getSource()){
            //In process
        }
        
        if(vistaMenuAdmin.getBtnCerrarSesion() == evento.getSource()){
            VistaIndex vistaIndex = new VistaIndex();
            ControlIndex index = new ControlIndex(vistaIndex);
            vistaIndex.setVisible(true);
            this.vistaMenuAdmin.dispose();
        }
    }
}