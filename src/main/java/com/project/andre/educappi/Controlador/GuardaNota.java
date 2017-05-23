package com.project.andre.educappi.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.andre.educappi.Modelo.Nota;
import com.project.andre.educappi.R;

public class GuardaNota extends AppCompatActivity {
    private Nota nota;
    private EditText titulo;
    private  EditText observaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_nota_ih);
        titulo = (EditText)findViewById(R.id.etTituloCN);
        observaciones = (EditText)findViewById(R.id.etOCN);
    }

    public void guardarNota(View v){
        nota = new Nota(getIntent().getStringExtra("curp"), titulo.getText().toString().trim(), observaciones.getText().toString().trim());
        if (validarFormulario()){
            nota.setContext(getApplicationContext());
            if (nota.guardarNota()){
                Toast.makeText(this, "Nota guardada con éxito", Toast.LENGTH_LONG).show();
                this.finish();
            }
        }
    }

    public Nota getNota(){
        return this.nota;
    }

    public boolean validarFormulario(){
        if (titulo.getText().toString().trim().isEmpty()){
            titulo.setError("Este espacio no debe estar vacio");
            return false;
        }
        if (observaciones.getText().toString().trim().isEmpty()){
            observaciones.setError("Este espacio no debe estar vacio");
            return false;
        }
        return true;
    }
}
