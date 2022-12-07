package control;
import java.awt.GridBagConstraints;
import vista.VistaComponenteUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Teatro;
import modelo.UsuarioCliente;
import vista.VistaModificarPerfil;
import vista.VistaUsuarios;

public class ControlComponenteUsuario implements ActionListener{
    private VistaComponenteUsuario vistaComponenteUsuario;
    private VistaUsuarios vistaUsuarios;
    private UsuarioCliente usuario;
    
    public ControlComponenteUsuario(VistaComponenteUsuario vistaComponenteUsuario, UsuarioCliente usuario, VistaUsuarios vistaUsuarios){
        this.vistaComponenteUsuario = vistaComponenteUsuario;
        this.vistaUsuarios = vistaUsuarios;
        this.usuario = usuario;
        this.vistaComponenteUsuario.getNombreUsuario().setText(this.usuario.getUsuario());
        this.vistaComponenteUsuario.getBtnModificarUsuario().addActionListener(this);
        this.vistaComponenteUsuario.getBtnEliminarUsuario().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        String[] botonesString = {"Sí", "No"};
        Object[] botonesObject = {"1", "2"};
        int verificador;
        
        if(vistaComponenteUsuario.getBtnModificarUsuario()== evento.getSource()){
            UsuarioCliente usuarioM = Teatro.obtenerCliente(usuario.getUsuario());
            VistaModificarPerfil vistaModificarUsuario = new VistaModificarPerfil();
            ControlModificarPerfil controlModificarUsuario = new ControlModificarPerfil(vistaModificarUsuario, usuarioM);
            vistaModificarUsuario.setVisible(true);
            this.vistaUsuarios.dispose();
        }
        
        if(vistaComponenteUsuario.getBtnEliminarUsuario() == evento.getSource()){
            verificador = JOptionPane.showOptionDialog (null, "¿Seguro que desea eliminar este usuario?", "Eliminar Usuario", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botonesString, botonesObject[0]);
            if(verificador == 0){
                Teatro.eliminarUsuario(usuario.getUsuario());
                JOptionPane.showMessageDialog(null, usuario.getUsuario() + " ha sido eliminado exitosamente", "Eliminación exitosa", JOptionPane.PLAIN_MESSAGE);
                this.vistaUsuarios.getPanelUsuarios().removeAll();
                this.vistaUsuarios.getPanelUsuarios().repaint();
                ArrayList<UsuarioCliente> listaUsuarios = Teatro.obtenerTodosClientes();
                for(int i = 0; i < listaUsuarios.size(); i++) {     
                    VistaComponenteUsuario panelUsuario = new VistaComponenteUsuario();
                    ControlComponenteUsuario ControlComponenteUsuario = new ControlComponenteUsuario(panelUsuario, listaUsuarios.get(i), this.vistaUsuarios);
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.gridx = 0; 
                    constraints.gridy = i; 
                    constraints.gridwidth = 1;
                    constraints.gridheight = 1; 
                    this.vistaUsuarios.getPanelUsuarios().add(panelUsuario, constraints);
                    this.vistaUsuarios.getPanelUsuarios().updateUI();   
                }
            } 
        }
    }    
}
