package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Obra;
import modelo.UsuarioAdministrador;
import vista.*;

public class ControlModificarObra implements ActionListener{
    private VistaModificarObra vistaModificarObra;
    private Obra obra;
    
    public ControlModificarObra(VistaModificarObra vistaModificarObra, Obra obra){
        this.vistaModificarObra = vistaModificarObra;
        this.obra = obra;
        this.vistaModificarObra.getBtnVolverAtras().addActionListener(this);
        this.vistaModificarObra.getBtnModificarObra().addActionListener(this);
        //Pre-asignar valores
        this.vistaModificarObra.getEntradaNombre().setText(obra.getNombreObra());
        this.vistaModificarObra.getEntradaGenero().setText(obra.getGenero());
        this.vistaModificarObra.getEntradaResumen().setText(obra.getResumen());
        String[] duracion = this.obra.getDuracion().split(":");
        this.vistaModificarObra.getEntradaHoras().setSelectedItem(duracion[0]);
        this.vistaModificarObra.getEntradaMinutos().setSelectedItem(duracion[1]);
        this.vistaModificarObra.getEntradaPrimerActor().setText(obra.getPrimerActor());
        this.vistaModificarObra.getEntradaSegundoActor().setText(obra.getSegundoActor());
        this.vistaModificarObra.getEntradaPrecio().setText(String.valueOf(obra.getPrecio()));
        this.vistaModificarObra.getTxtError().setVisible(false);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento){
        String nombreObra, genero, resumenTematico, horas, minutos, duracion, primerActor, segundoActor, precioBoleto;
        
        if(vistaModificarObra.getBtnModificarObra()== evento.getSource()){
            nombreObra = vistaModificarObra.getEntradaNombre().getText();
            genero = vistaModificarObra.getEntradaGenero().getText();
            resumenTematico = vistaModificarObra.getEntradaResumen().getText();
            horas = (String) vistaModificarObra.getEntradaHoras().getSelectedItem();
            minutos = (String) vistaModificarObra.getEntradaMinutos().getSelectedItem();
            primerActor = vistaModificarObra.getEntradaPrimerActor().getText();
            segundoActor = vistaModificarObra.getEntradaSegundoActor().getText();
            precioBoleto = vistaModificarObra.getEntradaPrecio().getText();
            duracion = horas + ":" + minutos;
            
            verificarObra(nombreObra, genero, resumenTematico, duracion, primerActor, segundoActor, precioBoleto);
        }
        
        if(vistaModificarObra.getBtnVolverAtras() == evento.getSource()){
            VistaObras vistaObras = new VistaObras();
            ControlVistaObras ControlVistaObras = new ControlVistaObras(vistaObras);
            vistaObras.setVisible(true);
            this.vistaModificarObra.dispose();
        }  
    }
    
    private void verificarObra(String nombreObra, String genero, String resumenTematico, String duracion, String primerActor, String segundoActor, String precioBoleto){
        if(nombreObra.equals("") || genero.equals("") || resumenTematico.equals("") || duracion.equals("") || primerActor.equals("") || segundoActor.equals("") || precioBoleto.equals("")){
            this.vistaModificarObra.getTxtError().setText("Hay espacios vacíos");
            this.vistaModificarObra.getTxtError().setVisible(true);
        } else{
            char[] numDuracion = duracion.toString().toCharArray();
            String[] horaMinutos = duracion.split(":");
            if(numDuracion[0] == ' ' || numDuracion[1] == ' ' || numDuracion[2] == ' ' || numDuracion[3] == ' ' || Integer.parseInt(horaMinutos[0]) > 24 || Integer.parseInt(horaMinutos[1]) > 59){
                this.vistaModificarObra.getTxtError().setText("Valores numéricos inválidos");
                this.vistaModificarObra.getTxtError().setVisible(true);
            } else{
                double precioF = Float.valueOf(precioBoleto);
                if(UsuarioAdministrador.modificarObra(this.obra.getNombreObra(), nombreObra, genero, resumenTematico, duracion, primerActor, segundoActor, precioF)){
                    JOptionPane.showMessageDialog(null, nombreObra + " ha sido modificada exitosamente", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
                    VistaObras vistaObras = new VistaObras();
                    ControlVistaObras ControlVistaObras = new ControlVistaObras(vistaObras);
                    vistaObras.setVisible(true);
                    this.vistaModificarObra.dispose();
                }
            }
        }
    }
}
