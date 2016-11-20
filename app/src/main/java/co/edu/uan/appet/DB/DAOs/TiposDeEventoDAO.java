package co.edu.uan.appet.DB.DAOs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.uan.appet.DB.ApPetDB;
import co.edu.uan.appet.DB.DTOs.TipoDeEventoDTO;

public class TiposDeEventoDAO {

    private static ApPetDB apPetDB;
    private static TiposDeEventoDAO tiposDeEventoDAO;

    private static final String NOMBRE_TABLA = "TIPOS_DE_EVENTO";
    private static final String COLUMNA_ID = "ID";
    private static final String COLUMNA_TIPO_DE_EVENTO = "TIPO_DE_EVENTO";

    private static final String SQL_CREAR_TABLA =
            "CREATE TABLE " + getNombreTabla() + "(" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    COLUMNA_TIPO_DE_EVENTO + " VARCHAR(100) NOT NULL" +
                    ")";

    private TiposDeEventoDAO() {
        apPetDB = ApPetDB.getInstance();
    }

    public static TiposDeEventoDAO getInstance() {
        if (tiposDeEventoDAO == null)
            tiposDeEventoDAO = new TiposDeEventoDAO();
        return tiposDeEventoDAO;
    }

    public static String getNombreTabla() {
        return NOMBRE_TABLA;
    }

    public static String getSqlCrearTabla() {
        return SQL_CREAR_TABLA;
    }

    public void addTipoDeEvento(TipoDeEventoDTO tipoDeEventoDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_ID, tipoDeEventoDTO.getId());
            contentValues.put(COLUMNA_TIPO_DE_EVENTO, tipoDeEventoDTO.getTipoDeEvento());
            sqLiteDatabase.insertOrThrow(getNombreTabla(), null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar adicionar tipo de evento a la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public TipoDeEventoDTO getTipoDeEvento(int id) {
        String SQL_SELECT = "SELECT * FROM " + getNombreTabla() + " WHERE " + COLUMNA_ID + " = " + id;
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        TipoDeEventoDTO tipoDeEventoDTO = new TipoDeEventoDTO();
        try {
            if (cursor.moveToFirst()) {
                tipoDeEventoDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                tipoDeEventoDTO.setTipoDeEvento(cursor.getString(cursor.getColumnIndex(COLUMNA_TIPO_DE_EVENTO)));
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer tipo de evento de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return tipoDeEventoDTO;
    }

    public List<TipoDeEventoDTO> getAllTiposDeEvento() {
        List<TipoDeEventoDTO> tipoDeEventoDTOs = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM %s", getNombreTabla());
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    TipoDeEventoDTO tipoDeEventoDTO = new TipoDeEventoDTO();
                    tipoDeEventoDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                    tipoDeEventoDTO.setTipoDeEvento(cursor.getString(cursor.getColumnIndex(COLUMNA_TIPO_DE_EVENTO)));
                    tipoDeEventoDTOs.add(tipoDeEventoDTO);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer tipo de evento de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return tipoDeEventoDTOs;
    }

    public void updateTipoDeEvento(TipoDeEventoDTO tipoDeEventoDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_TIPO_DE_EVENTO, tipoDeEventoDTO.getTipoDeEvento());
            sqLiteDatabase.update(getNombreTabla(), contentValues, COLUMNA_ID + "= ?", new String[]{String.valueOf(tipoDeEventoDTO.getId())});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar modificar tipo de evento de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void deleteTipoDeEvento(int id) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(getNombreTabla(), COLUMNA_ID + "= ?", new String[]{String.valueOf(id)});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar eliminar tipo de evento de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

}