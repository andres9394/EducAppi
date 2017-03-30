package com.project.andre.educappi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarAlumno extends AppCompatActivity {
    private String gradoSeleccionado;
    private String grupoSeleccionado;
    private int sexo;
    private String docenteRFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_alumno_ih);
        docenteRFC = getIntent().getStringExtra("docente");
        System.out.println("Valor retornado del intent: "+docenteRFC);

        Spinner spinner_grado;
        Spinner spinner_grupo;

        spinner_grado = (Spinner) findViewById(R.id.spinner_grado);
        ArrayAdapter<CharSequence> adapterGrado = ArrayAdapter.createFromResource(this, R.array.Grados, android.R.layout.simple_spinner_item);
        adapterGrado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_grado.setAdapter(adapterGrado);
        spinner_grado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gradoSeleccionado = (String) parent.getItemAtPosition(position);
                // Mostrar el elemento seleccionado
                    Toast.makeText(getApplicationContext(), "Selected : " + gradoSeleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        spinner_grupo = (Spinner) findViewById(R.id.spinner_grupo);
        ArrayAdapter<CharSequence> adapterGrupo = ArrayAdapter.createFromResource(this, R.array.Grupos, android.R.layout.simple_spinner_item);
        adapterGrupo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_grupo.setAdapter(adapterGrupo);
        spinner_grupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grupoSeleccionado = (String) parent.getItemAtPosition(position);
                // Mostrar el elemento seleccionado
                Toast.makeText(getApplicationContext(), "Selected : " + grupoSeleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void onRadioButtonClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rBtnHombre:
                if (checked)
                    sexo = 1;
            //    Toast.makeText(this, sexo, Toast.LENGTH_LONG).show();
                    break;
            case R.id.rBtnMujer:
                if (checked)
                    sexo = 0;
            //    Toast.makeText(this, sexo, Toast.LENGTH_LONG).show();
                    break;
        }
    }

    public boolean validarFormulario(String nombre, String apellido, String curp, String telefono, String edad, String email, String matricula){
        if(nombre.equals("") || apellido.equals("") || curp.equals("") || telefono.equals("") || edad.equals("") || email.equals("") || matricula.equals("")){
            return false;
        }
        return true;
    }


    public void agregarNuevoAlumno(View v){
        EditText nombre = (EditText) findViewById(R.id.et_nombre_alumno);
        EditText apellido = (EditText) findViewById(R.id.et_alumno_apellidos);
        EditText curp = (EditText) findViewById(R.id.et_Alumno_curp);
        EditText telefono = (EditText) findViewById(R.id.et_Alumno_telefono);
        EditText edad = (EditText) findViewById(R.id.et_Alumno_edad);
        EditText Email = (EditText) findViewById(R.id.et_Alumno_email);
        EditText matricula = (EditText) findViewById(R.id.et_Alumno_matricula);
        Alumno alumno;

        if(!validarFormulario(nombre.getText().toString(), apellido.getText().toString(), curp.getText().toString(), telefono.getText().toString(), edad.getText().toString(), Email.getText().toString(), matricula.getText().toString())){
            Toast.makeText(this, "Los campos estan incompletos, por favor llenalos.", Toast.LENGTH_LONG).show();
        }
        else{
            alumno = new Alumno(
                    nombre.getText().toString(),
                    apellido.getText().toString(),
                    matricula.getText().toString(),
                    curp.getText().toString(),
                    sexo,
                    Integer.parseInt(edad.getText().toString()),
                    telefono.getText().toString(),
                    Email.getText().toString(),
                    docenteRFC,
                    gradoSeleccionado,
                    grupoSeleccionado
            );
            alumno.setContext(getApplicationContext());
            if (alumno.agregarNuevoAlumno()){
                Toast.makeText(getApplicationContext(), "El alumno ha sido agregado con exito", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Ya existe un alumno con los mismos datos que se quieren registrar", Toast.LENGTH_LONG).show();
            }

        }

    }
}
