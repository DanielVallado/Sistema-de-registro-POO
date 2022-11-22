package control;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Teatro;
import modelo.UsuarioCliente;

public class ControlPreModificarUsuario implements ActionListener{
    private VistaPreModificarUsuario vistaPreModificarUsuario;
    
    
    public ControlPreModificarUsuario(VistaPreModificarUsuario vistaPreModificarUsuario){
        this.vistaPreModificarUsuario = vistaPreModificarUsuario;
        this.vistaPreModificarUsuario.getBtnVolverAtras().addActionListener(this);
        this.vistaPreModificarUsuario.getBtnModificarUsuario().addActionListener(this);
        this.vistaPreModificarUsuario.getTxtError().setVisible(false);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento){
        String usuario;
        if(vistaPreModificarUsuario.getBtnModificarUsuario() == evento.getSource()){
            usuario = vistaPreModificarUsuario.getEntradaUsuario().getText();
            if(usuario.equals("")){
                this.vistaPreModificarUsuario.getTxtError().setVisible(true);
                this.vistaPreModificarUsuario.getTxtError().setText("Campo vacío");
            } else{
                if(Teatro.verificarExistenciaUsuario(usuario)){
                    UsuarioCliente usuarioM = Teatro.obtenerCliente(usuario);
                    VistaModificarUsuario vistaModificarUsuario = new VistaModificarUsuario();
                    ControlModificarUsuario controlModificarUsuario = new ControlModificarUsuario(vistaModificarUsuario, usuarioM);
                    vistaModificarUsuario.setVisible(true);
                    this.vistaPreModificarUsuario.dispose();
                } else{
                    this.vistaPreModificarUsuario.getTxtError().setVisible(true);
                    this.vistaPreModificarUsuario.getTxtError().setText("Usuario inexistente");
                }
            }
        }
        
        if(vistaPreModificarUsuario.getBtnVolverAtras() == evento.getSource()){
            VistaUsuario vistaUsuario = new VistaUsuario();
            ControlUsuario controlVistaUsuario = new ControlUsuario(vistaUsuario);
            vistaUsuario.setVisible(true);
            this.vistaPreModificarUsuario.dispose();
        }  
    }
}
