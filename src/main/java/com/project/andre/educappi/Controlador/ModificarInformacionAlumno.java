package com.project.andre.educappi.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.andre.educappi.Modelo.Alumno;
import com.project.andre.educappi.R;

import java.util.regex.Pattern;

public class ModificarInformacionAlumno extends AppCompatActivity {
    private String curp;
    private TextView titulo;
    private EditText telefono;
    private EditText matricula;
    private EditText correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_informacion_alumno_ih);
        curp = getIntent().getStringExtra("curp");

        titulo = (TextView)findViewById(R.id.txtvTituloMIA);
        titulo.setText(titulo.getText().toString() + " " + curp);
        telefono = (EditText)findViewById(R.id.etTelefonoMIA);
        telefono.setText(getIntent().getStringExtra("telefono"));
        matricula = (EditText)findViewById(R.id.etMatriculaMIA);
        matricula.setText(getIntent().getStringExtra("matricula"));
        correo = (EditText)findViewById(R.id.etCorreoMIA);
        correo.setText(getIntent().getStringExtra("correo"));
    }

    public void modificarAlumno(View v){
        Alumno alumno = new Alumno();
        alumno.setContext(getApplicationContext());
        alumno.setCurp(curp);
        if (validarFormulario()){
            if (alumno.modificarAlumno(telefono.getText().toString().trim(), matricula.getText().toString().trim(), correo.getText().toString().trim())){
                Toast.makeText(this, "La modificación fue exitosa", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else{
                Toast.makeText(this, "En este momento no se puede conectar a la base de datos, inténtelo más tarde.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean validarFormulario(){
        if(telefono.getText().toString().isEmpty() || matricula.getText().toString().isEmpty() || correo.getText().toString().isEmpty()){
            Toast.makeText(this, "Los campos están incompletos o son incorrectos. Por favor revísalos", Toast.LENGTH_LONG).show();
            return false;
        }
        if (telefono.getText().toString().trim().length() < 10){
            telefono.setError("La longitud del número telefonico debe ser de 10 digitos");
            return false;
        }
        if (matricula.getText().toString().trim().length() < 8){
            matricula.setError("La matricula debe estar compuesta por 8 digitos");
            return false;
        }
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        Pattern patron_correo = Pattern.compile(regex);
        if (patron_correo.matcher(correo.getText().toString().trim()).matches()){
            correo.setError("La cadena introducida no coincide con un correo valido");
            return false;
        }
        return true;
    }
}
