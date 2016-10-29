package co.edu.uan.appet.DB.DTOs;

import java.util.Date;

/**
 * Created by Luis Alberto on 10/09/2016.
 */
public class EntradaBlogDTO {
    private int id;
    private EntradaBlogDTO padre;
    private UsuarioDTO usuarioDTO;
    private Date fechaYHora;
    private String entrada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntradaBlogDTO getPadre() {
        return padre;
    }

    public void setPadre(EntradaBlogDTO padre) {
        this.padre = padre;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public Date getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(Date fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }
}
