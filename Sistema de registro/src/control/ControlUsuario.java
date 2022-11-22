package control;
import java.awt.event.ActionEvent;
import vista.VistaUsuario;
import java.awt.event.ActionListener;
import vista.*;


public class ControlUsuario implements ActionListener {
    private VistaUsuario vistaUsuario;
    
    public ControlUsuario(VistaUsuario usuario){
        this.vistaUsuario = usuario;
        this.vistaUsuario.getBtnObras().addActionListener(this);
        this.vistaUsuario.getBtnUsuarios().addActionListener(this);
        this.vistaUsuario.getBtnFunciones().addActionListener(this);
        this.vistaUsuario.getBtnModificarUsuario().addActionListener(this);
        this.vistaUsuario.getBtnEliminarUsuario().addActionListener(this);
        this.vistaUsuario.getBtnVolverAtras().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (vistaUsuario.getBtnObras() == evento.getSource()){
            VistaObra vistaObra = new VistaObra();
            ControlObras controlObras = new ControlObras(vistaObra);
            vistaObra.setVisible(true);
            this.vistaUsuario.dispose();
        }
        
        if(vistaUsuario.getBtnFunciones() == evento.getSource()){
            VistaFuncion vistaFuncion = new VistaFuncion();
            ControlFuncion controlFuncion = new ControlFuncion(vistaFuncion);
            vistaFuncion.setVisible(true);
            this.vistaUsuario.dispose();
        }
        
        if(vistaUsuario.getBtnVolverAtras() == evento.getSource()){
            MenuAdmin vistaMenuAdmin = new MenuAdmin();
            ControlMenuAdmin menuUsuario = new ControlMenuAdmin(vistaMenuAdmin);
            vistaMenuAdmin.setVisible(true);
            this.vistaUsuario.dispose();
        }
        
        if(vistaUsuario.getBtnModificarUsuario() == evento.getSource()){
            VistaPreModificarUsuario vistaPreModificarUsuario = new VistaPreModificarUsuario();
            ControlPreModificarUsuario controlPreModificarUsuario = new ControlPreModificarUsuario(vistaPreModificarUsuario);
            vistaPreModificarUsuario.setVisible(true);
            this.vistaUsuario.dispose();
        }
        
        if(vistaUsuario.getBtnEliminarUsuario() == evento.getSource()){
            VistaEliminarUsuario vistaEliminarUsuario = new VistaEliminarUsuario();
            ControlEliminarUsuario controlEliminarUsuario = new ControlEliminarUsuario(vistaEliminarUsuario);
            vistaEliminarUsuario.setVisible(true);
            this.vistaUsuario.dispose();
        }    
    }   
}
