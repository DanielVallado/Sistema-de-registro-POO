package modelo;
import MySQL.MySQL_AsientosDAO;
import MySQL.MySQL_FuncionesDAO;
import MySQL.MySQL_ObrasDAO;
import java.util.ArrayList;
import java.util.Date;

public class UsuarioAdministrador extends Usuario{
    public UsuarioAdministrador(String usuario, String nombre, String apellido, String contrasena){
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
    }
    
    //Obras
    public static boolean verificarExistenciaObra(String nombreObra){
        return MySQL_ObrasDAO.verificarExistenciaObra(nombreObra);
    }
    
    public static boolean crearObra(String nombreObra, String genero, String resumenTematico, String duracion, String primerActor, String segundoActor, double precioBoleto){
        return MySQL_ObrasDAO.insertarObra(nombreObra, genero, resumenTematico, duracion, primerActor, segundoActor, precioBoleto);
    }
    
    public static boolean eliminarObra(String nombreObra){
        eliminarFuncion(nombreObra);
        return MySQL_ObrasDAO.eliminarObra(nombreObra);
    }
    
    public static boolean modificarObra(String nombreObra, String nombreObraN, String genero, String resumen, String duracion, String primerActor, String segundoActor, double precio){
        return MySQL_ObrasDAO.modificarObra(nombreObra, nombreObraN, genero, resumen, duracion, primerActor, segundoActor, precio);
    }
    
    public static ArrayList<Obra> buscarObra(String nombreObra){
        return MySQL_ObrasDAO.buscarObras(nombreObra);
    }
    
    public static Obra obtenerObra(String nombreObra){
        return MySQL_ObrasDAO.obtenerObra(nombreObra);
    }
    
    public static ArrayList<Obra> obtenerTodasObras(){
        return MySQL_ObrasDAO.obtenerTodos();
    }
    
    //Funciones
    public static int verificarFechaFuncion(Date fecha){
        return MySQL_FuncionesDAO.verificarFecha(fecha);
    }
    
    public static boolean verificarHoraFuncion(Date fecha, String hora){
        return MySQL_FuncionesDAO.verificarHora(fecha, hora);
    }
    
    public static boolean crearFuncion(String nombreObra, Date dia, String hora){
        crearAsientos(nombreObra, dia, hora);
        return MySQL_FuncionesDAO.insertarFuncion(nombreObra, dia, hora);
    }
    
    public static boolean eliminarFuncion(String nombreObra, Date fecha, String hora){
        return MySQL_FuncionesDAO.eliminarFuncion(nombreObra, fecha, hora);
    }
    
    public static boolean eliminarFuncion(String nombreObra){
        eliminarAsiento(nombreObra);
        return MySQL_FuncionesDAO.eliminarFuncion(nombreObra);
    }
    
    public static boolean modificarFuncion(String nombreObra, Date dia, String horario, Date fechaN, String horarioN){
        return MySQL_FuncionesDAO.modificarFuncion(nombreObra, dia, horario, fechaN, horarioN);
    }
    
    public static ArrayList<Funcion> buscarFuncion(String nombreObra, String fecha){
        return MySQL_FuncionesDAO.buscarFunciones(nombreObra, fecha);
    }
    
    public static ArrayList<Funcion> buscarFuncion(String nombreObra){
        return MySQL_FuncionesDAO.buscarFunciones(nombreObra);
    }
    
    public static ArrayList<Funcion> obtenerTodasFunciones(){
        return MySQL_FuncionesDAO.obtenerTodos();
    }
    
    //Asientos
    public static boolean verificarReserva(String nombreObra, Date dia, String hora, String seccion, int numero, Boolean reservado){
        return MySQL_AsientosDAO.verificarReserva(nombreObra, dia, hora, seccion, numero);
    }
    
    public static boolean crearAsiento(String nombreObra, Date dia, String hora, String seccion, int numero, Boolean reservado){
        return MySQL_AsientosDAO.insertar(nombreObra, dia, hora, seccion, numero, reservado);
    }
    
    public static boolean eliminarAsiento(String nombreObra, Date dia, String hora){
        return MySQL_AsientosDAO.eliminar(nombreObra, dia, hora);
    }
    
    public static boolean eliminarAsiento(String nombreObra){
        return MySQL_AsientosDAO.eliminar(nombreObra);
    }
    
    public static boolean modificarAsiento(String nombreObra, Date dia, String hora, String seccion, int numero, Boolean reservado){
        return MySQL_AsientosDAO.modificarAsiento(nombreObra, dia, hora, seccion, numero, reservado);
    }
    
    public static ArrayList<Asiento> buscarAsiento(String nombreObra, Date dia, String hora){
        return MySQL_AsientosDAO.buscarAsientos(nombreObra, dia, hora);
    }
    
    public static ArrayList<Asiento> buscarAsiento(String nombreObra, Date dia, String hora, String seccion){
        return MySQL_AsientosDAO.buscarAsientos(nombreObra, dia, hora, seccion);
    }
    
    //
    private static void crearAsientos(String nombreObra, Date dia, String hora){
        for (int i = 0; i < 15; i++) {
            MySQL_AsientosDAO.insertar(nombreObra, dia, hora, "A", i, false);
            MySQL_AsientosDAO.insertar(nombreObra, dia, hora, "C", i, false);
        }
        
        for (int i = 0; i < 6; i++) {
            MySQL_AsientosDAO.insertar(nombreObra, dia, hora, "B", i, false);
            MySQL_AsientosDAO.insertar(nombreObra, dia, hora, "D", i, false);
        }
    }
}
