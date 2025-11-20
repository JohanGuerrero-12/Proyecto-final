
package systemnotas;

import java.util.Date;

/**
 *
 * @author 57322
 */
public class Asistencia {
    Date fecha;
    boolean asistencia;

    public Asistencia() {
    }

    public Asistencia(Date fecha, boolean asistencia) {
        this.fecha = fecha;
        this.asistencia = asistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    
    
    
}
