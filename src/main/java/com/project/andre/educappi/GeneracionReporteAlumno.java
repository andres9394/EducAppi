package com.project.andre.educappi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GeneracionReporteAlumno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte_ih);
        Alumno alumno = new Alumno(getIntent().getStringExtra("nombre"),
                getIntent().getStringExtra("apellido"),
                getIntent().getStringExtra("matricula"),
                getIntent().getStringExtra("curp"),
                getIntent().getIntExtra("sexo", 0),
                getIntent().getIntExtra("edad", 0),
                getIntent().getStringExtra("telefono"),
                getIntent().getStringExtra("correo"),
                getIntent().getStringExtra("docente"),
                getIntent().getStringExtra("grado"),
                getIntent().getStringExtra("grupo"));

        mostrarReporte(alumno);
    }

    private void mostrarReporte(Alumno alumno){
        TextView nombreReporte = (TextView) findViewById(R.id.reporte_para);
        TextView promedio = (TextView) findViewById(R.id.promedio);
        TextView datosPersonales = (TextView) findViewById(R.id.datos_generales);
        TextView datosAcademicos = (TextView) findViewById(R.id.datos_academicos);

        nombreReporte.setText(nombreReporte.getText().toString() + " " + alumno.getNombre() + " " + alumno.getApellido());
        promedio.setText(promedio.getText().toString() + ": " + "8.0");
        datosPersonales.setText("Nombre: " + alumno.getNombre() + "\n"
                                + "Apellido: " + alumno.getApellido() + "\n"
                                + "CURP: " + alumno.getCurp() + "\n"
                                + "Sexo: " + ((alumno.getSexo() == 0)?"Femenino":"Masculino") + "\n"
                                + "Edad: " + Integer.toString(alumno.getEdad()) + "\n"
                                + "Telefono: " + alumno.getTelefono() + "\n"
                                + "Correo de padre de familia o tutor: " + alumno.getCorreo_padres() + "\n");
        datosAcademicos.setText("Matricula: " + alumno.getMatricula() + "\n"
                                + "RFC del docente: " + alumno.getDocenteRFC() + "\n"
                                + "Grado: " + alumno.getGrado() + "\n"
                                + "Grupo: " + alumno.getGrupo() + "\n");


    }
}
