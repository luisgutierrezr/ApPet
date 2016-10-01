package co.edu.uan.appet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Mascotas extends AppCompatActivity {

    static final int TOMAR_FOTO = 1;
    static final int CARGAR_ARCHIVO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        actualizarListaRazas();
        actualizarListaMascotas();
    }

    public void clickCamara(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TOMAR_FOTO);
    }

    public void clickSeleccionarArchivo(View v) {
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

    public void clickGuardarCambios(View v) {
        Toast.makeText(this, "Se guardarán los cambios...", Toast.LENGTH_LONG).show();
    }

    public void clickCancelar(View v) {
        finish();
    }

    private void actualizarListaRazas() {
        Spinner dropdown = (Spinner) findViewById(R.id.spRaza);
        String[] items = new String[]{"<Seleccionar>", "Angora", "Persa", "Siamés", "Criollo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    private void actualizarListaMascotas() {
        Spinner dropdown = (Spinner) findViewById(R.id.spMascotas);
        String[] items = new String[]{"Mascota 1", "Mascota 2", "Mascota 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

}
