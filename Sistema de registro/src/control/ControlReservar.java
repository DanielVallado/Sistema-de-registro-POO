package control;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import modelo.Asiento;
import modelo.Funcion;
import modelo.Obra;
import modelo.UsuarioAdministrador;
import modelo.UsuarioCliente;
import vista.*;

public class ControlReservar implements ActionListener{
    private int filas = 3, columnasCentro = 5, columnasLaterales = 2;
    private ArrayList<String> asientosReservadosA,asientosReservadosB, asientosReservadosC,asientosReservadosD;
    private int boletosA = 0, boletosB = 0, boletosC = 0, boletosD = 0, boletosTotales = 0;
    private double precioBoletosA, precioBoletosB, precioBoletosC, precioBoletosD, costoTotal;
    private ArrayList<Asiento> asientosA, asientosB, asientosC, asientosD;
    private VistaReservar vistaReservar;
    private UsuarioCliente usuarioActivo;
    private Obra obra;
    private Funcion funcion;
    
    public ControlReservar(VistaReservar vistaReservar, UsuarioCliente usuarioActivo, Obra obra, Funcion funcion){
        this.vistaReservar = vistaReservar;
        this.usuarioActivo = usuarioActivo; 
        this.obra = obra;
        this.funcion = funcion;
        this.vistaReservar.getBtnReservarBoletos().addActionListener(this);
        this.vistaReservar.getBtnVolverAtras().addActionListener(this);
        this.vistaReservar.getNombreObra().setText(this.obra.getNombreObra());
        this.vistaReservar.getPrecioTotal().setText("0.00");
        this.vistaReservar.getCantidadBoletos().setText("0");
        this.vistaReservar.getTextError().setVisible(false);
        this.asientosReservadosA = new ArrayList();
        this.asientosReservadosB = new ArrayList();
        this.asientosReservadosC = new ArrayList();
        this.asientosReservadosD = new ArrayList();
        //Precios
        this.precioBoletosA = obra.getPrecio()+(500*0.2);
        this.precioBoletosB = obra.getPrecio()+(500*0.1);
        this.precioBoletosC = obra.getPrecio();
        this.precioBoletosD = obra.getPrecio()+(500*0.1);
        this.vistaReservar.getPrecioBoletosA().setText(String.valueOf(precioBoletosA));
        this.vistaReservar.getPrecioBoletosB().setText(String.valueOf(precioBoletosB));
        this.vistaReservar.getPrecioBoletosC().setText(String.valueOf(precioBoletosC));
        this.vistaReservar.getPrecioBoletosD().setText(String.valueOf(precioBoletosD));
        //Crear asientos
        this.asientosA = UsuarioAdministrador.buscarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "A");
        this.asientosB = UsuarioAdministrador.buscarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "B");
        this.asientosC = UsuarioAdministrador.buscarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "C");
        this.asientosD = UsuarioAdministrador.buscarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "D");
        //Crear botones (asientos)
        colocarAsientos(asientosA, columnasCentro, filas, vistaReservar.getPanelAsientosA(), "A");
        colocarAsientos(asientosB, columnasLaterales, filas, vistaReservar.getPanelAsientosB(), "B");
        colocarAsientos(asientosC, columnasCentro, filas, vistaReservar.getPanelAsientosC(), "C");
        colocarAsientos(asientosD, columnasLaterales, filas, vistaReservar.getPanelAsientosD(), "D");
        verificarDisponibilidad();
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.vistaReservar.getBtnVolverAtras() == evento.getSource()){
            resetearValores();
            VistaFuncionesUsuarios vistaFuncionesUsuarios = new VistaFuncionesUsuarios();
            ControlVistaFuncionesUsuarios controlVistaFuncionesUsuarios = new ControlVistaFuncionesUsuarios(vistaFuncionesUsuarios, obra, usuarioActivo);
            vistaFuncionesUsuarios.setVisible(true);
            this.vistaReservar.dispose();;
        }
        
        if(this.vistaReservar.getBtnReservarBoletos()== evento.getSource()){
           if(boletosTotales == 0){
               this.vistaReservar.getTextError().setText("No hay asientos seleccionados");
               this.vistaReservar.getTextError().setVisible(true);
           } else{
               this.vistaReservar.getTextError().setVisible(false);
               VistaRealizarPago vistaRealizarPago = new VistaRealizarPago();
               ControlRealizarPago controlRealizarPago = new ControlRealizarPago(vistaRealizarPago, usuarioActivo, funcion, boletosTotales, costoTotal);
               vistaRealizarPago.setVisible(true);
               this.vistaReservar.dispose();
           }
        }
        
        //Botones seccion A
        for(int i = 0; i < asientosA.size(); i++){
            if(asientosA.get(i).getBotonAsiento() == evento.getSource()){
                if(asientosA.get(i).getBotonAsiento().isSelected()){
                    UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "A", i, true);
                    this.asientosReservadosA.add("A"+i);
                    this.boletosA += 1;
                    this.costoTotal += precioBoletosA;
                    this.vistaReservar.getPrecioTotal().setText(String.valueOf(costoTotal));
                    this.boletosTotales += 1;
                    this.vistaReservar.getCantidadBoletos().setText(String.valueOf(boletosTotales));
                } else{
                    UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "A", i, false);
                    this.asientosReservadosA.remove("A"+i);
                    this.boletosA -= 1;
                    this.costoTotal -= precioBoletosA;
                    this.vistaReservar.getPrecioTotal().setText(String.valueOf(costoTotal));
                    this.boletosTotales -= 1;
                    this.vistaReservar.getCantidadBoletos().setText(String.valueOf(boletosTotales));
                }
            }
        }
        
        //Botones seccion B
        for(int i = 0; i < asientosB.size(); i++){
            if(asientosB.get(i).getBotonAsiento() == evento.getSource()){
                if(asientosB.get(i).getBotonAsiento().isSelected()){
                    UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "B", i, true);
                    this.asientosReservadosB.add("B"+i);
                    this.boletosB += 1;
                    this.costoTotal += precioBoletosB;
                    this.vistaReservar.getPrecioTotal().setText(String.valueOf(costoTotal));
                    this.boletosTotales += 1;
                    this.vistaReservar.getCantidadBoletos().setText(String.valueOf(boletosTotales));
                } else{
                    UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "B", i, false);
                    this.asientosReservadosB.remove("B"+i);
                    this.boletosB -= 1;
                    this.costoTotal -= precioBoletosB;
                    this.vistaReservar.getPrecioTotal().setText(String.valueOf(costoTotal));
                    this.boletosTotales -= 1;
                    this.vistaReservar.getCantidadBoletos().setText(String.valueOf(boletosTotales));
                }
            }
        }
        
        //Botones seccion C
        for(int i = 0; i < asientosC.size(); i++){
            if(asientosC.get(i).getBotonAsiento() == evento.getSource()){
                if(asientosC.get(i).getBotonAsiento().isSelected()){
                    UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "C", i, true);
                    this.asientosReservadosC.add("C"+i);
                    this.boletosC += 1;
                    this.costoTotal += precioBoletosC;
                    this.vistaReservar.getPrecioTotal().setText(String.valueOf(costoTotal));
                    this.boletosTotales += 1;
                    this.vistaReservar.getCantidadBoletos().setText(String.valueOf(boletosTotales));
                } else{
                    UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "C", i, false);
                    this.asientosReservadosC.remove("C"+i);
                    this.boletosC -= 1;
                    this.costoTotal -= precioBoletosC;
                    this.vistaReservar.getPrecioTotal().setText(String.valueOf(costoTotal));
                    this.boletosTotales -= 1;
                    this.vistaReservar.getCantidadBoletos().setText(String.valueOf(boletosTotales));
                } 
            }
        }
        
        //Botones seccion D
        for(int i = 0; i < asientosD.size(); i++){
            if(asientosD.get(i).getBotonAsiento() == evento.getSource()){   
                if(asientosD.get(i).getBotonAsiento().isSelected()){
                    UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "D", i, true);
                    this.asientosReservadosD.add("D"+i);
                    this.boletosD += 1;
                    this.costoTotal += precioBoletosD;
                    this.vistaReservar.getPrecioTotal().setText(String.valueOf(costoTotal));
                    this.boletosTotales += 1;
                    this.vistaReservar.getCantidadBoletos().setText(String.valueOf(boletosTotales));
                } else{
                    UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "D", i, false);
                    this.asientosReservadosD.remove("D"+i);
                    this.boletosD -= 1;
                    this.costoTotal -= precioBoletosD;
                    this.vistaReservar.getPrecioTotal().setText(String.valueOf(costoTotal));
                    this.boletosTotales -= 1;
                    this.vistaReservar.getCantidadBoletos().setText(String.valueOf(boletosTotales));
                } 
            }
        }
    }  
    
    private void colocarAsientos(ArrayList<Asiento> listaAsientos, int columnas, int filas, JPanel panelAsientos, String seccion){
        int numeroAsiento = 0;
        int largoBtn = 60, anchotBtn = 30;
        int ejeX = 10, ejeY = 10;
        Font fuenteLetra = new Font("Verdana", Font.PLAIN, 12);
        
        for(int i = 0; i < filas; i++) {
            for(int j = 0; j < columnas; j++) {
                listaAsientos.get(numeroAsiento).setBotonAsiento(new JToggleButton());
                listaAsientos.get(numeroAsiento).getBotonAsiento().setBounds(ejeX, ejeY, largoBtn, anchotBtn);
                listaAsientos.get(numeroAsiento).getBotonAsiento().setText(seccion + numeroAsiento);
                listaAsientos.get(numeroAsiento).getBotonAsiento().setFont(fuenteLetra);
                listaAsientos.get(numeroAsiento).getBotonAsiento().setBackground(Color.green);
                listaAsientos.get(numeroAsiento).getBotonAsiento().setFocusPainted(false);
                listaAsientos.get(numeroAsiento).getBotonAsiento().setBorderPainted(false);
                listaAsientos.get(numeroAsiento).getBotonAsiento().addActionListener(this);
                panelAsientos.add(listaAsientos.get(numeroAsiento).getBotonAsiento());
                numeroAsiento++;
                ejeX += 70;
            }
            ejeX = 10;
            ejeY += 40;
        }
    }
    
    public void verificarDisponibilidad(){
        for (int i = 0; i < asientosA.size(); i++) {
            if(asientosA.get(i).getReservado() == true){
                asientosA.get(i).getBotonAsiento().setEnabled(false);
                asientosA.get(i).getBotonAsiento().setBackground(Color.red);
            }
        }
        
        for (int i = 0; i < asientosB.size(); i++) {
            if(asientosB.get(i).getReservado() == true){
                asientosD.get(i).getBotonAsiento().setEnabled(false);
                asientosB.get(i).getBotonAsiento().setBackground(Color.red);
            }
        }
        
        for (int i = 0; i < asientosC.size(); i++) {
            if(asientosC.get(i).getReservado() == true){
                asientosC.get(i).getBotonAsiento().setEnabled(false);
                asientosC.get(i).getBotonAsiento().setBackground(Color.red);
            }
        }
        for (int i = 0; i < asientosD.size(); i++) {
            if(asientosD.get(i).getReservado() == true){
                asientosD.get(i).getBotonAsiento().setEnabled(false);
                asientosD.get(i).getBotonAsiento().setBackground(Color.red);
            }
        }
    }
    
    private void resetearValores() {
        for (int i = 0; i < asientosReservadosA.size(); i++) {
            UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "A", Integer.valueOf(asientosReservadosA.get(i).substring(1)), false);
        }
        
        for (int i = 0; i < asientosReservadosB.size(); i++) {
            UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "B", Integer.valueOf(asientosReservadosB.get(i).substring(1)), false);
        }
        
        for (int i = 0; i < asientosReservadosC.size(); i++) {
            UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "C", Integer.valueOf(asientosReservadosC.get(i).substring(1)), false);
        }
        for (int i = 0; i < asientosReservadosD.size(); i++) {
            UsuarioAdministrador.modificarAsiento(obra.getNombreObra(), funcion.getFecha(), funcion.getHorario(), "D", Integer.valueOf(asientosReservadosD.get(i).substring(1)), false);
        }
    }
}
