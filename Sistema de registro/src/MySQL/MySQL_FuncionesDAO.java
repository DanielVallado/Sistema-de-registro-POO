package MySQL;
import BD.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Funcion;
import modelo.UsuarioAdministrador;

public class MySQL_FuncionesDAO{
    
    public static int verificarFecha(Date fecha){
        int cont = 0;
        try {         
            String query = "SELECT dia FROM funciones WHERE dia='"+fecha+"'";
            ResultSet rs = (ResultSet) ConexionBD.conectar().createStatement().executeQuery(query);
            while(rs.next()){
                cont++;
            }
            return cont;
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cont;
    }
    
    public static boolean verificarHora(Date fecha, String hora){
        try {         
            String query = "SELECT dia, horario FROM funciones WHERE dia='"+fecha+"' and horario='"+hora+"'";
            ResultSet rs = (ResultSet) ConexionBD.conectar().createStatement().executeQuery(query);
            if(rs.next()){ return true; }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean insertarFuncion(String nombreObra, Date fecha, String horario){
        try {
            String query = "INSERT INTO funciones(obra, dia, horario) values(?,?,?)";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setDate(2, (java.sql.Date) fecha);
            ps.setString(3, horario);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public static boolean eliminarFuncion(String nombreObra, Date fecha, String horario) {
        try {
            String query = "DELETE FROM funciones WHERE obra=? and dia=? and horario=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setDate(2, (java.sql.Date) fecha);
            ps.setString(3, horario);
            ps.executeUpdate();
            ps.close();
            UsuarioAdministrador.eliminarAsiento(nombreObra, fecha, horario);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;     
    }
    
    public static boolean eliminarFuncion(String nombreObra){
        try {
            String query = "DELETE FROM funciones WHERE obra=?";
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

    public static boolean modificarFuncion(String nombreObra, Date dia, String horario, Date fechaN, String horarioN) {
        try {
            String query = "UPDATE funciones SET dia=?, horario=? WHERE obra=? and dia=? and horario=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setDate(1, (java.sql.Date) fechaN);
            ps.setString(2, horarioN);
            ps.setString(3, nombreObra);
            ps.setDate(4, (java.sql.Date) dia);
            ps.setString(5, horario);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static ArrayList<Funcion> buscarFunciones(String nombreObra, String dia) {
        
        try {
            String query = "SELECT * FROM funciones WHERE obra LIKE '"+nombreObra+"%'";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Funcion> listaFunciones = new ArrayList();
            
            String horario;
            Date fecha;
            
            while(rs.next()){
                nombreObra = rs.getString(1);
                fecha = rs.getDate(2);
                horario = rs.getString(3);
                Funcion funcion = new Funcion(nombreObra, horario, fecha);
                if(String.valueOf(funcion.getFecha()).contains(dia)){
                    listaFunciones.add(funcion);
                }
            }
            return listaFunciones;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Funcion> buscarFunciones(String nombreObra) {
        
        try {
            String query = "SELECT * FROM funciones WHERE obra LIKE '"+nombreObra+"%'";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Funcion> listaFunciones = new ArrayList();
            
            String horario;
            Date fecha;
            
            while(rs.next()){
                nombreObra = rs.getString(1);
                fecha = rs.getDate(2);
                horario = rs.getString(3);
                Funcion funcion = new Funcion(nombreObra, horario, fecha);
                listaFunciones.add(funcion);
            }
            return listaFunciones;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Funcion obtenerFuncion(String nombreObra, Date fecha, String hora) {
       try {
            String query = "SELECT * FROM funciones WHERE obra=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Date dia = rs.getDate(2);
            String horario = rs.getString(3);
            Funcion funcion = new Funcion(nombreObra, horario, dia);
            return funcion;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }

    public static ArrayList<Funcion> obtenerTodos() {
        try {
            String query = "SELECT * FROM funciones";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Funcion> listaFunciones = new ArrayList<Funcion>();
            
            String nombreObra, horario;
            Date dia;
            
            while(rs.next()){
                nombreObra = rs.getString(1);
                dia = rs.getDate(2);
                horario = rs.getString(3);
                Funcion funcion = new Funcion(nombreObra, horario, dia);
                listaFunciones.add(funcion);
            }
            return listaFunciones;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
}
