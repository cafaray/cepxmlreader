package com.ftc.services.payments.model;

import java.util.Date;
import java.util.List;

public class CEPCabecera {

    private static final long serialVersionUID = 8372722219280582677L;

    private String version;
    private String serie;
    private String folio;
    private Date fecha;
    private double subTotal;
    private String moneda;
    private double total;
    private String tipoDeComprobante;
    private String lugarExpedicion;
    private String xmlnsPago10;

    private String rfcEmisor;
    private String nombreEmisor;
    private String regimenFiscalEmisor;

    private String rfcReceptor;
    private String nombreReceptor;
    private String usoCFDIReceptor;

    private String rfcProvCertif;
    private String versionTibreFiscal;
    private String Uuid;
    private Date fechaTimbrado;
    private String noCertificadoSAT;

    private String versionPagos;
    private List<CEPPago> pagos;

    public CEPCabecera() {
        System.out.println("Se genero la cabecera de pago.");
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    public void setTipoDeComprobante(String tipoDeComprobante) {
        this.tipoDeComprobante = tipoDeComprobante;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public String getXmlnsPago10() {
        return xmlnsPago10;
    }

    public void setXmlnsPago10(String xmlnsPago10) {
        this.xmlnsPago10 = xmlnsPago10;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getRegimenFiscalEmisor() {
        return regimenFiscalEmisor;
    }

    public void setRegimenFiscalEmisor(String regimenFiscalEmisor) {
        this.regimenFiscalEmisor = regimenFiscalEmisor;
    }

    public String getRfcReceptor() {
        return rfcReceptor;
    }

    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getUsoCFDIReceptor() {
        return usoCFDIReceptor;
    }

    public void setUsoCFDIReceptor(String usoCFDIReceptor) {
        this.usoCFDIReceptor = usoCFDIReceptor;
    }

    public String getRfcProvCertif() {
        return rfcProvCertif;
    }

    public void setRfcProvCertif(String rfcProvCertif) {
        this.rfcProvCertif = rfcProvCertif;
    }

    public String getVersionTibreFiscal() {
        return versionTibreFiscal;
    }

    public void setVersionTibreFiscal(String versionTibreFiscal) {
        this.versionTibreFiscal = versionTibreFiscal;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public Date getFechaTimbrado() {
        return fechaTimbrado;
    }

    public void setFechaTimbrado(Date fechaTimbrado) {
        this.fechaTimbrado = fechaTimbrado;
    }

    public String getNoCertificadoSAT() {
        return noCertificadoSAT;
    }

    public void setNoCertificadoSAT(String noCertificadoSAT) {
        this.noCertificadoSAT = noCertificadoSAT;
    }

    public String getVersionPagos() {
        return versionPagos;
    }

    public void setVersionPagos(String versionPagos) {
        this.versionPagos = versionPagos;
    }

    public List<CEPPago> getPagos() {
        return pagos;
    }

    public void setPagos(List<CEPPago> pagos) {
        this.pagos = pagos;
    }

    @Override
    public String toString() {
        return "CEPCabecera{" +
                "version='" + version + '\'' +
                ", serie='" + serie + '\'' +
                ", folio='" + folio + '\'' +
                ", fecha=" + fecha +
                ", subTotal=" + subTotal +
                ", moneda='" + moneda + '\'' +
                ", total=" + total +
                ", tipoDeComprobante='" + tipoDeComprobante + '\'' +
                ", lugarExpedicion='" + lugarExpedicion + '\'' +
                ", xmlnsPago10='" + xmlnsPago10 + '\'' +
                ", rfcEmisor='" + rfcEmisor + '\'' +
                ", nombreEmisor='" + nombreEmisor + '\'' +
                ", regimenFiscalEmisor='" + regimenFiscalEmisor + '\'' +
                ", rfcReceptor='" + rfcReceptor + '\'' +
                ", nombreReceptor='" + nombreReceptor + '\'' +
                ", usoCFDIReceptor='" + usoCFDIReceptor + '\'' +
                ", rfcProvCertif='" + rfcProvCertif + '\'' +
                ", versionTibreFiscal='" + versionTibreFiscal + '\'' +
                ", Uuid='" + Uuid + '\'' +
                ", fechaTimbrado=" + fechaTimbrado +
                ", noCertificadoSAT='" + noCertificadoSAT + '\'' +
                ", versionPagos='" + versionPagos + '\'' +
                ", pagos=" + pagos.size() +
                '}';
    }
}
