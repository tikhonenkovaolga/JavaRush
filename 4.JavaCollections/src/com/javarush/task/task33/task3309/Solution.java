package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;



/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);

        String xml = writer.toString();

        String [] str = xml.split("\n");
        xml = "";
        for (int i = 0; i < str.length; i++){
            if (str[i].contains("<" + tagName + ">") & !str[i].contains("CDATA")){
                str[i] = str[i].replaceAll("<" + tagName,"<!--" + comment + "-->" + "\n" + "<" + tagName);
                xml += str[i]+"\n";
            }
            else xml += str[i]+"\n";
        }

        return xml;
    }

    public static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerException, IOException, SAXException {
        System.out.println(Solution.toXmlWithComment(new First(), "second", "it's a comment"));

    }

    @XmlRootElement
    public static class TestClass {

        @XmlElement(name = "name")
        public String name = "Some text without forbidden symbols";

        @XmlElement (name = "numbers")
        public int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,10};

        @XmlElement
        public String[] strings = new String[]{"string1" , "string2", "string3"};

        @XmlElement (name = "numbers")
        public int data = 5435;

        @XmlElement (name = "numbers")
        public String[] b = new String[]{"</b>"};

        public TestClass () {

        }

    }


    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }


}
