package MySQL;
import BD.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Asiento;

public class MySQL_AsientosDAO {
    public static boolean verificarReserva(String nombreObra, Date dia, String hora, String seccion, int numero){
        try {     
             String query = "SELECT reservado FROM usuarios WHERE obra=? and dia=? and hora=? and seccion=? and numero=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setDate(2, (java.sql.Date) dia);
            ps.setString(3, hora);
            ps.setString(4, seccion);
            ps.setInt(5, numero);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getBoolean(1);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean insertar(String nombreObra, Date dia, String hora, String seccion, int numero, Boolean reservado){
        try {
            String query = "INSERT INTO asientos(obra, dia, hora, seccion, numero, reservado) values(?,?,?,?,?,?)";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setDate(2, (java.sql.Date) dia);
            ps.setString(3, hora);
            ps.setString(4, seccion);
            ps.setInt(5, numero);
            ps.setBoolean(6, reservado);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public static boolean eliminar(String nombreObra, Date dia, String hora) {    
        try {
            String query = "DELETE FROM asientos WHERE obra=? and dia=? and hora=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setDate(2, (java.sql.Date) dia);
            ps.setString(3, hora);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    
    }
    
    public static boolean eliminar(String nombreObra) {    
        try {
            String query = "DELETE FROM asientos WHERE obra=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    
    }

    public static boolean modificarAsiento(String nombreObra, Date dia, String hora, String seccion, int numero, Boolean reservado){
        try {
            String query = "UPDATE asientos SET reservado=? WHERE obra=? and dia=? and hora=? and seccion=? and numero=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setBoolean(1, reservado);
            ps.setString(2, nombreObra);
            ps.setDate(3, (java.sql.Date) dia);
            ps.setString(4, hora);
            ps.setString(5, seccion);
            ps.setInt(6, numero);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static ArrayList<Asiento> buscarAsientos(String nombreObra, Date dia, String hora, String seccion) {
        try {
            String query = "SELECT * FROM asientos WHERE obra=? AND dia=? AND hora=? AND seccion=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setDate(2, (java.sql.Date) dia);
            ps.setString(3, hora);
            ps.setString(4, seccion);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Asiento> listaAsientos = new ArrayList();
            
            int numero;
            Boolean reservado;
            
            while(rs.next()){
                numero = rs.getInt(5);
                reservado = rs.getBoolean(6);
                Asiento asiento = new Asiento(nombreObra, dia, hora, seccion, numero, reservado);
                listaAsientos.add(asiento);
            }
            return listaAsientos;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Asiento> buscarAsientos(String nombreObra, Date dia, String hora) {
        try {
            String query = "SELECT * FROM asientos WHERE obra=? and dia=? and hora=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setDate(2, (java.sql.Date) dia);
            ps.setString(3, hora);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Asiento> listaAsientos = new ArrayList();
            
            int numero;
            String seccion;
            Boolean reservado;
            
            while(rs.next()){
                seccion = rs.getString(4);
                numero = rs.getInt(5);
                reservado = rs.getBoolean(5);
                Asiento usuario = new Asiento(nombreObra, dia, hora, seccion, numero, reservado);
                listaAsientos.add(usuario);
            }
            return listaAsientos;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
