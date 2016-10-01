package co.edu.uan.appet.DB.DOs;

import java.util.Date;

/**
 * Created by Luis Alberto on 10/09/2016.
 */
public class EntradaBlog {
    private int id;
    private EntradaBlog padre;
    private Usuario usuario;
    private Date fechaYHora;
    private String entrada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntradaBlog getPadre() {
        return padre;
    }

    public void setPadre(EntradaBlog padre) {
        this.padre = padre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
