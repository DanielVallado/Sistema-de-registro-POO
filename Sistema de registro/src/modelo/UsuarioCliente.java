package modelo;

public class UsuarioCliente extends Usuario{
    private String curp;
    
    public UsuarioCliente(String usuario, String nombre, String apellido, String curp, String contrasena){
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.curp = curp;
        this.contrasena = contrasena;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
}
