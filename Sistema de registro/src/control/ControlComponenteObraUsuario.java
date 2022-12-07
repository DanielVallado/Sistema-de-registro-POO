package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Obra;
import modelo.UsuarioCliente;
import vista.VistaComponenteObraUsuario;
import vista.VistaFuncionesUsuarios;
import vista.VistaObrasUsuarios;

public class ControlComponenteObraUsuario implements ActionListener{
    private VistaComponenteObraUsuario vistaComponenteObra;
    private VistaObrasUsuarios vistaObrasUsuarios;
    private UsuarioCliente usuarioActivo;
    private Obra obra;
    
    public ControlComponenteObraUsuario(VistaComponenteObraUsuario vistaComponenteObra, Obra obra, VistaObrasUsuarios vistaObrasUsuarios, UsuarioCliente usuarioActivo){
        this.vistaComponenteObra = vistaComponenteObra;
        this.usuarioActivo = usuarioActivo;
        this.obra = obra;
        this.vistaObrasUsuarios = vistaObrasUsuarios;
        this.vistaComponenteObra.getNombreObra().setText(this.obra.getNombreObra());
        this.vistaComponenteObra.getBtnVerFunciones().addActionListener(this);
        this.vistaComponenteObra.getNombreObra().setText(this.obra.getNombreObra());
        this.vistaComponenteObra.getDuracion().setText(this.obra.getDuracion());
        this.vistaComponenteObra.getGenero().setText(this.obra.getGenero());
        this.vistaComponenteObra.getResumen().setText(this.obra.getResumen());
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaComponenteObra.getBtnVerFunciones() == evento.getSource()){
            VistaFuncionesUsuarios vistaFuncionesUsuarios = new VistaFuncionesUsuarios();
            ControlVistaFuncionesUsuarios controlVistaFuncionesUsuarios = new ControlVistaFuncionesUsuarios(vistaFuncionesUsuarios, obra, usuarioActivo);
            vistaFuncionesUsuarios.setVisible(true);
            this.vistaObrasUsuarios.dispose();
        }
    }
}