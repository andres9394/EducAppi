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
import com.project.andre.educappi.Modelo.Materia;
import com.project.andre.educappi.R;

import java.util.ArrayList;

public class ModificaMateria extends AppCompatActivity {
    private ListView lista;
    private EditText id_materia;
    private ArrayList<Materia> materias;
    private ArrayList<String> listaIds;
    private int indiceMateriaAModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_materia_ih);
        lista = (ListView)findViewById(R.id.lstMaterias);
        id_materia = (EditText)findViewById(R.id.etIDMateria);
        materias = new ArrayList<>();
        listaIds = new ArrayList<>();
        indiceMateriaAModificar = 0;
    }

    public void consultarMateriasDelProfesor(View v){
        Materia materia = new Materia();
        ArrayList<String> nombres = new ArrayList<>();
        materias = materia.generarListaMaterias(getApplicationContext(), getIntent().getStringExtra("rfc"));
        Log.e("Test", Integer.toString(materias.size()));
        if (materias.size() == 0){
            Toast.makeText(this, "Sin materias", Toast.LENGTH_SHORT).show();
        }
        else{
            for (int i = 0; i < materias.size(); i++){
                listaIds.add(Integer.toString(materias.get(i).getId()));
                nombres.add(materias.get(i).getNombre());
            }
            AlumnosListAdapter adaptador = new AlumnosListAdapter(getApplicationContext(), android.R.layout.simple_list_item_2, R.id.txtvNombre, nombres.toArray(new String[0]));
            adaptador.setContext(getApplicationContext());
            adaptador.setNombres(nombres);
            adaptador.setCurps(listaIds);
            Log.e("Test", Integer.toString(nombres.size()) + ", " + Integer.toString(listaIds.size()));
            lista.setAdapter(adaptador);
        }
    }

    public void modificarMateria(View v){
        if(!id_materia.getText().toString().trim().equals("")){
            Log.e("response", id_materia.getText().toString());
            if (validarID(id_materia.getText().toString().trim())){
                Intent modificarInformacionMateriaIH = new Intent(this, ModificaInformacionMateria.class);
                modificarInformacionMateriaIH.putExtra("id", Integer.toString(materias.get(indiceMateriaAModificar).getId()));
                startActivity(modificarInformacionMateriaIH);
            }
        }
    }

    public boolean validarID(String id){
        for (int i = 0; i < materias.size(); i++){
            Log.e("response", Integer.toString(materias.get(i).getId()));
            if (id.equals(Integer.toString(materias.get(i).getId()))){
                indiceMateriaAModificar = i;
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
}
