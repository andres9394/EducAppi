package com.project.andre.educappi.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.andre.educappi.Modelo.Alumno;
import com.project.andre.educappi.R;

import java.util.regex.Pattern;

public class AgregarAlumno extends AppCompatActivity {
    private String gradoSeleccionado;
    private String grupoSeleccionado;
    private int sexo;
    private String docenteRFC;

    private EditText nombre;
    private EditText apellido;
    private EditText curp;
    private EditText telefono;
    private EditText edad;
    private EditText Email;
    private EditText matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_alumno_ih);
        nombre = (EditText) findViewById(R.id.et_nombre_alumno);
        apellido = (EditText) findViewById(R.id.et_alumno_apellidos);
        curp = (EditText) findViewById(R.id.et_Alumno_curp);
        telefono = (EditText) findViewById(R.id.et_Alumno_telefono);
        edad = (EditText) findViewById(R.id.et_Alumno_edad);
        Email = (EditText) findViewById(R.id.et_Alumno_email);
        matricula = (EditText) findViewById(R.id.et_Alumno_matricula);

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

    public boolean validarFormulario(String nombre, String apellido, String curp, String telefono, String edad, String matricula){
        if(nombre.equals("") || apellido.equals("") || curp.equals("") || telefono.equals("") || edad.equals("") || matricula.equals("")){
            return false;
        }
        else{
            String patron_curp = "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
                    "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
                    "[HM]{1}" +
                    "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
                    "[B-DF-HJ-NP-TV-Z]{3}" +
                    "[0-9A-Z]{1}[0-9]{1}$";
            String patron_correo = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
            Pattern patron1 = Pattern.compile(patron_curp);
            Pattern patron2 = Pattern.compile(patron_correo);
            if(!patron1.matcher(curp).matches()){
                this.curp.setError("La cadena introducida no coincide con una curp valida");
                return false;
            }
            if(!patron2.matcher(this.Email.getText().toString()).matches()){
                this.Email.setError("La cadena introducida no coincide con un correo valido");
                return false;
            }
            if(telefono.trim().length() != 10){
                this.telefono.setError("La longitud del número telefonico debe ser de 10 digitos");
                return false;
            }
            if(Integer.parseInt(edad.trim()) < 6 || Integer.parseInt(edad.trim()) > 11){
                this.edad.setError("La edad debe estar en el rango de 6 a 11 años");
                return false;
            }
            if (matricula.trim().length() < 8){
                this.matricula.setError("La matricula debe estar compuesta por 8 digitos");
                return false;
            }
        }
        return true;
    }

    public void agregarAlumno(View v){
        Alumno alumno;
        if(!validarFormulario(nombre.getText().toString(), apellido.getText().toString(), curp.getText().toString(), telefono.getText().toString(), edad.getText().toString(), matricula.getText().toString())){
            Toast.makeText(this, "Los campos estan incompletos o son incorrectos. Por favor revisalos.", Toast.LENGTH_LONG).show();
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
