package com.project.andre.educappi.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.andre.educappi.Modelo.Alumno;
import com.project.andre.educappi.R;

public class GuardaCalificacion extends AppCompatActivity {
    private EditText calificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificando_ih);
        calificacion = (EditText)findViewById(R.id.etGC);
    }

    public void guardarCalificacion(View v){
        Alumno alumno = new Alumno();
        alumno.setContext(getApplicationContext());
        alumno.setCurp(getIntent().getStringExtra("curp"));
        if (validarformulario()){
            alumno.guardarCalificacion(calificacion.getText().toString().trim());
        }
    }

    private boolean validarformulario(){
        if (calificacion.getText().toString().isEmpty()){
            Toast.makeText(this, "Faltan datos por agregar", Toast.LENGTH_LONG).show();
        }
        if (Integer.parseInt(calificacion.getText().toString().trim()) < 5 || Integer.parseInt(calificacion.getText().toString().trim()) > 10){
            calificacion.setError("El rango de calificación es de 5 a 10");
            return false;
        }
        else{
            return true;
        }
    }
}
