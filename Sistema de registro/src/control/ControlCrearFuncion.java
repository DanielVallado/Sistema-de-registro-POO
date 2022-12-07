package control;
import vista.*;
import vista.VistaCrearFuncion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Date;
import java.util.Calendar;
import modelo.Obra;
import modelo.UsuarioAdministrador;

public class ControlCrearFuncion implements ActionListener{
    private VistaCrearFuncion vistaCrearFuncion;
    private Obra obra;

    public ControlCrearFuncion(VistaCrearFuncion vistaCrearFuncion, Obra obra) {
        this.obra = obra;
        this.vistaCrearFuncion = vistaCrearFuncion;
        this.vistaCrearFuncion.getBtnCrearFuncion().addActionListener(this);
        this.vistaCrearFuncion.getBtnVolverAtras().addActionListener(this);
        this.vistaCrearFuncion.getNombreFuncion().setText(this.obra.getNombreObra());
        this.vistaCrearFuncion.getTxtError().setVisible(false);
    }
   

    @Override
    public void actionPerformed(ActionEvent evento) {
        String hora, fecha;
        if(vistaCrearFuncion.getBtnCrearFuncion()==evento.getSource()){
            fecha = ((JTextField)vistaCrearFuncion.getEntradaFecha().getDateEditor().getUiComponent()).getText();
            if(vistaCrearFuncion.getEntradaPrimerHora().isSelected()){
                hora = "18:00";
                verificacion(fecha, hora);
            } else if(vistaCrearFuncion.getEntradaSegundaHora().isSelected()){
                hora = "20:30";
                verificacion(fecha, hora);
            } else{
                this.vistaCrearFuncion.getTxtError().setText("Horario no seleccionado");
                this.vistaCrearFuncion.getTxtError().setVisible(true);         
            } 
        }
        
        if(vistaCrearFuncion.getBtnVolverAtras()==evento.getSource()){
            VistaFunciones vistaFunciones = new VistaFunciones();
            ControlVistaFunciones controlVistaFunciones = new ControlVistaFunciones(vistaFunciones, obra);
            vistaFunciones.setVisible(true);
            this.vistaCrearFuncion.dispose();
        } 
    }
    
    private void verificacion(String fecha, String hora){
        switch(verificarFecha(fecha)){
            case 1: 
                if(verificarHora(Date.valueOf(fecha), hora)){
                    crearFuncion(fecha, hora);
                }
                break;
            case 2: 
                if(verificarChoqueHora(Date.valueOf(fecha), hora)){
                    crearFuncion(fecha, hora);
                }
                break;
            default: break;
        }
    }
    
    private void crearFuncion(String fecha, String hora){
        UsuarioAdministrador.crearFuncion(obra.getNombreObra(), Date.valueOf(fecha), hora);
        JOptionPane.showMessageDialog(null, "La función a las " + hora + " el día " + fecha + " ha sido creada exitosamente", "Registo de función exitoso", JOptionPane.PLAIN_MESSAGE);
        VistaFunciones vistaFunciones = new VistaFunciones();
        ControlVistaFunciones controlVistaFunciones = new ControlVistaFunciones(vistaFunciones, obra);
        vistaFunciones.setVisible(true);
        this.vistaCrearFuncion.dispose();
    }
    
    private int verificarFecha(String fecha){
        if(fecha == null){
            this.vistaCrearFuncion.getTxtError().setText("Fecha vacía");
            this.vistaCrearFuncion.getTxtError().setVisible(true);
            return 3;
        } else{
            int dia, mes, annio, diaN, mesN, annioN;
            String[] fechaN = fecha.split("-");
            Calendar c = Calendar.getInstance();
            dia = Integer.valueOf(c.get(Calendar.DATE));
            mes = Integer.valueOf(c.get(Calendar.MONTH));
            annio = Integer.valueOf(c.get(Calendar.YEAR));
            diaN = Integer.valueOf(fechaN[2]);
            mesN = Integer.valueOf(fechaN[1]);
            annioN = Integer.valueOf(fechaN[0]);
            
            if(dia <= diaN && mes <= mesN && annio <= annioN){
                if(UsuarioAdministrador.verificarFechaFuncion(Date.valueOf(fecha)) < 2){
                    if(UsuarioAdministrador.verificarFechaFuncion(Date.valueOf(fecha)) == 1){
                        return 2;
                    } else{
                        return 1;
                    }     
                } else{
                    this.vistaCrearFuncion.getTxtError().setText("Ya existen dos funciones para ese día");
                    this.vistaCrearFuncion.getTxtError().setVisible(true);
                }
            } else{
                this.vistaCrearFuncion.getTxtError().setText("La fecha es pasada");
                this.vistaCrearFuncion.getTxtError().setVisible(true);
                return 3;
            }        
        }
        return 3;
    }
    
    private boolean verificarChoqueHora(Date fecha, String hora){
        if(verificarHora(fecha, hora)){
            if(vistaCrearFuncion.getEntradaPrimerHora().isSelected()){
                String[] horaMinutos = hora.split(":");
                String[] duracionObra = obra.getDuracion().split(":");
                int horaInt = Integer.valueOf(horaMinutos[0]) + Integer.valueOf(duracionObra[0]);
                int minutosInt = Integer.valueOf(horaMinutos[1]) + Integer.valueOf(duracionObra[1]);
                if(horaInt > 20 || (horaInt == 20 && minutosInt >= 30)){
                    this.vistaCrearFuncion.getTxtError().setText("Choca con otra función");
                    this.vistaCrearFuncion.getTxtError().setVisible(true);
                    return false;
                } else {
                    return true;
                }
            } else{
                return true;
            }
        }
        return false;
    }
    
    private boolean verificarHora(Date fecha, String hora){
        char[] numHora = hora.toString().toCharArray();
        String[] horaMinutos = hora.split(":");
        if(numHora[0] == ' ' || numHora[1] == ' ' || numHora[2] == ' ' || numHora[3] == ' ' || Integer.parseInt(horaMinutos[0]) > 24 || Integer.parseInt(horaMinutos[1]) > 59){
            this.vistaCrearFuncion.getTxtError().setText("Hora inválida");
            this.vistaCrearFuncion.getTxtError().setVisible(true);
        } else{
            if(UsuarioAdministrador.verificarHoraFuncion(fecha, hora)){
                this.vistaCrearFuncion.getTxtError().setText("Hora ocupada");
                this.vistaCrearFuncion.getTxtError().setVisible(true);
            } else{
                return true;
            }
        }
        return false;
    }
}
