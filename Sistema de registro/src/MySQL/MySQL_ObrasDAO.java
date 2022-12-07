package MySQL;
import BD.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Obra;

public class MySQL_ObrasDAO{
    public static boolean verificarExistenciaObra(String nombreObra){
        try {         
            String query = "SELECT nombre FROM obras WHERE nombre='"+nombreObra+"'";
            ResultSet rs = (ResultSet) ConexionBD.conectar().createStatement().executeQuery(query);
            if(rs.next()){ return true; }  
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean insertarObra(String nombreObra, String genero, String resumenTematico, String duracion, String primerActor, String segundoActor, double precioBoleto){
        try {
            String query = "INSERT INTO obras(nombre, genero, resumen, duracion, primerActor, segundoActor, precio) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setString(2, genero);
            ps.setString(3, resumenTematico);
            ps.setString(4, duracion);
            ps.setString(5, primerActor);
            ps.setString(6, segundoActor);
            ps.setDouble(7, precioBoleto);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public static boolean eliminarObra(String nombreObra) {
        try {
            String query = "DELETE FROM obras WHERE nombre=?";
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

    public static boolean modificarObra(String nombreObra, String nombreObraN, String genero, String resumen, String duracion, String primerActor, String segundoActor, double precio) {
        try {
            String query = "UPDATE obras SET nombre=?, genero=?, resumen=?, duracion=?, primerActor=?, segundoActor=?, precio=? WHERE nombre=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObraN);
            ps.setString(2, genero);
            ps.setString(3, resumen);
            ps.setString(4, duracion);
            ps.setString(5, primerActor);
            ps.setString(6, segundoActor);
            ps.setDouble(7, precio);
            ps.setString(8, nombreObra);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }   
    
    public static ArrayList<Obra> buscarObras(String nObra) {
        
        try {
            String query = "SELECT * FROM obras WHERE nombre LIKE '"+nObra+"%'";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Obra> listaObras = new ArrayList();
            
            String nombreObra, genero, resumen, duracion, primerActor, segundoActor;
            double precio;
            
            while(rs.next()){
                nombreObra = rs.getString(1);
                genero = rs.getString(2);
                resumen = rs.getString(3);
                duracion = rs.getString(4);
                primerActor = rs.getString(5);
                segundoActor = rs.getString(6);
                precio = rs.getDouble(7);
                Obra obra = new Obra(nombreObra, genero, resumen, duracion, primerActor, segundoActor, precio);
                listaObras.add(obra);
            }
            return listaObras;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Obra obtenerObra(String nombreObra) {
        try {
            String query = "SELECT * FROM obras WHERE nombre=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String genero = rs.getString(2);
            String resumen = rs.getString(3);
            String duracion = rs.getString(4);
            String primerActor = rs.getString(5);
            String segundoActor = rs.getString(6);
            double precio = rs.getDouble(7);
            Obra obra = new Obra(nombreObra, genero, resumen, duracion, primerActor, segundoActor, precio);
            return obra;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Obra> obtenerTodos() {
        try {
            String query = "SELECT * FROM obras";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Obra> listaObras = new ArrayList<Obra>();
            
            String nombre, genero, resumen, primerActor, segundoActor, duracion;
            double precio;
            
            while(rs.next()){
                nombre = rs.getString(1);
                genero = rs.getString(2);
                resumen = rs.getString(3);
                duracion = rs.getString(4);
                primerActor = rs.getString(5);
                segundoActor = rs.getString(6);
                precio = rs.getDouble(7);
                Obra obra = new Obra(nombre, genero, resumen, duracion, primerActor, segundoActor, precio);
                listaObras.add(obra);
            }
  
            return listaObras;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
