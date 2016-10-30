package co.edu.uan.appet.DB.DTOs;

public class ConsecutivoDTO {
    String tabla;
    int consecutivoActual;

    public ConsecutivoDTO() {
    }

    public ConsecutivoDTO(String tabla, int consecutivoActual) {
        this.tabla = tabla;
        this.consecutivoActual = consecutivoActual;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public int getConsecutivoActual() {
        return consecutivoActual;
    }

    public void setConsecutivoActual(int consecutivoActual) {
        this.consecutivoActual = consecutivoActual;
    }
}
