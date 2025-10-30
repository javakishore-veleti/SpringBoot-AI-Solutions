package com.jk.labs.ai.sb.common.prompts_engg.rag.web;

import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebSearchRetriever implements DocumentRetriever {

    @Override
    public List<Document> retrieve(Query query) {
        return List.of();
    }
}
