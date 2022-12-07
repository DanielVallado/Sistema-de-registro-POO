package test;
import BD.ConexionBD;
import control.ControlIndex;
import modelo.Teatro;
import vista.*;

public class Main {
    public static void main(String[] args) {
        ConexionBD bd = new ConexionBD();
        ConexionBD.conectar();
        Teatro.setNombreTeatro("Teatro Armando Manzanero");
        Teatro.setDireccion("Direccion");
        Teatro.setTelefono("Teléfono");
        VistaIndex vistaIndex = new VistaIndex();
        ControlIndex index = new ControlIndex(vistaIndex);
        vistaIndex.setVisible(true);
    }  
}
