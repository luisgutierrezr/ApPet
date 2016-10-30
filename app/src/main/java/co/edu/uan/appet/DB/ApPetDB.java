package co.edu.uan.appet.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.util.Log;

import java.util.List;

import co.edu.uan.appet.DB.DAOs.ConsecutivosDAO;
import co.edu.uan.appet.DB.DAOs.EspeciesDAO;
import co.edu.uan.appet.DB.DAOs.MascotasDAO;
import co.edu.uan.appet.DB.DAOs.RazasDAO;
import co.edu.uan.appet.DB.DAOs.UsuariosDAO;
import co.edu.uan.appet.DB.DTOs.ConsecutivoDTO;
import co.edu.uan.appet.DB.DTOs.EspecieDTO;
import co.edu.uan.appet.DB.DTOs.MascotaDTO;
import co.edu.uan.appet.DB.DTOs.RazaDTO;
import co.edu.uan.appet.DB.DTOs.UsuarioDTO;

public class ApPetDB extends SQLiteOpenHelper {

    private static ApPetDB apPetDB;

    private static final String NOMBRE_BASE_DE_DATOS = "ApPetDB.db";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public static ApPetDB getInstance(Context context) {
        if (apPetDB == null)
            apPetDB = new ApPetDB(context);
        return apPetDB;
    }

    public static ApPetDB getInstance() {
        return apPetDB;
    }

    private ApPetDB(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConsecutivosDAO.getNombreTabla());
        sqLiteDatabase.execSQL(ConsecutivosDAO.getSqlCrearTabla());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UsuariosDAO.getNombreTabla());
        sqLiteDatabase.execSQL(UsuariosDAO.getSqlCrearTabla());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EspeciesDAO.getNombreTabla());
        sqLiteDatabase.execSQL(EspeciesDAO.getSqlCrearTabla());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RazasDAO.getNombreTabla());
        sqLiteDatabase.execSQL(RazasDAO.getSqlCrearTabla());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MascotasDAO.getNombreTabla());
        sqLiteDatabase.execSQL(MascotasDAO.getSqlCrearTabla());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                agregarConsecutivos();
                agregarUsuarioInicial();
                agregarEspecies();
                agregarRazas();
                agregarMascotaInicial();
                //test();
            }
        }, 500);
    }

    private void agregarConsecutivos() {
        ConsecutivosDAO consecutivosDAO = ConsecutivosDAO.getInstance();
        consecutivosDAO.addConsecutivo(new ConsecutivoDTO(MascotasDAO.getNombreTabla(), 0));
    }

    private void agregarUsuarioInicial() {
        UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1);
        usuarioDTO.setNombreCompleto("Nombre del usuario");
        usuarioDTO.setCorreoElectronico("usuario@ejemplo.com");
        usuarioDTO.setNumeroTelefonico("000-000-0000");
        usuariosDAO.addUsuario(usuarioDTO);
    }

    private void agregarEspecies() {
        EspeciesDAO especiesDAO = EspeciesDAO.getInstance();
        especiesDAO.addEspecie(new EspecieDTO(0, "<Seleccionar>"));
        especiesDAO.addEspecie(new EspecieDTO(1, "Gato"));
        especiesDAO.addEspecie(new EspecieDTO(2, "Perro"));
        especiesDAO.addEspecie(new EspecieDTO(3, "Ave"));
        especiesDAO.addEspecie(new EspecieDTO(4, "Hamster"));
        especiesDAO.addEspecie(new EspecieDTO(5, "Conejo"));
        especiesDAO.addEspecie(new EspecieDTO(6, "Tortuga"));
        especiesDAO.addEspecie(new EspecieDTO(7, "Pez"));
        especiesDAO.addEspecie(new EspecieDTO(8, "Otro"));
    }

    private void agregarRazas() {
        RazasDAO razasDAO = RazasDAO.getInstance();
        razasDAO.addRaza(new RazaDTO(0, 0, "<Seleccionar>"));
        razasDAO.addRaza(new RazaDTO(1, 1, "<Seleccionar>"));
        razasDAO.addRaza(new RazaDTO(2, 1, "Angora"));
        razasDAO.addRaza(new RazaDTO(3, 1, "Criollo"));
        razasDAO.addRaza(new RazaDTO(4, 1, "Himalayo"));
        razasDAO.addRaza(new RazaDTO(5, 1, "Persa"));
        razasDAO.addRaza(new RazaDTO(6, 1, "Ragdoll"));
        razasDAO.addRaza(new RazaDTO(7, 1, "Siamés"));
        razasDAO.addRaza(new RazaDTO(8, 1, "Sphinx"));
        razasDAO.addRaza(new RazaDTO(9, 1, "Otro"));
        razasDAO.addRaza(new RazaDTO(10, 2, "<Seleccionar>"));
        razasDAO.addRaza(new RazaDTO(11, 2, "Beagle"));
        razasDAO.addRaza(new RazaDTO(12, 2, "Boxer"));
        razasDAO.addRaza(new RazaDTO(13, 2, "Chihuahua"));
        razasDAO.addRaza(new RazaDTO(14, 2, "Cocker Spaniel"));
        razasDAO.addRaza(new RazaDTO(15, 2, "Collie"));
        razasDAO.addRaza(new RazaDTO(16, 2, "Criollo"));
        razasDAO.addRaza(new RazaDTO(17, 2, "Dálmata"));
        razasDAO.addRaza(new RazaDTO(18, 2, "Golden Retriever"));
        razasDAO.addRaza(new RazaDTO(19, 2, "Labrador"));
        razasDAO.addRaza(new RazaDTO(20, 2, "Schnauzer"));
        razasDAO.addRaza(new RazaDTO(21, 2, "Otro"));
        razasDAO.addRaza(new RazaDTO(22, 3, "<Seleccionar>"));
        razasDAO.addRaza(new RazaDTO(23, 3, "Cacatúa"));
        razasDAO.addRaza(new RazaDTO(24, 3, "Canario"));
        razasDAO.addRaza(new RazaDTO(25, 3, "Jilguero"));
        razasDAO.addRaza(new RazaDTO(26, 3, "Loro"));
        razasDAO.addRaza(new RazaDTO(27, 3, "Periquito"));
        razasDAO.addRaza(new RazaDTO(28, 3, "Otro"));
        razasDAO.addRaza(new RazaDTO(29, 4, "<Seleccionar>"));
        razasDAO.addRaza(new RazaDTO(30, 4, "Campbell"));
        razasDAO.addRaza(new RazaDTO(31, 4, "Chino"));
        razasDAO.addRaza(new RazaDTO(32, 4, "Común"));
        razasDAO.addRaza(new RazaDTO(33, 4, "Ruso"));
        razasDAO.addRaza(new RazaDTO(34, 4, "Otro"));
        razasDAO.addRaza(new RazaDTO(35, 5, "<Seleccionar>"));
        razasDAO.addRaza(new RazaDTO(36, 5, "Californiano"));
        razasDAO.addRaza(new RazaDTO(37, 5, "Chinchilla"));
        razasDAO.addRaza(new RazaDTO(38, 5, "Ruso"));
        razasDAO.addRaza(new RazaDTO(39, 5, "Otro"));
        razasDAO.addRaza(new RazaDTO(40, 6, "<Seleccionar>"));
        razasDAO.addRaza(new RazaDTO(41, 6, "Argentina"));
        razasDAO.addRaza(new RazaDTO(42, 6, "Cabeza amarilla"));
        razasDAO.addRaza(new RazaDTO(43, 6, "Mediterránea"));
        razasDAO.addRaza(new RazaDTO(44, 6, "Norteamericana"));
        razasDAO.addRaza(new RazaDTO(45, 6, "Sudamericana"));
        razasDAO.addRaza(new RazaDTO(46, 6, "Otro"));
        razasDAO.addRaza(new RazaDTO(47, 7, "<Seleccionar>"));
        razasDAO.addRaza(new RazaDTO(48, 7, "Arcoiris"));
        razasDAO.addRaza(new RazaDTO(49, 7, "Bailarinas"));
        razasDAO.addRaza(new RazaDTO(50, 7, "Barbo"));
        razasDAO.addRaza(new RazaDTO(51, 7, "Beta"));
        razasDAO.addRaza(new RazaDTO(52, 7, "Cíclido"));
        razasDAO.addRaza(new RazaDTO(53, 7, "Goldfish"));
        razasDAO.addRaza(new RazaDTO(54, 7, "Otro"));
        razasDAO.addRaza(new RazaDTO(55, 8, "Otro"));
    }

    private void agregarMascotaInicial() {
        MascotasDAO mascotasDAO = MascotasDAO.getInstance();
        MascotaDTO mascotaDTO = new MascotaDTO();
        ConsecutivosDAO consecutivosDAO = ConsecutivosDAO.getInstance();
        mascotaDTO.setId(consecutivosDAO.getConsecutivoParaTabla(MascotasDAO.getNombreTabla()));
        mascotaDTO.setPropietario(1);
        mascotaDTO.setNombre("Nombre de la mascota");
        mascotaDTO.setEspecie(0);
        mascotaDTO.setRaza(0);
        mascotasDAO.addMascota(mascotaDTO);
    }

    private void test() {
        UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
        UsuarioDTO usuarioDTO = usuariosDAO.getUsuario(1);
        Log.i("ApPet", usuarioDTO.getId() + " | " + usuarioDTO.getNombreCompleto() + " | " + usuarioDTO.getCorreoElectronico() + " | " + usuarioDTO.getNumeroTelefonico());
        EspeciesDAO especiesDAO = EspeciesDAO.getInstance();
        List<EspecieDTO> especieDTOs = especiesDAO.getAllEspecies();
        for (EspecieDTO especieDTO : especieDTOs) {
            Log.i("ApPet", especieDTO.getId() + " | " + especieDTO.getEspecie());
        }
        RazasDAO razasDAO = RazasDAO.getInstance();
        List<RazaDTO> razaDTOs = razasDAO.getAllRazas();
        for (RazaDTO razaDTO : razaDTOs) {
            Log.i("ApPet", razaDTO.getId() + " | " + razaDTO.getEspecie() + " | " + razaDTO.getRaza());
        }
        MascotasDAO mascotaDAO = MascotasDAO.getInstance();
        MascotaDTO mascotaDTO = mascotaDAO.getMascota(1);
        Log.i("ApPet", mascotaDTO.getId() + " | " + mascotaDTO.getPropietario() + " | " + mascotaDTO.getNombre() + " | " + mascotaDTO.getEspecie() + " | " + mascotaDTO.getRaza());
    }

    @Override
    public void onConfigure(SQLiteDatabase sqLiteDatabase) {
        super.onConfigure(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

}