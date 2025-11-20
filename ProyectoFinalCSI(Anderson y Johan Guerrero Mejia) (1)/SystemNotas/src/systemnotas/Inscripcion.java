
package systemnotas;

import java.util.ArrayList;


/**
 *
 * @author 57322
 */
public class Inscripcion  {
    Estudiante estudiante;
    Materia materia;
    private double n1,n2,n3,parcial;
    private ArrayList<Materia> listaMaterias = new ArrayList<>();

    public Inscripcion() {
    }

    public Inscripcion(Estudiante estudiante, Materia materia) {
        this.estudiante = estudiante;
        this.materia = materia;
    }

    public Inscripcion(Estudiante estudiante, Materia materia, double n1, double n2, double n3, double parcial ) {
        this.estudiante = estudiante;
        this.materia = materia;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.parcial = parcial;
        
    }

    

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public double getN3() {
        return n3;
    }

    public void setN3(double n3) {
        this.n3 = n3;
    }

    public double getParcial() {
        return parcial;
    }

    public void setParcial(double parcial) {
        this.parcial = parcial;
    }

    public double calcularFinal(){
    return((n1+n2+n3+parcial)/4);
    };
    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
    
    
    
    
}
