package com.project.andre.educappi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarMatera extends AppCompatActivity {
    private TextView materia;
    private Spinner grado;
    private Spinner grupo;
    private Spinner cantAlumnos;
    private String grado_selected;
    private String grupo_selected;
    private int countAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_materia_ih);
        materia = (TextView) findViewById(R.id.etNombreMateria);

        grado = (Spinner) findViewById(R.id.spinnerGrado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Grados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grado.setAdapter(adapter);
        grado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grado_selected = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        grupo = (Spinner) findViewById(R.id.spinnerGrupo);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Grupos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grupo.setAdapter(adapter1);
        grupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grupo_selected = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cantAlumnos = (Spinner) findViewById(R.id.spinnerNAlumnos);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Cantidad_Alumnos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cantAlumnos.setAdapter(adapter2);
        cantAlumnos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countAlumnos = Integer.parseInt((String)parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void agregarNuevaMateria(View view){
        if(!validarDatos()){
            Toast.makeText(this, "Hay espacios en blanco, por favor corrígelos", Toast.LENGTH_LONG).show();
        }else{
            Materia materia1 = new Materia(materia.getText().toString(), grado_selected, grupo_selected, countAlumnos);
            materia1.setContext(getApplicationContext());
            if(materia1.agregarNuevaMateria()){
                Toast.makeText(this, "Materia agregada con exito", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else{
                Toast.makeText(this, "Ya existe una materia con esos datos", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean validarDatos(){
        if(materia.getText().toString().equals("")){
            return false;
        }
        else{
            return true;
        }
    }
}
