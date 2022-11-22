package MySQL;
import BD.ConexionBD;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import modelo.UsuarioAdministrador;
import modelo.UsuarioCliente;

public class MySQL_UsuariosDAO {
    public static boolean verificarAdministrador(String usuario, String contrasena){
        try {         
            String query = "SELECT usuario, contrasena FROM administradores WHERE usuario='"+usuario+"' and contrasena='"+contrasena+"'";
            ResultSet rs = (ResultSet) ConexionBD.conectar().createStatement().executeQuery(query);
            if(rs.next()){ return true; }  
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean verificarUsuario(String usuario, String contrasena){
        String query = "SELECT usuario, contrasena FROM usuarios WHERE usuario='"+usuario+"' and contrasena='"+contrasena+"'";
        try {           
            ResultSet rs = (ResultSet) ConexionBD.conectar().createStatement().executeQuery(query);
            if(rs.next()){ return true; }  
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean verificarExistenciaAdmin(String usuario){
        String query = "SELECT usuario FROM administradores WHERE usuario='"+usuario+"'";
        try {           
            ResultSet rs = (ResultSet) ConexionBD.conectar().createStatement().executeQuery(query);
            if(rs.next()){ return true; }  
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean verificarExistenciaUsuario(String usuario){
        String query = "SELECT usuario FROM usuarios WHERE usuario='"+usuario+"'";
        try {           
            ResultSet rs = (ResultSet) ConexionBD.conectar().createStatement().executeQuery(query);
            if(rs.next()){ return true; }  
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean insertar(String usuario, String nombre, String apellido, String curp, String contrasena) {
        try {
            String query = "INSERT INTO usuarios(usuario, nombre, apellido, curp, contrasena) values(?,?,?,?,?)";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, curp);
            ps.setString(5, contrasena);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public static boolean eliminar(String usuario) {    
        try {
            String query = "DELETE FROM usuarios WHERE usuario=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, usuario);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    
    }

    public boolean modificarUsuario(String usuario, String usuarioN, String nombre, String apellido, String curp, String contrasena) {
       try {
            String query = "UPDATE usuarios SET usuario=?, nombre=?, apellido=?, curp=?, contrasena=? WHERE usuario=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, usuarioN);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, curp);
            ps.setString(5, contrasena);
            ps.setString(6, usuario);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public Usuario obtenerAdmin(String usuario) {
        try {
            String query = "SELECT * FROM administradores WHERE usuario=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String nombre = rs.getString(3);
            String apellido = rs.getString(4);
            String contrasena = rs.getString(5);
            UsuarioAdministrador usuarioActivo = new UsuarioAdministrador(usuario, nombre, apellido, contrasena);
            return usuarioActivo;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static UsuarioCliente obtenerCliente(String usuario) {   
        try {
            String query = "SELECT * FROM usuarios WHERE usuario=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String nombre = rs.getString(2);
            String apellido = rs.getString(3);
            String curp = rs.getString(4);
            String contrasena = rs.getString(5);
            UsuarioCliente usuarioActivo = new UsuarioCliente(usuario, nombre, apellido, curp, contrasena);
            return usuarioActivo;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Usuario> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}