package com.project.andre.educappi;

import android.content.Context;

/**
 * Created by andre on 22/03/2017.
 */

public class Docente {
    private String nombre;
    private String apellidos;
    private String rfc;
    private String telefono;
    private String correo;
    private String password;
    private Context context;

    public Docente(){}

    public Docente(String nombre, String apellidos, String rfc, String telefono, String correo, String password, Context context) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rfc = rfc;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
        this.context = context;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getRfc() {
        return rfc;
    }
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Context getContext(){
        return context;
    }
    public void setContext(Context context){
        this.context = context;
    }
    public boolean consultarDocente(String rfc){
        ConexionBD db = new ConexionBD(context);
        if(db.consultarDocente(rfc)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean agregarDocente(){
        System.out.println("En el metodo agregarDocente de la clase Docente");
        ConexionBD db = new ConexionBD(context);
        if(db.agregarDocente(nombre, apellidos, rfc, telefono, correo, password)){
            return true;
        }
        return false;
    }

}
