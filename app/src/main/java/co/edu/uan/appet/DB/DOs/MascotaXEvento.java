package co.edu.uan.appet.DB.DOs;

/**
 * Created by Luis Alberto on 10/09/2016.
 */
public class MascotaXEvento {
    private Mascota mascota;
    private Evento evento;
    private EstadoDeEvento estado;

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public EstadoDeEvento getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeEvento estado) {
        this.estado = estado;
    }
}
