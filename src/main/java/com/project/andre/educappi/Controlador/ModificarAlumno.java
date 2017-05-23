package com.project.andre.educappi.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.project.andre.educappi.AlumnosListAdapter;
import com.project.andre.educappi.Modelo.Alumno;
import com.project.andre.educappi.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class ModificarAlumno extends AppCompatActivity {
    private ListView lista;
    private EditText curp;
    private ArrayList<String> curps;
    private ArrayList<Alumno> listaAlumnos;
    private int indiceAlumnoAModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_alumno_ih);
        lista = (ListView)findViewById(R.id.lstAlumnos);
        curp = (EditText)findViewById(R.id.etCurp);
        listaAlumnos = new ArrayList<>();
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

    public void modificarAlumno(View v){
        if(validarCURP(curp.getText().toString().trim())){
            Intent modificarInformacionAlumnoIH = new Intent(this, ModificarInformacionAlumno.class);
            modificarInformacionAlumnoIH.putExtra("curp", listaAlumnos.get(indiceAlumnoAModificar).getCurp());
            modificarInformacionAlumnoIH.putExtra("telefono", listaAlumnos.get(indiceAlumnoAModificar).getTelefono());
            modificarInformacionAlumnoIH.putExtra("correo", listaAlumnos.get(indiceAlumnoAModificar).getCorreo_padres());
            modificarInformacionAlumnoIH.putExtra("matricula", listaAlumnos.get(indiceAlumnoAModificar).getMatricula());
            startActivity(modificarInformacionAlumnoIH);
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
            this.curp.setError("La cadena introducida no coincide con una curp valida");
            return false;
        }
        else{
            for (int i = 0; i < listaAlumnos.size(); i++){
                if (curp.equals(listaAlumnos.get(i).getCurp())){
                    indiceAlumnoAModificar = i;
                    return true;
                }
            }
        }
        return false;
    }
}
