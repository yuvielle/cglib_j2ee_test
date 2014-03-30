package lib.saver;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yuvielle on 15.02.14.
 */
public class XmlSaver implements BaseSaver {

    private Document doc;
    private File file;
    private Result output;

    public XmlSaver() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        File dataFolder = new File("main\\data\\");
        boolean exists = dataFolder.exists();
        if(!exists){
            dataFolder.mkdirs();
        }
        this.file = new File(dataFolder + "\\shops.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.doc = dBuilder.parse(this.file);
        this.doc.getDocumentElement().normalize();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        this.output = new StreamResult(this.file);
    }

    @Override
    public void insertObject(Object key, Object value) throws IOException, ClassNotFoundException {
        NodeList e = this.doc.getElementsByTagName("shop_product");
        Node node = e.item(0).appendChild(this.doc.createElement("object_%s" + key));
        HashMap record = (HashMap) value;
        for (Object k : record.keySet() ) {
            node.appendChild(this.doc.createElement(k.toString())).appendChild(this.doc.createTextNode(record.get(k).toString()));
        }


    }

    @Override
    public void updateObject(Object key, Object value) throws IOException, ClassNotFoundException {
        NodeList e = this.doc.getElementsByTagName("shop_product");
        if(e.getLength()>0) {
            NodeList list = e.item(0).getChildNodes();
            for (int i=0; i<list.getLength(); i++) {
                // Get element
                Node element = list.item(i);
                if(element.getNodeType() == Node.ELEMENT_NODE && element.getNodeName().equals("object_" + key)){
                    HashMap record = new HashMap();
                    NodeList el = element.getChildNodes();
                    int lenght = el.getLength();
                    for(int ii = 0; ii < lenght; ii++ ) {
                        if(element.getNodeType() == Node.ELEMENT_NODE){
                            String name = el.item(ii).getNodeName();
                            el.item(ii).setTextContent(record.get(name).toString());
                        }
                    }
                }
            }
        }

    }

    @Override
    public Object getObject(Object key, String tablename) throws Exception {

        ArrayList<Object> collection = new ArrayList<Object>();
        NodeList e = this.doc.getElementsByTagName("shop_product");
        if(e.getLength()>0) {
            NodeList list = e.item(0).getChildNodes();
            for (int i=0; i<list.getLength(); i++) {
                // Get element
                Node element = list.item(i);
                if(element.getNodeType() == Node.ELEMENT_NODE && element.getNodeName().equals("object_" + key)){
                    HashMap record = new HashMap();
                    NodeList el = element.getChildNodes();
                    int lenght = el.getLength();
                    for(int ii = 0; ii < lenght; ii++ ) {
                        if(element.getNodeType() == Node.ELEMENT_NODE){
                            String name = el.item(ii).getNodeName();
                            String value = el.item(ii).getTextContent();
                            record.put(name, value);
                        }
                    }
                    collection.add(record);
                }
            }
        }
        return collection.iterator().next();
    }

    @Override
    public void deleteObject(Object key, String tablename) {
        NodeList e = this.doc.getElementsByTagName("shop_product");
        if(e.getLength()>0) {
            NodeList list = e.item(0).getChildNodes();
            for (int i=0; i<list.getLength(); i++) {
                // Get element
                Node element = list.item(i);
                if(element.getNodeType() == Node.ELEMENT_NODE && element.getNodeName().equals("object_" + key)){
                    element.getParentNode().removeChild(element);
                }
            }
        }
    }

    @Override
    public void clearHolder() {

    }

    @Override
    public Object removeObject(Object key, String tablename) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean containsKey(Object key, String tablename) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void reset() throws IOException, ClassNotFoundException {

    }

    @Override
    public ArrayList getCollection(String modelname) throws IOException {

        ArrayList<Object> collection = new ArrayList<Object>();
        NodeList e = this.doc.getElementsByTagName(modelname);
        if(e.getLength()>0) {
            NodeList list = e.item(0).getChildNodes();
            for (int i=0; i<list.getLength(); i++) {
                // Get element
                Node element = list.item(i);
                if(element.getNodeType() == Node.ELEMENT_NODE){
                    HashMap record = new HashMap();
                    NodeList el = element.getChildNodes();
                    int lenght = el.getLength();
                    for(int ii = 0; ii < lenght; ii++ ) {
                        if(element.getNodeType() == Node.ELEMENT_NODE){
                             String name = el.item(ii).getNodeName();
                             String value = el.item(ii).getTextContent();
                             record.put(name, value);
                        }
                    }
                    collection.add(record);
                }
            }
        }
        return collection;
    }

    @Override
    public void addWhere(String expression) throws Exception {

    }

    @Override
    public void addJoin(String tablename) throws Exception {

    }

    @Override
    public ArrayList executeQuery(String query) throws Exception {
        return null;
    }
}
