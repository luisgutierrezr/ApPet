package co.edu.uan.appet.DB.DOs;

/**
 * Created by Luis Alberto on 10/09/2016.
 */
public class Raza {
    private int id;
    private Especie especie;
    private String raza;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

}
