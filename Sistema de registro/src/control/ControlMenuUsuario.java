package control;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.UsuarioCliente;

public class ControlMenuUsuario implements ActionListener{
    private MenuUsuario vistaMenuUsuario;
    private UsuarioCliente clienteActivo;
    
    public ControlMenuUsuario(MenuUsuario vistaMenuUsuario, UsuarioCliente clienteActivo){
        this.vistaMenuUsuario = vistaMenuUsuario;
        this.clienteActivo = clienteActivo;
        this.vistaMenuUsuario.getBtnInicio().addActionListener(this);
        this.vistaMenuUsuario.getBtnReservar().addActionListener(this);
        this.vistaMenuUsuario.getBtnPerfil().addActionListener(this);
        this.vistaMenuUsuario.getBtnCerrarSesion().addActionListener(this);
        this.vistaMenuUsuario.getTxtBienvenida().setText("Bienvenido " + this.clienteActivo.getUsuario());
    }

    @Override
    public void actionPerformed(ActionEvent evento) {        
        if(vistaMenuUsuario.getBtnReservar() == evento.getSource()){
            Reservar vistaReservar = new Reservar();
            ControlReservar controlReservar = new ControlReservar(vistaReservar, clienteActivo);
            vistaReservar.setVisible(true);
            this.vistaMenuUsuario.dispose();
        }
        
        if(vistaMenuUsuario.getBtnPerfil() == evento.getSource()){
            Perfil vistaPerfil = new Perfil();
            ControlPerfil controlPerfil = new ControlPerfil(vistaPerfil, clienteActivo);
            vistaPerfil.setVisible(true);
            this.vistaMenuUsuario.dispose();
        }
        
        if(vistaMenuUsuario.getBtnCerrarSesion() == evento.getSource()){
            VistaIndex vistaIndex = new VistaIndex();
            ControlIndex index = new ControlIndex(vistaIndex);
            vistaIndex.setVisible(true);
            this.vistaMenuUsuario.dispose();
        }
    }
}
