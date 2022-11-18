package modelo;

import MySQL.MySQL_ObrasDAO;
import MySQL.MySQL_UsuariosDAO;

public class UsuarioAdministrador extends Usuario{
    public UsuarioAdministrador(String usuario, String nombre, String apellido, String contrasena){
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
    }
    
    public static boolean verificarExistenciaObra(String nombreObra){
        return MySQL_ObrasDAO.verificarExistenciaObra(nombreObra);
    }
    
    public static boolean crearObra(String nombreObra, String genero, String resumenTematico, double duracion, String primerActor, String segundoActor, double precioBoleto){
        return MySQL_ObrasDAO.insertar(nombreObra, genero, resumenTematico, duracion, primerActor, segundoActor, precioBoleto);
    }
    
    public static boolean eliminarObra(String nombreObra){
        return MySQL_ObrasDAO.eliminar(nombreObra);
    }
}
