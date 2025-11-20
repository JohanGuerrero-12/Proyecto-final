/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemnotas;

public class Estudiante extends Persona {
    
    public Estudiante() {
    }
    public Estudiante(String nombre, String apellido, String nickname,String rol,String password) {
        super(nombre,apellido,nickname,rol,password);
    }
    @Override
    public void setPassword(String password) {
        super.setPassword(password); }

    @Override
    public String getPassword() {
        return super.getPassword(); }

    @Override
    public void setRol(String rol) {
        super.setRol(rol); }

    @Override
    public String getRol() {
        return super.getRol(); }

    @Override
    public void setNickname(String nickname) {
        super.setNickname(nickname); }

    @Override
    public String getNickname() {
        return super.getNickname(); }

    @Override
    public void setApellido(String apellido) {
        super.setApellido(apellido); }

    @Override
    public String getApellido() {
        return super.getApellido();}

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); }

    @Override
    public String getNombre() {
        return super.getNombre();  }
    
}
