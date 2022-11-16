package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.Index;

public class ControlIndex implements ActionListener{
    private Index vistaIndex;
    
    public ControlIndex(Index vistaIndex){
        this.vistaIndex = vistaIndex;
        
        this.vistaIndex.getEntradaUsuario().addActionListener(this);
        this.vistaIndex.getEntradaContrasena().addActionListener(this);
        this.vistaIndex.getBotonLogin().addActionListener(this);
        this.vistaIndex.getBotonRegistro().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        String usuario, contrasena;
        boolean verificacion = false;
        if(vistaIndex.getBotonLogin() == evento.getSource()){
           usuario = vistaIndex.getEntradaUsuario().getText();
           contrasena = vistaIndex.getEntradaContrasena().getText();
           //comprobarUsuario(usuario, contrasena);
           if(verificacion == true){
               
           } else{
               
           }
        }
        
        if(vistaIndex.getBotonRegistro() == evento.getSource()){
           
        }
    }
}
