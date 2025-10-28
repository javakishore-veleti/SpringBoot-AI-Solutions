package com.jk.labs.ai.sb.common.prompts_engg.rag.vector_dbs;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HrPolicyDataLoader {

    @Autowired
    private VectorStore vectorStore;

    @Value("classpath:/hr-policies/CorporateHrPolicies.pdf")
    private Resource policyPdfResource;

    @PostConstruct
    public void loadPolicyPDF() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(policyPdfResource);
        List<Document> docs = tikaDocumentReader.get();
        vectorStore.add(docs);
    }
}
