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
import modelo.UsuarioCliente;
import vista.VistaComponenteFuncionUsuario;
import vista.VistaFuncionesUsuarios;
import vista.VistaObrasUsuarios;

public class ControlVistaFuncionesUsuarios implements ActionListener, KeyListener{
    private VistaFuncionesUsuarios vistaFuncionesUsuarios;
    private UsuarioCliente usuarioActivo;
    private Obra obra;
    private ArrayList<Funcion> listaFunciones;

    public ControlVistaFuncionesUsuarios(VistaFuncionesUsuarios vistaFuncionesUsuarios, Obra obra, UsuarioCliente usuarioActivo) {
        this.vistaFuncionesUsuarios = vistaFuncionesUsuarios;
        this.usuarioActivo = usuarioActivo;
        this.obra = obra;
        this.listaFunciones = UsuarioAdministrador.buscarFuncion(this.obra.getNombreObra());           
        this.vistaFuncionesUsuarios.getBtnVolverAtras().addActionListener(this);
        this.vistaFuncionesUsuarios.getEntradaBusqueda().addKeyListener(this);
        this.vistaFuncionesUsuarios.getNombreObra().setText(obra.getNombreObra());
        cargarFunciones(listaFunciones);
    }
     
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaFuncionesUsuarios.getBtnVolverAtras() == evento.getSource()){
            VistaObrasUsuarios vistaObrasUsuarios = new VistaObrasUsuarios();
            ControlVistaObrasUsuarios controlVistaObrasUsuarios = new ControlVistaObrasUsuarios(vistaObrasUsuarios, usuarioActivo);
            vistaObrasUsuarios.setVisible(true);
            this.vistaFuncionesUsuarios.dispose();
        }

    }    
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(java.awt.event.KeyEvent evento){
        if (vistaFuncionesUsuarios.getEntradaBusqueda() == evento.getSource()){
            String busqueda = vistaFuncionesUsuarios.getEntradaBusqueda().getText();
            if(!busqueda.equals("")){
                ArrayList<Funcion> listaFunciones = UsuarioAdministrador.buscarFuncion(obra.getNombreObra(), busqueda);
                cargarFunciones(listaFunciones);
            } else{
                cargarFunciones(this.listaFunciones);
            }
        }
    }
    
    private void cargarFunciones(ArrayList<Funcion> listaFunciones){
        this.vistaFuncionesUsuarios.getPanelFunciones().removeAll();
        this.vistaFuncionesUsuarios.getPanelFunciones().repaint();
        for(int i = 0; i < listaFunciones.size(); i++) {     
            VistaComponenteFuncionUsuario vistaComponenteFuncionUsuario = new VistaComponenteFuncionUsuario();
            ControlComponenteFuncionUsuario controlComponenteFuncionUsuario = new ControlComponenteFuncionUsuario(vistaComponenteFuncionUsuario, listaFunciones.get(i), vistaFuncionesUsuarios, obra, usuarioActivo);
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; 
            constraints.gridy = i; 
            constraints.gridwidth = 1;
            constraints.gridheight = 1; 
            this.vistaFuncionesUsuarios.getPanelFunciones().add(vistaComponenteFuncionUsuario, constraints);
            this.vistaFuncionesUsuarios.getPanelFunciones().updateUI();      
        }  
    }
}