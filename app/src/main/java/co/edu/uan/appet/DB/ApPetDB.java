package co.edu.uan.appet.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import co.edu.uan.appet.DB.DOs.EntradaBlog;
import co.edu.uan.appet.DB.DOs.Especie;
import co.edu.uan.appet.DB.DOs.EstadoDeEvento;
import co.edu.uan.appet.DB.DOs.Evento;
import co.edu.uan.appet.DB.DOs.Mascota;
import co.edu.uan.appet.DB.DOs.MascotaXEvento;
import co.edu.uan.appet.DB.DOs.Raza;
import co.edu.uan.appet.DB.DOs.TipoDeEvento;
import co.edu.uan.appet.DB.DOs.Usuario;

/**
 * Created by Luis Alberto on 07/09/2016.
 */
public class ApPetDB extends SQLiteOpenHelper {

    private static ApPetDB apPetDB;

    List<Usuario> usuarioList;
    List<Especie> especieList;
    List<Mascota> mascotaList;
    List<MascotaXEvento> mascotaXEventoList;
    List<EstadoDeEvento> estadoDeEventoList;
    List<Evento> eventoList;
    List<EntradaBlog> entradaBlogList;
    List<Raza> razaList;
    List<TipoDeEvento> tipoDeEventoList;

    private static final String NOMBRE_BASE_DE_DATOS = "ApPetDB";
    private static final String SQL_CREAR_TABLA_USUARIOS =
            "CREATE TABLE USUARIOS(" +
                    "ID INTEGER PRIMARY KEY," +
                    "NOMBRE_COMPLETO TEXT NOT NULL," +
                    "CORREO_ELECTRONICO TEXT," +
                    "NUMERO_TELEFONICO TEXT" +
                    ")";

    public static ApPetDB getDB(Context context) {
        if (apPetDB == null)
            apPetDB = new ApPetDB(context);
        return apPetDB;
    }

    ApPetDB(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, 1);
        Log.i("ApPetDB", "ApPetDB(Context context)");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREAR_TABLA_USUARIOS);
        Log.i("ApPetDB", "onCreate(SQLiteDatabase sqLiteDatabase)");
        Log.i("ApPetDB", SQL_CREAR_TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
