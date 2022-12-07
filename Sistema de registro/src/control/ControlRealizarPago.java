package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import modelo.Funcion;
import modelo.Teatro;
import modelo.UsuarioCliente;
import modelo.Venta;
import vista.VistaRealizarPago;
import vista.VistaTicket;

public class ControlRealizarPago implements ActionListener{
    private VistaRealizarPago vistaRealizarPago;
    private UsuarioCliente usuarioActivo;
    private Funcion funcion;
    private int boletosTotales;
    double costoTotal;

    public ControlRealizarPago(VistaRealizarPago vistaRealizarPago, UsuarioCliente usuarioActivo, Funcion funcion, int boletosTotales, double costoTotal) {
        this.vistaRealizarPago = vistaRealizarPago;
        this.usuarioActivo = usuarioActivo;
        this.funcion = funcion;
        this.boletosTotales = boletosTotales;
        this.costoTotal = costoTotal;
        this.vistaRealizarPago.getBtnPagar().addActionListener(this);
        this.vistaRealizarPago.getBtnCancelar().addActionListener(this);
        this.vistaRealizarPago.getPrecio().setText(String.valueOf(this.costoTotal));
        this.vistaRealizarPago.getTextError().setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
       if(vistaRealizarPago.getBtnPagar() == evento.getSource()){
           String pago = vistaRealizarPago.getEntradaPago().getText();
           if(!pago.equals("")){
               vistaRealizarPago.getTextError().setVisible(false);
               try{
                   double pagoD = Double.valueOf(pago);
                   if(pagoD >= costoTotal){
                        double cambio = pagoD - costoTotal;
                        JOptionPane.showMessageDialog(null, "Pago realizado de manera exitosa", "Pago exitoso", JOptionPane.PLAIN_MESSAGE);
                        Calendar fecha = Calendar.getInstance();
                        int mes = fecha.get(Calendar.MONTH) + 1;
                        int dia = fecha.get(Calendar.DAY_OF_MONTH);
                        Teatro.insertarVenta(usuarioActivo.getUsuario(), dia, mes, funcion.getNombreObra(), funcion.getFecha(), funcion.getHorario(), boletosTotales, costoTotal);
                        Venta venta = Teatro.buscarVenta(usuarioActivo.getUsuario(), dia, mes, funcion.getNombreObra(), funcion.getFecha(), funcion.getHorario());
                        VistaTicket VistaTicket = new VistaTicket();
                        ControlVistaTicket ControlVistaTicket = new ControlVistaTicket(VistaTicket, pagoD, cambio, venta, usuarioActivo);
                        VistaTicket.setVisible(true);
                        this.vistaRealizarPago.dispose();
                   } else{
                        vistaRealizarPago.getTextError().setText("Cantidad insuficiente");
                        vistaRealizarPago.getTextError().setVisible(true);
                   }
               } catch(NumberFormatException ex){
                    vistaRealizarPago.getTextError().setText("Caracter inválido");
                    vistaRealizarPago.getTextError().setVisible(true);
               }
           } else{
               vistaRealizarPago.getTextError().setText("Entrada vacía");
               vistaRealizarPago.getTextError().setVisible(true);
           }
       }
       
       if(vistaRealizarPago.getBtnCancelar() == evento.getSource()){
           
       }
    }  
}
