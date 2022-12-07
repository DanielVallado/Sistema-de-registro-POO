package control;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Date;
import java.util.Calendar;
import modelo.Funcion;
import modelo.Obra;
import modelo.UsuarioAdministrador;

public class ControlModificarFuncion implements ActionListener{
    private VistaModificarFuncion VistaModificarFuncion;
    private Obra obra;
    private Funcion funcion;

    public ControlModificarFuncion(VistaModificarFuncion VistaModificarFuncion, Funcion funcion, Obra obra) {
        this.funcion = funcion;
        this.obra = obra;
        this.VistaModificarFuncion = VistaModificarFuncion;
        this.VistaModificarFuncion.getBtnVolverAtras().addActionListener(this);
        this.VistaModificarFuncion.getNombreFuncion().setText(this.funcion.getNombreObra());
        this.VistaModificarFuncion.getEntradaFecha().setDate(this.funcion.getFecha());
        if(this.funcion.getHorario().equals("18:00")){
            this.VistaModificarFuncion.getEntradaPrimerHora().setSelected(true);
        } else if(this.funcion.getHorario().equals("20:30")){
            this.VistaModificarFuncion.getEntradaSegundaHora().setSelected(true);
        }
        this.VistaModificarFuncion.getBtnModificarFuncion().addActionListener(this);
        this.VistaModificarFuncion.getTxtError().setVisible(false);
    }
   

    @Override
    public void actionPerformed(ActionEvent evento) {
        String hora = "00:00", fecha;
        if(VistaModificarFuncion.getBtnModificarFuncion()==evento.getSource()){
            fecha = ((JTextField)VistaModificarFuncion.getEntradaFecha().getDateEditor().getUiComponent()).getText();
            if(VistaModificarFuncion.getEntradaPrimerHora().isSelected()){
                hora = "18:00";
                verificacion(fecha, hora);
            } else if(VistaModificarFuncion.getEntradaSegundaHora().isSelected()){
                hora = "20:30";
                verificacion(fecha, hora);
            } else{
                this.VistaModificarFuncion.getTxtError().setText("Horario no seleccionado");
                this.VistaModificarFuncion.getTxtError().setVisible(true);         
            }     
        }
        
        if(VistaModificarFuncion.getBtnVolverAtras()==evento.getSource()){
            VistaFunciones vistaFunciones = new VistaFunciones();
            ControlVistaFunciones controlVistaFunciones = new ControlVistaFunciones(vistaFunciones, obra);
            vistaFunciones.setVisible(true);
            this.VistaModificarFuncion.dispose();
        } 
    }
    
    private void verificacion(String fecha, String hora){
       if(verificarFecha(fecha)){
                if(verificarHora(Date.valueOf(fecha), hora)){
                    UsuarioAdministrador.modificarFuncion(funcion.getNombreObra(), funcion.getFecha(), funcion.getHorario(), Date.valueOf(fecha), hora);
                    JOptionPane.showMessageDialog(null, "La función a las "+funcion.getHorario()+" el día "+funcion.getFecha()+" ha sido modificada exitosamente", "Modificación de función exitosa", JOptionPane.PLAIN_MESSAGE);
                    VistaFunciones vistaFunciones = new VistaFunciones();
                    ControlVistaFunciones controlVistaFunciones = new ControlVistaFunciones(vistaFunciones, obra);
                    vistaFunciones.setVisible(true);
                    this.VistaModificarFuncion.dispose();
                }
            } 
    }
    
    private boolean verificarFecha(String fecha){
        if(fecha == null){
            this.VistaModificarFuncion.getTxtError().setText("Fecha vacía");
            this.VistaModificarFuncion.getTxtError().setVisible(true);
           return false;
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
                    return true;          
                } else{
                    this.VistaModificarFuncion.getTxtError().setText("Ya existen dos funciones para ese día");
                    this.VistaModificarFuncion.getTxtError().setVisible(true);
                }
            } else{
                this.VistaModificarFuncion.getTxtError().setText("La fecha es pasada");
                this.VistaModificarFuncion.getTxtError().setVisible(true);
                return false;
            }        
        }
        return false;
    }
    
    private boolean verificarHora(Date fecha, String hora){
        char[] numHora = hora.toString().toCharArray();
        String[] horaMinutos = hora.split(":");
            
        if(numHora[0] == ' ' || numHora[1] == ' ' || numHora[2] == ' ' || numHora[3] == ' ' || Integer.parseInt(horaMinutos[0]) > 24 || Integer.parseInt(horaMinutos[1]) > 59){
            this.VistaModificarFuncion.getTxtError().setText("Hora inválida");
            this.VistaModificarFuncion.getTxtError().setVisible(true);
        } else{
            if(UsuarioAdministrador.verificarHoraFuncion(fecha, hora)){
                this.VistaModificarFuncion.getTxtError().setText("Hora ocupada");
                this.VistaModificarFuncion.getTxtError().setVisible(true);
            } else{
                return true;
            }
        }
        return false;
    }
}