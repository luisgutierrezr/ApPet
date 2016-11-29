package co.edu.uan.appet.DB.DTOs;

public class TipoDeEventoDTO {
    private int id;
    private String tipoDeEvento;

    public TipoDeEventoDTO(int id, String tipoDeEvento) {
        this.id = id;
        this.tipoDeEvento = tipoDeEvento;
    }

    public TipoDeEventoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDeEvento() {
        return tipoDeEvento;
    }

    public void setTipoDeEvento(String tipoDeEvento) {
        this.tipoDeEvento = tipoDeEvento;
    }

    @Override
    public String toString() {
        return tipoDeEvento;
    }
}
