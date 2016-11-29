package co.edu.uan.appet.DB.DAOs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.edu.uan.appet.DB.ApPetDB;
import co.edu.uan.appet.DB.DTOs.UsuarioDTO;

public class UsuariosDAO {

    private static ApPetDB apPetDB;
    private static UsuariosDAO usuariosDAO;

    private static final String NOMBRE_TABLA = "USUARIOS";
    private static final String COLUMNA_ID = "ID";
    private static final String COLUMNA_NOMBRE_COMPLETO = "NOMBRE_COMPLETO";
    private static final String COLUMNA_CORREO_ELECTRONICO = "CORREO_ELECTRONICO";
    private static final String COLUMNA_NUMERO_TELEFONICO = "NUMERO_TELEFONICO";

    private static final String SQL_CREAR_TABLA =
            "CREATE TABLE " + getNombreTabla() + "(" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    COLUMNA_NOMBRE_COMPLETO + " VARCHAR(100) NOT NULL," +
                    COLUMNA_CORREO_ELECTRONICO + " VARCHAR(100)," +
                    COLUMNA_NUMERO_TELEFONICO + " VARCHAR(100)" +
                    ")";

    private UsuariosDAO() {
        apPetDB = ApPetDB.getInstance();
    }

    public static UsuariosDAO getInstance() {
        if (usuariosDAO == null)
            usuariosDAO = new UsuariosDAO();
        return usuariosDAO;
    }

    public static String getNombreTabla() {
        return NOMBRE_TABLA;
    }

    public static String getSqlCrearTabla() {
        return SQL_CREAR_TABLA;
    }

    public void addUsuario(UsuarioDTO usuarioDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_ID, usuarioDTO.getId());
            contentValues.put(COLUMNA_NOMBRE_COMPLETO, usuarioDTO.getNombreCompleto());
            contentValues.put(COLUMNA_CORREO_ELECTRONICO, usuarioDTO.getCorreoElectronico());
            contentValues.put(COLUMNA_NUMERO_TELEFONICO, usuarioDTO.getNumeroTelefonico());
            sqLiteDatabase.insertOrThrow(getNombreTabla(), null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar adicionar usuario a la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public UsuarioDTO getUsuario(int id) {
        String SQL_SELECT = "SELECT * FROM " + getNombreTabla() + " WHERE " + COLUMNA_ID + " = " + id;
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
            if (cursor.moveToFirst()) {
                usuarioDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                usuarioDTO.setNombreCompleto(cursor.getString(cursor.getColumnIndex(COLUMNA_NOMBRE_COMPLETO)));
                usuarioDTO.setCorreoElectronico(cursor.getString(cursor.getColumnIndex(COLUMNA_CORREO_ELECTRONICO)));
                usuarioDTO.setNumeroTelefonico(cursor.getString(cursor.getColumnIndex(COLUMNA_NUMERO_TELEFONICO)));
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer usuario de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return usuarioDTO;
    }

    public void updateUsuario(UsuarioDTO usuarioDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_NOMBRE_COMPLETO, usuarioDTO.getNombreCompleto());
            contentValues.put(COLUMNA_CORREO_ELECTRONICO, usuarioDTO.getCorreoElectronico());
            contentValues.put(COLUMNA_NUMERO_TELEFONICO, usuarioDTO.getNumeroTelefonico());
            sqLiteDatabase.update(getNombreTabla(), contentValues, COLUMNA_ID + "= ?", new String[]{String.valueOf(usuarioDTO.getId())});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar modificar usuario de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void deleteUsuario(int id) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(getNombreTabla(), COLUMNA_ID + "= ?", new String[]{String.valueOf(id)});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar eliminar usuario de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

}