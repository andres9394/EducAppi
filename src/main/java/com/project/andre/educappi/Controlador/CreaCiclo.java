package com.project.andre.educappi.Controlador;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.project.andre.educappi.Modelo.ConexionBD;

/**
 * Created by andre on 22/05/2017.
 */

public class CreaCiclo {
    private Activity context;
    private boolean flag;
    private String user;


    public CreaCiclo(){}

    public CreaCiclo(Activity context){
        this.context = context;
    }

    public boolean crearNuevoCiclo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Esta acción borrará toda la información de alumnos y materias del ciclo actual. ¿Esta seguro que desea continuar?").setTitle("Confirmación");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                confirmar();
                //flag = true;
                // User clicked OK button
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context, "Operación cancelada", Toast.LENGTH_SHORT).show();
                //flag = false;
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }

    public boolean confirmar(){
        Log.e("response", "CreaCiclo: confirmar");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("¿Proceder con la creación del nuevo ciclo?").setTitle("Confirmación");
        builder.setPositiveButton("Crear Ciclo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                crearCiclo();
                //Codigo de eliminar aqui
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Operación cancelada", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog dialogo = builder.create();
        dialogo.show();
        return false;
    }

    public boolean crearCiclo(){
        ConexionBD db = new ConexionBD(context);
        if(db.borrarInformacion(user)){
            return true;
        }
        return false;
    }

    public void setDocente(String user){
        this.user = user;
    }

}
