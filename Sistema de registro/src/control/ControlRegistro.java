package control;
import MySQL.MySQL_UsuariosDAO;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Teatro;

public class ControlRegistro implements ActionListener{
    private RegistroUsuarios vistaRegistro;
    
    public ControlRegistro(RegistroUsuarios vistaRegistro){
        this.vistaRegistro = vistaRegistro;
        this.vistaRegistro.getBtnRegistro().addActionListener(this);
        this.vistaRegistro.getBtnAtras().addActionListener(this);
        this.vistaRegistro.getTextError().setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        String usuario, nombre, apellido, curp, contrasena, verificacionContrasena; 
        
        if(vistaRegistro.getBtnRegistro() == evento.getSource()){
             this.vistaRegistro.getTextError().setVisible(false);
            usuario = this.vistaRegistro.getEntradaUsuario().getText();
            nombre = this.vistaRegistro.getEntradaNombre().getText();
            apellido = this.vistaRegistro.getEntradaApellido().getText();
            curp = this.vistaRegistro.getEntradaCurp().getText();
            contrasena = String.valueOf(this.vistaRegistro.getEntradaContrasena().getPassword());
            verificacionContrasena = String.valueOf(this.vistaRegistro.getEntradaConfirmacionContrasena().getPassword());
            verificarRegistro(usuario, nombre, apellido, curp, contrasena, verificacionContrasena);
        }
        
        if(vistaRegistro.getBtnAtras() == evento.getSource()){
            VistaIndex vistaIndex = new VistaIndex();
            ControlIndex index = new ControlIndex(vistaIndex);
            vistaIndex.setVisible(true);
            this.vistaRegistro.dispose();
        }
    }
    
    private void verificarRegistro(String usuario, String nombre, String apellido, String curp, String contrasena, String verificacionContrasena){
        if(usuario.equals("") || nombre.equals("") || apellido.equals("") || curp.equals("") || contrasena.equals("")){
            this.vistaRegistro.getTextError().setText("Hay espacios vacíos");
            this.vistaRegistro.getTextError().setVisible(true);
        } else{         
            if(Teatro.verificarExistenciaAdministrador(usuario) == true){            
                this.vistaRegistro.getTextError().setText("Ya existe ese nombre de usuario");
                this.vistaRegistro.getTextError().setVisible(true);
            } else{
                if(Teatro.verificarExistenciaUsuario(usuario) == true){    
                    this.vistaRegistro.getTextError().setText("Ya existe ese nombre de usuario");
                    this.vistaRegistro.getTextError().setVisible(true);      
                } else{
                    if(contrasena.equals(verificacionContrasena)){
                        realizarRegistro(usuario, nombre, apellido, curp, contrasena);
                    } else{
                        this.vistaRegistro.getTextError().setText("Las contraseñas no son iguales");
                        this.vistaRegistro.getTextError().setVisible(true); 
                    }
                }
                
            }  
        }
    }
    
    private void realizarRegistro(String usuario, String nombre, String apellido, String curp, String contrasena){
        if(Teatro.insertarUsuario(usuario, nombre, apellido, curp, contrasena)){
            JOptionPane.showMessageDialog(null, usuario + " ha sido registrado exitosamente", "Registo exitoso", JOptionPane.PLAIN_MESSAGE);
            VistaIndex vistaIndex = new VistaIndex();
            ControlIndex index = new ControlIndex(vistaIndex);
            vistaIndex.setVisible(true);
            this.vistaRegistro.dispose();
        } else{
            System.out.println("Error al insertar");
        }  
    }
}
