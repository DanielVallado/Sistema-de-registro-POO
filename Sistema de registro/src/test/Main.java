package test;
import BD.ConexionBD;
import control.ControlIndex;
import vista.*;

public class Main {
    public static void main(String[] args) {
        ConexionBD bd = new ConexionBD();
        ConexionBD.conectar();
        VistaIndex vistaIndex = new VistaIndex();
        ControlIndex index = new ControlIndex(vistaIndex);
        vistaIndex.setVisible(true);
    }  
}
