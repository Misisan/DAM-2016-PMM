package masimeon.add_03c.florida.add_act03c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "ciclo.db";
    private static final int DATABASE_VERSION = 1;
    //Tabla Profesores
    private static final String DATABASE_TABLE_PROF = "profesores";
        //Campos de la tabla
        private static final String PROF_NOMBRE = "nombre";
        private static final String PROF_EDAD = "edad";
        private static final String PROF_CICLO = "ciclo";
        private static final String PROF_CURSOTUTOR = "cursotutor";
        private static final String PROF_DESPACHO = "despacho";
        private static final String PROF_ID = "id";
    //Tabla Estudiantes
    private static final String DATABASE_TABLE_EST = "estudiantes";
        //Campos de la tabla
        private static final String EST_NOMBRE = "nombre";
        private static final String EST_EDAD = "edad";
        private static final String EST_CICLO = "ciclo";
        private static final String EST_CURSO = "curso";
        private static final String EST_NOTAMEDIA = "notamedia";
        private static final String EST_ID = "id";

    //Variable para crear las 2 tablas
    private static final String DATABASE_CREATE_PROF =
            "CREATE TABLE " + DATABASE_TABLE_PROF +
                " ( "+PROF_ID+" integer primary key autoincrement, " +
                    PROF_NOMBRE + " text not null, " +
                    PROF_EDAD + " integer not null, " +
                    PROF_CICLO + " text not null, " +
                    PROF_CURSOTUTOR + " text not null, " +
                    PROF_DESPACHO + " text not null);";
    private static final String DATABASE_CREATE_EST =
            "CREATE TABLE " + DATABASE_TABLE_EST +
                " ( "+EST_ID+" integer primary key autoincrement, " +
                    EST_NOMBRE + " text not null, " +
                    EST_EDAD + " integer not null, " +
                    EST_CICLO + " text not null, " +
                    EST_CURSO + " text not null, " +
                    EST_NOTAMEDIA + " integer not null);";

    //Variable para borrar las 2 tablas
    private static final String DATABASE_DROP_PROF = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROF+";";
    private static final String DATABASE_DROP_EST = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROF+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    //Variables para combos sin opciones
    private String cursoVacio = "Ningún curso seleccionado";
    private String cicloVacio = "Ningún ciclo seleccionado";

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        open();
    }

    public void open() {

        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarProfesor(Profesor p){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(PROF_NOMBRE, p.getNombre());
        newValues.put(PROF_EDAD, p.getEdad());
        newValues.put(PROF_CICLO, p.getCiclo());
        newValues.put(PROF_CURSOTUTOR, p.getTutorCurso());
        newValues.put(PROF_DESPACHO, p.getDespacho());
        //Los insertamos dentro de su tabla
        db.insert(DATABASE_TABLE_PROF,null,newValues);
    }

    public void insertarEstudiante(Estudiante e){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(EST_NOMBRE, e.getNombre());
        newValues.put(EST_EDAD, e.getEdad());
        newValues.put(EST_CICLO, e.getCiclo());
        newValues.put(EST_CURSO, e.getCurso());
        newValues.put(EST_NOTAMEDIA, e.getNotaMedia());
        //Los insertamos dentro de su tabla
        db.insert(DATABASE_TABLE_EST,null,newValues);
    }

    public ArrayList<Profesor> obtenerProfesores(String whereCurso, String whereCiclo){

        ArrayList<Profesor> prof = new ArrayList<Profesor>();

        //Si el curso o el ciclo no tienen nada seleccionado buscamos con el comodín
        if (whereCurso.equals(cursoVacio)){
            whereCurso="%";
        }
        if (whereCiclo.equals(cicloVacio)){
            whereCiclo="%";
        }

        //Montamos la sentencia where para aplicar los filtros a la búsqueda
        String lsqlwhere = "where "+PROF_CURSOTUTOR+" like '"+whereCurso+"' and "+PROF_CICLO+" like '"+whereCiclo+"'";

        //Montamos la query definitiva
        String lsql = "select * from "+DATABASE_TABLE_PROF+" "+lsqlwhere+";";

        Cursor c = db.rawQuery(lsql, null);

        if (c != null && c.moveToFirst()){
            while(c.moveToNext()){
                String nombre = c.getString(c.getColumnIndex(PROF_NOMBRE));
                Integer edad = c.getInt(c.getColumnIndex(PROF_EDAD));
                String ciclo = c.getString(c.getColumnIndex(PROF_CICLO));
                String curso = c.getString(c.getColumnIndex(PROF_CURSOTUTOR));
                String nota = c.getString(c.getColumnIndex(PROF_DESPACHO));
                Profesor p = new Profesor(nombre, edad, curso, ciclo, nota);
                p.setID(c.getInt(c.getColumnIndex(PROF_ID)));
                prof.add(p);
            }
        }

        //Si no hay resultados mostramos al siguiente para informar al usuario
        if (prof.size()==0){
            Profesor p = new Profesor("Ningún Profesor encontrado", 0, "", "", "");
            p.setID(0);
            prof.add(p);
        }

        return prof;
    }

    public ArrayList<Estudiante> obtenerEstudiantes(String whereCurso, String whereCiclo){

        ArrayList<Estudiante> est = new ArrayList<Estudiante>();

        //Si el curso o el ciclo no tienen nada seleccionado buscamos con el comodín
        if (whereCurso.equals(cursoVacio)){
            whereCurso="%";
        }
        if (whereCiclo.equals(cicloVacio)){
            whereCiclo="%";
        }

        //Montamos la sentencia where para aplicar los filtros a la búsqueda
        String lsqlwhere = "where "+EST_CURSO+" like '"+whereCurso+"' and "+EST_CICLO+" like '"+whereCiclo+"'";

        //Montamos la query definitiva
        String lsql = "select * from "+DATABASE_TABLE_EST+" "+lsqlwhere+";";

        Cursor c = db.rawQuery(lsql, null);

        if (c != null && c.moveToFirst()){
            while(c.moveToNext()){
                String nombre = c.getString(c.getColumnIndex(EST_NOMBRE));
                Integer edad = c.getInt(c.getColumnIndex(EST_EDAD));
                String ciclo = c.getString(c.getColumnIndex(EST_CICLO));
                String curso = c.getString(c.getColumnIndex(EST_CURSO));
                Integer nota = c.getInt(c.getColumnIndex(EST_NOTAMEDIA));
                Estudiante e = new Estudiante(nombre, edad, curso, ciclo, nota);
                e.setID(c.getInt(c.getColumnIndex(EST_ID)));
                est.add(e);
            }
        }

        if (est.size()==0){
            Estudiante e = new Estudiante("Ningún estudiante encontrado", 0, "", "", 0);
            e.setID(0);
            est.add(e);
        }
        return est;
    }



    public ArrayList<Profesor> obtenerProfesoresExamen(String letra){

        ArrayList<Profesor> prof = new ArrayList<Profesor>();

        //Montamos la sentencia where para aplicar los filtros a la búsqueda
        String lsqlwhere = "where "+PROF_NOMBRE+" like '"+letra+"%'";

        //Montamos la query definitiva
        String lsql = "select * from "+DATABASE_TABLE_PROF+" "+lsqlwhere+";";

        Cursor c = db.rawQuery(lsql, null);

        if (c != null && c.moveToFirst()){
            while(c.moveToNext()){
                String nombre = c.getString(c.getColumnIndex(PROF_NOMBRE));
                Integer edad = c.getInt(c.getColumnIndex(PROF_EDAD));
                String ciclo = c.getString(c.getColumnIndex(PROF_CICLO));
                String curso = c.getString(c.getColumnIndex(PROF_CURSOTUTOR));
                String nota = c.getString(c.getColumnIndex(PROF_DESPACHO));
                Profesor p = new Profesor(nombre, edad, curso, ciclo, nota);
                p.setID(c.getInt(c.getColumnIndex(PROF_ID)));
                prof.add(p);
            }
        }

        //Si no hay resultados mostramos al siguiente para informar al usuario
       /* if (prof.size()==0){
            Profesor p = new Profesor("Ningún Profesor encontrado", 0, "", "", "");
            p.setID(0);
            prof.add(p);
        }*/

        return prof;
    }

    public ArrayList<Estudiante> obtenerEstudiantesExamen(String letra){

        ArrayList<Estudiante> est = new ArrayList<Estudiante>();

        //Montamos la sentencia where para aplicar los filtros a la búsqueda
        //String lsqlwhere = "where "+EST_CURSO+" like '"+letra+"%' and "+EST_CICLO+" like '"+letra+"%'";
        String lsqlwhere = "where "+EST_NOMBRE+" like '"+letra+"%'";

        //Montamos la query definitiva
        String lsql = "select * from "+DATABASE_TABLE_EST+" "+lsqlwhere+";";

        Cursor c = db.rawQuery(lsql, null);

        if (c != null && c.moveToFirst()){
            while(c.moveToNext()){
                String nombre = c.getString(c.getColumnIndex(EST_NOMBRE));
                Integer edad = c.getInt(c.getColumnIndex(EST_EDAD));
                String ciclo = c.getString(c.getColumnIndex(EST_CICLO));
                String curso = c.getString(c.getColumnIndex(EST_CURSO));
                Integer nota = c.getInt(c.getColumnIndex(EST_NOTAMEDIA));
                Estudiante e = new Estudiante(nombre, edad, curso, ciclo, nota);
                e.setID(c.getInt(c.getColumnIndex(EST_ID)));
                est.add(e);
            }
        }
/*
        if (est.size()==0){
            Estudiante e = new Estudiante("Ningún estudiante encontrado", 0, "", "", 0);
            e.setID(0);
            est.add(e);
        }*/
        return est;
    }



    public void borrarEstudiante(int id){
        String lsql = "DELETE FROM "+DATABASE_TABLE_EST+" WHERE "+EST_ID+" = '"+id+"';";
        db.rawQuery(lsql, null).moveToNext();
    }

    public void borrarProfesor(int id){
        String lsql = "DELETE FROM "+DATABASE_TABLE_PROF+" WHERE "+PROF_ID+" = '"+id+"';";
        db.rawQuery(lsql, null).moveToNext();
    }

    public ArrayList<String> rellenarComboCurso(){

        ArrayList<String> curso = new ArrayList<String>();
        curso.add(cursoVacio);
        String lsql = "select distinct "+EST_CURSO+" from (SELECT "+EST_CURSO+" from "+DATABASE_TABLE_EST+" union select "+PROF_CURSOTUTOR+" as "+EST_CURSO+" from "+DATABASE_TABLE_PROF+")A;";
        Cursor c = db.rawQuery(lsql, null);

        if (c != null && c.moveToFirst()){
            while(c.moveToNext()){
                String cursoR = c.getString(c.getColumnIndex(EST_CURSO));
                curso.add(cursoR);
            }
        }

        return curso;
    }

    public ArrayList<String> rellenarComboCiclo(){

        ArrayList<String> ciclo = new ArrayList<String>();
        ciclo.add(cicloVacio);
        String lsql = "select distinct "+EST_CICLO+" from (SELECT "+EST_CICLO+" from "+DATABASE_TABLE_EST+" union select "+PROF_CICLO+" as "+EST_CICLO+" from "+DATABASE_TABLE_PROF+")A;";
        Cursor c = db.rawQuery(lsql, null);

        if (c != null && c.moveToFirst()){
            while(c.moveToNext()){
                String cursoR = c.getString(c.getColumnIndex(EST_CICLO));
                ciclo.add(cursoR);
            }
        }

        return ciclo;
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_PROF);
            db.execSQL(DATABASE_CREATE_EST);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_PROF);
            db.execSQL(DATABASE_DROP_EST);
            onCreate(db);
        }

    }
}
