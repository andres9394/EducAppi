package com.project.andre.educappi.Controlador;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.project.andre.educappi.Modelo.Alumno;

import java.util.ArrayList;

/**
 * Created by andre on 18/05/2017.
 */

public class SeleccionaProveedor {
    private Activity context;
    private String rfc;

    public SeleccionaProveedor(Activity context){
        this.context = context;
    }

    public void enviarNotificacion(){
        Alumno alumno = new Alumno();
        alumno.setContext(context.getApplicationContext());
        alumno.setDocenteRFC(rfc);
        ArrayList<String> correos;
        correos = alumno.seleccionarCorreos();
        if (correos != null){
            String[] array = new String[correos.size()];
            correos.toArray(array);
            Intent openEmailApp = new Intent(Intent.ACTION_SENDTO);
            openEmailApp.setType("text/plain");
            openEmailApp.setData(Uri.parse("mailto:"));
            openEmailApp.putExtra(Intent.EXTRA_EMAIL, correos);
            try {
                context.startActivity(Intent.createChooser(openEmailApp, "Enviar correo"));
                Toast.makeText(context, "Escribe un correo", Toast.LENGTH_LONG).show();
            }catch (ActivityNotFoundException e){
                Toast.makeText(context, "No hay ningun proveedor de correo disponible", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context, "Sin destinatarios", Toast.LENGTH_LONG).show();
        }
    }

    public void setRFC(String rfc){
        this.rfc = rfc;
    }


}
