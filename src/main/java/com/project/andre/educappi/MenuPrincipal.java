package com.project.andre.educappi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.project.andre.educappi.Controlador.AgregaCalificacion;
import com.project.andre.educappi.Controlador.AgregarAlumno;
import com.project.andre.educappi.Controlador.AgregarMateria;
import com.project.andre.educappi.Controlador.CreaCiclo;
import com.project.andre.educappi.Controlador.CreaNotaAlumno;
import com.project.andre.educappi.Controlador.EliminarAlumno;
import com.project.andre.educappi.Controlador.EnviarEvidencia;
import com.project.andre.educappi.Controlador.GeneracionListaAlumno;
import com.project.andre.educappi.Controlador.HerramientasOnline;
import com.project.andre.educappi.Controlador.JustificaInasistencia;
import com.project.andre.educappi.Controlador.ModificaMateria;
import com.project.andre.educappi.Controlador.ModificarAlumno;
import com.project.andre.educappi.Controlador.SeleccionaProveedor;
import com.project.andre.educappi.Controlador.incio_sesion_controlador;

public class MenuPrincipal extends AppCompatActivity {
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal_ih);
        user = getIntent().getStringExtra("docente");
    }

    public void agregarAlumno(View v){
        Intent agregaAlumno = new Intent(this, AgregarAlumno.class);
        agregaAlumno.putExtra("docente", user);
        startActivity(agregaAlumno);
    }

    public void modificarAlumno(View v){
        Intent modificarAlumnoIH = new Intent(this, ModificarAlumno.class);
        modificarAlumnoIH.putExtra("rfc", user);
        startActivity(modificarAlumnoIH);
    }

    public void eliminarAlumno(View v){
        Intent eliminarAlumnoIH = new Intent(this, EliminarAlumno.class);
        eliminarAlumnoIH.putExtra("rfc", user);
        startActivity(eliminarAlumnoIH);
    }

    public void abrirHerramienta(View v){
        Intent visualizarHerramientasIH = new Intent(this, HerramientasOnline.class);
        startActivity(visualizarHerramientasIH);
    }

    public void generarReportes(View view){
        Intent lista_alumnos_ih = new Intent(this, GeneracionListaAlumno.class);
        lista_alumnos_ih.putExtra("docente", user);
        Log.e("Response", "Menu principal: " + user);
        startActivity(lista_alumnos_ih);
    }

    public void agregarMateria(View view){
        Intent agregarMateria = new Intent(this, AgregarMateria.class);
        agregarMateria.putExtra("rfc", user);
        startActivity(agregarMateria);
    }

    public void modificarMateria(View v){
        Intent modificarMateriaIH = new Intent(this, ModificaMateria.class);
        modificarMateriaIH.putExtra("rfc", user);
        startActivity(modificarMateriaIH);
    }

    public void crearNuevoCiclo(View v){
        CreaCiclo crear_ciclo = new CreaCiclo(this);
        crear_ciclo.setDocente(user);
        if (crear_ciclo.crearNuevoCiclo()){
            crear_ciclo.confirmar();
            confirmar();
        }
    }

    public void enviarEvidencias(View v){//
        EnviarEvidencia evidencia = new EnviarEvidencia(this);
        evidencia.enviarEvidencia();
    }

    public void enviarNotificacion(View v){
        SeleccionaProveedor proveedor = new SeleccionaProveedor(this);
        proveedor.setRFC(user);
        proveedor.enviarNotificacion();
    }

    public void cerrarSession(View v){
        Intent intent = new Intent(this, incio_sesion_controlador.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void crearNota(View v){
        Intent creaNotaAlumnoIH = new Intent(this, CreaNotaAlumno.class);
        creaNotaAlumnoIH.putExtra("rfc", user);
        startActivity(creaNotaAlumnoIH);
    }

    public void justificarInasistencia(View v){
        Intent justificarInasistenciaIH = new Intent(this, JustificaInasistencia.class);
        startActivity(justificarInasistenciaIH);
    }

    public void agregarCalificacion(View v){
        Intent agregaCalificacionIH = new Intent(this, AgregaCalificacion.class);
        agregaCalificacionIH.putExtra("rfc", user);
        startActivity(agregaCalificacionIH);

    }


    public void confirmar(){
        Log.e("response", "MP: confirmar");
        CreaCiclo cc = new CreaCiclo(this);
        if(cc.confirmar()){
            crearCiclo();
        }
    }

    public void crearCiclo(){
        CreaCiclo cc = new CreaCiclo(this);
        cc.setDocente(user);
        if (cc.crearCiclo()){
            Toast.makeText(this, "Nuevo ciclo creado correctamente", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "No hay conexión a la base de datos", Toast.LENGTH_LONG).show();
        }
    }

}
