package com.project.andre.educappi.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.project.andre.educappi.Modelo.Materia;
import com.project.andre.educappi.R;

public class ModificaInformacionMateria extends AppCompatActivity {
    private EditText textoPonderacion;
    private EditText textoTemas;
    private EditText textoObjetivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_informacion_materia_ih);
        textoPonderacion = (EditText)findViewById(R.id.editText3);
        textoTemas = (EditText)findViewById(R.id.editText4);
        textoObjetivos = (EditText)findViewById(R.id.editText5);
    }

    public void modificarMateria(View v){
        if (validarFormulario()){
            Materia materia = new Materia(getApplicationContext());
            if(materia.modificarMateria("id")){
                finish();
            }
        }
    }

    public boolean validarFormulario(){
        if (textoObjetivos.getText().toString().isEmpty()){
            textoObjetivos.setError("El campo no debe ir vacio");
            return false;
        }
        if (textoTemas.getText().toString().isEmpty()){
            textoTemas.setError("El campo no debe ir vacio");
            return false;
        }
        if (textoPonderacion.getText().toString().isEmpty()){
            textoPonderacion.setError("El campo no debe ir vacio");
            return false;
        }
        return true;
    }
}
