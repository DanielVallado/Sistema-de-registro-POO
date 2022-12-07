package control;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import modelo.Teatro;
import modelo.UsuarioCliente;
import vista.MenuAdmin;
import vista.VistaComponenteUsuario;
import vista.VistaObras;
import vista.VistaUsuarios;

public class ControlVistaUsuarios implements ActionListener, KeyListener{
    private VistaUsuarios vistaUsuarios;
    private ArrayList<UsuarioCliente> ListaUsuarios;

    public ControlVistaUsuarios(VistaUsuarios vistaUsuarios) {
        this.vistaUsuarios = vistaUsuarios;
        this.ListaUsuarios = Teatro.obtenerTodosClientes();       
        this.vistaUsuarios.getBtnObras().addActionListener(this);
        this.vistaUsuarios.getBtnReporte().addActionListener(this);
        this.vistaUsuarios.getBtnVolverAtras().addActionListener(this);
        this.vistaUsuarios.getEntradaBusqueda().addKeyListener(this);
        cargarUsuarios(this.ListaUsuarios);
    }
     
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (vistaUsuarios.getBtnObras() == evento.getSource()){
            VistaObras vistaObras = new VistaObras();
            ControlVistaObras controlVistaObras = new ControlVistaObras(vistaObras);
            vistaObras.setVisible(true);
            this.vistaUsuarios.dispose();
        }
        
        if(vistaUsuarios.getBtnReporte() == evento.getSource()){
           
        }
        
        if(vistaUsuarios.getBtnVolverAtras() == evento.getSource()){
            MenuAdmin vistaMenuAdmin = new MenuAdmin();
            ControlMenuAdmin menuUsuario = new ControlMenuAdmin(vistaMenuAdmin);
            vistaMenuAdmin.setVisible(true);
            this.vistaUsuarios.dispose();
        }
    }    
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(java.awt.event.KeyEvent evento){
        if (vistaUsuarios.getEntradaBusqueda() == evento.getSource()){
            String busqueda = vistaUsuarios.getEntradaBusqueda().getText();
            if(!busqueda.equals("")){
                ArrayList<UsuarioCliente> listaUsuarios = Teatro.buscarClientes(busqueda);
                cargarUsuarios(listaUsuarios);
            } else{
                this.ListaUsuarios = Teatro.obtenerTodosClientes();
                cargarUsuarios(ListaUsuarios);
            }
        }
    }
    
    private void cargarUsuarios(ArrayList<UsuarioCliente> listaUsuarios){
        this.vistaUsuarios.getPanelUsuarios().removeAll();
        this.vistaUsuarios.getPanelUsuarios().repaint();
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
