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
    private CEPPagoDocumento documentoRelacionado;

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

    public CEPPagoDocumento getDocumentoRelacionado() {
        return documentoRelacionado;
    }

    public void setDocumentoRelacionado(CEPPagoDocumento documentoRelacionado) {
        this.documentoRelacionado = documentoRelacionado;
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
                ", documentoRelacionado='" + documentoRelacionado + '\'' +
                '}';
    }

}
