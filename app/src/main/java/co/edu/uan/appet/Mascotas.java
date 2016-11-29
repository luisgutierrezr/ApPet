package co.edu.uan.appet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import co.edu.uan.appet.DB.DAOs.ConsecutivosDAO;
import co.edu.uan.appet.DB.DAOs.EspeciesDAO;
import co.edu.uan.appet.DB.DAOs.MascotasDAO;
import co.edu.uan.appet.DB.DAOs.RazasDAO;
import co.edu.uan.appet.DB.DTOs.EspecieDTO;
import co.edu.uan.appet.DB.DTOs.MascotaDTO;
import co.edu.uan.appet.DB.DTOs.RazaDTO;

public class Mascotas extends AppCompatActivity {

    static final int TOMAR_FOTO = 1;
    static final int CARGAR_ARCHIVO = 2;
    private int idMascota;
    private int especieEnListaRazas;
    private int razaEnListaRazas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        actualizarListaMascotas();
        actualizarListaEspecies();
        especieEnListaRazas = -1;
        actualizarListaRazas(0);
        idMascota = 1;
        cargarMascota();
    }

    private void cargarMascota() {
        MascotasDAO mascotasDAO = MascotasDAO.getInstance();
        MascotaDTO mascotaDTO = mascotasDAO.getMascota(idMascota);
        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        if (etNombre != null) {
            etNombre.setText(mascotaDTO.getNombre());
        }
        Spinner spEspecie = (Spinner) findViewById(R.id.spEspecie);
        if (spEspecie != null) {
            Adapter spEspecieAdapter = spEspecie.getAdapter();
            for (int i = 0; i < spEspecieAdapter.getCount(); i++) {
                EspecieDTO especieDTO = (EspecieDTO) spEspecieAdapter.getItem(i);
                if (especieDTO.getId() == mascotaDTO.getEspecie()) {
                    spEspecie.setSelection(i);
                    break;
                }
            }
        }
        actualizarListaRazas(mascotaDTO.getEspecie());
        Spinner spRaza = (Spinner) findViewById(R.id.spRaza);
        if (spRaza != null) {
            Adapter spRazaAdapter = spRaza.getAdapter();
            for (int j = 0; j < spRazaAdapter.getCount(); j++) {
                RazaDTO razaDTO = (RazaDTO) spRazaAdapter.getItem(j);
                if (razaDTO.getId() == mascotaDTO.getRaza()) {
                    spRaza.setSelection(j);
                    break;
                }
            }
        }
    }

    public void clickCamara(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TOMAR_FOTO);
    }

    public void clickSeleccionarArchivo(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, CARGAR_ARCHIVO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) switch (requestCode) {
            case TOMAR_FOTO:
                ((ImageView) findViewById(R.id.ivMascota)).setImageBitmap((Bitmap) data.getExtras().get("data"));
                break;
            case CARGAR_ARCHIVO:
                Toast.makeText(this, "Se cargará el archivo...", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void actualizarListaMascotas() {
        MascotasDAO mascotasDAO = MascotasDAO.getInstance();
        List<MascotaDTO> mascotaDTOs = mascotasDAO.getAllMascotas();
        ArrayAdapter<MascotaDTO> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mascotaDTOs);
        Spinner spMascotas = (Spinner) findViewById(R.id.spMascotas);
        if (spMascotas != null) {
            spMascotas.setAdapter(arrayAdapter);
            spMascotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                     @Override
                                                     public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                         MascotaDTO mascotaDTO = (MascotaDTO) adapterView.getSelectedItem();
                                                         if (idMascota != mascotaDTO.getId()) {
                                                             idMascota = mascotaDTO.getId();
                                                             cargarMascota();
                                                         }
                                                     }

                                                     @Override
                                                     public void onNothingSelected(AdapterView<?> adapterView) {
                                                         Log.i("ApPet", "onNothingSelected");
                                                     }
                                                 }
            );
        }
    }

    private void actualizarListaEspecies() {
        EspeciesDAO especiesDAO = EspeciesDAO.getInstance();
        List<EspecieDTO> especieDTOs = especiesDAO.getAllEspecies();
        ArrayAdapter<EspecieDTO> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, especieDTOs);
        Spinner spEspecie = (Spinner) findViewById(R.id.spEspecie);
        if (spEspecie != null) {
            spEspecie.setAdapter(arrayAdapter);
            spEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    EspecieDTO especieDTO = (EspecieDTO) adapterView.getSelectedItem();
                    actualizarListaRazas(especieDTO.getId());
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Log.i("ApPet", "onNothingSelected");
                }
            });
        }
    }

    private void actualizarListaRazas(int especie) {
        if (especie != especieEnListaRazas) {
            RazasDAO razasDAO = RazasDAO.getInstance();
            List<RazaDTO> razaDTOs = razasDAO.getRazasDeEspecie(especie);
            ArrayAdapter<RazaDTO> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, razaDTOs);
            Spinner spRaza = (Spinner) findViewById(R.id.spRaza);
            if (spRaza != null) {
                spRaza.setAdapter(arrayAdapter);
                especieEnListaRazas = especie;
                spRaza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        RazaDTO razaDTO = (RazaDTO) adapterView.getSelectedItem();
                        razaEnListaRazas = razaDTO.getId();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Log.i("ApPet", "onNothingSelected");
                    }
                });
            }
        }
    }

    public void clickGuardarCambios(View view) {
        guardarCambios();
        finish();
    }

    private void guardarCambios() {
        MascotasDAO mascotasDAO = MascotasDAO.getInstance();
        MascotaDTO mascotaDTO = new MascotaDTO();
        mascotaDTO.setId(idMascota);
        mascotaDTO.setPropietario(1);
        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        if (etNombre != null) {
            mascotaDTO.setNombre(etNombre.getText().toString());
        } else {
            mascotaDTO.setNombre("");
        }
        mascotaDTO.setEspecie(especieEnListaRazas);
        mascotaDTO.setRaza(razaEnListaRazas);
        mascotasDAO.updateMascota(mascotaDTO);
    }

    public void clickCancelar(View view) {
        finish();
    }

    public void clickAgregarOtraMascota(View view) {
        guardarCambios();
        MascotasDAO mascotasDAO = MascotasDAO.getInstance();
        MascotaDTO mascotaDTO = new MascotaDTO();
        ConsecutivosDAO consecutivosDAO = ConsecutivosDAO.getInstance();
        idMascota = consecutivosDAO.getConsecutivoParaTabla(MascotasDAO.getNombreTabla());
        mascotaDTO.setId(idMascota);
        mascotaDTO.setPropietario(1);
        mascotaDTO.setNombre("Nombre de la mascota (" + idMascota + ")");
        mascotaDTO.setEspecie(0);
        mascotaDTO.setRaza(0);
        mascotasDAO.addMascota(mascotaDTO);
        cargarMascota();
        actualizarListaMascotas();
    }

    public void clickRemoverMascota(View view) {
        MascotasDAO mascotasDAO = MascotasDAO.getInstance();
        mascotasDAO.deleteMascota(idMascota);
        finish();
    }

}