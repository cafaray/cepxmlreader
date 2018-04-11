package com.ftc.services.payments.model;

import java.util.Date;

public class CEPPago {

    private Date fechaPago;
    private String formaDePago;
    private String moneda;
    private double monto;
    private String rfcEmisorCtaOrd;
    private String ctaOrdenante;
    private String rfcEmisorCtaBen;
    private String ctaBeneficiario;

    private String idDocumento;
    private String folio;
    private String serie;
    private String monedaDR;
    private String metodoDePagoDR;
    private int numParcialidad;
    private double saldoAnt;
    private double pagado;
    private double saldoInsoluto;

    public CEPPago() {
        System.out.println("Se genero el detalle de pago.");
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getRfcEmisorCtaOrd() {
        return rfcEmisorCtaOrd;
    }

    public void setRfcEmisorCtaOrd(String rfcEmisorCtaOrd) {
        this.rfcEmisorCtaOrd = rfcEmisorCtaOrd;
    }

    public String getCtaOrdenante() {
        return ctaOrdenante;
    }

    public void setCtaOrdenante(String ctaOrdenante) {
        this.ctaOrdenante = ctaOrdenante;
    }

    public String getRfcEmisorCtaBen() {
        return rfcEmisorCtaBen;
    }

    public void setRfcEmisorCtaBen(String rfcEmisorCtaBen) {
        this.rfcEmisorCtaBen = rfcEmisorCtaBen;
    }

    public String getCtaBeneficiario() {
        return ctaBeneficiario;
    }

    public void setCtaBeneficiario(String ctaBeneficiario) {
        this.ctaBeneficiario = ctaBeneficiario;
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
        return "CEPPago{" +
                "fechaPago=" + fechaPago +
                ", formaDePago='" + formaDePago + '\'' +
                ", moneda='" + moneda + '\'' +
                ", monto=" + monto +
                ", rfcEmisorCtaOrd='" + rfcEmisorCtaOrd + '\'' +
                ", ctaOrdenante='" + ctaOrdenante + '\'' +
                ", rfcEmisorCtaBen='" + rfcEmisorCtaBen + '\'' +
                ", ctaBeneficiario='" + ctaBeneficiario + '\'' +
                ", idDocumento='" + idDocumento + '\'' +
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
