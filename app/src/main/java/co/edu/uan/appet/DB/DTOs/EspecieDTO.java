package co.edu.uan.appet.DB.DTOs;

/**
 * Created by Luis Alberto on 10/09/2016.
 */
public class EspecieDTO {
    private int id;
    private String especie;

    public EspecieDTO() {
    }

    public EspecieDTO(int id, String especie) {
        this.id = id;
        this.especie = especie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return especie;
    }
}
