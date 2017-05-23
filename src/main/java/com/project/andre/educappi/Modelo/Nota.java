package com.project.andre.educappi.Modelo;

import android.content.Context;

/**
 * Created by andre on 18/05/2017.
 */

public class Nota {
    private Context context;

    private String titulo;
    private String observacion;
    private String curp;

    public Nota(Context context, String curp, String titulo, String observacion){
        this.context = context;
        this.titulo = titulo;
        this.observacion = observacion;
        this.curp = curp;
    }

    public Nota(String curp, String titulo, String observaciones){
        this.curp = curp;
        this.titulo = titulo;
        this.observacion = observaciones;
    }

    public Nota(){}

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setObservacion(String observacion){
        this.observacion = observacion;
    }

    public void setCurp(String curp){
        this.curp = curp;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getObservacion(){
        return this.observacion;
    }

    public boolean guardarNota(){
        ConexionBD db = new ConexionBD(context);
        if (db.guardarNota(curp, titulo, observacion)){
            return true;
        }
        return false;
    }

}
