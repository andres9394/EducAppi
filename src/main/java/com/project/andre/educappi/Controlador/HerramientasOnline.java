package com.project.andre.educappi.Controlador;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.andre.educappi.R;

public class HerramientasOnline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herramientas_online_ih);
    }

    public void abrirHerramienta(View v){
        if (verificarConexionInternet()){
            Intent openWeb = new Intent();
            switch (v.getId()){
                case R.id.btnTool1:
                    openWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://evernote.com/intl/es/"));
                    break;
                case R.id.btnTool2:
                    openWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.quizbean.com/home"));
                    break;
                case R.id.btnTool3:
                    openWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://es.duolingo.com/"));
                    break;
                case R.id.btnTool4:
                    openWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.socrative.com/"));
                    break;
                case R.id.btnTool5:
                    openWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.codecademy.com/"));
                    break;
                case R.id.btnTool6:
                    openWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wolframalpha.com/"));
                    break;
            }
            startActivity(openWeb);
            Button btn = (Button)v;
            Toast.makeText(this, "Herramienta " + btn.getText().toString() + " abierta", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "En este momento no estas conectado a internet, por favor inténtalo más tarde.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean verificarConexionInternet(){
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnectedOrConnecting();
    }
}
