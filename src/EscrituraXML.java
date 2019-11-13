import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class EscrituraXML {
    public static void main(String args[]) throws IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String id[] = {"001","002","003"};
        String apellido[] = {"Cabello","Galves","Burgos"};
        String dep[] = {"Informatica01","Informatica02","Informatica03"};
        String salario[] = {"2000","2100","2200"};
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0"); 
            for (int i = 0; i < id.length; i++) {
                Element raiz = document.createElement("empleado"); //nodo empleado
                document.getDocumentElement().appendChild(raiz); 
                CrearElemento("id",id[i], raiz, document); 
                //Apellido
                CrearElemento("apellido",apellido[i], raiz, document); 
                //añadir DEP
                CrearElemento("dep",dep[i], raiz, document); 
                //añadir salario
                CrearElemento("salario",salario[i], raiz,document);
            }
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Empleados.xml"));        
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }catch(Exception e){ 
            System.err.println("Error: "+e); 
        }
    }
    static void  CrearElemento(String datoEmple, String valor,Element raiz, Document document){
        Element elem = document.createElement(datoEmple); 
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor		 	
    }
 }
