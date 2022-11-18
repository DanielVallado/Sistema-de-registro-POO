package MySQL;
import BD.ConexionBD;
import java.sql.*;
import java.util.List;
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
    
    public static boolean insertar(String nombreObra, String genero, String resumenTematico, double duracion, String primerActor, String segundoActor, double precioBoleto) {
        try {
            String query = "INSERT INTO obras(nombre, genero, resumen, duracion, primerActor, segundoActor, precio) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ps.setString(2, genero);
            ps.setString(3, resumenTematico);
            ps.setDouble(4, duracion);
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

    public static boolean eliminar(String nombreObra) {
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

    public void modificar(Obra usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Obra obtener(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Obra> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
