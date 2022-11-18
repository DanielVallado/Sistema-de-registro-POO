package control;
import java.awt.event.ActionEvent;
import vista.*;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Teatro;

public class ControlEliminarUsuario implements ActionListener{
    private VistaEliminarUsuario vistaEliminarUsuario;
    
    public ControlEliminarUsuario(VistaEliminarUsuario vistaEliminarUsuario){
        this.vistaEliminarUsuario = vistaEliminarUsuario;
        
        this.vistaEliminarUsuario.getBtnEliminar().addActionListener(this);
        this.vistaEliminarUsuario.getBtnVolverAtras().addActionListener(this);
        this.vistaEliminarUsuario.getTxtError().setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaEliminarUsuario.getBtnEliminar() == evento.getSource()){
            String usuario = vistaEliminarUsuario.getEntradaUsuario().getText();
            if(usuario.equals("")){
                this.vistaEliminarUsuario.getTxtError().setVisible(true);
                this.vistaEliminarUsuario.getTxtError().setText("Entrada vacía");
            } else{
                verificarUsuario(vistaEliminarUsuario.getEntradaUsuario().getText());
            }
        }
        
        if(vistaEliminarUsuario.getBtnVolverAtras() == evento.getSource()){
            VistaUsuario vistaUsuario = new VistaUsuario();
            ControlUsuario controlVistaUsuario = new ControlUsuario(vistaUsuario);
            vistaUsuario.setVisible(true);
            this.vistaEliminarUsuario.dispose();
        }    
    }
    
    private void verificarUsuario(String usuario){
        if(Teatro.verificarExistenciaAdministrador(usuario) || Teatro.verificarExistenciaUsuario(usuario)){
            Teatro.eliminarUsuario(usuario);
            JOptionPane.showMessageDialog(null, usuario + " ha sido eliminado exitosamente", "Eliminación exiitosa", JOptionPane.PLAIN_MESSAGE);
            VistaUsuario vistaUsuario = new VistaUsuario();
            ControlUsuario controlVistaUsuario = new ControlUsuario(vistaUsuario);
            vistaUsuario.setVisible(true);
            this.vistaEliminarUsuario.dispose();
        } else{
            this.vistaEliminarUsuario.getTxtError().setVisible(true);
            this.vistaEliminarUsuario.getTxtError().setText("Nombre de usuario inexistente");
        }
    }
}
