package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;

public class ControlObras implements ActionListener{
    private VistaObra vistaObra;
    
    public ControlObras(VistaObra vistaObra){
        this.vistaObra = vistaObra;
        this.vistaObra.getBtnObras().addActionListener(this);
        this.vistaObra.getBtnUsuarios().addActionListener(this);
        this.vistaObra.getBtnFunciones().addActionListener(this);
        this.vistaObra.getBtnVolverAtras().addActionListener(this);
        this.vistaObra.getBtnCrearObra().addActionListener(this);
        this.vistaObra.getBtnModificarObra().addActionListener(this);
        this.vistaObra.getBtnEliminarObra().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        if(vistaObra.getBtnUsuarios() == evento.getSource()){
            VistaUsuario vistaUsuario = new VistaUsuario();
            ControlUsuario controlVistaUsuario = new ControlUsuario(vistaUsuario);
            vistaUsuario.setVisible(true);
            this.vistaObra.dispose();
        }
        
        if(vistaObra.getBtnFunciones() == evento.getSource()){
            VistaFuncion vistaFuncion = new VistaFuncion();
            ControlFuncion controlFuncion = new ControlFuncion(vistaFuncion);
            vistaFuncion.setVisible(true);
            this.vistaObra.dispose();
        }
        
        if(vistaObra.getBtnVolverAtras() == evento.getSource()){
            MenuAdmin vistaMenuAdmin = new MenuAdmin();
            ControlMenuAdmin menuUsuario = new ControlMenuAdmin(vistaMenuAdmin);
            vistaMenuAdmin.setVisible(true);
            this.vistaObra.dispose();
        }
        
        if(vistaObra.getBtnCrearObra() == evento.getSource()){
            VistaCrearObra vistaCrearObra = new VistaCrearObra();
            ControlCrearObra ControlCrearObra = new ControlCrearObra(vistaCrearObra);
            vistaCrearObra.setVisible(true);
            this.vistaObra.dispose();
        }
        
        if(vistaObra.getBtnModificarObra() == evento.getSource()){
            VistaModificarObra vistaModificarObra = new VistaModificarObra();
            ControlModificarObra controlModificarObra = new ControlModificarObra(vistaModificarObra);
            vistaModificarObra.setVisible(true);
            this.vistaObra.dispose();
        }   
        
        if(vistaObra.getBtnEliminarObra() == evento.getSource()){
            VistaEliminarObra vistaEliminarObra = new VistaEliminarObra();
            ControlEliminarObra controlEliminarObra = new ControlEliminarObra(vistaEliminarObra);
            vistaEliminarObra.setVisible(true);
            this.vistaObra.dispose();
        } 
    }   
}
