package co.edu.uan.appet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import co.edu.uan.appet.DB.ApPetDB;

public class Inicio extends AppCompatActivity {

    private ApPetDB apPetDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        apPetDB = ApPetDB.getDB(this);
        Log.i("ApPetDB", "onCreate de inicio, apPetDB.toString():"+ apPetDB.toString());
    }

    public void click(View v) {
        Class clase = null;
        switch (v.getId()) {
            case R.id.ibUsuario:
                clase = Usuario.class;
                break;
            case R.id.ibMascotas:
                clase = Mascotas.class;
                break;
            case R.id.ibEventos:
                clase = Eventos.class;
                break;
            case R.id.ibMapa:
                clase = Mapa.class;
                break;
            case R.id.ibBlog:
                clase = Blog.class;
                break;
        }
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

}