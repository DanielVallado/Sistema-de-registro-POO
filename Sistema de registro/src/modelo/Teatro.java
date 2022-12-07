package modelo;
import MySQL.MySQL_UsuariosDAO;
import MySQL.MySQL_VentasDAO;
import java.util.ArrayList;
import java.util.Date;

public class Teatro {
    private static String nombreTeatro, direccion, telefono;
    
        
    public Teatro(String nombreTeatro, String direccion, String telefono){
        this.nombreTeatro = nombreTeatro;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public static String getNombreTeatro() {
        return nombreTeatro;
    }

    public static void setNombreTeatro(String nombreTeatro) {
        nombreTeatro = nombreTeatro;
    }

    public static String getDireccion() {
        return direccion;
    }

    public static void setDireccion(String direccion) {
        direccion = direccion;
    }

    public static String getTelefono() {
        return telefono;
    }

    public static void setTelefono(String telefono) {
        telefono = telefono;
    }
    
    //Clientes
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
    
    public static boolean modificarCliente(String usuario, String usuarioN, String nombre, String apellido, String curp, String contrasena){
        return MySQL_UsuariosDAO.modificarUsuario(usuario, usuarioN, nombre, apellido, curp, contrasena);
    }
    
    public static ArrayList<UsuarioCliente> buscarClientes(String usuario){
        return MySQL_UsuariosDAO.buscarClientes(usuario);
    }
    
    public static ArrayList<UsuarioCliente> obtenerTodosClientes(){
        return MySQL_UsuariosDAO.obtenerTodosClientes();
    }
    
    //Ventas
    public static boolean insertarVenta(String usuario, int dia, int mes, String nombraObra, Date diaFuncion, String horaFuncion, int boletos, double precioTotal){     
        return MySQL_VentasDAO.insertar(usuario, dia, mes, nombraObra, diaFuncion, horaFuncion, boletos, precioTotal);
    }
    
    public static boolean eliminarVenta(String usuario, String nombraObra, Date dia, String hora){     
        return MySQL_VentasDAO.eliminar(usuario, nombraObra, dia, hora);
    }
    
    public static boolean eliminarVenta(String nombraObra){     
        return MySQL_VentasDAO.eliminar(nombraObra);
    }
    
    public static Venta buscarVenta(String usuario, int dia, int mes, String nombraObra, Date diaFuncion, String horaFuncion){
        return MySQL_VentasDAO.buscar(usuario, dia, mes, nombraObra, diaFuncion, horaFuncion);
    }
    
    public static ArrayList<Venta> buscarVentasPorObra(String nombraObra){     
        return MySQL_VentasDAO.buscarVentasPorObra(nombraObra);
    }
    
    public static ArrayList<Venta> buscarVentasPorUsuario(String usuario){     
        return MySQL_VentasDAO.buscarVentasPorUsuario(usuario);
    }
    
    public static ArrayList<Venta> buscarVentasPorDia(Date fecha){     
        return MySQL_VentasDAO.buscarVentasPorDia(fecha);
    }
}