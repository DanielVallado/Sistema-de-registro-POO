package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.UsuarioCliente;
import vista.*;

public class ControlPerfil implements ActionListener{
    private Perfil vistaPerfil;
    private UsuarioCliente clienteActivo;
    
    public ControlPerfil(Perfil vistaPerfil, UsuarioCliente clienteActivo){
        this.vistaPerfil = vistaPerfil;
        this.clienteActivo = clienteActivo;
        
        this.vistaPerfil.getBtnInicio().addActionListener(this);
        this.vistaPerfil.getBtnReservar().addActionListener(this);
        this.vistaPerfil.getBtnCerrarSesion().addActionListener(this);
        
        this.vistaPerfil.getTxtUsuario().setText(clienteActivo.getUsuario());
        this.vistaPerfil.getTxtNombre().setText(clienteActivo.getNombre());
        this.vistaPerfil.getTxtApellido().setText(clienteActivo.getApellido());
        this.vistaPerfil.getTxtCurp().setText(clienteActivo.getCurp());
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.vistaPerfil.getBtnInicio() == evento.getSource()){
            MenuUsuario vistaMenuUsuario = new MenuUsuario();
            ControlMenuUsuario controlMenuUsuario = new ControlMenuUsuario(vistaMenuUsuario, clienteActivo);
            vistaMenuUsuario.setVisible(true);
            this.vistaPerfil.dispose();
        }
        
        if(this.vistaPerfil.getBtnReservar() == evento.getSource()){
            Reservar vistaReservar = new Reservar();
            ControlReservar controlReservar = new ControlReservar(vistaReservar, clienteActivo);
            vistaReservar.setVisible(true);
            this.vistaPerfil.dispose();
        }
        
        if(this.vistaPerfil.getBtnCerrarSesion() == evento.getSource()){
            VistaIndex vistaIndex = new VistaIndex();
            ControlIndex index = new ControlIndex(vistaIndex);
            vistaIndex.setVisible(true);
            this.vistaPerfil.dispose();
        }
    } 
}
