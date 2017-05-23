package com.project.andre.educappi.Modelo;

import android.content.Context;
import android.media.MediaActionSound;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by andre on 28/03/2017.
 */

public class Materia {
    private int id;
    private String nombre;
    private String grado;
    private String grupo;
    private String docente;
    private int cantidad_alumnos;
    private Context context;

    public Materia(){}

    public Materia(Context context){
        this.context = context;
    }

    public Materia(String nombre, String grado, String grupo, int cantidad_alumnos){
        this.nombre = nombre;
        this.grado = grado;
        this.grupo = grupo;
        this.cantidad_alumnos = cantidad_alumnos;
    }

    public Materia(String nombre, String grado, String grupo, String docente, int cantidad_alumnos){
        this.nombre = nombre;
        this.grado = grado;
        this.grupo = grupo;
        this.docente = docente;
        this.cantidad_alumnos = cantidad_alumnos;
    }

    public Materia(String nombre, String grado, String grupo, String docente, int cantidad_alumnos, int id){
        this.nombre = nombre;
        this.grado = grado;
        this.grupo = grupo;
        this.docente = docente;
        this.cantidad_alumnos = cantidad_alumnos;
        this.id = id;
    }

    public ArrayList<Materia> generarListaMaterias(Context context, String rfc){
        ConexionBD db = new ConexionBD(context);
        ArrayList<Materia> lista = db.generarListaMaterias(rfc);
        return lista;
    }

    public int getId(){
        return this.id;
    }

    public boolean modificarMateria(String id){
        ConexionBD db = new ConexionBD(context);
        if (db.modificarMateria(id)){
            Toast.makeText(context, "Materia modificada con éxito", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void setDocente(String user){
        this.docente = user;
    }

    public String getDocente(){
        return docente;
    }

    public boolean agregarNuevaMateria(){
        ConexionBD db = new ConexionBD(context);
        if(db.consultaMateria(nombre, grado)){
            return false;
        }
        else{
            if(db.insertaMateria(this));
                return true;
        }
    }

    public void setContext(Context context){
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
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

    public void setCantidad_alumnos(int cantidad_alumnos){
        this.cantidad_alumnos = cantidad_alumnos;
    }

    public int getCantidad_alumnos(){
        return cantidad_alumnos;
    }
}
