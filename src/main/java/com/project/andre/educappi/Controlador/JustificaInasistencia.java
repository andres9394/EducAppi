package com.project.andre.educappi.Controlador;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.project.andre.educappi.R;

public class JustificaInasistencia extends AppCompatActivity {
    private EditText correos;
    private EditText asunto;
    private EditText cuerpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justifica_inasistencia_ih);
        correos = (EditText)findViewById(R.id.etEmailJI);
        asunto = (EditText)findViewById(R.id.etAsuntoJI);
        cuerpo = (EditText)findViewById(R.id.etCuerpoJI);
    }

    public void enviarInasistencia(View view){
        Intent sendEmail = new Intent(Intent.ACTION_SEND);
        sendEmail.setType("text/email");
        sendEmail.setData(Uri.parse("mailto:"));
        sendEmail.putExtra(Intent.EXTRA_EMAIL, correos.getText().toString().trim());
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, asunto.getText().toString().trim());
        sendEmail.putExtra(Intent.EXTRA_TEXT, cuerpo.getText().toString().trim());

        startActivity(Intent.createChooser(sendEmail, "Seleccionar proveedor de correo"));
    }
}
