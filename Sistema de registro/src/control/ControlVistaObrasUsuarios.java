package control;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import modelo.Obra;
import modelo.UsuarioAdministrador;
import modelo.UsuarioCliente;
import vista.*;

public class ControlVistaObrasUsuarios implements ActionListener, KeyListener{
    private VistaObrasUsuarios vistaObrasUsuarios;
    private UsuarioCliente usuarioActivo;
    private ArrayList<Obra> listaObras;

    public ControlVistaObrasUsuarios(VistaObrasUsuarios vistaObrasUsuarios, UsuarioCliente usuarioActivo) {
        this.vistaObrasUsuarios = vistaObrasUsuarios;
        this.usuarioActivo = usuarioActivo;
        this.listaObras = UsuarioAdministrador.obtenerTodasObras(); 
        this.vistaObrasUsuarios.getBtnInicio().addActionListener(this);
        this.vistaObrasUsuarios.getBtnPerfil().addActionListener(this);
        this.vistaObrasUsuarios.getBtnCerrarSesion().addActionListener(this);
        this.vistaObrasUsuarios.getEntradaBusqueda().addKeyListener(this);
        cargarObras(listaObras);
    }
     
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaObrasUsuarios.getBtnInicio() == evento.getSource()){
            MenuUsuario vistaMenuUsuario = new MenuUsuario();
            ControlMenuUsuario controlMenuUsuario = new ControlMenuUsuario(vistaMenuUsuario, usuarioActivo);
            vistaMenuUsuario.setVisible(true);
            this.vistaObrasUsuarios.dispose();
        }
        
        if(vistaObrasUsuarios.getBtnPerfil() == evento.getSource()){
            VistaPerfil vistaPerfil = new VistaPerfil();
            ControlPerfil controlPerfil = new ControlPerfil(vistaPerfil, usuarioActivo);
            vistaPerfil.setVisible(true);
            this.vistaObrasUsuarios.dispose();
        }
        
        if(vistaObrasUsuarios.getBtnCerrarSesion()== evento.getSource()){
            VistaIndex vistaIndex = new VistaIndex();
            ControlIndex index = new ControlIndex(vistaIndex);
            vistaIndex.setVisible(true);
            this.vistaObrasUsuarios.dispose();
        }
    }    
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(java.awt.event.KeyEvent evento) {
        if (vistaObrasUsuarios.getEntradaBusqueda() == evento.getSource()){
            String busqueda = vistaObrasUsuarios.getEntradaBusqueda().getText();
            if(!busqueda.equals("")){
                ArrayList<Obra> listaObras = UsuarioAdministrador.buscarObra(busqueda);
                cargarObras(listaObras);
            } else{
                cargarObras(this.listaObras);
            }
        }
    }
    
    private void cargarObras(ArrayList<Obra> listaObras){
        this.vistaObrasUsuarios.getPanelObras().removeAll();
        this.vistaObrasUsuarios.getPanelObras().repaint();
        for(int i = 0; i < listaObras.size(); i++) {     
            VistaComponenteObraUsuario panelObra = new VistaComponenteObraUsuario();
            ControlComponenteObraUsuario controlComponenteObraUsuario = new ControlComponenteObraUsuario(panelObra, listaObras.get(i), vistaObrasUsuarios, usuarioActivo);
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; 
            constraints.gridy = i; 
            constraints.gridwidth = 1;
            constraints.gridheight = 1; 
            this.vistaObrasUsuarios.getPanelObras().add(panelObra, constraints);
            this.vistaObrasUsuarios.getPanelObras().updateUI();      
        }  
    }    
}