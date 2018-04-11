package com.ftc.services.payments.model;

public class CEPPagoDocumento {

    private int partida;
    private String idDocumento;
    private String folio;
    private String serie;
    private String monedaDR;
    private String metodoDePagoDR;
    private int numParcialidad;
    private double saldoAnt;
    private double pagado;
    private double saldoInsoluto;


    public int getPartida() {
        return partida;
    }

    public void setPartida(int partida) {
        this.partida = partida;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMonedaDR() {
        return monedaDR;
    }

    public void setMonedaDR(String monedaDR) {
        this.monedaDR = monedaDR;
    }

    public String getMetodoDePagoDR() {
        return metodoDePagoDR;
    }

    public void setMetodoDePagoDR(String metodoDePagoDR) {
        this.metodoDePagoDR = metodoDePagoDR;
    }

    public int getNumParcialidad() {
        return numParcialidad;
    }

    public void setNumParcialidad(int numParcialidad) {
        this.numParcialidad = numParcialidad;
    }

    public double getSaldoAnt() {
        return saldoAnt;
    }

    public void setSaldoAnt(double saldoAnt) {
        this.saldoAnt = saldoAnt;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public double getSaldoInsoluto() {
        return saldoInsoluto;
    }

    public void setSaldoInsoluto(double saldoInsoluto) {
        this.saldoInsoluto = saldoInsoluto;
    }

    @Override
    public String toString() {
        return "CEPPagoDocumento{" +
                "partida='" + partida + '\'' +
                "idDocumento='" + idDocumento + '\'' +
                ", folio='" + folio + '\'' +
                ", serie='" + serie + '\'' +
                ", monedaDR='" + monedaDR + '\'' +
                ", metodoDePagoDR='" + metodoDePagoDR + '\'' +
                ", numParcialidad=" + numParcialidad +
                ", saldoAnt=" + saldoAnt +
                ", pagado=" + pagado +
                ", saldoInsoluto=" + saldoInsoluto +
                '}';
    }
}
