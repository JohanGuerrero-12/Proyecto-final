package systemnotas;

import java.util.ArrayList;

public class Profesor extends Persona {
    private ArrayList<Materia> listaMaterias = new ArrayList<>();
    public Profesor() {
    }
    
    public Profesor(String nombre, String apellido, String nickname,String rol,String password) {
        super(nombre,apellido,nickname,rol,password);
    }
    public void asignarMateria(Materia m){
        if (!listaMaterias.contains(m)){
            listaMaterias.add(m);
        }
    };
    
    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
    
    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public String getApellido() {
        return super.getApellido();
    }

    @Override
    public void setApellido(String apellido) {
        super.setApellido(apellido);
    }

    @Override
    public String getNickname() {
        return super.getNickname();
    }

    @Override
    public void setNickname(String nickname) {
        super.setNickname(nickname);
    }

    @Override
    public String getRol() {
        return super.getRol();
    }

    @Override
    public void setRol(String rol) {
        super.setRol(rol);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
    
}
