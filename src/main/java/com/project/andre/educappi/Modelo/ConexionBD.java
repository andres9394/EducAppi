package com.project.andre.educappi.Modelo;

import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.project.andre.educappi.MenuPrincipal;

import java.util.ArrayList;

/**
 * Created by andre on 22/03/2017.
 */

public class ConexionBD extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myProjectDataBaseFinal.db";
    public static final String TABLA_DOCENTE = "docente";
    public static final String TABLA_ALUMNO = "alumnos";
    public static final String TABLA_MATERIA = "materias";
    public static final String TABLA_NOTA = "notas";
    //COLUMNAS PARA TABLA DOCENTE
    public static final String TDOCENTE_COLUMNA_ID = "id_docente";
    public static final String TDOCENTE_COLUMNA_RFC = "rfc";
    public static final String TDOCENTE_COLUMNA_NOMBRES = "nombres";
    public static final String TDOCENTE_COLUMNA_APELLIDOS = "apellidos";
    public static final String TDOCENTE_COLUMNA_TELEFONO = "telefono";
    public static final String TDOCENTE_COLUMNA_CORREO = "correo";
    public static final String TDOCENTE_COLUMNA_PASSWORD = "password";
    //COLUMNAS PARA TABLA ALUMNO
    public static final String TALUMNO_COLUMNA_ID = "id_alumno";
    public static final String TALUMNO_COLUMNA_CURP = "curp";
    public static final String TALUMNO_COLUMNA_MATRICULA = "matricula";
    public static final String TALUMNO_COLUMNA_NOMBRE = "nombre";
    public static final String TALUMNO_COLUMNA_APELLIDO = "apellido";
    public static final String TALUMNO_COLUMNA_TELEFONO = "telefono";
    public static final String TALUMNO_COLUMNA_EDAD = "edad";
    public static final String TALUMNO_COLUMNA_CORREO = "correo";
    public static final String TALUMNO_COLUMNA_SEXO = "sexo";
    public static final String TALUMNO_COLUMNA_GRADO = "grado";
    public static final String TALUMNO_COLUMNA_GRUPO = "grupo";
    public static final String TALUMNO_COLUMNA_PROFESOR = "profesor";
    public static final String TALUMNO_COLUMNA_CALIFICACION = "cal";
    //COLUMNAS PARA TABLA MATERIA
    public static final String TMATERIA_COLUMNA_ID = "id_materia";
    public static final String TMATERIA_COLUMNA_NOMBRE = "nombre";
    public static final String TMATERIA_COLUMNA_GRADO = "grado";
    public static final String TMATERIA_COLUMNA_GRUPO = "grupo";
    public static final String TMATERIA_COLUMNA_DOCENTE = "docente";
    public static final String TMATERIA_COLUMNA_CANTIDAD_ALUMNOS = "cantidad_alumnos";
    //COLUMNAS PARA TABLA NOTAS
    public static final String TNOTAS_COLUMNA_CURP = "curp";
    public static final String TNOTAS_COLUMNA_TITULO = "titulio";
    public static final String TNOTAS_COLUMNA_OBSERVACIONES = "observaciones";
    //INSTRUCCIONES PARA CREAR TABLAS
    public static final String SQL_CLEAR ="CREATE TABLE " + TABLA_DOCENTE + "("
            + TDOCENTE_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TDOCENTE_COLUMNA_RFC + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_NOMBRES + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_APELLIDOS + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_TELEFONO + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_CORREO + " TEXT,"
            + TDOCENTE_COLUMNA_PASSWORD + " TEXT NOT NULL);";
    public static final String SQL_CREATE_TABLA_ALUMNO = "CREATE TABLE " + TABLA_ALUMNO + "("
            + TALUMNO_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TALUMNO_COLUMNA_NOMBRE + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_APELLIDO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_CURP + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_TELEFONO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_EDAD + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_CORREO + " TEXT,"
            + TALUMNO_COLUMNA_MATRICULA + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_GRADO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_GRUPO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_SEXO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_PROFESOR + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_CALIFICACION + " TEXT NOT NULL);";
    public static final String SQL_CREATE_TABLA_MATERIA = "CREATE TABLE " + TABLA_MATERIA + "("
            + TMATERIA_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TMATERIA_COLUMNA_NOMBRE + " TEXT NOT NULL,"
            + TMATERIA_COLUMNA_GRADO + " TEXT NOT NULL,"
            + TMATERIA_COLUMNA_GRUPO + " TEXT NOT NULL,"
            + TMATERIA_COLUMNA_DOCENTE + " TEXT NOT NULL,"
            + TMATERIA_COLUMNA_CANTIDAD_ALUMNOS + " INTEGER NOT NULL);";
    public  static final String SQL_CREATE_TABLA_NOTA = "CREATE TABLE " + TABLA_NOTA + "("
            + TNOTAS_COLUMNA_CURP + " TEXT NOT NULL,"
            + TNOTAS_COLUMNA_TITULO + " TEXT NOT NULL,"
            + TNOTAS_COLUMNA_OBSERVACIONES + " TEXT NOT NULL);";


    public ConexionBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(SQL_CLEAR);
        bd.execSQL(SQL_CREATE_TABLA_ALUMNO);
        bd.execSQL(SQL_CREATE_TABLA_MATERIA);
        bd.execSQL(SQL_CREATE_TABLA_NOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
    }

    public boolean agregarDocente(String nombres, String apellidos, String Rfc, String telefono, String correo, String password){
        boolean success = false;
        System.out.println("En el metodo agregarDocente de la clase ConexionDB");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TDOCENTE_COLUMNA_RFC, Rfc);
        values.put(TDOCENTE_COLUMNA_NOMBRES, nombres);
        values.put(TDOCENTE_COLUMNA_APELLIDOS, apellidos);
        values.put(TDOCENTE_COLUMNA_TELEFONO, telefono);
        values.put(TDOCENTE_COLUMNA_CORREO, correo);
        values.put(TDOCENTE_COLUMNA_PASSWORD, password);

        db.insert(TABLA_DOCENTE, null,values);
        db.close();
        return true;

    }

    public boolean consultarDocente(String rfc){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery("SELECT * FROM "+TABLA_DOCENTE+ " WHERE "+TDOCENTE_COLUMNA_RFC+"=?", new String[]{rfc+""});
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                return true;
            }
            else{
                return false;
            }
        }catch(CursorIndexOutOfBoundsException e){
            System.out.println(e);
        }
        return false;
    }

    public Docente obtenerDocente(String rfc){
        SQLiteDatabase db = this.getReadableDatabase();
        Docente docente = new Docente();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery("SELECT * FROM "+TABLA_DOCENTE+" WHERE "+TDOCENTE_COLUMNA_RFC+"=?", new String[]{rfc+""});
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                docente.setNombre(cursor.getString(cursor.getColumnIndex(TDOCENTE_COLUMNA_NOMBRES)));
                docente.setApellidos(cursor.getString(cursor.getColumnIndex(TDOCENTE_COLUMNA_APELLIDOS)));
                docente.setCorreo(cursor.getString(cursor.getColumnIndex(TDOCENTE_COLUMNA_CORREO)));
                docente.setTelefono(cursor.getString(cursor.getColumnIndex(TDOCENTE_COLUMNA_TELEFONO)));
                docente.setRfc(cursor.getString(cursor.getColumnIndex(TDOCENTE_COLUMNA_RFC)));
                docente.setPassword(cursor.getString(cursor.getColumnIndex(TDOCENTE_COLUMNA_PASSWORD)));
                docente.setContext(null);
                System.out.println(docente.getNombre());
                System.out.println(docente.getApellidos());
                System.out.println(docente.getCorreo());
                System.out.println(docente.getPassword());
                System.out.println(docente.getRfc());
                System.out.println(docente.getTelefono());
                System.out.println();
            }
        }finally {
            cursor.close();
        }
        return docente;
    }

    public boolean consultaMateria(String nombre, String grado){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLA_MATERIA + " WHERE (" + TMATERIA_COLUMNA_NOMBRE + "=?" + " AND " + TMATERIA_COLUMNA_GRADO + "=?)", new String[]{nombre+"", grado+""});
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                return true;
            }
            else{
                return false;
            }
        }catch(CursorIndexOutOfBoundsException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean insertaMateria(Materia materia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TMATERIA_COLUMNA_NOMBRE, materia.getNombre());
        values.put(TMATERIA_COLUMNA_GRADO, materia.getGrado());
        values.put(TMATERIA_COLUMNA_GRUPO, materia.getGrupo());
        values.put(TMATERIA_COLUMNA_DOCENTE, materia.getDocente());
        values.put(TMATERIA_COLUMNA_CANTIDAD_ALUMNOS, materia.getCantidad_alumnos());

        db.insert(TABLA_MATERIA, null, values);
        db.close();
        return true;
    }

    public boolean consultaAlumno(String curp, String matricula){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery("SELECT * FROM "+TABLA_ALUMNO+ " WHERE "+TALUMNO_COLUMNA_CURP+"=?" + " OR "+TALUMNO_COLUMNA_MATRICULA+"=?", new String[]{curp+"", matricula+""});
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                return true;
            }
            else{
                return false; //No existe el registro
            }
        }catch(CursorIndexOutOfBoundsException e){
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Alumno> generarListaAlumnos(String rfcDocente){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLA_ALUMNO + " WHERE " + TALUMNO_COLUMNA_PROFESOR + "=?", new String[]{rfcDocente+""});
            Log.e("Response", "CDB: consultas: " + cursor.getCount());
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                lista.add(new Alumno(cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_APELLIDO)),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_MATRICULA)),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_CURP)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_SEXO))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_EDAD))),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_TELEFONO)),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_CORREO)),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_PROFESOR)),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_GRADO)),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_GRUPO)),
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_CALIFICACION))));
                while (cursor.moveToNext()){
                    lista.add(new Alumno(cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_NOMBRE)),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_APELLIDO)),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_MATRICULA)),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_CURP)),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_SEXO))),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_EDAD))),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_TELEFONO)),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_CORREO)),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_PROFESOR)),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_GRADO)),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_GRUPO)),
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_CALIFICACION))));
                }
            }
        }catch (CursorIndexOutOfBoundsException e){

        }
        cursor.close();
        return lista;
    }

    public boolean guardarNota(String curp, String titulo, String observaciones){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TNOTAS_COLUMNA_CURP, curp);
        values.put(TNOTAS_COLUMNA_TITULO, titulo);
        values.put(TNOTAS_COLUMNA_OBSERVACIONES, observaciones);

        db.insert(TABLA_NOTA, null, values);
        db.close();
        return true;
    }

    public ArrayList<Nota> getNotas(String curp){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Nota> listaNotas = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery("SELECT * FROM "+TABLA_NOTA+" WHERE "+TNOTAS_COLUMNA_CURP+"=?", new String[]{curp+""});
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                listaNotas.add(new Nota(null,
                        cursor.getString(cursor.getColumnIndex(TNOTAS_COLUMNA_CURP)),
                        cursor.getString(cursor.getColumnIndex(TNOTAS_COLUMNA_TITULO)),
                        cursor.getString(cursor.getColumnIndex(TNOTAS_COLUMNA_OBSERVACIONES))));

                while (cursor.moveToNext()){
                    listaNotas.add(new Nota(null,
                        cursor.getString(cursor.getColumnIndex(TNOTAS_COLUMNA_CURP)),
                        cursor.getString(cursor.getColumnIndex(TNOTAS_COLUMNA_TITULO)),
                        cursor.getString(cursor.getColumnIndex(TNOTAS_COLUMNA_OBSERVACIONES))));
                }
            }
        }finally {
            cursor.close();
        }
        return listaNotas;
    }

    public ArrayList<Materia> generarListaMaterias(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Materia> materias = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLA_MATERIA + " WHERE " + TMATERIA_COLUMNA_DOCENTE + "=?", new String[]{user+""});
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                materias.add(new Materia(cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_GRADO)),
                        cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_GRUPO)),
                        cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_DOCENTE)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_CANTIDAD_ALUMNOS))),
                        cursor.getInt(cursor.getColumnIndex(TMATERIA_COLUMNA_ID))));
                while (cursor.moveToNext()){
                    materias.add(new Materia(cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_NOMBRE)),
                            cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_GRADO)),
                            cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_GRUPO)),
                            cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_DOCENTE)),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(TMATERIA_COLUMNA_CANTIDAD_ALUMNOS))),
                            cursor.getInt(cursor.getColumnIndex(TMATERIA_COLUMNA_ID))));
                }
            }
        }finally {
           //cursor.close();
        }
        return materias;
    }

    public long insertaAlumno(Alumno alumno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TALUMNO_COLUMNA_NOMBRE, alumno.getNombre());
        values.put(TALUMNO_COLUMNA_APELLIDO, alumno.getApellido());
        values.put(TALUMNO_COLUMNA_CURP, alumno.getCurp());
        values.put(TALUMNO_COLUMNA_TELEFONO, alumno.getTelefono());
        values.put(TALUMNO_COLUMNA_EDAD, Integer.toString(alumno.getEdad()));
        values.put(TALUMNO_COLUMNA_CORREO, alumno.getCorreo_padres());
        values.put(TALUMNO_COLUMNA_MATRICULA, alumno.getMatricula());
        values.put(TALUMNO_COLUMNA_GRADO, alumno.getGrado());
        values.put(TALUMNO_COLUMNA_GRUPO, alumno.getGrupo());
        values.put(TALUMNO_COLUMNA_SEXO, Integer.toString(alumno.getSexo()));
        values.put(TALUMNO_COLUMNA_PROFESOR, alumno.getDocenteRFC());
        values.put(TALUMNO_COLUMNA_CALIFICACION, "0");
        long insertCorrectly = db.insertOrThrow(TABLA_ALUMNO, null, values);
        db.close();

        return insertCorrectly; //-1 Si devuelve este valor, hubo un error;
    }

    public boolean modificarAlumno(String curp, String telefono, String matricula, String correo){
        ContentValues cv = new ContentValues();
        cv.put(TALUMNO_COLUMNA_TELEFONO, telefono); //These Fields should be your String values of actual column names
        cv.put(TALUMNO_COLUMNA_MATRICULA, matricula);
        cv.put(TALUMNO_COLUMNA_CORREO, correo);
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            db.update(TABLA_ALUMNO, cv, TALUMNO_COLUMNA_CURP + " =?", new String[]{String.valueOf(curp)});
        }
        else{
            return false;
        }
        return true;
    }

    public boolean eliminarAlumno(String curp){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            db.delete(TABLA_ALUMNO, TALUMNO_COLUMNA_CURP + " =?", new String[]{String.valueOf(curp)});
        }
        else{
            return false;
        }
        return true;
    }

    public ArrayList<String> conectar(String rfc){
        ArrayList<Alumno> listaAlumnos;
        listaAlumnos = generarListaAlumnos(rfc);
        ArrayList<String> listaCorreos = new ArrayList<>();
        if (listaAlumnos.size() > 0){
            for (int i = 0; i < listaAlumnos.size(); i++){
                listaCorreos.add(listaAlumnos.get(i).getCorreo_padres());
            }
            return listaCorreos;
        }
        return null;
    }

    public boolean iniciarSesion(String user, String pass){
        boolean seInicioSesion = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {TDOCENTE_COLUMNA_RFC, TDOCENTE_COLUMNA_PASSWORD};
        Cursor cursor = db.query(TABLA_DOCENTE, projection, TDOCENTE_COLUMNA_RFC+"=?", new String[] { String.valueOf(user) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                if(cursor.getString(1).equals(pass)){
                    seInicioSesion = true;
                }
            }catch(CursorIndexOutOfBoundsException e){}
        }
        db.close();
        return seInicioSesion;
    }

    public void guardarCalificacion(Alumno a){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TALUMNO_COLUMNA_CALIFICACION, a.getpromedio());
        db.update(TABLA_ALUMNO, cv, TALUMNO_COLUMNA_CURP + "=?", new String[]{String.valueOf(a.getCurp())});
    }

    public boolean modificarMateria(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(false)
            cursor = db.rawQuery("SELECT * FROM " + TABLA_MATERIA + " WHERE " + TMATERIA_COLUMNA_ID + "=?", new String[]{id});
        return true;
    }

    public boolean borrarInformacion(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase dbr = this.getReadableDatabase();
        ArrayList<Alumno> lista = generarListaAlumnos(user);
        Cursor cursor = null;
        if (lista.size() > 0){
            cursor.moveToFirst();
            do {
                db.delete(TABLA_NOTA, TNOTAS_COLUMNA_CURP + "=?", new String[]{String.valueOf(cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_CURP)))});
            }while(cursor.moveToNext());
        }
            db.delete(TABLA_ALUMNO, TALUMNO_COLUMNA_PROFESOR + "=?", new String[]{String.valueOf(user)});

            return true;
    }
}
