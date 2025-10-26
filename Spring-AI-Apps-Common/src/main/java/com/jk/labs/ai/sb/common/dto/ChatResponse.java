package com.jk.labs.ai.sb.common.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ChatResponse {

    private Map<String, Object> results;

    public ChatResponse() {
        results = new HashMap<>();
    }

    public void addResult(String key, Object value) {
        results.put(key, value);
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }
}
