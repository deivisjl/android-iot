package com.umg.iot.models;

public class Temperature {
    private String Id;
    private String Fecha;
    private String Temperatura;
    private String Humedad;
    private int Estado;
    private String Hora;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String temperatura) {
        Temperatura = temperatura;
    }

    public String getHumedad() {
        return Humedad;
    }

    public void setHumedad(String humedad) {
        Humedad = humedad;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }
}
