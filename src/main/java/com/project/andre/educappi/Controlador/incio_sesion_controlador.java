package com.project.andre.educappi.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.andre.educappi.MenuPrincipal;
import com.project.andre.educappi.Modelo.ConexionBD;
import com.project.andre.educappi.R;

public class incio_sesion_controlador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incio_sesion_ih);
    }

    public void registrarDocente(View v){
        Intent registraDocente = new Intent(this, RegistraDocente.class);
        startActivity(registraDocente);
    }

    public void iniciarSesion(View v){
        EditText user = (EditText) findViewById(R.id.user);
        EditText pass = (EditText) findViewById(R.id.pass);
        ConexionBD consulta = new ConexionBD(getApplicationContext());
        if(validarDatos(user, pass)){
            if(consulta.iniciarSesion(user.getText().toString(), pass.getText().toString())){
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                Intent abrirMenuPrincipal = new Intent(this, MenuPrincipal.class);
                abrirMenuPrincipal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                abrirMenuPrincipal.putExtra("docente", user.getText().toString());
                startActivity(abrirMenuPrincipal);
            }
            else{
                Toast.makeText(this, "El docente no existe en la base de datos", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean validarDatos(EditText e1, EditText e2){
        if(e1.getText().toString().equals("") || e2.getText().toString().equals("")) {
            Toast.makeText(this, "Faltan campos obligatorios por llenar", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
