package co.edu.uan.appet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Eventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        ListView list = (ListView) findViewById(R.id.lvEventos);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> list,
                                            View row,
                                            int index,
                                            long rowID) {
                        editarEvento(row);
                    }
                }
        );

    }

    public void editarEvento(View v){
        Intent intent = new Intent(this, Evento.class);
        startActivity(intent);
    }
}
