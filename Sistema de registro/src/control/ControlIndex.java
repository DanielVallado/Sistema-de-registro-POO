package control;
import MySQL.MySQL_UsuariosDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;
import modelo.*;

public class ControlIndex implements ActionListener{
    private VistaIndex vistaIndex;
    
    public ControlIndex(VistaIndex vistaIndex){
        this.vistaIndex = vistaIndex;
        this.vistaIndex.getEntradaUsuario().addActionListener(this);
        this.vistaIndex.getEntradaContrasena().addActionListener(this);
        this.vistaIndex.getBotonLogin().addActionListener(this);
        this.vistaIndex.getBotonRegistro().addActionListener(this);
        this.vistaIndex.getTxtError().setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        String usuario = "", contrasena = "";
        
        if(vistaIndex.getBotonLogin() == evento.getSource()){
            usuario = this.vistaIndex.getEntradaUsuario().getText();
            contrasena = String.valueOf(this.vistaIndex.getEntradaContrasena().getPassword());
            
            if(Teatro.verificarAdministrador(usuario, contrasena)){
                MenuAdmin vistaMenuAdmin = new MenuAdmin();
                ControlMenuAdmin menuUsuario = new ControlMenuAdmin(vistaMenuAdmin);
                vistaMenuAdmin.setVisible(true);
                this.vistaIndex.dispose();
            } else{
                
                if(Teatro.verificarUsuario(usuario, contrasena)){
                    this.vistaIndex.getTxtError().setVisible(false);
                    MenuUsuario vistaMenuUsuario = new MenuUsuario();
                    UsuarioCliente clienteActivo = Teatro.obtenerCliente(usuario);
                    ControlMenuUsuario controlMenuUsuario = new ControlMenuUsuario(vistaMenuUsuario, clienteActivo);
                    vistaMenuUsuario.setVisible(true);
                    this.vistaIndex.dispose();
                } else{
                    this.vistaIndex.getTxtError().setText("Usuario o contrasena incorrectos");
                    this.vistaIndex.getTxtError().setVisible(true);
                }
            }
        }
        
        if(vistaIndex.getBotonRegistro() == evento.getSource()){
            RegistroUsuarios vistaRegistro = new RegistroUsuarios();
            ControlRegistro registro = new ControlRegistro(vistaRegistro);
            vistaRegistro.setVisible(true);
            this.vistaIndex.dispose();
        }
    }
}