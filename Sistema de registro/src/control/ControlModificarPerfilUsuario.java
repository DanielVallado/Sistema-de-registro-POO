package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Teatro;
import modelo.UsuarioCliente;
import vista.*;

public class ControlModificarPerfilUsuario implements ActionListener{
    private VistaModificarPerfil vistaModificarUsuario;
    private UsuarioCliente usuarioActivo;
    
    public ControlModificarPerfilUsuario(VistaModificarPerfil vistaModificarUsuario, UsuarioCliente usuarioActivo){
        this.vistaModificarUsuario = vistaModificarUsuario;
        this.usuarioActivo = usuarioActivo;
        this.vistaModificarUsuario.getBtnVolverAtras().addActionListener(this);
        this.vistaModificarUsuario.getBtnModificarDatos().addActionListener(this);
        //Pre-asignar valores
        this.vistaModificarUsuario.getEntradaUsuario().setText(usuarioActivo.getUsuario());
        this.vistaModificarUsuario.getEntradaNombre().setText(usuarioActivo.getNombre());
        this.vistaModificarUsuario.getEntradaApellido().setText(usuarioActivo.getApellido());
        this.vistaModificarUsuario.getEntradaCurp().setText(usuarioActivo.getCurp());
        this.vistaModificarUsuario.getEntradaContrasena().setText(usuarioActivo.getContrasena());
        this.vistaModificarUsuario.getEntradaConfirmacion().setText(usuarioActivo.getContrasena());
        this.vistaModificarUsuario.getTxtError().setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        String usuario, nombre, apellido, curp, contrasena, confirmacion;
        if(vistaModificarUsuario.getBtnModificarDatos() == evento.getSource()){
            usuario = vistaModificarUsuario.getEntradaUsuario().getText();
            nombre = vistaModificarUsuario.getEntradaNombre().getText();
            apellido = vistaModificarUsuario.getEntradaApellido().getText();
            curp = vistaModificarUsuario.getEntradaCurp().getText();
            contrasena = String.valueOf(vistaModificarUsuario.getEntradaContrasena().getPassword());
            confirmacion = String.valueOf(vistaModificarUsuario.getEntradaConfirmacion().getPassword());
            verificarModificacion(usuario, nombre, apellido, curp, contrasena, confirmacion);
        }
        
        if(vistaModificarUsuario.getBtnVolverAtras() == evento.getSource()){
            VistaPerfil vistaPerfil = new VistaPerfil();
            ControlPerfil controlPerfil = new ControlPerfil(vistaPerfil, usuarioActivo);
            vistaPerfil.setVisible(true);
            this.vistaModificarUsuario.dispose();
        }  
    }
    
    public void verificarModificacion(String usuario, String nombre, String apellido, String curp, String contrasena, String confirmacion){
        if(usuario.equals("") || nombre.equals("") || apellido.equals("") || curp.equals("") || contrasena.equals("")){
            this.vistaModificarUsuario.getTxtError().setText("Hay espacios vacíos");
            this.vistaModificarUsuario.getTxtError().setVisible(true);
        } else{    
            if(contrasena.equals(confirmacion)){
                realizarModificacion(usuario, nombre, apellido, curp, contrasena);
            } else{
                this.vistaModificarUsuario.getTxtError().setText("Las contraseñas no son iguales");
                this.vistaModificarUsuario.getTxtError().setVisible(true); 
            }
        }
    }
    
    private void realizarModificacion(String usuario, String nombre, String apellido, String curp, String contrasena){
        if(Teatro.modificarCliente(this.usuarioActivo.getUsuario(), usuario, nombre, apellido, curp, contrasena)){
            JOptionPane.showMessageDialog(null, this.usuarioActivo.getUsuario() + " ha sido modificado exitosamente", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
            VistaPerfil vistaPerfil = new VistaPerfil();
            ControlPerfil controlPerfil = new ControlPerfil(vistaPerfil, Teatro.obtenerCliente(usuario));
            vistaPerfil.setVisible(true);
            this.vistaModificarUsuario.dispose();
        } else{
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la modificación", "Modificación fallida", JOptionPane.ERROR_MESSAGE);
        }  
    }
}
