package com.project.andre.educappi.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.andre.educappi.Modelo.Docente;
import com.project.andre.educappi.R;

public class RegistraDocente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registra_docente_ih);
    }

    public void Guardar(View v){
        EditText etNombre = (EditText) findViewById(R.id.etNombres);
        EditText etApellidos = (EditText) findViewById(R.id.etApellidos);
        EditText etTelefono = (EditText) findViewById(R.id.etTel);
        EditText etCorreo = (EditText) findViewById(R.id.etEmail);
        EditText etpassword = (EditText) findViewById(R.id.etContra);
        EditText etRFC = (EditText) findViewById(R.id.etRfc);
        if(validarDatos(etNombre.getText().toString(), etApellidos.getText().toString(), etTelefono.getText().toString(), etCorreo.getText().toString(), etpassword.getText().toString(), etRFC.getText().toString())){
            Docente docente = new Docente();
            docente.setNombre(etNombre.getText().toString());
            docente.setApellidos(etApellidos.getText().toString());
            docente.setTelefono(etTelefono.getText().toString());
            docente.setCorreo(etCorreo.getText().toString());
            docente.setPassword(etpassword.getText().toString());
            docente.setRfc(etRFC.getText().toString());
            docente.setContext(getApplicationContext());
            System.out.println("En Boton Guardar de la clase RegistrarDocente");
            if(docente.consultarDocente(etRFC.getText().toString())){
                Toast.makeText(this, "Ya existe ese usuario", Toast.LENGTH_LONG).show();
            }
            else{
                if(docente.agregarDocente()){
                    Toast.makeText(this, "Docente registrado", Toast.LENGTH_LONG).show();
                }
            }
            this.finish();
        }

    }

    private boolean validarDatos(String nombre, String apellido, String telefono, String correo, String password, String rfc){
        if(nombre.equals("") || apellido.equals("") || telefono.equals("") || correo.equals("") || password.equals("") || rfc.equals("")){
            Toast.makeText(this, "Faltan datos por llenar", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
