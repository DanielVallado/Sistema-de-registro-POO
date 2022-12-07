package modelo;
import java.util.Date;
import javax.swing.JToggleButton;

public class Asiento {
    private String nombreObra, hora, seccion;
    int numero;
    private Date dia;
    private Boolean reservado;
    private JToggleButton botonAsiento;

    public Asiento(String nombreObra, Date dia, String hora, String seccion, int numero, Boolean reservado) {
        this.nombreObra = nombreObra;
        this.dia = dia;
        this.hora = hora;
        this.seccion = seccion;
        this.numero = numero;
        this.reservado = reservado;
        this.botonAsiento = new JToggleButton();
    }

    public JToggleButton getBotonAsiento() {
        return botonAsiento;
    }

    public void setBotonAsiento(JToggleButton botonAsiento) {
        this.botonAsiento = botonAsiento;
    }
    
    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Boolean getReservado() {
        return reservado;
    }

    public void setReservado(Boolean reservado) {
        this.reservado = reservado;
    }
}
