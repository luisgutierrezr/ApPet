package co.edu.uan.appet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import co.edu.uan.appet.DB.DAOs.EventosDAO;
import co.edu.uan.appet.DB.DAOs.TiposDeEventoDAO;
import co.edu.uan.appet.DB.DTOs.EventoDTO;
import co.edu.uan.appet.DB.DTOs.TipoDeEventoDTO;

public class Evento extends AppCompatActivity {

    int idEvento;
    int tipoDeEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        actualizarListaTiposDeEvento();
        Intent intent = getIntent();
        idEvento = intent.getExtras().getInt("id");
        cargarEvento(idEvento);
    }

    private void actualizarListaTiposDeEvento() {
        TiposDeEventoDAO tiposDeEventoDAO = TiposDeEventoDAO.getInstance();
        List<TipoDeEventoDTO> tipoDeEventoDTOs = tiposDeEventoDAO.getAllTiposDeEvento();
        ArrayAdapter<TipoDeEventoDTO> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tipoDeEventoDTOs);
        Spinner spTipoDeEvento = (Spinner) findViewById(R.id.spTipoDeEvento);
        if (spTipoDeEvento != null) {
            spTipoDeEvento.setAdapter(arrayAdapter);
            spTipoDeEvento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    TipoDeEventoDTO tipoDeEventoDTO = (TipoDeEventoDTO) adapterView.getSelectedItem();
                    tipoDeEvento = tipoDeEventoDTO.getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Log.i("ApPet", "onNothingSelected");
                }
            });
        }
    }

    private void cargarEvento(int id) {
        EventosDAO eventosDAO = EventosDAO.getInstance();
        EventoDTO eventoDTO = eventosDAO.getEvento(id);
        EditText etEvento = (EditText) findViewById(R.id.etDescripcionEvento);
        if (etEvento != null)
            etEvento.setText(eventoDTO.getEvento());
        Spinner spTipoDeEvento = (Spinner) findViewById(R.id.spTipoDeEvento);
        tipoDeEvento = eventoDTO.getTipo();
        if (spTipoDeEvento != null) {
            Adapter spTipoDeEventodapter = spTipoDeEvento.getAdapter();
            for (int i = 0; i < spTipoDeEventodapter.getCount(); i++) {
                TipoDeEventoDTO tipoDeEventoDTO = (TipoDeEventoDTO) spTipoDeEventodapter.getItem(i);
                if (tipoDeEventoDTO.getId() == eventoDTO.getTipo()) {
                    spTipoDeEvento.setSelection(i);
                    break;
                }
            }
        }
        EditText etFecha = (EditText) findViewById(R.id.etFecha);
        if (etFecha != null)
            etFecha.setText(eventoDTO.getFecha());
        CheckBox cbTodoElDia = (CheckBox) findViewById(R.id.cbTodoElDia);
        if (cbTodoElDia != null)
            cbTodoElDia.setChecked(eventoDTO.isTodoElDia());
        mostrarHorasInicioYFin(!eventoDTO.isTodoElDia());
        EditText etHoraDeInicio = (EditText) findViewById(R.id.etHoraDeInicio);
        if (etHoraDeInicio != null)
            etHoraDeInicio.setText(eventoDTO.getHoraDeInicio());
        EditText etHoraDeFin = (EditText) findViewById(R.id.etHoraDeFin);
        if (etHoraDeFin != null)
            etHoraDeFin.setText(eventoDTO.getHoraDeFin());
        EditText etLugar = (EditText) findViewById(R.id.etLugar);
        String lugar = eventoDTO.getLugarLatitud() + "|" + eventoDTO.getLugarLongitud();
        if (etLugar != null)
            etLugar.setText(lugar);
    }

    public void clickTodoElDia(View view) {
        CheckBox cbTodoElDia = (CheckBox) view;
        mostrarHorasInicioYFin(!cbTodoElDia.isChecked());
    }

    private void mostrarHorasInicioYFin(boolean mostrar) {
        int estado;
        if (mostrar)
            estado = View.VISIBLE;
        else
            estado = View.INVISIBLE;
        EditText etHoraDeInicio = (EditText) findViewById(R.id.etHoraDeInicio);
        if (etHoraDeInicio != null)
            etHoraDeInicio.setVisibility(estado);
        EditText etHoraDeFin = (EditText) findViewById(R.id.etHoraDeFin);
        if (etHoraDeFin != null)
            etHoraDeFin.setVisibility(estado);
    }

    public void clickGuardarCambios(View view) {
        EventosDAO eventosDAO = EventosDAO.getInstance();
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setId(idEvento);
        EditText etEvento = (EditText) findViewById(R.id.etDescripcionEvento);
        if (etEvento != null)
            eventoDTO.setEvento(etEvento.getText().toString());
        else
            eventoDTO.setEvento("");
        eventoDTO.setTipo(tipoDeEvento);
        eventoDTO.setEstado(1);
        CheckBox cbTodoElDia = (CheckBox) findViewById(R.id.cbTodoElDia);
        if (cbTodoElDia != null)
            eventoDTO.setTodoElDia(cbTodoElDia.isChecked());
        else
            eventoDTO.setTodoElDia(false);
        EditText etFecha = (EditText) findViewById(R.id.etFecha);
        if (etFecha != null)
            eventoDTO.setFecha(etFecha.getText().toString());
        else
            eventoDTO.setEvento("");
        EditText etHoraDeInicio = (EditText) findViewById(R.id.etHoraDeInicio);
        if (etHoraDeInicio != null)
            eventoDTO.setHoraDeInicio(etHoraDeInicio.getText().toString());
        else
            eventoDTO.setEvento("");
        EditText etHoraDeFin = (EditText) findViewById(R.id.etHoraDeFin);
        if (etHoraDeFin != null)
            eventoDTO.setHoraDeFin(etHoraDeFin.getText().toString());
        else
            eventoDTO.setEvento("");
        EditText etLugar = (EditText) findViewById(R.id.etLugar);
        eventoDTO.setLugarLatitud(-74.0001);
        eventoDTO.setLugarLongitud(4.0001);
        eventosDAO.updateEvento(eventoDTO);
        finish();
    }

    public void clickCancelar(View view) {
        finish();
    }

    public void clickRemoverEvento(View view) {
        EventosDAO eventosDAO = EventosDAO.getInstance();
        eventosDAO.deleteEvento(idEvento);
        finish();
    }

}