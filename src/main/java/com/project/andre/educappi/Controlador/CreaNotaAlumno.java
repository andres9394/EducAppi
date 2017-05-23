package com.project.andre.educappi.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.project.andre.educappi.AlumnosListAdapter;
import com.project.andre.educappi.Modelo.Alumno;
import com.project.andre.educappi.R;

import java.util.ArrayList;

public class CreaNotaAlumno extends AppCompatActivity {
    private ListView lista;
    private EditText curp;
    private ArrayList<String> curps;
    private ArrayList<Alumno> listaAlumnos;
    private int indiceAlumnoAModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_nota_ih);
        lista = (ListView)findViewById(R.id.lstAlumnosCN);
        curp = (EditText)findViewById(R.id.etCurpCN);
        curps = new ArrayList<>();
        listaAlumnos = new ArrayList<>();
        indiceAlumnoAModificar = 0;
    }

    public void consultarAlumnosDelProfesor(View v){
        Alumno alumno = new Alumno();
        listaAlumnos = alumno.generarListaAlumnos(getApplicationContext(), getIntent().getStringExtra("rfc"));
        Log.e("Test", Integer.toString(listaAlumnos.size()));
        if (listaAlumnos.size() == 0){
            Toast.makeText(this, "Sin alumnos", Toast.LENGTH_SHORT).show();
        }
        else{
            ArrayList<String> nombres = new ArrayList<>();
            curps = new ArrayList<>();
            for (int i = 0; i < listaAlumnos.size(); i++){
                nombres.add(listaAlumnos.get(i).getNombre());
                curps.add(listaAlumnos.get(i).getCurp());
            }
            AlumnosListAdapter adaptador = new AlumnosListAdapter(getApplicationContext(), android.R.layout.simple_list_item_2, R.id.txtvNombre, nombres.toArray(new String[0]));
            adaptador.setContext(getApplicationContext());
            adaptador.setNombres(nombres);
            adaptador.setCurps(curps);
            Log.e("Test", Integer.toString(nombres.size()) + ", " + Integer.toString(curps.size()));
            lista.setAdapter(adaptador);
        }
    }

    public void crearNota(View v){
        Intent crearNotaIH = new Intent(this, GuardaNota.class);
        crearNotaIH.putExtra("curp", curp.getText().toString().trim());
        startActivity(crearNotaIH);
    }
}
