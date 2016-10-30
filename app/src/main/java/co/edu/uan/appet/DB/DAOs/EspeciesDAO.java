package co.edu.uan.appet.DB.DAOs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.uan.appet.DB.ApPetDB;
import co.edu.uan.appet.DB.DTOs.EspecieDTO;

public class EspeciesDAO {

    private static ApPetDB apPetDB;
    private static EspeciesDAO especiesDAO;

    private static final String NOMBRE_TABLA = "ESPECIES";
    private static final String COLUMNA_ID = "ID";
    private static final String COLUMNA_ESPECIE = "ESPECIE";

    private static final String SQL_CREAR_TABLA =
            "CREATE TABLE " + getNombreTabla() + "(" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    COLUMNA_ESPECIE + " VARCHAR(100) NOT NULL" +
                    ")";

    private EspeciesDAO() {
        apPetDB = ApPetDB.getInstance();
    }

    public static EspeciesDAO getInstance() {
        if (especiesDAO == null)
            especiesDAO = new EspeciesDAO();
        return especiesDAO;
    }

    public static String getNombreTabla() {
        return NOMBRE_TABLA;
    }

    public static String getSqlCrearTabla() {
        return SQL_CREAR_TABLA;
    }

    public void addEspecie(EspecieDTO especieDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_ID, especieDTO.getId());
            contentValues.put(COLUMNA_ESPECIE, especieDTO.getEspecie());
            sqLiteDatabase.insertOrThrow(getNombreTabla(), null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar adicionar especie a la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public EspecieDTO getEspecie(int id) {
        String SQL_SELECT = "SELECT * FROM " + getNombreTabla() + " WHERE " + COLUMNA_ID + " = " + id;
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        EspecieDTO especieDTO = new EspecieDTO();
        try {
            if (cursor.moveToFirst()) {
                especieDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                especieDTO.setEspecie(cursor.getString(cursor.getColumnIndex(COLUMNA_ESPECIE)));
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer especie de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return especieDTO;
    }

    public List<EspecieDTO> getAllEspecies() {
        List<EspecieDTO> especieDTOs = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM %s", getNombreTabla());
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    EspecieDTO especieDTO = new EspecieDTO();
                    especieDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                    especieDTO.setEspecie(cursor.getString(cursor.getColumnIndex(COLUMNA_ESPECIE)));
                    especieDTOs.add(especieDTO);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer especies de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return especieDTOs;
    }

    public void updateEspecie(EspecieDTO especieDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_ESPECIE, especieDTO.getEspecie());
            sqLiteDatabase.update(getNombreTabla(), contentValues, COLUMNA_ID + "= ?", new String[]{String.valueOf(especieDTO.getId())});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar modificar especie de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void deleteEspecie(int id) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(getNombreTabla(), COLUMNA_ID + "= ?", new String[]{String.valueOf(id)});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar eliminar especie de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

}