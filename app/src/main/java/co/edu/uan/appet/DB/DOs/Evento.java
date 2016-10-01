package co.edu.uan.appet.DB.DOs;

import android.location.Location;

import java.util.Date;

/**
 * Created by Luis Alberto on 10/09/2016.
 */
public class Evento {
    private int id;
    private String evento;
    private TipoDeEvento tipo;
    private EstadoDeEvento estado;
    private boolean todoElDia;
    private Date fechaYHoraDeInicio;
    private Date fechaYHoraDeFin;
    private Location lugar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public TipoDeEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeEvento tipo) {
        this.tipo = tipo;
    }

    public EstadoDeEvento getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeEvento estado) {
        this.estado = estado;
    }

    public boolean isTodoElDia() {
        return todoElDia;
    }

    public void setTodoElDia(boolean todoElDia) {
        this.todoElDia = todoElDia;
    }

    public Date getFechaYHoraDeInicio() {
        return fechaYHoraDeInicio;
    }

    public void setFechaYHoraDeInicio(Date fechaYHoraDeInicio) {
        this.fechaYHoraDeInicio = fechaYHoraDeInicio;
    }

    public Date getFechaYHoraDeFin() {
        return fechaYHoraDeFin;
    }

    public void setFechaYHoraDeFin(Date fechaYHoraDeFin) {
        this.fechaYHoraDeFin = fechaYHoraDeFin;
    }

    public Location getLugar() {
        return lugar;
    }

    public void setLugar(Location lugar) {
        this.lugar = lugar;
    }
}
