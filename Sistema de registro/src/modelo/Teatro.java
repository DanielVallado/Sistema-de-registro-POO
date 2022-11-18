package modelo;
import MySQL.MySQL_UsuariosDAO;

public class Teatro {
    private String nombreTeatro, direccion, telefono;
    
        
    public Teatro(String nombreTeatro, String direccion, String telefono){
        this.nombreTeatro = nombreTeatro;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public static UsuarioCliente obtenerCliente(String usuario){
        return MySQL_UsuariosDAO.obtenerCliente(usuario);
    }
    
    public static boolean insertarUsuario(String usuario, String nombre, String apellido, String curp, String contrasena){     
        return MySQL_UsuariosDAO.insertar(usuario, nombre, apellido, curp, contrasena);
    }
    
    public static boolean eliminarUsuario(String usuario){
        return MySQL_UsuariosDAO.eliminar(usuario);
    }
    
    public static boolean verificarExistenciaAdministrador(String usuario){
        return MySQL_UsuariosDAO.verificarExistenciaAdmin(usuario);
    }
    
    public static boolean verificarExistenciaUsuario(String usuario){
        return MySQL_UsuariosDAO.verificarExistenciaUsuario(usuario);
    }
    
    public static boolean verificarAdministrador(String usuario, String contrasena){
        return MySQL_UsuariosDAO.verificarAdministrador(usuario, contrasena);
    }
    
    public static boolean verificarUsuario(String usuario, String contrasena){
        return MySQL_UsuariosDAO.verificarUsuario(usuario, contrasena);
    }
}