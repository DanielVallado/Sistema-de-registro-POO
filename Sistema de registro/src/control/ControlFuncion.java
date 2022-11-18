package control;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFuncion implements ActionListener{
    private VistaFuncion vistaFuncion;
    
    public ControlFuncion(VistaFuncion funcion){
        this.vistaFuncion = funcion;
        
        this.vistaFuncion.getBtnObras().addActionListener(this);
        this.vistaFuncion.getBtnUsuarios().addActionListener(this);
        this.vistaFuncion.getBtnFunciones().addActionListener(this);
        this.vistaFuncion.getBtnVolverAtras().addActionListener(this);
        this.vistaFuncion.getBtnCrearFuncion().addActionListener(this);
        this.vistaFuncion.getBtnModificarFuncion().addActionListener(this);
        this.vistaFuncion.getBtnEliminarFuncion().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaFuncion.getBtnUsuarios() == evento.getSource()){
            VistaUsuario vistaUsuario = new VistaUsuario();
            ControlUsuario controlVistaUsuario = new ControlUsuario(vistaUsuario);
            vistaUsuario.setVisible(true);
            this.vistaFuncion.dispose();
        }
    
        if (vistaFuncion.getBtnObras() == evento.getSource()){
            VistaObra vistaObra = new VistaObra();
            ControlObras controlObras = new ControlObras(vistaObra);
            vistaObra.setVisible(true);
            this.vistaFuncion.dispose();
        }
        
        if(vistaFuncion.getBtnVolverAtras() == evento.getSource()){
            MenuAdmin vistaMenuAdmin = new MenuAdmin();
            ControlMenuAdmin menuUsuario = new ControlMenuAdmin(vistaMenuAdmin);
            vistaMenuAdmin.setVisible(true);
            this.vistaFuncion.dispose();
        }
        
        if(vistaFuncion.getBtnCrearFuncion() == evento.getSource()){
            
        }
        
        if(vistaFuncion.getBtnModificarFuncion() == evento.getSource()){
            
        }
        
        if(vistaFuncion.getBtnEliminarFuncion() == evento.getSource()){
            
        }   
    }
}
