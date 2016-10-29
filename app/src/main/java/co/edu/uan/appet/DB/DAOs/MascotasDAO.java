package co.edu.uan.appet.DB.DAOs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.uan.appet.DB.ApPetDB;
import co.edu.uan.appet.DB.DTOs.MascotaDTO;

/**
 * Created by Luis Alberto on 08/10/2016.
 */
public class MascotasDAO {

    private static ApPetDB apPetDB;
    private static MascotasDAO mascotasDAO;

    private static final String NOMBRE_TABLA = "MASCOTAS";
    private static final String COLUMNA_ID = "ID";
    private static final String COLUMNA_PROPIETARIO = "PROPIETARIO";
    private static final String COLUMNA_NOMBRE = "NOMBRE";
    private static final String COLUMNA_ESPECIE = "ESPECIE";
    private static final String COLUMNA_RAZA = "RAZA";

    private static final String SQL_CREAR_TABLA =
            "CREATE TABLE " + getNombreTabla() + "(" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    COLUMNA_PROPIETARIO + " INTEGER NOT NULL," +
                    COLUMNA_NOMBRE + " VARCHAR(100) NOT NULL," +
                    COLUMNA_ESPECIE + " INTEGER NOT NULL," +
                    COLUMNA_RAZA + " VARCHAR(100) NOT NULL" +
                    ")";

    private MascotasDAO() {
        apPetDB = ApPetDB.getInstance();
    }

    public static MascotasDAO getInstance() {
        if (mascotasDAO == null)
            mascotasDAO = new MascotasDAO();
        return mascotasDAO;
    }

    public static String getNombreTabla() {
        return NOMBRE_TABLA;
    }

    public static String getSqlCrearTabla() {
        return SQL_CREAR_TABLA;
    }

    public void addMascota(MascotaDTO mascotaDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_ID, mascotaDTO.getId());
            contentValues.put(COLUMNA_PROPIETARIO, mascotaDTO.getPropietario());
            contentValues.put(COLUMNA_NOMBRE, mascotaDTO.getNombre());
            contentValues.put(COLUMNA_ESPECIE, mascotaDTO.getEspecie());
            contentValues.put(COLUMNA_RAZA, mascotaDTO.getRaza());
            sqLiteDatabase.insertOrThrow(getNombreTabla(), null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar adicionar mascota a la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public MascotaDTO getMascota(int id) {
        String SQL_SELECT = "SELECT * FROM " + getNombreTabla() + " WHERE " + COLUMNA_ID + " = " + id;
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        MascotaDTO mascotaDTO = new MascotaDTO();
        try {
            if (cursor.moveToFirst()) {
                mascotaDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                mascotaDTO.setPropietario(cursor.getInt(cursor.getColumnIndex(COLUMNA_PROPIETARIO)));
                mascotaDTO.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNA_NOMBRE)));
                mascotaDTO.setEspecie(cursor.getInt(cursor.getColumnIndex(COLUMNA_ESPECIE)));
                mascotaDTO.setRaza(cursor.getInt(cursor.getColumnIndex(COLUMNA_RAZA)));
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer mascota de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return mascotaDTO;
    }

    public List<MascotaDTO> getAllMascotas() {
        List<MascotaDTO> mascotaDTOs = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM %s", getNombreTabla());
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    MascotaDTO mascotaDTO = new MascotaDTO();
                    mascotaDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                    mascotaDTO.setPropietario(cursor.getInt(cursor.getColumnIndex(COLUMNA_PROPIETARIO)));
                    mascotaDTO.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNA_NOMBRE)));
                    mascotaDTO.setEspecie(cursor.getInt(cursor.getColumnIndex(COLUMNA_ESPECIE)));
                    mascotaDTO.setRaza(cursor.getInt(cursor.getColumnIndex(COLUMNA_RAZA)));
                    mascotaDTOs.add(mascotaDTO);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer mascotas de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return mascotaDTOs;
    }

    public List<MascotaDTO> getMascotasDeUsuario(int usuario) {
        List<MascotaDTO> mascotaDTOs = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM %s WHERE PROPIETARIO = %d", getNombreTabla(), usuario);
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    MascotaDTO mascotaDTO = new MascotaDTO();
                    mascotaDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                    mascotaDTO.setPropietario(cursor.getInt(cursor.getColumnIndex(COLUMNA_PROPIETARIO)));
                    mascotaDTO.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNA_NOMBRE)));
                    mascotaDTO.setEspecie(cursor.getInt(cursor.getColumnIndex(COLUMNA_ESPECIE)));
                    mascotaDTO.setRaza(cursor.getInt(cursor.getColumnIndex(COLUMNA_RAZA)));
                    mascotaDTOs.add(mascotaDTO);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer mascotas de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return mascotaDTOs;
    }

    public void updateMascota(MascotaDTO mascotaDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_PROPIETARIO, mascotaDTO.getPropietario());
            contentValues.put(COLUMNA_NOMBRE, mascotaDTO.getNombre());
            contentValues.put(COLUMNA_ESPECIE, mascotaDTO.getEspecie());
            contentValues.put(COLUMNA_RAZA, mascotaDTO.getRaza());
            sqLiteDatabase.update(getNombreTabla(), contentValues, COLUMNA_ID + "= ?", new String[]{String.valueOf(mascotaDTO.getId())});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar modificar mascota de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void deleteMascota(int id) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(getNombreTabla(), COLUMNA_ID + "= ?", new String[]{String.valueOf(id)});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar eliminar mascota de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

}