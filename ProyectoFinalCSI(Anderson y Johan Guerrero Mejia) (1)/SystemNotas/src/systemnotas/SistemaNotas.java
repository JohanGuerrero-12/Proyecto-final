
package systemnotas;

import java.util.ArrayList;

/**
 *
 * @author 57322
 */
public class SistemaNotas {
    public ArrayList<Persona> usuarios = new ArrayList();
    public ArrayList<Materia> materias = new ArrayList();
    public ArrayList<Inscripcion> inscripciones = new ArrayList();
    
    public Persona login(String nickname,String contraseña){
    for(Persona u : usuarios){
        if (u.getNickname().equals(nickname) && u.getPassword().equals(contraseña))
               return u;

    }
        return null;
    }
    public String registrarEstudiante(String nombre, String nickname, String password) {
        if (buscarUsuario(nickname) != null) return "Error: El username ya existe.";
        Estudiante u = new Estudiante();
        u.setNombre(nombre);
        u.setNickname(nickname);
        u.setPassword(password);
        usuarios.add(u);
        return "Estudiante registrado con éxito.";
    }

    public String registrarProfesor(String nombre, String nickname, String password) {
        if (buscarUsuario(nickname) != null) return "Error: El username ya existe.";
        Profesor p = new Profesor();
        p.setNickname(nickname);
        p.setNombre(nombre);
        p.setPassword(password);
        usuarios.add(p);
        return "Profesor registrado con éxito.";
    }

    public String crearMateria(String nombre) {
        if (buscarMateria(nombre) != null) return "Error: La materia ya existe.";
        materias.add(new Materia(nombre));
        return "Materia creada con éxito.";
    }
    
    public String inscribirEstudianteEnMateria(String usernameEst, String nombreMat) {
        Persona p = buscarUsuario(usernameEst);
        Materia m = buscarMateria(nombreMat);
        if (p instanceof Estudiante && m != null) {
            inscripciones.add(new Inscripcion((Estudiante) p, m));
            return "Inscripción exitosa.";
        }
        return "Error: Estudiante o materia no encontrados.";
    }
    
    public String asignarMateriaAProfesor(String usernameProf, String nombreMat) {
        Persona p = buscarUsuario(usernameProf);
        Materia m = buscarMateria(nombreMat);
        if (p instanceof Profesor && m != null) {
            ((Profesor) p).asignarMateria(m);
            return "Materia asignada al profesor exitosamente.";
        }
        return "Error: Profesor o materia no encontrados.";
    }
    
    public String calificarEstudiante(String usernameEst, String nombreMat, double n1, double n2, double n3, double parcial) {
        Inscripcion inscripcion = buscarInscripcion(usernameEst, nombreMat);
        if (inscripcion != null) {
            inscripcion.setN1(n1);
            inscripcion.setN2(n2);
            inscripcion.setN3(n3);
            inscripcion.setParcial(parcial);
            return "Calificaciones registradas exitosamente. Nota final: " + String.format("%.2f", inscripcion.calcularFinal());
        }
        return "Error: No se encontró la inscripción del estudiante en la materia.";
    }
    
    public ArrayList<Inscripcion> getInscripcionesPorMateria(String nombreMat) {
        ArrayList<Inscripcion> inscripcionesMateria = new ArrayList<>();
        for (Inscripcion insc : inscripciones) {
            if (insc.getMateria().getNombre().equalsIgnoreCase(nombreMat)) {
                inscripcionesMateria.add(insc);
            }
        }
        return inscripcionesMateria;
    }

    public Persona buscarUsuario(String username) {
        for (Persona u : usuarios) if (u.getNickname().equalsIgnoreCase(username)) return u;
        return null;
    }
    
    public Materia buscarMateria(String nombre) {
        for (Materia m : materias) if (m.getNombre().equalsIgnoreCase(nombre)) return m;
        return null;
    }

    public Inscripcion buscarInscripcion(String usernameEst, String nombreMat) {
        for (Inscripcion i : inscripciones) {
            if (i.estudiante.getNickname().equalsIgnoreCase(usernameEst) && i.materia.getNombre().equalsIgnoreCase(nombreMat)) {
                return i;
            }
        }
        return null;
    }
    
    // Métodos getter para acceder a las listas desde las ventanas
    public ArrayList<Materia> getMaterias() {
        return materias;
    }
    
    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    
    public ArrayList<Persona> getUsuarios() {
        return usuarios;
    }
       //En caso de necesitar un reporte.
   /* public String generarReporteEstudiante(Estudiante estudiante) {
        StringBuilder reporte = new StringBuilder("--- Reporte para " + estudiante.getNombreCompleto() + " ---\n");
        boolean encontrado = false;
        for (Inscripcion i : inscripciones) {
            if (i.estudiante == estudiante) {
                encontrado = true;
                reporte.append("Materia: ").append(i.materia.nombre).append("\n");
                reporte.append("  - Notas: N1=").append(i.n1).append(", N2=").append(i.n2)
                       .append(", N3=").append(i.n3).append(", Parcial=").append(i.parcial).append("\n");
                reporte.append("  - Nota Final: ").append(String.format("%.2f", i.calcularNotaFinal())).append("\n\n");
            }
        }
        return encontrado ? reporte.toString() : "No tienes materias inscritas.";
    
}*/

    
}
