package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Teatro;
import modelo.UsuarioCliente;
import vista.*;

public class ControlModificarPerfil implements ActionListener{
    private VistaModificarPerfil vistaModificarUsuario;
    private UsuarioCliente usuario;
    
    public ControlModificarPerfil(VistaModificarPerfil vistaModificarUsuario, UsuarioCliente usuario){
        this.vistaModificarUsuario = vistaModificarUsuario;
        this.usuario = usuario;
        this.vistaModificarUsuario.getBtnVolverAtras().addActionListener(this);
        this.vistaModificarUsuario.getBtnModificarDatos().addActionListener(this);
        //Pre-asignar valores
        this.vistaModificarUsuario.getEntradaUsuario().setText(usuario.getUsuario());
        this.vistaModificarUsuario.getEntradaNombre().setText(usuario.getNombre());
        this.vistaModificarUsuario.getEntradaApellido().setText(usuario.getApellido());
        this.vistaModificarUsuario.getEntradaCurp().setText(usuario.getCurp());
        this.vistaModificarUsuario.getEntradaContrasena().setText(usuario.getContrasena());
        this.vistaModificarUsuario.getEntradaConfirmacion().setText(usuario.getContrasena());
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
            VistaUsuarios vistaUsuarios = new VistaUsuarios();
            ControlVistaUsuarios controlVistaUsuario = new ControlVistaUsuarios(vistaUsuarios);
            vistaUsuarios.setVisible(true);
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
        if(Teatro.modificarCliente(this.usuario.getUsuario(), usuario, nombre, apellido, curp, contrasena)){
            JOptionPane.showMessageDialog(null, this.usuario.getUsuario() + " ha sido modificado exitosamente", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
            VistaUsuarios vistaUsuarios = new VistaUsuarios();
            ControlVistaUsuarios controlVistaUsuario = new ControlVistaUsuarios(vistaUsuarios);
            vistaUsuarios.setVisible(true);
            this.vistaModificarUsuario.dispose();
        } else{
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la modificación", "Modificación fallida", JOptionPane.ERROR_MESSAGE);
        }  
    }
}
