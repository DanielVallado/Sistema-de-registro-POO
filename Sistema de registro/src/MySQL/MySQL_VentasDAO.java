package MySQL;
import BD.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Venta;

public class MySQL_VentasDAO {
    public static boolean insertar(String usuario, int dia, int mes, String nombreObra, Date diaFuncion, String horaFuncion, int boletos, double precioTotal){
        try {
            String query = "INSERT INTO ventas(dia, mes, usuario, obra, diaFuncion, horaFuncion, boletos, precioTotal) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setInt(1, dia);
            ps.setInt(2, mes);
            ps.setString(3, usuario);
            ps.setString(4, nombreObra);
            ps.setDate(5, (java.sql.Date) diaFuncion);
            ps.setString(6, horaFuncion);
            ps.setInt(7, boletos);
            ps.setDouble(8, precioTotal);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean eliminar(String usuario, String nombreObra, Date dia, String hora) {    
        try {
            String query = "DELETE FROM ventas WHERE usuario=? and diaFuncion=? and horaFuncion=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, usuario);
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
            String query = "DELETE FROM ventas WHERE obra=?";
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
    
    public static Venta buscar(String usuario, int dia, int mes, String nombreObra, Date diaFuncion, String horaFuncion){
        try {
            String query = "SELECT * FROM ventas WHERE usuario=? AND dia=? AND mes=? AND obra=? AND diaFuncion=? AND horaFuncion=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, usuario);
            ps.setInt(2, dia);
            ps.setInt(3, mes);
            ps.setString(4, nombreObra);
            ps.setDate(5, (java.sql.Date) diaFuncion);
            ps.setString(6, horaFuncion);
            ResultSet rs = ps.executeQuery();
            
            int noVenta, boletos;
            double precioTotal;
  
            rs.next();
            
            noVenta = rs.getInt(1);
            dia = rs.getInt(2);
            mes = rs.getInt(3);
            usuario = rs.getString(4);
            nombreObra = rs.getString(5);
            diaFuncion = rs.getDate(6);
            horaFuncion = rs.getString(7);
            boletos = rs.getInt(8);
            precioTotal = rs.getDouble(9);
            Venta venta = new Venta(noVenta, dia, mes, usuario, nombreObra, diaFuncion, horaFuncion, boletos, precioTotal);

            return venta;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Venta> buscarVentasPorObra(String nombreObra){
        try {
            String query = "SELECT * FROM ventas WHERE obra=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, nombreObra);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Venta> listaVentas = new ArrayList();
            
            int noVenta, boletos, dia, mes;
            double precioTotal;
            String usuario, horaFuncion;
            Date diaFuncion;
            
            while(rs.next()){
                noVenta = rs.getInt(1);
                dia = rs.getInt(2);
                mes = rs.getInt(3);
                usuario = rs.getString(4);
                nombreObra = rs.getString(5);
                diaFuncion = rs.getDate(6);
                horaFuncion = rs.getString(7);
                boletos = rs.getInt(8);
                precioTotal = rs.getDouble(9);
                Venta venta = new Venta(noVenta, dia, mes, usuario, nombreObra, diaFuncion, horaFuncion, boletos, precioTotal);
                listaVentas.add(venta);
            }
            return listaVentas;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Venta> buscarVentasPorUsuario(String usuario){
        try {
            String query = "SELECT * FROM ventas WHERE usuario=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Venta> listaVentas = new ArrayList();
            
            int noVenta, boletos, dia, mes;
            double precioTotal;
            String nombreObra, horaFuncion;
            Date diaFuncion;
            
            while(rs.next()){
                noVenta = rs.getInt(1);
                dia = rs.getInt(2);
                mes = rs.getInt(3);
                usuario = rs.getString(4);
                nombreObra = rs.getString(5);
                diaFuncion = rs.getDate(6);
                horaFuncion = rs.getString(7);
                boletos = rs.getInt(8);
                precioTotal = rs.getDouble(9);
                Venta venta = new Venta(noVenta, dia, mes, usuario, nombreObra, diaFuncion, horaFuncion, boletos, precioTotal);
                listaVentas.add(venta);
            }
            return listaVentas;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Venta> buscarVentasPorDia(Date diaFuncion){
        try {
            String query = "SELECT * FROM ventas WHERE dia=?";
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(query);
            ps.setDate(1, (java.sql.Date) diaFuncion);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Venta> listaVentas = new ArrayList();
            
            int noVenta, boletos, dia, mes;
            double precioTotal;
            String nombreObra, horaFuncion, usuario;
            
            while(rs.next()){
                noVenta = rs.getInt(1);
                dia = rs.getInt(2);
                mes = rs.getInt(3);
                usuario = rs.getString(4);
                nombreObra = rs.getString(5);
                diaFuncion = rs.getDate(6);
                horaFuncion = rs.getString(7);
                boletos = rs.getInt(8);
                precioTotal = rs.getDouble(9);

                Venta venta = new Venta(noVenta, dia, mes, usuario, nombreObra, diaFuncion, horaFuncion, boletos, precioTotal);
                listaVentas.add(venta);
            }
            return listaVentas;
        } catch (SQLException ex){
            Logger.getLogger(MySQL_UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
