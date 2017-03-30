package com.project.andre.educappi;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by andre on 22/03/2017.
 */

public class Alumno {
    //Datos personales del alumno
    private String nombre;
    private String apellido;
    private String matricula;
    private String curp;
    private int sexo;
    private int edad;
    private String telefono;
    private String correo_padres;
    private String grado;
    private String grupo;
   //Profesor
    private String docenteRFC;
    private Context context;
    //Materias que cursa
    //private ArrayList<Materia> materias;

    public Alumno(){}

    public Alumno(String nombre, String apellido, String matricula, String curp, int sexo, int edad, String telefono, String correo_padres, String docenteRFC, String grado, String grupo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.curp = curp;
        this.sexo = sexo;
        this.edad = edad;
        this.telefono = telefono;
        this.correo_padres = correo_padres;
        this.docenteRFC = docenteRFC;
        this.grado = grado;
        this.grupo = grupo;
    }

    public boolean agregarNuevoAlumno(){
        ConexionBD db = new ConexionBD(context);
        if(db.consultaAlumno(curp, matricula)){
            return false;
        }
        else{
            if(db.insertaAlumno(this))
                return true;
        }
        return false;
    }

    public ArrayList<Alumno> generarListaAlumnos(Context context, String rfcDocente){
        ConexionBD db = new ConexionBD(context);
        ArrayList<Alumno> lista = db.generarListaAlumnos(rfcDocente);
        return lista;
    }

    public Context getContext() {
        return context;
    }

    public void setGrado(String grado){
        this.grado = grado;
    }

    public String getGrado(){
        return grado;
    }

    public void setGrupo(String grupo){
        this.grupo = grupo;
    }

    public String getGrupo(){
        return grupo;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_padres() {
        return correo_padres;
    }

    public void setCorreo_padres(String correo_padres) {
        this.correo_padres = correo_padres;
    }

    public String getDocenteRFC() {
        return docenteRFC;
    }

    public void setDocenteRFC(String docenteRFC) {
        this.docenteRFC = docenteRFC;
    }
}
