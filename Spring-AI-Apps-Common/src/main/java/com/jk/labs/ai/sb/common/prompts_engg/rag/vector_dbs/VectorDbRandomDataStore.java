package com.jk.labs.ai.sb.common.prompts_engg.rag.vector_dbs;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Profile("vector-db-qdrant")
@Configuration
public class VectorDbRandomDataStore {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final VectorStore vectorStore;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public VectorDbRandomDataStore(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadSentencesIntoVectorStore() {
        @SuppressWarnings("unused") List<String> sentences = List.of(
                "The quick brown fox jumps over the lazy dog.",
                "Artificial Intelligence is transforming the world.",
                "Spring Boot makes it easy to create stand-alone applications.",
                "Vector databases are essential for modern AI applications.",
                "Java is a versatile programming language."
        );

        List<Document> documents = sentences.stream().map(Document::new).collect(Collectors.toList());
        vectorStore.add(documents);
    }

}
