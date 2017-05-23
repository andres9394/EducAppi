package com.project.andre.educappi.Controlador;

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
import java.util.regex.Pattern;

public class EliminarAlumno extends AppCompatActivity {
    private ListView lstAlumnos;
    private EditText etCURP;
    private ArrayList<String> nombres;
    private ArrayList<String> curps;
    private ArrayList<Alumno> listaAlumnos;
    private int indiceAlumnoAEliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_alumno_ih);
        lstAlumnos = (ListView)findViewById(R.id.lstAlumnosEA);
        etCURP = (EditText)findViewById(R.id.etCurpEA);
        nombres = new ArrayList<>();
        curps = new ArrayList<>();
        listaAlumnos = new ArrayList<>();
        indiceAlumnoAEliminar = 0;
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
            lstAlumnos.setAdapter(adaptador);
        }
    }

    public void eliminarAlumno(View v){
        if(validarCURP(etCURP.getText().toString().trim())){
            Alumno alumno = new Alumno();
            alumno.setContext(getApplicationContext());
            alumno.setCurp(etCURP.getText().toString().trim());
            if (alumno.eliminarAlumno()){
                Toast.makeText(this, "La eliminación fue exitosa", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else{
                Toast.makeText(this, "En este momento no se puede conectar a la base de datos, inténtelo más tarde.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean validarCURP(String curp){
        String regex = "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
                "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
                "[HM]{1}" +
                "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
                "[B-DF-HJ-NP-TV-Z]{3}" +
                "[0-9A-Z]{1}[0-9]{1}$";
        Pattern patronCurp = Pattern.compile(regex);
        if (!patronCurp.matcher(curp).matches()){
            this.etCURP.setError("La cadena introducida no coincide con una curp valida");
            return false;
        }
        else{
            for (int i = 0; i < listaAlumnos.size(); i++){
                if (curp.equals(listaAlumnos.get(i).getCurp())){
                    indiceAlumnoAEliminar = i;
                    return true;
                }
            }
        }
        return false;
    }
}
