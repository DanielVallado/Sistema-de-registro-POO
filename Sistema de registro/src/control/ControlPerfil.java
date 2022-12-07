package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.UsuarioCliente;
import vista.*;

public class ControlPerfil implements ActionListener{
    private VistaPerfil vistaPerfil;
    private UsuarioCliente usuarioActivo;
    
    public ControlPerfil(VistaPerfil vistaPerfil, UsuarioCliente usuarioActivo){
        this.vistaPerfil = vistaPerfil;
        this.usuarioActivo = usuarioActivo;
        
        this.vistaPerfil.getBtnInicio().addActionListener(this);
        this.vistaPerfil.getBtnReservar().addActionListener(this);
        this.vistaPerfil.getBtnCerrarSesion().addActionListener(this);
        this.vistaPerfil.getBtnEditarPerfil().addActionListener(this);
        
        this.vistaPerfil.getTxtUsuario().setText(usuarioActivo.getUsuario());
        this.vistaPerfil.getTxtNombre().setText(usuarioActivo.getNombre());
        this.vistaPerfil.getTxtApellido().setText(usuarioActivo.getApellido());
        this.vistaPerfil.getTxtCurp().setText(usuarioActivo.getCurp());
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.vistaPerfil.getBtnInicio() == evento.getSource()){
            MenuUsuario vistaMenuUsuario = new MenuUsuario();
            ControlMenuUsuario controlMenuUsuario = new ControlMenuUsuario(vistaMenuUsuario, usuarioActivo);
            vistaMenuUsuario.setVisible(true);
            this.vistaPerfil.dispose();
        }
        
        if(this.vistaPerfil.getBtnReservar() == evento.getSource()){
            VistaObrasUsuarios vistaObrasUsuarios = new VistaObrasUsuarios();
            ControlVistaObrasUsuarios controlVistaObrasUsuarios = new ControlVistaObrasUsuarios(vistaObrasUsuarios, usuarioActivo);
            vistaObrasUsuarios.setVisible(true);
            this.vistaPerfil.dispose();
        }
        
        if(this.vistaPerfil.getBtnCerrarSesion() == evento.getSource()){
            VistaIndex vistaIndex = new VistaIndex();
            ControlIndex index = new ControlIndex(vistaIndex);
            vistaIndex.setVisible(true);
            this.vistaPerfil.dispose();
        }
        
        if(this.vistaPerfil.getBtnEditarPerfil()== evento.getSource()){
            VistaModificarPerfil vistaModificarPerfil = new VistaModificarPerfil();
            ControlModificarPerfilUsuario controlModificarPerfilUsuario = new ControlModificarPerfilUsuario(vistaModificarPerfil, usuarioActivo);
            vistaModificarPerfil.setVisible(true);
            this.vistaPerfil.dispose();
        }
    } 
}
