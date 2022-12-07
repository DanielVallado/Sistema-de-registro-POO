package modelo;
import java.util.Date;

public class Venta {
    int noVenta;
    int dia, mes;
    String usuario, nombraObra;
    Date diaFuncion;
    String horaFuncion;
    int boletos;
    double precioTotal;

    public Venta(int noVenta, int dia, int mes, String usuario, String nombraObra, Date diaFuncion, String horaFuncion, int boletos, double precioTotal) {
        this.noVenta = noVenta;
        this.usuario = usuario;
        this.nombraObra = nombraObra;
        this.diaFuncion = diaFuncion;
        this.horaFuncion = horaFuncion;
        this.boletos = boletos;
        this.precioTotal = precioTotal;
    }

    public int getNoVenta() {
        return noVenta;
    }

    public void setNoVenta(int noVenta) {
        this.noVenta = noVenta;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombraObra() {
        return nombraObra;
    }

    public void setNombraObra(String nombraObra) {
        this.nombraObra = nombraObra;
    }

    public Date getDiaFuncion() {
        return diaFuncion;
    }

    public void setDiaFuncion(Date diaFuncion) {
        this.diaFuncion = diaFuncion;
    }

    public String getHoraFuncion() {
        return horaFuncion;
    }

    public void setHoraFuncion(String horaFuncion) {
        this.horaFuncion = horaFuncion;
    }
    
    public int getBoletos() {
        return boletos;
    }

    public void setBoletos(int boletos) {
        this.boletos = boletos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
}
