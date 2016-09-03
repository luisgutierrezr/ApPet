package co.edu.uan.appet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }
    public void click(View v){
        Class clase=null;
        switch (v.getId()){
            case R.id.ibUsuario:
                clase=Usuario.class;
                break;
        }
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

    public void clickUsuario(View v){
        Intent intent = new Intent(this, Usuario.class);
        startActivity(intent);
    }
    public void clickMascotas(View v){
        Intent intent = new Intent(this, Mascotas.class);
        startActivity(intent);
    }
    public void clickEventos(View v){
        Intent intent = new Intent(this, Eventos.class);
        startActivity(intent);
    }
    public void clickMapa(View v){
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }
    public void clickBlog(View v){
        Intent intent = new Intent(this, Blog.class);
        startActivity(intent);
    }
}
