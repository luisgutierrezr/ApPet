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

    List<EventoDTO> eventoDTOs;
    static final int EDITAR_EVENTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        actualizarListaEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarListaEventos();
    }

    private void actualizarListaEventos() {
        ListView lvEventos = (ListView) findViewById(R.id.lvEventos);
        EventosDAO eventosDAO = EventosDAO.getInstance();
        eventoDTOs = eventosDAO.getAllEventos();
        ArrayAdapter<EventoDTO> arrayAdapter = new ArrayAdapter<EventoDTO>(this, android.R.layout.simple_list_item_1, eventoDTOs);
        lvEventos.setAdapter(arrayAdapter);
        lvEventos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> list,
                                            View row,
                                            int index,
                                            long rowID) {
                        editarEvento(index);
                    }
                }
        );
    }

    public void clickAgregarEvento(View view) {
        Intent intent = new Intent(this, Evento.class);
        intent.putExtra("id", -1);
        startActivity(intent);
    }

    public void editarEvento(int index) {
        Intent intent = new Intent(this, Evento.class);
        EventoDTO eventoDTO = eventoDTOs.get(index);
        intent.putExtra("id", eventoDTO.getId());
        //startActivity(intent);
        startActivityForResult(intent, EDITAR_EVENTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) switch (requestCode) {
            case EDITAR_EVENTO:
                actualizarListaEventos();
                break;
        }
    }



}
