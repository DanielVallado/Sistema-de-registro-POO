package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.UsuarioCliente;
import vista.*;

public class ControlReservar implements ActionListener{
    private Reservar vistaReservar;
    private UsuarioCliente clienteActivo;
    
    public ControlReservar(Reservar vistaReservar, UsuarioCliente clienteActivo){
        this.vistaReservar = vistaReservar;
        this.clienteActivo = clienteActivo;
        
        this.vistaReservar.getBtnInicio().addActionListener(this);
        this.vistaReservar.getBtnPerfil().addActionListener(this);
        this.vistaReservar.getBtnCerrarSesion().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.vistaReservar.getBtnInicio() == evento.getSource()){
            MenuUsuario vistaMenuUsuario = new MenuUsuario();
            ControlMenuUsuario controlMenuUsuario = new ControlMenuUsuario(vistaMenuUsuario, clienteActivo);
            vistaMenuUsuario.setVisible(true);
            this.vistaReservar.dispose();
        }
        
        if(this.vistaReservar.getBtnPerfil() == evento.getSource()){
            Perfil vistaPerfil = new Perfil();
            ControlPerfil controlPerfil = new ControlPerfil(vistaPerfil, clienteActivo);
            vistaPerfil.setVisible(true);
            this.vistaReservar.dispose();
        }
        
        if(this.vistaReservar.getBtnCerrarSesion() == evento.getSource()){
            VistaIndex vistaIndex = new VistaIndex();
            ControlIndex index = new ControlIndex(vistaIndex);
            vistaIndex.setVisible(true);
            this.vistaReservar.dispose();
        }
    }  
}
