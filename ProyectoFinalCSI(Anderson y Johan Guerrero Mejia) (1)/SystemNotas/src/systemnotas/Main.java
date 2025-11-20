
package systemnotas;

import systemnotas.SystemNotasVisual.VentanaAdmin;
import systemnotas.SystemNotasVisual.VentanaEstudiante;
import systemnotas.SystemNotasVisual.VentanaLogin;
import systemnotas.SystemNotasVisual.VentanaProfesor;

/**
 *
 * @author 57322
 */
public class Main {

    

    SistemaNotas sistema;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        SistemaNotas sistema= new SistemaNotas();
        sistema.usuarios.add(new Administrador("daniel","carreon","jose","Administrador","123"));
        /*super(nombre, apellido, nickname, rol, password);*/
        
        VentanaLogin lg= new VentanaLogin(sistema);
        lg.show();
        lg.setLocationRelativeTo(null);
    }
        
    public static void iniciarSesion(Persona usuario, SistemaNotas sistema) {
        if (usuario instanceof Administrador) {
            new VentanaAdmin(sistema).setVisible(true);
        } else if (usuario instanceof Profesor) {
            new VentanaProfesor((Profesor) usuario, sistema).setVisible(true);
        } else if (usuario instanceof Estudiante) {
            new VentanaEstudiante((Estudiante) usuario, sistema).setVisible(true);
        }
    }
    
    public static void mostrarVentanaLogin() {
        SistemaNotas sistema = new SistemaNotas();
        sistema.usuarios.add(new Administrador("daniel","carreon","jose","Administrador","123"));
        VentanaLogin lg = new VentanaLogin(sistema);
        lg.setVisible(true);
        lg.setLocationRelativeTo(null);
    }
}
