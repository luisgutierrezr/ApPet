package co.edu.uan.appet.DB.DAOs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.uan.appet.DB.ApPetDB;
import co.edu.uan.appet.DB.DTOs.RazaDTO;

/**
 * Created by Luis Alberto on 08/10/2016.
 */
public class RazasDAO {

    private static ApPetDB apPetDB;
    private static RazasDAO razasDAO;

    private static final String NOMBRE_TABLA = "RAZAS";
    private static final String COLUMNA_ID = "ID";
    private static final String COLUMNA_ESPECIE = "ESPECIE";
    private static final String COLUMNA_RAZA = "RAZA";

    private static final String SQL_CREAR_TABLA =
            "CREATE TABLE " + getNombreTabla() + "(" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    COLUMNA_ESPECIE + " INTEGER NOT NULL," +
                    COLUMNA_RAZA + " VARCHAR(100) NOT NULL" +
                    ")";

    private RazasDAO() {
        apPetDB = ApPetDB.getInstance();
    }

    public static RazasDAO getInstance() {
        if (razasDAO == null)
            razasDAO = new RazasDAO();
        return razasDAO;
    }

    public static String getNombreTabla() {
        return NOMBRE_TABLA;
    }

    public static String getSqlCrearTabla() {
        return SQL_CREAR_TABLA;
    }

    public void addRaza(RazaDTO razaDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_ID, razaDTO.getId());
            contentValues.put(COLUMNA_ESPECIE, razaDTO.getEspecie());
            contentValues.put(COLUMNA_RAZA, razaDTO.getRaza());
            sqLiteDatabase.insertOrThrow(getNombreTabla(), null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar adicionar raza a la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public RazaDTO getRaza(int id) {
        String SQL_SELECT = "SELECT * FROM " + getNombreTabla() + " WHERE " + COLUMNA_ID + " = " + id;
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        RazaDTO razaDTO = new RazaDTO();
        try {
            if (cursor.moveToFirst()) {
                razaDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                razaDTO.setEspecie(cursor.getInt(cursor.getColumnIndex(COLUMNA_ESPECIE)));
                razaDTO.setRaza(cursor.getString(cursor.getColumnIndex(COLUMNA_RAZA)));
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer raza de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return razaDTO;
    }

    public List<RazaDTO> getAllRazas() {
        List<RazaDTO> razaDTOs = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM %s", getNombreTabla());
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    RazaDTO razaDTO = new RazaDTO();
                    razaDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                    razaDTO.setEspecie(cursor.getInt(cursor.getColumnIndex(COLUMNA_ESPECIE)));
                    razaDTO.setRaza(cursor.getString(cursor.getColumnIndex(COLUMNA_RAZA)));
                    razaDTOs.add(razaDTO);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer razas de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return razaDTOs;
    }

    public List<RazaDTO> getRazasDeEspecie(int especie) {
        List<RazaDTO> razaDTOs = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM %s WHERE ESPECIE = %d", getNombreTabla(), especie);
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    RazaDTO razaDTO = new RazaDTO();
                    razaDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                    razaDTO.setEspecie(cursor.getInt(cursor.getColumnIndex(COLUMNA_ESPECIE)));
                    razaDTO.setRaza(cursor.getString(cursor.getColumnIndex(COLUMNA_RAZA)));
                    razaDTOs.add(razaDTO);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer razas de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return razaDTOs;
    }

    public void updateRaza(RazaDTO razaDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_ESPECIE, razaDTO.getEspecie());
            contentValues.put(COLUMNA_RAZA, razaDTO.getRaza());
            sqLiteDatabase.update(getNombreTabla(), contentValues, COLUMNA_ID + "= ?", new String[]{String.valueOf(razaDTO.getId())});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar modificar raza de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void deleteRaza(int id) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(getNombreTabla(), COLUMNA_ID + "= ?", new String[]{String.valueOf(id)});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar eliminar raza de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

}