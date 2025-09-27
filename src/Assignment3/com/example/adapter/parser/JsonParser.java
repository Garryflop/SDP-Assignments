package com.example.adapter.parser;


import com.example.adapter.model.Computer;


/**
 * Target interface expected by the client: JSON-oriented parser
 */
public interface JsonParser {
    //Parse input (expected to be JSON or XML if adapter supports XML input) into Computer model

    Computer parse(String input);


    /**
     * Serialize Computer into JSON string
     */
    String serialize(Computer computer);
}