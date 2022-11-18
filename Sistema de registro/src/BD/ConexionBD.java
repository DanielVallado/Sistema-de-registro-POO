package BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    private static String nombreBD, user, clave, url, driver;
    private static Connection cx;
    
    public ConexionBD(String nombreBD, String user, String clave){
        this.nombreBD = nombreBD;
        this.user = user;
        this.clave = clave;
        this.url= "jdbc:mysql://localhost:3306/"+this.nombreBD;
        this.driver = "com.mysql.cj.jdbc.Driver";
    }
    
    public ConexionBD(String nombreBD){
        this(nombreBD, "root", "");
    }
    
    public ConexionBD(){
        this("teatro", "root", "");
    }
    
    public static Connection conectar(){
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url, user, clave);
        } catch(ClassNotFoundException | SQLException ex){
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se conecto a " + nombreBD + "\nError:" + ex);
        }
        return cx;
    }
    
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
