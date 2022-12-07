package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Teatro;
import modelo.UsuarioCliente;
import modelo.Venta;
import vista.VistaObrasUsuarios;
import vista.VistaTicket;

public class ControlVistaTicket implements ActionListener{
    private VistaTicket VistaTicket;
    private UsuarioCliente usuarioActivo;
    double pago, cambio;
    private Venta venta;

    public ControlVistaTicket(VistaTicket VistaTicket, double pago, double cambio, Venta venta, UsuarioCliente usuarioActivo) {
        this.VistaTicket = VistaTicket;
        this.pago = pago;
        this.cambio = cambio;
        this.venta = venta;
        this.usuarioActivo = usuarioActivo;
        
        this.VistaTicket.getNombreTeatro().setText("Teatro Armando Manzanero");
        this.VistaTicket.getUsuario().setText(venta.getUsuario());
        this.VistaTicket.getNumeroVenta().setText(String.valueOf(venta.getNoVenta()));
        this.VistaTicket.getNombreObra().setText(venta.getNombraObra());
        this.VistaTicket.getFechaFuncion().setText(String.valueOf(venta.getDiaFuncion()));
        this.VistaTicket.getHoraFuncion().setText(venta.getHoraFuncion());
        this.VistaTicket.getFechaCompra().setText(String.valueOf(venta.getDia())+"/"+String.valueOf(venta.getMes()));
        this.VistaTicket.getCantidadBoletos().setText(String.valueOf(venta.getBoletos()));
        this.VistaTicket.getPrecioTotal().setText(String.valueOf(venta.getPrecioTotal()));
        this.VistaTicket.getPago().setText(String.valueOf(pago));
        this.VistaTicket.getCambio().setText(String.valueOf(cambio));
        this.VistaTicket.getBtnFinalizar().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(VistaTicket.getBtnFinalizar() == evento.getSource()){
            VistaObrasUsuarios vistaObrasUsuarios = new VistaObrasUsuarios();
            ControlVistaObrasUsuarios controlVistaObrasUsuarios = new ControlVistaObrasUsuarios(vistaObrasUsuarios, usuarioActivo);
            vistaObrasUsuarios.setVisible(true);
            this.VistaTicket.dispose();
        }
    } 
}
