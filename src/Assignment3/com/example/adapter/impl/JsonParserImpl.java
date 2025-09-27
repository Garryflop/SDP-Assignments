package com.example.adapter.impl;


import com.example.adapter.model.Computer;
import com.example.adapter.parser.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


/**
 * Concrete JSON parser that knows how to parse/serialize JSON representations of Computer
 */
public class JsonParserImpl implements JsonParser {
    private final Gson gson = new Gson();


    @Override
    public Computer parse(String input) {
        try {
// Expecting a direct JSON representation of Computer
            return gson.fromJson(input, Computer.class);
        } catch (JsonSyntaxException ex) {
            throw new IllegalArgumentException("Invalid JSON input", ex);
        }
    }


    @Override
    public String serialize(Computer computer) {
        return gson.toJson(computer);
    }
}