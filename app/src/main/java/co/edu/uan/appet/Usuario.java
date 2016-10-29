package co.edu.uan.appet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import co.edu.uan.appet.DB.DAOs.UsuariosDAO;
import co.edu.uan.appet.DB.DTOs.UsuarioDTO;

public class Usuario extends AppCompatActivity {

    static final int TOMAR_FOTO = 1;
    static final int CARGAR_ARCHIVO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        cargarUsuario();
    }

    private void cargarUsuario() {
        UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
        UsuarioDTO usuarioDTO = usuariosDAO.getUsuario(1);
        EditText etNombreCompleto = (EditText) findViewById(R.id.etNombreCompleto);
        if (etNombreCompleto != null) {
            etNombreCompleto.setText(usuarioDTO.getNombreCompleto());
        }
        EditText etCorreoElectronico = (EditText) findViewById(R.id.etCorreoElectronico);
        if (etCorreoElectronico != null) {
            etCorreoElectronico.setText(usuarioDTO.getCorreoElectronico());
        }
        EditText etNumeroTelefonico = (EditText) findViewById(R.id.etNumeroTelefonico);
        if (etNumeroTelefonico != null) {
            etNumeroTelefonico.setText(usuarioDTO.getNumeroTelefonico());
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
                ((ImageView) findViewById(R.id.ivUsuario)).setImageBitmap((Bitmap) data.getExtras().get("data"));
                break;
            case CARGAR_ARCHIVO:
                Toast.makeText(this, "Se cargará el archivo...", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void clickGuardarCambios(View view) {
        UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1);
        EditText etNombreCompleto = (EditText) findViewById(R.id.etNombreCompleto);
        if (etNombreCompleto != null) {
            usuarioDTO.setNombreCompleto(etNombreCompleto.getText().toString());
        }
        EditText etCorreoElectronico = (EditText) findViewById(R.id.etCorreoElectronico);
        if (etCorreoElectronico != null) {
            usuarioDTO.setCorreoElectronico(etCorreoElectronico.getText().toString());
        }
        EditText etNumeroTelefonico = (EditText) findViewById(R.id.etNumeroTelefonico);
        if (etNumeroTelefonico != null) {
            usuarioDTO.setNumeroTelefonico(etNumeroTelefonico.getText().toString());
        }
        usuariosDAO.updateUsuario(usuarioDTO);
        finish();
    }

    public void clickCancelar(View view) {
        finish();
    }

}