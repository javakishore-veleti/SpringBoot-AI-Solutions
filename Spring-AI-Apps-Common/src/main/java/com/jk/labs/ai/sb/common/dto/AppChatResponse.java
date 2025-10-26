package com.jk.labs.ai.sb.common.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AppChatResponse {

    private Map<String, Object> results;

    public AppChatResponse() {
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
