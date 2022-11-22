package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Teatro;
import modelo.UsuarioCliente;
import vista.*;

public class ControlModificarUsuario implements ActionListener{
    private VistaModificarUsuario vistaModificarUsuario;
    private UsuarioCliente usuario;
    
    public ControlModificarUsuario(VistaModificarUsuario vistaModificarUsuario, UsuarioCliente usuario){
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
            usuario = vistaModificarUsuario.getEntradaNombre().getText();
            nombre = vistaModificarUsuario.getEntradaNombre().getText();
            apellido = vistaModificarUsuario.getEntradaApellido().getText();
            curp = vistaModificarUsuario.getEntradaCurp().getText();
            contrasena = String.valueOf(vistaModificarUsuario.getEntradaContrasena().getPassword());
            confirmacion = String.valueOf(vistaModificarUsuario.getEntradaConfirmacion().getPassword());
            verificarModificacion(usuario, nombre, apellido, curp, contrasena, confirmacion);
        }
        
        if(vistaModificarUsuario.getBtnVolverAtras() == evento.getSource()){
            VistaPreModificarUsuario vistaPreModificarUsuario = new VistaPreModificarUsuario();
            ControlPreModificarUsuario controlPreModificarUsuario = new ControlPreModificarUsuario(vistaPreModificarUsuario);
            vistaPreModificarUsuario.setVisible(true);
            this.vistaModificarUsuario.dispose();
        }  
    }
    
    public void verificarModificacion(String usuario, String nombre, String apellido, String curp, String contrasena, String confirmacion){
        if(usuario.equals("") || nombre.equals("") || apellido.equals("") || curp.equals("") || contrasena.equals("")){
            this.vistaModificarUsuario.getTxtError().setText("Hay espacios vacíos");
            this.vistaModificarUsuario.getTxtError().setVisible(true);
        } else{         
            if(Teatro.verificarExistenciaUsuario(usuario) == true){        
                if(contrasena.equals(confirmacion)){
                        realizarRegistro(usuario, nombre, apellido, curp, contrasena);
                } else{
                    this.vistaModificarUsuario.getTxtError().setText("Las contraseñas no son iguales");
                    this.vistaModificarUsuario.getTxtError().setVisible(true); 
                }
            } else{
                this.vistaModificarUsuario.getTxtError().setText("El nombre de usuario es inexistente");
                this.vistaModificarUsuario.getTxtError().setVisible(true);                
            }  
        }
    }
    
    private void realizarRegistro(String usuario, String nombre, String apellido, String curp, String contrasena){
        if(Teatro.insertarUsuario(usuario, nombre, apellido, curp, contrasena)){
            JOptionPane.showMessageDialog(null, usuario + " ha sido modificado exitosamente", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
            VistaPreModificarUsuario vistaPreModificarUsuario = new VistaPreModificarUsuario();
            ControlPreModificarUsuario controlPreModificarUsuario = new ControlPreModificarUsuario(vistaPreModificarUsuario);
            vistaPreModificarUsuario.setVisible(true);
            this.vistaModificarUsuario.dispose();
        } else{
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la modificación", "Modificación fallido", JOptionPane.ERROR_MESSAGE);
        }  
    }
}
