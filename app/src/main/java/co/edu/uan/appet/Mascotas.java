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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        actualizarListaMascotas();
        actualizarListaEspecies();
        especieEnListaRazas = -1;
//        actualizarListaRazas(0);
//        idMascota = 1;
//        cargarMascota();
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
                    spRaza.setSelection(2);
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
        Spinner dropdown = (Spinner) findViewById(R.id.spMascotas);
        String[] items = new String[]{"Mascota 1", "Mascota 2", "Mascota 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    private void actualizarListaEspecies() {
        EspeciesDAO especiesDAO = EspeciesDAO.getInstance();
        List<EspecieDTO> especieDTOs = especiesDAO.getAllEspecies();
        ArrayAdapter<EspecieDTO> arrayAdapter = new ArrayAdapter<EspecieDTO>(this, android.R.layout.simple_spinner_dropdown_item, especieDTOs);
        Spinner spEspecie = (Spinner) findViewById(R.id.spEspecie);
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
                                            }

        );
    }

    private void actualizarListaRazas(int especie) {
        if (especie != especieEnListaRazas) {
            RazasDAO razasDAO = RazasDAO.getInstance();
            List<RazaDTO> razaDTOs = razasDAO.getRazasDeEspecie(especie);
            ArrayAdapter<RazaDTO> arrayAdapter = new ArrayAdapter<RazaDTO>(this, android.R.layout.simple_spinner_dropdown_item, razaDTOs);
            Spinner spRaza = (Spinner) findViewById(R.id.spRaza);
            spRaza.setAdapter(arrayAdapter);
            especieEnListaRazas = especie;
        }
    }

    public void clickGuardarCambios(View view) {
        MascotasDAO mascotaDAO = MascotasDAO.getInstance();
        MascotaDTO mascotaDTO = new MascotaDTO();
        mascotaDTO.setId(idMascota);
        mascotaDTO.setPropietario(1);
        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        if (etNombre != null) {
            mascotaDTO.setNombre(etNombre.getText().toString());
        }
/*        Spinner spEspecie = (Spinner) findViewById(R.id.spEspecie);
        if (spEspecie != null) {
            AdapterView spEspecieAdapter = (AdapterView)spEspecie.getAdapter();
            //EspecieDTO especieDTO = (EspecieDTO) spEspecieAdapter.getSelectedItem();
            int id1=(int) spEspecieAdapter.getSelectedItemId();
            Log.i("ApPet",""+ id1);
        }
        Spinner spRaza = (Spinner) findViewById(R.id.spRaza);
        if (spRaza != null) {

        }*/
        //mascotaDAO.updateMascota(mascotaDTO);
        //finish();
    }

    public void clickCancelar(View view) {
        finish();
    }

    public void clickAgregarOtraMascota(View view) {
    }

    public void clickRemoverMascota(View view) {
    }

}
