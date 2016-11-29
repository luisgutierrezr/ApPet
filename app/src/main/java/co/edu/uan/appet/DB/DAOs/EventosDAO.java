package co.edu.uan.appet.DB.DAOs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.uan.appet.DB.ApPetDB;
import co.edu.uan.appet.DB.DTOs.EventoDTO;

public class EventosDAO {

    private static ApPetDB apPetDB;
    private static EventosDAO eventosDAO;

    private static final String NOMBRE_TABLA = "EVENTOS";
    private static final String COLUMNA_ID = "ID";
    private static final String COLUMNA_EVENTO = "EVENTO";
    private static final String COLUMNA_TIPO = "TIPO";
    private static final String COLUMNA_ESTADO = "ESTADO";
    private static final String COLUMNA_TODO_EL_DIA = "TODO_EL_DIA";
    private static final String COLUMNA_FECHA = "FECHA";
    private static final String COLUMNA_HORA_DE_INICIO = "HORA_DE_INICIO";
    private static final String COLUMNA_HORA_DE_FIN = "HORA_DE_FIN";
    private static final String COLUMNA_LUGAR_LATITUD = "LUGAR_LATITUD";
    private static final String COLUMNA_LUGAR_LONGITUD = "LUGAR_LONGITUD";

    private static final String SQL_CREAR_TABLA =
            "CREATE TABLE " + getNombreTabla() + "(" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    COLUMNA_EVENTO + " VARCHAR(100) NOT NULL," +
                    COLUMNA_TIPO + " INTEGER NOT NULL," +
                    COLUMNA_ESTADO + " INTEGER NOT NULL," +
                    COLUMNA_TODO_EL_DIA + " INTEGER NOT NULL," +
                    COLUMNA_FECHA + " STRING," +
                    COLUMNA_HORA_DE_INICIO + " STRING," +
                    COLUMNA_HORA_DE_FIN + " STRING," +
                    COLUMNA_LUGAR_LATITUD + " REAL," +
                    COLUMNA_LUGAR_LONGITUD + " REAL" +
                    ")";

    private EventosDAO() {
        apPetDB = ApPetDB.getInstance();
    }

    public static EventosDAO getInstance() {
        if (eventosDAO == null)
            eventosDAO = new EventosDAO();
        return eventosDAO;
    }

    public static String getNombreTabla() {
        return NOMBRE_TABLA;
    }

    public static String getSqlCrearTabla() {
        return SQL_CREAR_TABLA;
    }

    public static String getColumnaId() {
        return COLUMNA_ID;
    }

    public static String getColumnaEvento() {
        return COLUMNA_EVENTO;
    }

    public static String getColumnaTipo() {
        return COLUMNA_TIPO;
    }

    public static String getColumnaEstado() {
        return COLUMNA_ESTADO;
    }

    public static String getColumnaTodoElDia() {
        return COLUMNA_TODO_EL_DIA;
    }

    public static String getColumnaFecha() {
        return COLUMNA_FECHA;
    }

    public static String getColumnaHoraDeInicio() {
        return COLUMNA_HORA_DE_INICIO;
    }

    public static String getColumnaHoraDeFin() {
        return COLUMNA_HORA_DE_FIN;
    }

    public static String getColumnaLugarLatitud() {
        return COLUMNA_LUGAR_LATITUD;
    }

    public static String getColumnaLugarLongitud() {
        return COLUMNA_LUGAR_LONGITUD;
    }

    public void addEvento(EventoDTO eventoDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_ID, eventoDTO.getId());
            contentValues.put(COLUMNA_EVENTO, eventoDTO.getEvento());
            contentValues.put(COLUMNA_TIPO, eventoDTO.getTipo());
            contentValues.put(COLUMNA_ESTADO, eventoDTO.getEstado());
            contentValues.put(COLUMNA_TODO_EL_DIA, eventoDTO.isTodoElDia() ? 1 : 0);
            contentValues.put(COLUMNA_FECHA, eventoDTO.getFecha());
            contentValues.put(COLUMNA_HORA_DE_INICIO, eventoDTO.getHoraDeInicio());
            contentValues.put(COLUMNA_HORA_DE_FIN, eventoDTO.getHoraDeFin());
            contentValues.put(COLUMNA_LUGAR_LATITUD, eventoDTO.getLugarLatitud());
            contentValues.put(COLUMNA_LUGAR_LONGITUD, eventoDTO.getLugarLongitud());
            sqLiteDatabase.insertOrThrow(getNombreTabla(), null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar adicionar evento a la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public EventoDTO getEvento(int id) {
        String SQL_SELECT = "SELECT * FROM " + getNombreTabla() + " WHERE " + COLUMNA_ID + " = " + id;
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        EventoDTO eventoDTO = new EventoDTO();
        try {
            if (cursor.moveToFirst()) {
                eventoDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                eventoDTO.setEvento(cursor.getString(cursor.getColumnIndex(COLUMNA_EVENTO)));
                eventoDTO.setTipo(cursor.getInt(cursor.getColumnIndex(COLUMNA_TIPO)));
                eventoDTO.setEstado(cursor.getInt(cursor.getColumnIndex(COLUMNA_ESTADO)));
                eventoDTO.setTodoElDia(cursor.getInt(cursor.getColumnIndex(COLUMNA_TODO_EL_DIA)) == 1);
                eventoDTO.setFecha(cursor.getString(cursor.getColumnIndex(COLUMNA_FECHA)));
                eventoDTO.setHoraDeInicio(cursor.getString(cursor.getColumnIndex(COLUMNA_HORA_DE_INICIO)));
                eventoDTO.setHoraDeFin(cursor.getString(cursor.getColumnIndex(COLUMNA_HORA_DE_FIN)));
                eventoDTO.setLugarLatitud(cursor.getDouble(cursor.getColumnIndex(COLUMNA_LUGAR_LATITUD)));
                eventoDTO.setLugarLongitud(cursor.getDouble(cursor.getColumnIndex(COLUMNA_LUGAR_LONGITUD)));
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer evento de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return eventoDTO;
    }

    public List<EventoDTO> getAllEventos() {
        List<EventoDTO> eventoDTOs = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM %s", getNombreTabla());
        SQLiteDatabase sqLiteDatabase = apPetDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    EventoDTO eventoDTO = new EventoDTO();
                    eventoDTO.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_ID)));
                    eventoDTO.setEvento(cursor.getString(cursor.getColumnIndex(COLUMNA_EVENTO)));
                    eventoDTO.setTipo(cursor.getInt(cursor.getColumnIndex(COLUMNA_TIPO)));
                    eventoDTO.setEstado(cursor.getInt(cursor.getColumnIndex(COLUMNA_ESTADO)));
                    eventoDTO.setTodoElDia(cursor.getInt(cursor.getColumnIndex(COLUMNA_TODO_EL_DIA)) == 1);
                    eventoDTO.setFecha(cursor.getString(cursor.getColumnIndex(COLUMNA_FECHA)));
                    eventoDTO.setHoraDeInicio(cursor.getString(cursor.getColumnIndex(COLUMNA_HORA_DE_INICIO)));
                    eventoDTO.setHoraDeFin(cursor.getString(cursor.getColumnIndex(COLUMNA_HORA_DE_FIN)));
                    eventoDTO.setLugarLatitud(cursor.getDouble(cursor.getColumnIndex(COLUMNA_LUGAR_LATITUD)));
                    eventoDTO.setLugarLongitud(cursor.getDouble(cursor.getColumnIndex(COLUMNA_LUGAR_LONGITUD)));
                    eventoDTOs.add(eventoDTO);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar leer eventos de la base de datos", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return eventoDTOs;
    }

    public void updateEvento(EventoDTO eventoDTO) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_EVENTO, eventoDTO.getEvento());
            contentValues.put(COLUMNA_TIPO, eventoDTO.getTipo());
            contentValues.put(COLUMNA_ESTADO, eventoDTO.getEstado());
            contentValues.put(COLUMNA_TODO_EL_DIA, eventoDTO.isTodoElDia() ? 1 : 0);
            contentValues.put(COLUMNA_FECHA, eventoDTO.getFecha());
            contentValues.put(COLUMNA_HORA_DE_INICIO, eventoDTO.getHoraDeInicio());
            contentValues.put(COLUMNA_HORA_DE_FIN, eventoDTO.getHoraDeFin());
            contentValues.put(COLUMNA_LUGAR_LATITUD, eventoDTO.getLugarLatitud());
            contentValues.put(COLUMNA_LUGAR_LONGITUD, eventoDTO.getLugarLongitud());
            sqLiteDatabase.update(getNombreTabla(), contentValues, COLUMNA_ID + "= ?", new String[]{String.valueOf(eventoDTO.getId())});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar modificar evento de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void deleteEvento(int id) {
        SQLiteDatabase sqLiteDatabase = apPetDB.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(getNombreTabla(), COLUMNA_ID + "= ?", new String[]{String.valueOf(id)});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ApPet", "Error al intentar eliminar evento de la base de datos", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

}