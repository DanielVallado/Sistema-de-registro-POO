package modelo;

public class Obra {
    private String nombreObra, genero, resumen, primerActor, segundoActor, duracion;
    private double precio;
    
    public Obra(String nombreObra, String genero, String resumen, String duracion, String primerActor, String segundoActor, double precio){
        this.nombreObra = nombreObra;
        this.genero = genero;
        this.resumen = resumen;
        this.primerActor = primerActor;
        this.segundoActor = segundoActor;
        this.duracion = duracion;
        this.precio = precio;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getPrimerActor() {
        return primerActor;
    }

    public void setPrimerActor(String primerActor) {
        this.primerActor = primerActor;
    }

    public String getSegundoActor() {
        return segundoActor;
    }

    public void setSegundoActor(String segundoActor) {
        this.segundoActor = segundoActor;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    } 
}
