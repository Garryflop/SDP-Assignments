package com.example.adapter.adapter;


import com.example.adapter.impl.XmlParserImpl;
import com.example.adapter.model.Computer;
import com.example.adapter.parser.JsonParser;
import com.google.gson.Gson;


//Object Adapter: adapts XmlParserImpl (adaptee) to the JsonParser (target) interface
public class XmlToJsonAdapter implements JsonParser {
    private final XmlParserImpl xmlParser;
    private final Gson gson = new Gson();


    public XmlToJsonAdapter(XmlParserImpl xmlParser) {
        this.xmlParser = xmlParser;
    }


    @Override
    public Computer parse(String input) {
// Assume input is XML. Adapter converts XML to Computer using Adaptee.
        return xmlParser.parseXml(input);
    }


    @Override
    public String serialize(Computer computer) {
// Provide JSON serialization of Computer directly - adapter is JSON-facing
        return gson.toJson(computer);
    }
}