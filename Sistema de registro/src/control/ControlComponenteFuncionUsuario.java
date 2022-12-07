package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Funcion;
import modelo.Obra;
import modelo.UsuarioCliente;
import vista.VistaComponenteFuncionUsuario;
import vista.VistaFuncionesUsuarios;
import vista.VistaReservar;

public class ControlComponenteFuncionUsuario implements ActionListener{
    private VistaComponenteFuncionUsuario vistaComponenteFuncionUsuario;
    private VistaFuncionesUsuarios vistaFuncionesUsuarios;
    private UsuarioCliente usuarioActivo;
    private Obra obra;
    private Funcion funcion;
    
    public ControlComponenteFuncionUsuario(VistaComponenteFuncionUsuario vistaComponenteFuncionUsuario, Funcion funcion, VistaFuncionesUsuarios vistaFuncionesUsuarios, Obra obra, UsuarioCliente usuarioActivo){
        this.vistaComponenteFuncionUsuario = vistaComponenteFuncionUsuario;
        this.funcion = funcion;
        this.usuarioActivo = usuarioActivo;
        this.obra = obra;
        this.vistaFuncionesUsuarios = vistaFuncionesUsuarios;
        this.vistaComponenteFuncionUsuario.getFecha().setText(String.valueOf(this.funcion.getFecha()));
        this.vistaComponenteFuncionUsuario.getHora().setText(this.funcion.getHorario());
        this.vistaComponenteFuncionUsuario.getBtnReservar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaComponenteFuncionUsuario.getBtnReservar()== evento.getSource()){
            VistaReservar VistaReservar = new VistaReservar();
            ControlReservar controlReservar = new ControlReservar(VistaReservar, usuarioActivo, obra, funcion);
            VistaReservar.setVisible(true);
            this.vistaFuncionesUsuarios.dispose();
        }
    }
}
