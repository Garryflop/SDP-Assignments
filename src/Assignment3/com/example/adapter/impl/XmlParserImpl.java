package com.example.adapter.impl;


import com.example.adapter.model.Computer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;


/**
 * Adaptee: an existing XML parser that works specifically with XML strings
 */
public class XmlParserImpl {


    public Computer parseXml(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            Document doc = builder.parse(is);
            Element root = doc.getDocumentElement();


            String cpu = getTextContent(root, "cpu");
            String ram = getTextContent(root, "ram");
            String storage = getTextContent(root, "storage");


            return new Computer(cpu, ram, storage);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid XML input", e);
        }
    }


    public String toXml(Computer c) {
        StringBuilder sb = new StringBuilder();
        sb.append("<computer>\n");
        sb.append(" <cpu>").append(escape(c.getCpu())).append("</cpu>\n");
        sb.append(" <ram>").append(escape(c.getRam())).append("</ram>\n");
        sb.append(" <storage>").append(escape(c.getStorage())).append("</storage>\n");
        sb.append("</computer>");
        return sb.toString();
    }


    private String getTextContent(Element root, String tag) {
        try {
            return root.getElementsByTagName(tag).item(0).getTextContent();
        } catch (Exception e) {
            return null;
        }
    }


    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}