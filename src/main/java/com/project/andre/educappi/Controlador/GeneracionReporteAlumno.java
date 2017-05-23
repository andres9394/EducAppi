package com.project.andre.educappi.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.project.andre.educappi.Modelo.Alumno;
import com.project.andre.educappi.Modelo.Nota;
import com.project.andre.educappi.R;

import java.util.ArrayList;

public class GeneracionReporteAlumno extends AppCompatActivity {
    private ArrayList<Nota> notas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte_ih);

        Alumno alumno = seleccionarAlumno();
        if (alumno != null){
            mostrarReporte(seleccionarAlumno());
        }
        else{
            Toast.makeText(this, "Alumno null", Toast.LENGTH_LONG).show();
        }
    }

    private void mostrarReporte(Alumno alumno){
        TextView nombreReporte = (TextView) findViewById(R.id.reporte_para);
        TextView promedio = (TextView) findViewById(R.id.promedio);
        TextView datosPersonales = (TextView) findViewById(R.id.datos_generales);
        TextView datosAcademicos = (TextView) findViewById(R.id.datos_academicos);
        TextView datosNotas = (TextView) findViewById(R.id.txtvNotas);

        nombreReporte.setText(nombreReporte.getText().toString() + " " + alumno.getNombre() + " " + alumno.getApellido());
        promedio.setText(promedio.getText().toString() + ": " + alumno.getpromedio());
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
        for (int i = 0; i < notas.size(); i++){
            datosNotas.setText(datosNotas.getText() + "\n"
                                + "Nota " + i + "\n"
                                + "Titulo: " + notas.get(i).getTitulo() + "\n"
                                + "Observación: " + notas.get(i).getObservacion() + "\n\n");
        }

    }

    private Alumno seleccionarAlumno(){
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
        alumno.setContext(getApplicationContext());
        alumno.setpromedio(getIntent().getStringExtra("promedio"));
        alumno.generarReporte(getApplicationContext());
        notas = alumno.getNotas();
        if (notas != null){
            return alumno;
        }
        return null;
    }

}
