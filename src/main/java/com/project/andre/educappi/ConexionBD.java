package com.project.andre.educappi;

import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by andre on 22/03/2017.
 */

public class ConexionBD extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myProjectDataBase0.db";
    public static final String TABLA_DOCENTE = "docente";
    public static final String TABLA_ALUMNO = "alumnos";
    public static final String TABLA_MATERIA = "materias";
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
    //COLUMNAS PARA TABLA MATERIA
    public static final String TMATERIA_COLUMNA_ID = "id_materia";
    public static final String TMATERIA_COLUMNA_NOMBRE = "nombre";
    public static final String TMATERIA_COLUMNA_GRADO = "grado";
    public static final String TMATERIA_COLUMNA_GRUPO = "grupo";
    public static final String TMATERIA_COLUMNA_CANTIDAD_ALUMNOS = "cantidad_alumnos";
    //INSTRUCCIONES PARA CREAR TABLAS
    public static final String SQL_CLEAR ="CREATE TABLE " + TABLA_DOCENTE + "("
            + TDOCENTE_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TDOCENTE_COLUMNA_RFC + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_NOMBRES + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_APELLIDOS + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_TELEFONO + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_CORREO + " TEXT NOT NULL,"
            + TDOCENTE_COLUMNA_PASSWORD + " TEXT NOT NULL);";
    public static final String SQL_CREATE_TABLA_ALUMNO = "CREATE TABLE " + TABLA_ALUMNO + "("
            + TALUMNO_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TALUMNO_COLUMNA_NOMBRE + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_APELLIDO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_CURP + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_TELEFONO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_EDAD + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_CORREO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_MATRICULA + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_GRADO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_GRUPO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_SEXO + " TEXT NOT NULL,"
            + TALUMNO_COLUMNA_PROFESOR + " TEXT NOT NULL);";
    public static final String SQL_CREATE_TABLA_MATERIA = "CREATE TABLE " + TABLA_MATERIA + "("
            + TMATERIA_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TMATERIA_COLUMNA_NOMBRE + " TEXT NOT NULL,"
            + TMATERIA_COLUMNA_GRADO + " TEXT NOT NULL,"
            + TMATERIA_COLUMNA_GRUPO + " TEXT NOT NULL,"
            + TMATERIA_COLUMNA_CANTIDAD_ALUMNOS + " INTEGER NOT NULL);";


    public ConexionBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(SQL_CLEAR);
        bd.execSQL(SQL_CREATE_TABLA_ALUMNO);
        bd.execSQL(SQL_CREATE_TABLA_MATERIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int j) {

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
                return false;
            }
        }catch(CursorIndexOutOfBoundsException e){
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Alumno> generarListaAlumnos(String rfcDocente){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        String[] columnas = {TALUMNO_COLUMNA_NOMBRE,
                TALUMNO_COLUMNA_APELLIDO,
                TALUMNO_COLUMNA_CURP,
                TALUMNO_COLUMNA_TELEFONO,
                TALUMNO_COLUMNA_EDAD,
                TALUMNO_COLUMNA_CORREO,
                TALUMNO_COLUMNA_MATRICULA,
                TALUMNO_COLUMNA_GRADO,
                TALUMNO_COLUMNA_GRUPO,
                TALUMNO_COLUMNA_SEXO,
                TALUMNO_COLUMNA_PROFESOR};
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLA_ALUMNO + " WHERE " + TALUMNO_COLUMNA_PROFESOR + "=?", new String[]{rfcDocente+""});
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
                        cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_GRUPO))));
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
                            cursor.getString(cursor.getColumnIndex(TALUMNO_COLUMNA_GRUPO))));
                }
            }
        }catch (CursorIndexOutOfBoundsException e){

        }
        return lista;
    }

    public boolean insertaAlumno(Alumno alumno){
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

        db.insert(TABLA_ALUMNO, null, values);
        db.close();
        return true;
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
}
