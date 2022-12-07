package control;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import modelo.Obra;
import modelo.UsuarioAdministrador;
import vista.*;

public class ControlVistaObras implements ActionListener, KeyListener{
    private VistaObras vistaObras;
    private ArrayList<Obra> listaObras;

    public ControlVistaObras(VistaObras vistaObras) {
        this.vistaObras = vistaObras;
        this.listaObras = UsuarioAdministrador.obtenerTodasObras();       
        this.vistaObras.getBtnObras().addActionListener(this);
        this.vistaObras.getBtnUsuarios().addActionListener(this);
        this.vistaObras.getBtnReporte().addActionListener(this);
        this.vistaObras.getBtnVolverAtras().addActionListener(this);
        this.vistaObras.getBtnCrearObra().addActionListener(this);
        this.vistaObras.getEntradaBusqueda().addKeyListener(this);
        cargarObras(listaObras);
    }
     
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaObras.getBtnUsuarios() == evento.getSource()){
            VistaUsuarios vistaUsuarios = new VistaUsuarios();
            ControlVistaUsuarios controlVistaUsuarios = new ControlVistaUsuarios(vistaUsuarios);
            vistaUsuarios.setVisible(true);
            this.vistaObras.dispose();
        }
        
        if(vistaObras.getBtnReporte()== evento.getSource()){
            //Quitar
        }
        
        if(vistaObras.getBtnVolverAtras() == evento.getSource()){
            MenuAdmin vistaMenuAdmin = new MenuAdmin();
            ControlMenuAdmin menuUsuario = new ControlMenuAdmin(vistaMenuAdmin);
            vistaMenuAdmin.setVisible(true);
            this.vistaObras.dispose();
        }
        
        if(vistaObras.getBtnCrearObra()== evento.getSource()){
            VistaCrearObra vistaCrearObra = new VistaCrearObra();
            ControlCrearObra controlCrearObra = new ControlCrearObra(vistaCrearObra);
            vistaCrearObra.setVisible(true);
            this.vistaObras.dispose();
        }
    }    
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(java.awt.event.KeyEvent evento) {
        if (vistaObras.getEntradaBusqueda() == evento.getSource()){
            String busqueda = vistaObras.getEntradaBusqueda().getText();
            if(!busqueda.equals("")){
                ArrayList<Obra> listaObras = UsuarioAdministrador.buscarObra(busqueda);
                cargarObras(listaObras);
            } else{
                cargarObras(this.listaObras);
            }
        }
    }
    
    private void cargarObras(ArrayList<Obra> listaObras){
        this.vistaObras.getPanelObras().removeAll();
        this.vistaObras.getPanelObras().repaint();
        for(int i = 0; i < listaObras.size(); i++) {     
            VistaComponenteObra panelObra = new VistaComponenteObra();
            ControlComponenteObra controlComponenteObra = new ControlComponenteObra(panelObra, listaObras.get(i), this.vistaObras);
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; 
            constraints.gridy = i; 
            constraints.gridwidth = 1;
            constraints.gridheight = 1; 
            this.vistaObras.getPanelObras().add(panelObra, constraints);
            this.vistaObras.getPanelObras().updateUI();      
        }  
    }    
}
