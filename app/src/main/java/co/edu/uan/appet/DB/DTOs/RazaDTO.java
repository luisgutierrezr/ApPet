package co.edu.uan.appet.DB.DTOs;

/**
 * Created by Luis Alberto on 10/09/2016.
 */
public class RazaDTO {
    private int id;
    private int especie;
    private String raza;

    public RazaDTO() {
    }

    public RazaDTO(int id, int especie, String raza) {
        this.id = id;
        this.especie = especie;
        this.raza = raza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEspecie() {
        return especie;
    }

    public void setEspecie(int especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Override
    public String toString() {
        return raza;
    }

}
