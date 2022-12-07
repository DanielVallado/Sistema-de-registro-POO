package modelo;
import java.util.Date;

public class Funcion {
    private String nombreObra, horario;
    private Date fecha;

    public Funcion(String nombreObra, String horario, Date fecha) {
        this.nombreObra = nombreObra;
        this.horario = horario;
        this.fecha = fecha;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }  
}
