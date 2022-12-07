package control;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import modelo.Funcion;
import modelo.Obra;
import modelo.UsuarioAdministrador;
import vista.VistaComponenteFuncion;
import vista.VistaCrearFuncion;
import vista.VistaFunciones;
import vista.VistaObras;

public class ControlVistaFunciones implements ActionListener, KeyListener{
    private VistaFunciones vistaFunciones;
    private Obra obra;
    private ArrayList<Funcion> listaFunciones;

    public ControlVistaFunciones(VistaFunciones vistaFunciones, Obra obra) {
        this.vistaFunciones = vistaFunciones;
        this.obra = obra;
        this.listaFunciones = UsuarioAdministrador.buscarFuncion(this.obra.getNombreObra());       
        this.vistaFunciones.getBtnObras().addActionListener(this);
        this.vistaFunciones.getBtnUsuarios().addActionListener(this);
        this.vistaFunciones.getBtnVolverAtras().addActionListener(this);
        this.vistaFunciones.getBtnCrearFunción().addActionListener(this);
        this.vistaFunciones.getEntradaBusqueda().addKeyListener(this);
        cargarFunciones(listaFunciones);
    }
     
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaFunciones.getBtnUsuarios() == evento.getSource()){
            //Quitar
        }
        
        if(vistaFunciones.getBtnFunciones() == evento.getSource()){
            //Quitar
        }
        
        if(vistaFunciones.getBtnVolverAtras() == evento.getSource()){
            VistaObras vistaObras = new VistaObras();
            ControlVistaObras controlVistaObras = new ControlVistaObras(vistaObras);
            vistaObras.setVisible(true);
            this.vistaFunciones.dispose();
        }
        
        if(vistaFunciones.getBtnCrearFunción()== evento.getSource()){
            VistaCrearFuncion VistaCrearFuncion = new VistaCrearFuncion();
            ControlCrearFuncion controlCrearFuncion = new ControlCrearFuncion(VistaCrearFuncion, obra);
            VistaCrearFuncion.setVisible(true);
            this.vistaFunciones.dispose();
        }
    }    
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(java.awt.event.KeyEvent evento){
        if (vistaFunciones.getEntradaBusqueda() == evento.getSource()){
            String busqueda = vistaFunciones.getEntradaBusqueda().getText();
            if(!busqueda.equals("")){
                ArrayList<Funcion> listaFunciones = UsuarioAdministrador.buscarFuncion(obra.getNombreObra(), busqueda);
                cargarFunciones(listaFunciones);
            } else{
                cargarFunciones(this.listaFunciones);
            }
        }
    }
    
    private void cargarFunciones(ArrayList<Funcion> listaFunciones){
        this.vistaFunciones.getPanelFunciones().removeAll();
        this.vistaFunciones.getPanelFunciones().repaint();
        for(int i = 0; i < listaFunciones.size(); i++) {     
            VistaComponenteFuncion panelFuncion = new VistaComponenteFuncion();
            ControlComponenteFuncion ControlComponenteFuncion = new ControlComponenteFuncion(panelFuncion, listaFunciones.get(i), this.vistaFunciones, this.obra);
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; 
            constraints.gridy = i; 
            constraints.gridwidth = 1;
            constraints.gridheight = 1; 
            this.vistaFunciones.getPanelFunciones().add(panelFuncion, constraints);
            this.vistaFunciones.getPanelFunciones().updateUI();      
        }  
    }
}