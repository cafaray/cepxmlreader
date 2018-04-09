package com.ftc.services.payments.model;

public class Cep {

    public final static String IMPUESTO_IVA = "IVA";
    public final static String IMPUESTO_IVA_CODIGO = "002";
    public final static String IMPUESTO_IEPS = "IEPS";

    private static final long serialVersionUID = 8372722219280582677L;

    private String serie;
    private String folio;
    private String strFecha;
    private String formaDePago;
    private String strSubTotal;
    private double subTotal;
    private String strDescuento;
    private double descuento;
    private String TipoCambio;
    private String moneda;
    private String strTotal;
    private double total;
    private String metodoDePago;
    private String lugarExpedicion;
    private String rfc;
    private String nombre;
    private String strTotalImpuestosTrasladados;
    private double totalImpuestosTrasladados;
    private String uuid;
    private String strFechaTimbrado;
    private String rfcReceptor;
    private String nombreReceptor;

    private String iva_strTasa;
    private double iva_tasa;
    private String iva_strImporte;
    private double iva_importe;

    private String ieps_strTasa = "0";
    private double ieps_tasa = 0;
    private String ieps_strImporte = "0";
    private double ieps_importe = 0;


    public Cep() {
        System.out.println("Se genero la cabecera de factura.");
    }

    public Cep(String serie, String folio, String fecha, String rfc, String nombre, String formaDePago){
        this.serie = serie;
        this.folio = folio;
        this.strFecha = fecha;
        this.rfc = rfc;
        this.nombre = nombre;
        this.formaDePago = formaDePago;
    }

    public Cep(String serie, String folio, String fecha, String rfc, String nombre, String formaDePago, String uuid, String fechaTimbrado){
        this.serie = serie;
        this.folio = folio;
        this.strFecha = fecha;
        this.rfc = rfc;
        this.nombre = nombre;
        this.formaDePago = formaDePago;
        this.setUuid(uuid);
        this.setStrFechaTimbrado(fechaTimbrado);
    }

    public void asignaValores(String subTotal, String descuento, String total, String impuestosTrasladados){
        this.setStrSubTotal(subTotal);
        this.setStrDescuento(descuento);
        this.setStrTotal(total);
        this.setStrTotalImpuestosTrasladados(impuestosTrasladados);
    }

    public void asignaValores(double subTotal, double descuento, double total, double impuestosTrasladados){
        this.setSubTotal(subTotal);
        this.setDescuento(descuento);
        this.setTotal(total);
        this.setTotalImpuestosTrasladados(impuestosTrasladados);
    }

    public void asignaAdicionales(String formaDePago, String metodoDePago, String tipoCambio, String moneda, String lugarExpedicion){
        this.setFormaDePago(formaDePago);
        this.setMetodoDePago(metodoDePago);
        this.setTipoCambio(tipoCambio);
        this.setMoneda(moneda);
        this.setLugarExpedicion(lugarExpedicion);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public String getStrSubTotal() {
        return strSubTotal;
    }

    public void setStrSubTotal(String strSubTotal) {
        try{
            this.subTotal = Double.parseDouble(strSubTotal);
            this.strSubTotal = strSubTotal;
        }catch(NumberFormatException e){
            this.subTotal = 0;
            this.strSubTotal = "0";
            System.out.println("El valor asignado a subtotal no parece ser un numero ["+strSubTotal+"].");
        }
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
        this.strSubTotal = String.valueOf(subTotal);
    }

    public String getStrDescuento() {
        return strDescuento;
    }

    public void setStrDescuento(String strDescuento) {
        try{
            this.descuento = Double.parseDouble(strDescuento);
            this.strDescuento = strDescuento;
        }catch (NumberFormatException e){
            this.descuento = 0;
            this.strDescuento = "0";
            System.out.println("El valor asignado a descuento no parece ser un numero ["+strDescuento+"].");
        }    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
        this.strDescuento=String.valueOf(descuento);
    }

    public String getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        TipoCambio = tipoCambio;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getStrTotal() {
        return strTotal;
    }

    public void setStrTotal(String strTotal) {
        try{
            this.total = Double.parseDouble(strTotal);
            this.strTotal = strTotal;
        }catch(NumberFormatException e){
            this.total = 0;
            this.strTotal = "0";
            System.out.println("El valor asignado a total no parece ser un numero ["+strTotal+"].");
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
        this.strTotal = String.valueOf(total);
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStrTotalImpuestosTrasladados() {
        return strTotalImpuestosTrasladados;
    }

    public void setStrTotalImpuestosTrasladados(String strTotalImpuestosTrasladados) {
        try{
            this.totalImpuestosTrasladados = Double.parseDouble(strTotalImpuestosTrasladados);
            this.strTotalImpuestosTrasladados = strTotalImpuestosTrasladados;
        }catch(NumberFormatException e){
            this.strTotalImpuestosTrasladados = "0";
            this.totalImpuestosTrasladados = 0;
            System.out.println("El valor asignado a total de impuestos trasladados no parece ser un numero ["+strTotalImpuestosTrasladados+"].");
        }    }

    public double getTotalImpuestosTrasladados() {
        return totalImpuestosTrasladados;
    }

    public void setTotalImpuestosTrasladados(double totalImpuestosTrasladados) {
        this.totalImpuestosTrasladados = totalImpuestosTrasladados;
        this.strTotalImpuestosTrasladados = String.valueOf(totalImpuestosTrasladados);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStrFechaTimbrado() {
        return strFechaTimbrado;
    }

    public void setStrFechaTimbrado(String strFechaTimbrado) {
        this.strFechaTimbrado = strFechaTimbrado;
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

    public String getIva_strTasa() {
        return iva_strTasa;
    }

    public void setIva_strTasa(String iva_strTasa) {
        try{
            this.iva_tasa = Double.parseDouble(iva_strTasa);
            this.iva_strTasa = iva_strTasa;
        }catch(NumberFormatException e){
            this.iva_tasa = 0;
            this.iva_strTasa = "0";
            System.out.println("El valor asignado a tasa IVA no parece ser un numero ["+iva_strTasa+"].");
        }
    }

    public double getIva_tasa() {
        return iva_tasa;
    }

    public void setIva_tasa(double iva_tasa) {
        this.iva_tasa = iva_tasa;
        this.iva_strTasa = String.valueOf(iva_tasa);
    }

    public String getIva_strImporte() {
        return iva_strImporte;
    }

    public void setIva_strImporte(String iva_strImporte) {
        try{
            this.iva_importe = Double.parseDouble(iva_strImporte);
            this.iva_strImporte = iva_strImporte;
        }catch(NumberFormatException e){
            this.iva_importe = 0;
            this.iva_strImporte = "0";
            System.out.println("El valor asignado a importe IVA no parece ser un numero ["+iva_strImporte+"].");
        }
    }

    public double getIva_importe() {
        return iva_importe;
    }

    public void setIva_importe(double iva_importe) {
        this.iva_importe = iva_importe;
        this.iva_strImporte = String.valueOf(iva_importe);
    }

    public String getIeps_strTasa() {
        return ieps_strTasa;
    }

    public void setIeps_strTasa(String ieps_strTasa) {
        try{
            this.ieps_tasa = Double.parseDouble(ieps_strTasa);
            this.ieps_strTasa = ieps_strTasa;
        }catch(NumberFormatException e){
            this.ieps_tasa = 0;
            this.ieps_strTasa = "0";
            System.out.println("El valor asignado a tasa IEPS no parece ser un numero ["+ieps_strTasa+"].");
        }
    }

    public double getIeps_tasa() {
        return ieps_tasa;
    }

    public void setIeps_tasa(double ieps_tasa) {
        this.ieps_tasa = ieps_tasa;
        this.ieps_strTasa = String.valueOf(ieps_tasa);
    }

    public String getIeps_strImporte() {
        return ieps_strImporte;
    }

    public void setIeps_strImporte(String ieps_strImporte) {
        try{
            this.ieps_importe = Double.parseDouble(ieps_strImporte);
            this.ieps_strImporte = ieps_strImporte;
        }catch(NumberFormatException e){
            this.ieps_importe = 0;
            this.ieps_strImporte = "0";
            System.out.println("El valor asignado a importe IEPS no parece ser un numero ["+ieps_strImporte+"].");
        }
    }

    public double getIeps_importe() {
        return ieps_importe;
    }

    public void setIeps_importe(double ieps_importe) {
        this.ieps_importe = ieps_importe;
        this.ieps_strImporte = String.valueOf(ieps_importe);
    }

    public static String titulosCommaSeparateValues(){
        StringBuilder builder = new StringBuilder();
        builder.append("\"").append("RFC").append("\",");
        builder.append("\"").append("Nombre").append("\",");
        builder.append("\"").append("Serie").append("\",");
        builder.append("\"").append("Folio").append("\",");
        builder.append("\"").append("Fecha").append("\",");
        builder.append("\"").append("Lugar de expedicion").append("\",");
        builder.append("\"").append("UUID").append("\",");
        builder.append("\"").append("Fecha de timbrado").append("\",");
        builder.append("\"").append("Forma de pago").append("\",");
        builder.append("\"").append("Moneda").append("\",");
        builder.append("\"").append("RFC-Receptor").append("\",");
        builder.append("\"").append("Receptor").append("\",");
        builder.append("\"").append("Subtotal").append("\",");
        builder.append("\"").append("Descuento").append("\",");
        builder.append("\"").append("Impuestos trasladados").append("\",");
        builder.append("\"").append("Tasa IVA").append("\",");
        builder.append("\"").append("IVA").append("\",");
        builder.append("\"").append("Tasa IEPS").append("\",");
        builder.append("\"").append("IEPS").append("\",");
        builder.append("\"").append("Total").append("\"");
        return builder.toString();
    }

    public String toCommaSeparateValues(){
        StringBuilder builder = new StringBuilder();
        builder.append("\"").append(rfc).append("\",");
        builder.append("\"").append(nombre).append("\",");
        builder.append("\"").append(serie).append("\",");
        builder.append("\"").append(folio).append("\",");
        builder.append("\"").append(strFecha).append("\",");
        builder.append("\"").append(lugarExpedicion).append("\",");
        builder.append("\"").append(uuid).append("\",");
        builder.append("\"").append(strFechaTimbrado).append("\",");
        builder.append("\"").append(formaDePago).append("\",");
        builder.append("\"").append(moneda).append("\",");
        builder.append("\"").append(rfcReceptor).append("\",");
        builder.append("\"").append(nombreReceptor).append("\",");
        builder.append(subTotal).append(",");
        builder.append(descuento).append(",");
        builder.append(totalImpuestosTrasladados).append(",");
        builder.append(iva_tasa).append(",");
        builder.append(iva_importe).append(",");
        builder.append(ieps_tasa).append(",");
        builder.append(ieps_importe).append(",");
        builder.append(total);
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cabecera [serie=");
        builder.append(serie);
        builder.append(", folio=");
        builder.append(folio);
        builder.append(", strFecha=");
        builder.append(strFecha);
        builder.append(", formaDePago=");
        builder.append(formaDePago);
        builder.append(", subTotal=");
        builder.append(subTotal);
        builder.append(", descuento=");
        builder.append(descuento);
        builder.append(", TipoCambio=");
        builder.append(TipoCambio);
        builder.append(", moneda=");
        builder.append(moneda);
        builder.append(", total=");
        builder.append(total);
        builder.append(", metodoDePago=");
        builder.append(metodoDePago);
        builder.append(", lugarExpedicion=");
        builder.append(lugarExpedicion);
        builder.append(", rfc=");
        builder.append(rfc);
        builder.append(", nombre=");
        builder.append(nombre);
        builder.append(", uuid=");
        builder.append(uuid);
        builder.append(", fechaTimbrado=");
        builder.append(strFechaTimbrado);
        builder.append(", totalImpuestosTrasladados=");
        builder.append(totalImpuestosTrasladados);
        builder.append("]");
        return builder.toString();
    }
}
