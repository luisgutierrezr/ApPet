package co.edu.uan.appet.DB.DAOs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.edu.uan.appet.DB.ApPetDB;
import co.edu.uan.appet.DB.DTOs.ConsecutivoDTO;

public class ConsecutivosDAO {

    private static ApPetDB apPetDB;
    private static ConsecutivosDAO consecutivosDAO;

    private static final String NOMBRE_TABLA = "CONSECUTIVOS";
    private static final String COLUMNA_TABLA = "TABLA";
    private static final String COLUMNA_CONSECUTIVO_ACTUAL = "CONSECUTIVO_ACTUAL";

    private static final String SQL_CREAR_TABLA =
            "CREATE TABLE " + getNombreTabla() + "(" +
                    COLUMNA_TABLA + " VARCHAR(100) PRIMARY KEY," +
                    COLUMNA_CONSECUTIVO_ACTUAL + " INTEGER NOT NULL" +
                    ")";

    private ConsecutivosDAO() {
        apPetDB = ApPetDB.getInstance();
    }

    public static ConsecutivosDAO getInstance() {
        if (consecutivosDAO == null)
            consecutivosDAO = new ConsecutivosDAO();
        return consecutivosDAO;
    }

    public static String getNombreTabla() {
        return NOMBRE_TABLA;
    }

    public static String getSqlCrearTabla() {
        return SQL_CREAR_TABLA;
    }

    public void addConsecutivo(ConsecutivoDTO consecutivoDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_TABLA, consecutivoDTO.getTabla());
            contentValues.put(COLUMNA_CONSECUTIVO_ACTUAL, consecutivoDTO.getConsecutivoActual());
            sqLiteDatabase.insertOrThrow(getNombreTabla(), null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar adicionar consecutivo a la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public int getConsecutivoParaTabla(String tabla) {
        String SQL_SELECT = "SELECT * FROM " + getNombreTabla() + " WHERE " + COLUMNA_TABLA + " = '" + tabla + "'";
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        ConsecutivoDTO consecutivoDTO = new ConsecutivoDTO();
        try {
            if (cursor.moveToFirst()) {
                consecutivoDTO.setTabla(cursor.getString(cursor.getColumnIndex(COLUMNA_TABLA)));
                consecutivoDTO.setConsecutivoActual(cursor.getInt(cursor.getColumnIndex(COLUMNA_CONSECUTIVO_ACTUAL)));
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer consecutivo de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        consecutivoDTO.setConsecutivoActual(consecutivoDTO.getConsecutivoActual() + 1);
        updateConsecutivo(consecutivoDTO);
        return consecutivoDTO.getConsecutivoActual();
    }

    public void updateConsecutivo(ConsecutivoDTO consecutivoDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_TABLA, consecutivoDTO.getTabla());
            contentValues.put(COLUMNA_CONSECUTIVO_ACTUAL, consecutivoDTO.getConsecutivoActual());
            sqLiteDatabase.update(getNombreTabla(), contentValues, COLUMNA_TABLA + "= ?", new String[]{String.valueOf(consecutivoDTO.getTabla())});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar modificar consecutivo de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void deleteConsecutivo(String tabla) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(getNombreTabla(), COLUMNA_TABLA + "= ?", new String[]{tabla});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar eliminar consecutivo de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

}