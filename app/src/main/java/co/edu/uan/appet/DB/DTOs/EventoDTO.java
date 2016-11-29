package co.edu.uan.appet.DB.DTOs;

public class EventoDTO {
    private int id;
    private String evento;
    private int tipo;
    private int estado;
    private boolean todoElDia;
    private String fecha;
    private String horaDeInicio;
    private String horaDeFin;
    private double lugarLatitud;
    private double lugarLongitud;

    public EventoDTO() {
    }

    public EventoDTO(int id, String evento, int tipo, int estado, boolean todoElDia, String fecha, String horaDeInicio, String horaDeFin, double lugarLatitud, double lugarLongitud) {
        this.id = id;
        this.evento = evento;
        this.tipo = tipo;
        this.estado = estado;
        this.todoElDia = todoElDia;
        this.fecha = fecha;
        this.horaDeInicio = horaDeInicio;
        this.horaDeFin = horaDeFin;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraDeInicio() {
        return horaDeInicio;
    }

    public void setHoraDeInicio(String horaDeInicio) {
        this.horaDeInicio = horaDeInicio;
    }

    public String getHoraDeFin() {
        return horaDeFin;
    }

    public void setHoraDeFin(String horaDeFin) {
        this.horaDeFin = horaDeFin;
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
