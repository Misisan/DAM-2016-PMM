package masimeon.pmm_final.florida.pmm_final;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "mathdice.db";
    private static final int DATABASE_VERSION = 1;
    //Tabla Jugadores
    private static final String DATABASE_TABLE_JUGADOR = "jugadores";
    //Campos de la jugadores
    private static final String NOMBRE = "nombre";
    private static final String NICK = "nick";
    private static final String PUNTOS = "puntos";
    private static final String ACTIVO = "activo";
    private static final String ID = "id";
    private static final String IMG = "URLimagen";

    //Creamos la tabla
    private static final String DATABASE_CREATE_JUGADOR =
            "CREATE TABLE " + DATABASE_TABLE_JUGADOR +
                    " ( "+ID+" integer primary key autoincrement, " +
                    NOMBRE + " text not null, " +
                    NICK + " text not null, " +
                    PUNTOS + " integer not null, "+
                    IMG + " text, " +
                    ACTIVO + " integer not null); "; //lo pongo integer y no bit por que no se el SQLite como lo trabaja

    //Variable para borrar la tabla
    private static final String DATABASE_DROP_JUGADOR = "DROP TABLE IF EXISTS "+DATABASE_TABLE_JUGADOR+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

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

    public void insertarJugador(Jugador j){

        //Ponemos al resto de jugadores desactivos
        String lsql = "UPDATE "+DATABASE_TABLE_JUGADOR+" SET "+ACTIVO+"= 0;";
        db.execSQL(lsql);

        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE, j.getNombre());
        newValues.put(NICK, j.getNick());
        newValues.put(PUNTOS, 0); //Por defecto todos empiezan con 0 puntos
        newValues.put(IMG, j.getImg());
        newValues.put(ACTIVO, 1); //Así activamos el jugador recién creado
        //Los insertamos dentro de su tabla
        db.insert(DATABASE_TABLE_JUGADOR,null,newValues);

    }

    public Jugador obtenerJugadorActivo(){

        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        Jugador jugador = new Jugador();

        //Montamos la query para la consulta
        //No le ponemos condición pq con la variables ACTIVA para de mi como de la mierda
        //Si la activa es 1, con la condición =1 me devuelve null (debería devolver 1 resultado) y si la condición es =0 va bien
        //pero si intercambio los valores se vuelve a comportar igual pero al revés, solo em saca lo que no me interesa
        String lsql = "select * from "+DATABASE_TABLE_JUGADOR+";";

        Cursor c = db.rawQuery(lsql, null);

        //Recorremos los resultados y los añadimos
        if (c != null && c.moveToFirst()){
            while(c.moveToNext()){
                String nombre = c.getString(c.getColumnIndex(NOMBRE));
                String nick = c.getString(c.getColumnIndex(NICK));
                Integer puntos = c.getInt(c.getColumnIndex(PUNTOS));
                Integer id = c.getInt(c.getColumnIndex(ID));
                String img = c.getString(c.getColumnIndex(IMG));
                Integer act = c.getInt(c.getColumnIndex(ACTIVO));
                Jugador j = new Jugador(nombre, nick);
                j.setPuntos(puntos);
                j.setId(id);
                j.setActivo(act);
                j.setImg(img);
               jugadores.add(j);
            }
        }

        //Como la consulta SQL hace lo que quiere lo rescatamos todo y montamos la condición
        //por código. Tengo un odio viscelar al SQLite!!
        for( int i = 0 ; i < jugadores.size() ; i++ ){
            if (jugadores.get(i).getActivo()==1 ){
                jugador = jugadores.get(i);
            }
        }

        return jugador;
    }

    public ArrayList<Jugador> obtenerJugadores(){

        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

        //Montamos la query para la consulta
        String lsql = "select * from "+DATABASE_TABLE_JUGADOR+" order by "+NICK+";";

        Cursor c = db.rawQuery(lsql, null);

        //Recorremos los resultados y los añadimos al ArrayList
        if (c != null && c.moveToFirst()){
            while(c.moveToNext()){
                String nombre = c.getString(c.getColumnIndex(NOMBRE));
                String nick = c.getString(c.getColumnIndex(NICK));
                Integer puntos = c.getInt(c.getColumnIndex(PUNTOS));
                Integer id = c.getInt(c.getColumnIndex(ID));
                String img = c.getString(c.getColumnIndex(IMG));
                Integer act = c.getInt(c.getColumnIndex(ACTIVO));
                Jugador j = new Jugador(nombre, nick);
                j.setPuntos(puntos);
                j.setId(id);
                j.setImg(img);
                j.setActivo(act);
                jugadores.add(j);
            }
        }

        //Si no hay resultados mostramos al siguiente para informar al usuario
        if (jugadores.size()==0){
            Jugador j = new Jugador("Ningún Jugador encontrado", "");
            j.setId(0);
            jugadores.add(j);
        }

        return jugadores;
    }

    public void borrarJugador(int id){
        String lsql = "DELETE FROM "+DATABASE_TABLE_JUGADOR+" WHERE "+ID+" = "+id+";";
        db.rawQuery(lsql, null).moveToNext();
    }

    public void limpiarJugador(){
        String lsql = "DELETE FROM "+DATABASE_TABLE_JUGADOR+" WHERE "+NOMBRE+" = NULL;";
        db.rawQuery(lsql, null).moveToNext();
    }

    public void actJugador(int pts){
        String lsql = "UPDATE "+DATABASE_TABLE_JUGADOR+" SET "+PUNTOS+"="+pts+" WHERE "+ACTIVO+" = 1;";
        db.execSQL(lsql);
    }

    public void seleccionarJugador(int idSelecc){
        String lsql = "UPDATE "+DATABASE_TABLE_JUGADOR+" SET "+ACTIVO+" = 0;";
        String lsql2 = "UPDATE "+DATABASE_TABLE_JUGADOR+" SET "+ACTIVO+" = 1 WHERE "+ID+"="+idSelecc+";";

        db.execSQL(lsql);
        db.execSQL(lsql2);
    }

    public Boolean esElJugadorActivo(int idJug){

        //Montamos la query para la consulta
        String lsql = "select * from "+DATABASE_TABLE_JUGADOR+" WHERE +"+ID+"="+idJug+" AND "+ACTIVO+"=1;";

        Cursor c = db.rawQuery(lsql, null);

        //Recorremos los resultados y los añadimos al ArrayList
        if (c != null && c.moveToFirst()){
            return true;        }

        return false;
    }


    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_JUGADOR);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_JUGADOR);
            onCreate(db);
        }

    }
}