package com.ftc.services.payments;

import com.ftc.services.payments.model.Cep;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {

    private static final String XML_ATRIBUTO_VERSION = "Version";
    private static final String VERSION_33 = "3.3";


    public static void main(String args[]){
        String folderIn = "/tmp/";
        String folderOut = folderIn;
        String yfileName = "hashPipe";
        try {
            if (args.length > 0) {
                for (String arg : args) {
                    if (arg.startsWith("--dir=")) {
                        folderIn = arg.substring(arg.indexOf("=") + 1);
                        System.out.println("Estableciendo folder de trabajo en: " + folderIn);
                    } else if (arg.startsWith("--file=")) {
                        yfileName = arg.substring(arg.indexOf("=") + 1);
                        System.out.println("Estableciendo nombre de archivo como: " + yfileName);
                    } else {
                        System.out.printf("No se reconoce el argumento %s. Usa --dir=[directorio] o --file=[nombre archivo]");
                        System.exit(1);
                    }
                }
            }
            folderOut = folderIn;

            File folder = new File(folderIn);
            System.out.println("La ruta al archivo " + folderIn + " existe: " + folder.exists());
            if (folder.isDirectory()) {
                FilenameFilter filter = new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".xml");
                    }
                };
                File[] xfiles = folder.listFiles(filter);
                List<Cep> registros = new ArrayList<Cep>();
                for (File xfile : xfiles) {
                    Cep cabecera = procesaXML(xfile.getAbsolutePath());
                    registros.add(cabecera);
                }

                int resultado = escribeSalida(folderOut, yfileName, registros);
                if (resultado < 0) {
                    System.out.println("Hubo un error durante la escritura de los registros. Revise el log de salida.");
                    System.exit(1);
                }

            } else {
                System.out.println("La ruta especificada no es un directorio valido [" + folderIn + "]");
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (ParserConfigurationException e) {
            e.printStackTrace(System.out);
        } catch (SAXException e) {
            e.printStackTrace(System.out);
        }
    }

    private static int escribeSalida(String folder, String yfileName, List<Cep> registros)
            throws IOException {
        File file = new File(folder);
        int iregistros = 0;
        if (file.exists()) {
            yfileName += ".csv";
            File yfile = new File(new String(folder + yfileName));
            if (yfile.exists()) {
                System.out.printf("El archivo %s existe, sera eliminado: ", yfileName);
                yfile.delete();
                System.out.println("[OK]");
            }
            System.out.printf("Archivo %s listo para escribir: ", yfileName);
            if (yfile.createNewFile()) {
                System.out.println("[OK]");
                FileOutputStream fos;
                DataOutputStream dos = null;
                try {
                    fos = new FileOutputStream(yfile);
                    dos = new DataOutputStream(fos);
                    dos.writeChars(Cep.titulosCommaSeparateValues());
                    for (Cep registro : registros) {
                        dos.writeChars(registro.toCommaSeparateValues());
                        iregistros++;
                    }
                } catch (IOException ioe) {
                    throw ioe;
                } finally {
                    if (dos != null) {
                        dos.close();
                    }
                }
            }
            return iregistros;
        } else {
            System.out.println("El directorio de salida no parece ser valido. Error en la ejecución.");
            return -1;
        }
    }

    static public Cep procesaXML(String file) throws IOException, ParserConfigurationException, SAXException {
        File fXmlFile = new File(file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = null;
        try{
            doc = dBuilder.parse(fXmlFile);
        }catch(SAXException e){
            e.printStackTrace(System.out);
            throw new SAXException(e);
        }
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        boolean esCFDI = true;
        esCFDI = doc.getDocumentElement().getNodeName().toUpperCase().startsWith("CFDI");

        System.out.println("El documento es CFDI? " + esCFDI);
        String prefijo = (esCFDI ? "cfdi:" : "");

        NodeList nList = doc.getElementsByTagName(prefijo + "Comprobante");

        System.out.println("----------------------------");
        Cep cabecera = new Cep();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String version = eElement.getAttribute(XML_ATRIBUTO_VERSION);
                System.out.println("Version del documento XML : " + version);
                if (version!=null && version.equals(VERSION_33)){
                    System.out.println("Serie : " + eElement.getAttribute("Serie"));
                    System.out.println("Folio : " + eElement.getAttribute("Folio"));
                    System.out.println("Fecha : " + eElement.getAttribute("Fecha"));
                    System.out.println("Forma de pago : " + eElement.getAttribute("FormaPago"));
                    System.out.println("Sub total : " + eElement.getAttribute("SubTotal"));
                    System.out.println("Moneda : " + eElement.getAttribute("Moneda"));
                    System.out.println("Total : " + eElement.getAttribute("Total"));
                    System.out.println("Metodo de pago : " + eElement.getAttribute("MetodoPago"));
                    System.out.println("Lugar de expedición : " + eElement.getAttribute("LugarExpedicion"));

                    cabecera.setSerie(eElement.getAttribute("Serie"));
                    cabecera.setFolio(eElement.getAttribute("Folio"));
                    cabecera.setStrFecha(eElement.getAttribute("Fecha"));
                    cabecera.setFormaDePago(eElement.getAttribute("FormaPago"));
                    cabecera.setStrSubTotal(eElement.getAttribute("SubTotal"));
                    cabecera.setStrDescuento("0.0"); //eElement.getAttribute("descuento"));
                    cabecera.setTipoCambio("1.0"); //eElement.getAttribute("TipoCambio"));
                    cabecera.setMoneda(eElement.getAttribute("Moneda"));
                    cabecera.setStrTotal(eElement.getAttribute("Total"));
                    cabecera.setMetodoDePago(eElement.getAttribute("MetodoPago"));
                    cabecera.setLugarExpedicion(eElement.getAttribute("LugarExpedicion"));
                } else {
                    System.out.println("Serie : " + eElement.getAttribute("serie"));
                    System.out.println("Folio : " + eElement.getAttribute("folio"));
                    System.out.println("Fecha : " + eElement.getAttribute("fecha"));
                    System.out.println("Forma de pago : " + eElement.getAttribute("formaDePago"));
                    System.out.println("Sub total : " + eElement.getAttribute("subTotal"));
                    System.out.println("Descuento : " + eElement.getAttribute("descuento"));
                    System.out.println("Tipo de cambio : " + eElement.getAttribute("TipoCambio"));
                    System.out.println("Moneda : " + eElement.getAttribute("Moneda"));
                    System.out.println("Total : " + eElement.getAttribute("total"));
                    System.out.println("Metodo de pago : " + eElement.getAttribute("metodoDePago"));
                    System.out.println("Lugar de expedición : " + eElement.getAttribute("LugarExpedicion"));

                    cabecera.setSerie(eElement.getAttribute("serie"));
                    cabecera.setFolio(eElement.getAttribute("folio"));
                    cabecera.setStrFecha(eElement.getAttribute("fecha"));
                    cabecera.setFormaDePago(eElement.getAttribute("formaDePago"));
                    cabecera.setStrSubTotal(eElement.getAttribute("subTotal"));
                    cabecera.setStrDescuento(eElement.getAttribute("descuento"));
                    cabecera.setTipoCambio(eElement.getAttribute("TipoCambio"));
                    cabecera.setMoneda(eElement.getAttribute("Moneda"));
                    cabecera.setStrTotal(eElement.getAttribute("total"));
                    cabecera.setMetodoDePago(eElement.getAttribute("metodoDePago"));
                    cabecera.setLugarExpedicion(eElement.getAttribute("LugarExpedicion"));
                }
            }
        }

        nList = doc.getElementsByTagName(prefijo + "Emisor");
        System.out.println("----------------------------");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("RFC : " + eElement.getAttribute("rfc") + eElement.getAttribute("Rfc"));
                System.out.println("Nombre : " + eElement.getAttribute("nombre")+eElement.getAttribute("Nombre"));
                cabecera.setRfc(eElement.getAttribute("rfc") + eElement.getAttribute("Rfc"));
                cabecera.setNombre(eElement.getAttribute("nombre") + eElement.getAttribute("Nombre"));
            }
        }

        nList = doc.getElementsByTagName(prefijo + "Receptor");
        System.out.println("----------------------------");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("RFC : " + eElement.getAttribute("rfc") + eElement.getAttribute("Rfc"));
                System.out.println("Nombre : " + eElement.getAttribute("nombre") + eElement.getAttribute("Nombre"));
                cabecera.setRfcReceptor(eElement.getAttribute("rfc") + eElement.getAttribute("Rfc"));
                cabecera.setNombreReceptor(eElement.getAttribute("nombre") + eElement.getAttribute("Nombre"));
            }
        }

        nList = doc.getElementsByTagName(prefijo + "Impuestos");
        System.out.println("----------------------------");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("Impuestos trasladados : " + eElement.getAttribute("totalImpuestosTrasladados") + eElement.getAttribute("TotalImpuestosTrasladados"));
                cabecera.setStrTotalImpuestosTrasladados(eElement.getAttribute("totalImpuestosTrasladados") + eElement.getAttribute("TotalImpuestosTrasladados"));
            }
        }

        nList = doc.getElementsByTagName(prefijo + "Traslados");
        System.out.println("----------------------------");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.printf("\nCurrent Element : %s-%s%n", nNode.getNodeType(), nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // Element eElement = (Element) nNode;
                NodeList nodes = nNode.getChildNodes();
                System.out.println("Impuestos encontrados : " + nodes.getLength());
                for (int ximpuesto = 0; ximpuesto < nodes.getLength(); ximpuesto++) {
                    Node nNodeImpuesto = nodes.item(ximpuesto);

                    if (nNodeImpuesto.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.printf("\nCurrent Element : %s-%s%n", nNodeImpuesto.getNodeType(), nNodeImpuesto.getNodeName());
                        Element xElement = (Element) nNodeImpuesto;
                        String impuesto, tasa, importe;
                        impuesto = xElement.getAttribute("impuesto") + xElement.getAttribute("Impuesto");
                        tasa = xElement.getAttribute("tasa") + xElement.getAttribute("TasaOCuota");
                        importe = xElement.getAttribute("importe") + xElement.getAttribute("Importe");
                        System.out.printf("impuesto = %s, tasa = %s, importe = %s %n", impuesto, tasa, importe);
                        if (impuesto.equals(Cep.IMPUESTO_IVA) || impuesto.equals(Cep.IMPUESTO_IVA_CODIGO)) {
                            cabecera.setIva_strTasa(tasa);
                            cabecera.setIva_strImporte(importe);
                        } else if (impuesto.equals(Cep.IMPUESTO_IEPS)) {
                            cabecera.setIeps_strTasa(tasa);
                            cabecera.setIeps_strImporte(importe);
                        } else {
                            cabecera.setIeps_strTasa(tasa);
                            cabecera.setIeps_strImporte(importe);
                            System.out.println("Impuesto no detectado en el objeto: " + impuesto);
                        }
                    }
                }
            }
        }

        nList = doc.getElementsByTagName("tfd:TimbreFiscalDigital");
        System.out.println("----------------------------");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("UUID : " + eElement.getAttribute("UUID"));
                System.out.println("Fecha de timbrado: " + eElement.getAttribute("FechaTimbrado"));
                cabecera.setUuid(eElement.getAttribute("UUID"));
                cabecera.setStrFechaTimbrado(eElement.getAttribute("FechaTimbrado"));
            }
        }

        return cabecera;
    }

}