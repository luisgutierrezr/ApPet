package co.edu.uan.appet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Usuario extends AppCompatActivity {

    static final int TOMAR_FOTO = 1;
    static final int CARGAR_ARCHIVO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
    }

    public void clickCamara(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TOMAR_FOTO);
    }

    public void clickSeleccionarArchivo(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, CARGAR_ARCHIVO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TOMAR_FOTO:
                    Bundle extras = data.getExtras();
                    Bitmap bm = (Bitmap) extras.get("data");
                    ImageView iv = (ImageView) findViewById(R.id.ivUsuario);
                    iv.setImageBitmap(bm);
                    break;
                case CARGAR_ARCHIVO:
                    Toast.makeText(this, "Se cargará el archivo...", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    public void clickGuardarCambios(View v){
        Toast.makeText(this, "Se guardarán los cambios...", Toast.LENGTH_LONG).show();
    }

    public void clickCancelar(View v){
        finish();
    }

}