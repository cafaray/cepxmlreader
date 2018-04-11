package com.ftc.services.payments;

import com.ftc.services.payments.model.CEPCabecera;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lanzador {

    private static final String XML_ATRIBUTO_VERSION = "Version";
    private static final String VERSION_33 = "3.3";


    public static void main(String args[]) {
        String folderIn = "/Users/omash/Desktop";
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
                List<CEPCabecera> registros = new ArrayList<CEPCabecera>();
                for (File xfile : xfiles) {
                    CEPCabecera cabecera = procesaXML(xfile.getAbsolutePath());
                    registros.add(cabecera);
                }

                /*int resultado = escribeSalida(folderOut, yfileName, registros);
                if (resultado < 0) {
                    System.out.println("Hubo un error durante la escritura de los registros. Revise el log de salida.");
                    System.exit(1);
                }*/

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

    /*private static int escribeSalida(String folder, String yfileName, List<CEPCabecera> registros)
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
                    dos.writeChars(CEPCabecera.titulosCommaSeparateValues());
                    for (CEPCabecera registro : registros) {
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
    }*/

    static public CEPCabecera procesaXML(String file) throws IOException, ParserConfigurationException, SAXException {
        File fXmlFile = new File(file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = null;
        try {
            doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            boolean esCFDI = true;
            esCFDI = doc.getDocumentElement().getNodeName().toUpperCase().startsWith("CFDI");

            System.out.println("El documento es CFDI? " + esCFDI);
            String prefijo = (esCFDI ? "cfdi:" : "");

            NodeList nList = doc.getElementsByTagName(prefijo + "Comprobante");

            System.out.println("----------------------------");
            CEPCabecera cabecera = new CEPCabecera();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String version = eElement.getAttribute(XML_ATRIBUTO_VERSION);
                    String ns = eElement.getAttribute("xmlns:pago10");

                    System.out.println("NS y Version del documento XML : " + ns + " - " + version);
                    if (version != null && version.equals(VERSION_33)) {
                        System.out.println("Serie : " + eElement.getAttribute("Serie"));
                        System.out.println("Folio : " + eElement.getAttribute("Folio"));
                        System.out.println("Fecha : " + eElement.getAttribute("Fecha"));
                        System.out.println("Sub total : " + eElement.getAttribute("SubTotal"));
                        System.out.println("Moneda : " + eElement.getAttribute("Moneda"));
                        System.out.println("Total : " + eElement.getAttribute("Total"));
                        System.out.println("Lugar de expedición : " + eElement.getAttribute("LugarExpedicion"));

                        System.out.println("NameSpacePagos: " + ns);
                        System.out.println("Tipo de comprobante : " + eElement.getAttribute("TipoDeComprobante"));

                        cabecera.setVersion(version);
                        cabecera.setSerie(eElement.getAttribute("Serie"));
                        cabecera.setFolio(eElement.getAttribute("Folio"));
                        cabecera.setFecha(parseDate(eElement.getAttribute("Fecha")));
                        cabecera.setSubTotal(parseDouble(eElement.getAttribute("SubTotal")));
                        cabecera.setMoneda(eElement.getAttribute("Moneda"));
                        cabecera.setTotal(parseDouble(eElement.getAttribute("Total")));
                        cabecera.setLugarExpedicion(eElement.getAttribute("LugarExpedicion"));
                        cabecera.setXmlnsPago10(ns);
                        cabecera.setTipoDeComprobante(eElement.getAttribute("TipoDeComprobante"));

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
                    System.out.println("Nombre : " + eElement.getAttribute("nombre") + eElement.getAttribute("Nombre"));
                    cabecera.setRfcEmisor(eElement.getAttribute("rfc") + eElement.getAttribute("Rfc"));
                    cabecera.setNombreEmisor(eElement.getAttribute("nombre") + eElement.getAttribute("Nombre"));
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


            nList = doc.getElementsByTagName("tfd:TimbreFiscalDigital");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("RfcProvCertif : " + eElement.getAttribute("RfcProvCertif"));
                    System.out.println("Version : " + eElement.getAttribute("Version"));
                    System.out.println("UUID : " + eElement.getAttribute("UUID"));
                    System.out.println("FechaTimbrado : " + eElement.getAttribute("FechaTimbrado"));
                    System.out.println("NoCertificadoSAT : " + eElement.getAttribute("NoCertificadoSAT"));

                } else if (nNode.getNodeType() == Node.ATTRIBUTE_NODE) {
                    System.out.println(nNode.getNodeName());
                }
            }

            nList = doc.getElementsByTagName("pago10:Pagos");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Version : " + eElement.getAttribute("Version"));

                } else if (nNode.getNodeType() == Node.ATTRIBUTE_NODE) {
                    System.out.println(nNode.getNodeName());
                }
            }

            nList = doc.getElementsByTagName("pago10:CEPPago");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("FechaPago : " + eElement.getAttribute("FechaPago"));
                    System.out.println("FormaDePagoP : " + eElement.getAttribute("FormaDePagoP"));
                    System.out.println("MonedaP : " + eElement.getAttribute("MonedaP"));
                    System.out.println("Monto : " + eElement.getAttribute("Monto"));
                    System.out.println("RfcEmisorCtaOrd : " + eElement.getAttribute("RfcEmisorCtaOrd"));
                    System.out.println("CtaOrdenante : " + eElement.getAttribute("CtaOrdenante"));
                    System.out.println("RfcEmisorCtaBen : " + eElement.getAttribute("RfcEmisorCtaBen"));
                    System.out.println("CtaBeneficiario : " + eElement.getAttribute("CtaBeneficiario"));

                } else if (nNode.getNodeType() == Node.ATTRIBUTE_NODE) {
                    System.out.println(nNode.getNodeName());
                }
            }

            nList = doc.getElementsByTagName("pago10:DoctoRelacionado");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("IdDocumento : " + eElement.getAttribute("IdDocumento"));
                    System.out.println("Folio : " + eElement.getAttribute("Folio"));
                    System.out.println("Serie : " + eElement.getAttribute("Serie"));
                    System.out.println("MonedaDR : " + eElement.getAttribute("MonedaDR"));
                    System.out.println("MetodoDePagoDR : " + eElement.getAttribute("MetodoDePagoDR"));
                    System.out.println("NumParcialidad : " + eElement.getAttribute("NumParcialidad"));
                    System.out.println("ImpSaldoAnt : " + eElement.getAttribute("ImpSaldoAnt"));
                    System.out.println("ImpPagado : " + eElement.getAttribute("ImpPagado"));
                    System.out.println("ImpSaldoInsoluto : " + eElement.getAttribute("ImpSaldoInsoluto"));

                } else if (nNode.getNodeType() == Node.ATTRIBUTE_NODE) {
                    System.out.println(nNode.getNodeName());
                }
            }

            return cabecera;
        } catch (SAXException e) {
            e.printStackTrace(System.out);
            throw new SAXException(e);
        } catch (ParseException e) {
            e.printStackTrace(System.out);
            throw new SAXException(e);
        } catch (NumberFormatException e) {
            e.printStackTrace(System.out);
            throw new SAXException(e);
        }
    }

    private static final Date parseDate(String fecha) throws ParseException {
        // 2018-04-03T08:58:45
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date myDate = dateFormat.parse(fecha);
        return myDate;
    }

    private static final double parseDouble(String numero) throws NumberFormatException {
        return Float.parseFloat(numero);
    }

    private static final int parseIntger(String numero) throws NumberFormatException {
        return Integer.parseInt(numero);
    }
}