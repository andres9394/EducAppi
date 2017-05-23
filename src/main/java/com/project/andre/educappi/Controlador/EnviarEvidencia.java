package com.project.andre.educappi.Controlador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.project.andre.educappi.R;

public class EnviarEvidencia{
    private Activity context;

    public EnviarEvidencia(){}

    public EnviarEvidencia(Activity context){
        this.context = context;
    }

    public void enviarEvidencia(){
        try {
            Intent enviarCorreo = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+context.getResources().getString(R.string.correo_director)));
            //enviarCorreo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(enviarCorreo, "Enviar correo"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "No hay proveedor de correo.", Toast.LENGTH_SHORT).show();
        }
    }
}
