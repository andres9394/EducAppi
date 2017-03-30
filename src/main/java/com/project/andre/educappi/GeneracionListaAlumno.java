package com.project.andre.educappi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class GeneracionListaAlumno extends AppCompatActivity {
    private ArrayList<Alumno> listaAlumnos;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_alumno_ih);
        lv = (ListView)findViewById(R.id.lview);

        String docente = getIntent().getStringExtra("docente");

        Alumno alumno = new Alumno();
        listaAlumnos = alumno.generarListaAlumnos(getApplicationContext(), docente);


        if (listaAlumnos.size() <= 0){
            Toast.makeText(this, "No se obtuvo ningun nombre de alumno", Toast.LENGTH_LONG).show();
        }
        else{
            ArrayList<String> nombres = new ArrayList<String>();
            Iterator<Alumno> it = listaAlumnos.iterator();
            do {
                nombres.add(it.next().getNombre());
            }while(it.hasNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                // argument position gives the index of item which is clicked
                public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
                {
                    Alumno alumno1 = listaAlumnos.get(position);
                    String alumnoSeleccionado = alumno1.getDocenteRFC();
                //    Toast.makeText(getApplicationContext(), "RFC Docente: "+alumnoSeleccionado, Toast.LENGTH_LONG).show();
                    Intent mostrarReporte = new Intent(getApplicationContext(), GeneracionReporteAlumno.class);
                    mostrarReporte.putExtra("nombre", alumno1.getNombre());
                    mostrarReporte.putExtra("apellido", alumno1.getApellido());
                    mostrarReporte.putExtra("matricula", alumno1.getMatricula());
                    mostrarReporte.putExtra("curp", alumno1.getCurp());
                    mostrarReporte.putExtra("sexo", alumno1.getSexo());
                    mostrarReporte.putExtra("edad", alumno1.getEdad());
                    mostrarReporte.putExtra("telefono", alumno1.getTelefono());
                    mostrarReporte.putExtra("correo", alumno1.getCorreo_padres());
                    mostrarReporte.putExtra("docente", alumno1.getDocenteRFC());
                    mostrarReporte.putExtra("grado", alumno1.getGrado());
                    mostrarReporte.putExtra("grupo", alumno1.getGrupo());

                    startActivity(mostrarReporte);
                }
            });
        }
    }
}
