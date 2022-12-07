package control;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioAdministrador;

public class ControlCrearObra implements ActionListener {
    private VistaCrearObra vistaCrearObra;
    
    public ControlCrearObra (VistaCrearObra vistaCrearObra){
        this.vistaCrearObra = vistaCrearObra;
        this.vistaCrearObra.getBtnCrearObra().addActionListener(this);
        this.vistaCrearObra.getBtnVolverAtras().addActionListener(this);
        this.vistaCrearObra.getTxtError().setVisible(false);
    } 

    @Override
    public void actionPerformed(ActionEvent evento) {
        String nombreObra, genero, resumenTematico, horas, minutos, duracion, primerActor, segundoActor, precioBoleto;
        
        if (vistaCrearObra.getBtnCrearObra() == evento.getSource()){
            nombreObra = vistaCrearObra.getEntradaNombre().getText();
            genero = vistaCrearObra.getEntradaGenero().getText();
            resumenTematico = vistaCrearObra.getEntradaResumen().getText();
            horas = (String) vistaCrearObra.getEntradaHoras().getSelectedItem();
            minutos = (String) vistaCrearObra.getEntradaMinutos().getSelectedItem();
            primerActor = vistaCrearObra.getEntradaPrimerActor().getText();
            segundoActor = vistaCrearObra.getEntradaSegundoActor().getText();
            precioBoleto = vistaCrearObra.getEntradaPrecio().getText();
            duracion = horas + ":" + minutos;
            
            verificarObra(nombreObra, genero, resumenTematico, duracion, primerActor, segundoActor, precioBoleto);
        }
        
        if(vistaCrearObra.getBtnVolverAtras() == evento.getSource()){
            VistaObras vistaObras = new VistaObras();
            ControlVistaObras controlVistaObras = new ControlVistaObras(vistaObras);
            vistaObras.setVisible(true);
            this.vistaCrearObra.dispose();
        }
    }
    
    private void verificarObra(String nombreObra, String genero, String resumenTematico, String duracion, String primerActor, String segundoActor, String precioBoleto){
        if(nombreObra.equals("") || genero.equals("") || resumenTematico.equals("") || duracion.equals("") || primerActor.equals("") || segundoActor.equals("") || precioBoleto.equals("")){
            this.vistaCrearObra.getTxtError().setText("Hay espacios vacíos");
            this.vistaCrearObra.getTxtError().setVisible(true);
        } else{
            char[] numDuracion = duracion.toString().toCharArray();
            String[] horaMinutos = duracion.split(":");
            if(numDuracion[0] == ' ' || numDuracion[1] == ' ' || numDuracion[2] == ' ' || numDuracion[3] == ' ' || Integer.parseInt(horaMinutos[0]) > 24 || Integer.parseInt(horaMinutos[1]) > 59){
                this.vistaCrearObra.getTxtError().setText("Duración inválida");
                this.vistaCrearObra.getTxtError().setVisible(true);
            } else{
                try{
                    double precioF = Float.valueOf(precioBoleto);
                    if(UsuarioAdministrador.verificarExistenciaObra(nombreObra)){
                        this.vistaCrearObra.getTxtError().setText("Obra ya existente");
                        this.vistaCrearObra.getTxtError().setVisible(true);
                    } else{
                        if(UsuarioAdministrador.crearObra(nombreObra, genero, resumenTematico, duracion, primerActor, segundoActor, precioF)){
                            JOptionPane.showMessageDialog(null, nombreObra + " ha sido creada exitosamente", "Registo exitoso", JOptionPane.PLAIN_MESSAGE);
                            VistaObras vistaObras = new VistaObras();
                            ControlVistaObras controlVistaObras = new ControlVistaObras(vistaObras);
                            vistaObras.setVisible(true);
                            this.vistaCrearObra.dispose();
                        }
                    }
                } catch(NumberFormatException e){
                    this.vistaCrearObra.getTxtError().setText("Precio inválido");
                    this.vistaCrearObra.getTxtError().setVisible(true);
                }
            }
        }
    }
}
