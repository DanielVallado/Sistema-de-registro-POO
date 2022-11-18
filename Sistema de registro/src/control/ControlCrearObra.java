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
        String nombreObra, genero, resumenTematico, duracion, primerActor, segundoActor, precioBoleto;
        
        if (vistaCrearObra.getBtnCrearObra() == evento.getSource()){
            nombreObra = vistaCrearObra.getEntradaNombre().getText();
            genero = vistaCrearObra.getEntradaGenero().getText();
            resumenTematico = vistaCrearObra.getEntradaResumen().getText();
            duracion = vistaCrearObra.getEntradaDuracion().getText();
            primerActor = vistaCrearObra.getEntradaPrimerActor().getText();
            segundoActor = vistaCrearObra.getEntradaSegundoActor().getText();
            precioBoleto = vistaCrearObra.getEntradaPrecio().getText();
            
            verificarObra(nombreObra, genero, resumenTematico, duracion, primerActor, segundoActor, precioBoleto);
        }
        
        if(vistaCrearObra.getBtnVolverAtras() == evento.getSource()){
            VistaObra vistaObra = new VistaObra();
            ControlObras ControlObra = new ControlObras(vistaObra);
            vistaObra.setVisible(true);
            this.vistaCrearObra.dispose();
        }
    }
    
    private void verificarObra(String nombreObra, String genero, String resumenTematico, String duracion, String primerActor, String segundoActor, String precioBoleto){
        if(nombreObra.equals("") || genero.equals("") || resumenTematico.equals("") || duracion.equals("") || primerActor.equals("") || segundoActor.equals("") || precioBoleto.equals("")){
            this.vistaCrearObra.getTxtError().setText("Hay espacios vacíos");
            this.vistaCrearObra.getTxtError().setVisible(true);
        } else{
            double duracionF = Float.valueOf(duracion);
            double precioF = Float.valueOf(precioBoleto);
            if(duracionF <= 0 || precioF <= 0){
                this.vistaCrearObra.getTxtError().setText("Valores numéricos inválidos");
                this.vistaCrearObra.getTxtError().setVisible(true);
            } else{
                if(UsuarioAdministrador.verificarExistenciaObra(nombreObra)){
                    this.vistaCrearObra.getTxtError().setText("Obra ya existente");
                    this.vistaCrearObra.getTxtError().setVisible(true);
                } else{
                    duracionF = Math.round(duracionF * 100.0) / 100.0;
                    precioF = Math.round(precioF * 100.0) / 100.0;
                    if(UsuarioAdministrador.crearObra(nombreObra, genero, resumenTematico, duracionF, primerActor, segundoActor, precioF)){
                        JOptionPane.showMessageDialog(null, nombreObra + " ha sido creada exitosamente", "Registo exitoso", JOptionPane.PLAIN_MESSAGE);
                        VistaObra vistaObra = new VistaObra();
                        ControlObras ControlObra = new ControlObras(vistaObra);
                        vistaObra.setVisible(true);
                        this.vistaCrearObra.dispose();
                    }
                }  
            }
        }
    }
}
