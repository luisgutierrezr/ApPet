package co.edu.uan.appet.DB.DTOs;

public class EventoDTO {
    private int id;
    private String evento;
    private int tipo;
    private int estado;
    private boolean todoElDia;
    private String fechaYHoraDeInicio;
    private String fechaYHoraDeFin;
    private double lugarLatitud;
    private double lugarLongitud;

    public EventoDTO() {
    }

    public EventoDTO(int id, String evento, int tipo, int estado, boolean todoElDia, String fechaYHoraDeInicio, String fechaYHoraDeFin, double lugarLatitud, double lugarLongitud) {
        this.id = id;
        this.evento = evento;
        this.tipo = tipo;
        this.estado = estado;
        this.todoElDia = todoElDia;
        this.fechaYHoraDeInicio = fechaYHoraDeInicio;
        this.fechaYHoraDeFin = fechaYHoraDeFin;
        this.lugarLatitud = lugarLatitud;
        this.lugarLongitud = lugarLongitud;
    }

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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isTodoElDia() {
        return todoElDia;
    }

    public void setTodoElDia(boolean todoElDia) {
        this.todoElDia = todoElDia;
    }

    public String getFechaYHoraDeInicio() {
        return fechaYHoraDeInicio;
    }

    public void setFechaYHoraDeInicio(String fechaYHoraDeInicio) {
        this.fechaYHoraDeInicio = fechaYHoraDeInicio;
    }

    public String getFechaYHoraDeFin() {
        return fechaYHoraDeFin;
    }

    public void setFechaYHoraDeFin(String fechaYHoraDeFin) {
        this.fechaYHoraDeFin = fechaYHoraDeFin;
    }

    public double getLugarLatitud() {
        return lugarLatitud;
    }

    public void setLugarLatitud(double lugarLatitud) {
        this.lugarLatitud = lugarLatitud;
    }

    public double getLugarLongitud() {
        return lugarLongitud;
    }

    public void setLugarLongitud(double lugarLongitud) {
        this.lugarLongitud = lugarLongitud;
    }

    @Override
    public String toString() {
        return evento;
    }
}
