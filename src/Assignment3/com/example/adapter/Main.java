package com.example.adapter;

import com.example.adapter.adapter.XmlToJsonAdapter;
import com.example.adapter.impl.JsonParserImpl;
import com.example.adapter.impl.XmlParserImpl;
import com.example.adapter.model.Computer;
import com.example.adapter.parser.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            //reading xml and json files
            String xmlRelPath = "src/Assignment3/resources/samples/computer.xml";
            String jsonRelPath = "src/Assignment3/resources/samples/computer.json";

            String xmlContent = readResourceOrFile("/samples/computer.xml", xmlRelPath);
            String jsonContent = readResourceOrFile("/samples/computer.json", jsonRelPath);

            System.out.println("XML content read:\n" + xmlContent + "\n");
            System.out.println("JSON content read:\n" + jsonContent + "\n");

            // Client waits JsonParser, but I have xml file -> we use adapter
            XmlParserImpl xmlParser = new XmlParserImpl();
            JsonParser xmlToJsonAdapter = new XmlToJsonAdapter(xmlParser);

            // Adapter.parse returns Computer
            Computer fromXml = xmlToJsonAdapter.parse(xmlContent);
            System.out.println("Parsed from XML to model: " + fromXml);

            // Adapter serializes to gson
            String producedJson = xmlToJsonAdapter.serialize(fromXml);
            System.out.println("Adapter produced JSON:\n" + producedJson + "\n");

            // Example
            JsonParser jsonParser = new JsonParserImpl();
            Computer fromJson = jsonParser.parse(jsonContent);
            System.out.println("Parsed from JSON to model: " + fromJson);
            System.out.println("Serialized back to JSON: " + jsonParser.serialize(fromJson));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //2ways to read
    private static String readResourceOrFile(String pathInClasspath, String relativePath) throws Exception {
        InputStream is = Main.class.getResourceAsStream(pathInClasspath);
        if (is != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                return br.lines().collect(Collectors.joining("\n"));
            }
        }

        Path p = Path.of(relativePath);
        if (Files.exists(p)) {
            return Files.readString(p, StandardCharsets.UTF_8);
        }

        throw new IllegalStateException("Resource not found in classpath and file not found: " + pathInClasspath + " / " + relativePath);
    }
}
