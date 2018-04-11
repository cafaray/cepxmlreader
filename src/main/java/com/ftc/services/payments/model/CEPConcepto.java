package com.ftc.services.payments.model;

public class CEPConcepto {

    private String claveProdServ;
    private int cantidad;
    private String claveUnidad;
    private String descripcion;
    private double valorUnitario;
    private double importe;

    public CEPConcepto(){
        System.out.println("Concepto de pago CEP creado");
    }

    public String getClaveProdServ() {
        return claveProdServ;
    }

    public void setClaveProdServ(String claveProdServ) {
        this.claveProdServ = claveProdServ;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "CEPConcepto{" +
                "claveProdServ='" + claveProdServ + '\'' +
                ", cantidad=" + cantidad +
                ", claveUnidad='" + claveUnidad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", importe=" + importe +
                '}';
    }
}
