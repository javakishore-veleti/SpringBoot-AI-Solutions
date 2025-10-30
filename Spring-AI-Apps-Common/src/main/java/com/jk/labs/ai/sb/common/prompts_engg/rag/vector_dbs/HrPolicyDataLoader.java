package com.jk.labs.ai.sb.common.prompts_engg.rag.vector_dbs;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Profile("vector-db-qdrant")
@Component
public class HrPolicyDataLoader {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private VectorStore vectorStore;

    @Value("classpath:/hr-policies/CorporateHrPolicies.pdf")
    private Resource policyPdfResource;

    @PostConstruct
    public void loadPolicyPDF() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(policyPdfResource);
        List<Document> docs = tikaDocumentReader.get();
        docs.forEach(doc -> {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
            doc.getMetadata().put("source", "CorporateHrPolicies-"+timestamp + ".pdf");
        });
        TextSplitter textSplitter = TokenTextSplitter.builder().withChunkSize(100).withMaxNumChunks(500).build();
        vectorStore.add(textSplitter.split(docs));
    }
}
