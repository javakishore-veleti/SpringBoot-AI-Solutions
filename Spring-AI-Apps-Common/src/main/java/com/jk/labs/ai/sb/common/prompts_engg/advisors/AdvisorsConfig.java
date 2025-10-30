package com.jk.labs.ai.sb.common.prompts_engg.advisors;

import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("vector-db-qdrant")
@Configuration
public class AdvisorsConfig {

    @Bean
    public RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(VectorStore vectorStore) {
        return RetrievalAugmentationAdvisor.builder().documentRetriever(
                VectorStoreDocumentRetriever.builder().vectorStore(vectorStore).topK(5).similarityThreshold(0.5).build()).build();
    }
}
