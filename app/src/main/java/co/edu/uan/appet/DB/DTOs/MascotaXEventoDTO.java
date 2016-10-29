package co.edu.uan.appet.DB.DTOs;

/**
 * Created by Luis Alberto on 10/09/2016.
 */
public class MascotaXEventoDTO {
    private MascotaDTO mascota;
    private EventoDTO evento;
    private EstadoDeEventoDTO estado;

    public MascotaDTO getMascota() {
        return mascota;
    }

    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }

    public EventoDTO getEvento() {
        return evento;
    }

    public void setEvento(EventoDTO evento) {
        this.evento = evento;
    }

    public EstadoDeEventoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeEventoDTO estado) {
        this.estado = estado;
    }
}
