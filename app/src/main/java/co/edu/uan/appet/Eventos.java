package co.edu.uan.appet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import co.edu.uan.appet.DB.DAOs.EventosDAO;
import co.edu.uan.appet.DB.DTOs.EventoDTO;

public class Eventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        ListView lvEventos = (ListView) findViewById(R.id.lvEventos);
        EventosDAO eventosDAO = EventosDAO.getInstance();
        eventosDAO.deleteEvento(3);
        List<EventoDTO> eventoDTOs = eventosDAO.getAllEventos();
        ArrayAdapter<EventoDTO> arrayAdapter = new ArrayAdapter<EventoDTO>(this, android.R.layout.simple_list_item_1, eventoDTOs);
        lvEventos.setAdapter(arrayAdapter);
        lvEventos.setOnItemClickListener(
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

    public void editarEvento(View view) {
        Intent intent = new Intent(this, Evento.class);
        startActivity(intent);
    }
}
