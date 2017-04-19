package com.dpro.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractAPI {

    protected ResponseEntity<Map<String, String>> getMessage() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "ok");
        result.put("code", "200");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    protected ResponseEntity<Map<String, String>> getError() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "error");
        result.put("code", "200");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
