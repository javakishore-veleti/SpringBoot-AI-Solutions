package com.jk.labs.ai.sb.openai.service.vector_db.qdrant.impl;

import com.jk.labs.ai.sb.common.dto.AppChatRequest;
import com.jk.labs.ai.sb.common.dto.AppChatResponse;
import com.jk.labs.ai.sb.openai.service.vector_db.qdrant.QdrantHrPolicDocsChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@SuppressWarnings({"SpellCheckingInspection", "DuplicatedCode"})
@Profile("vector-db-qdrant")
@Service
public class QdrantHrPolicDocsChatServiceImpl implements QdrantHrPolicDocsChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QdrantHrPolicDocsChatServiceImpl.class);

    @Value("classpath:/promptTemplates/systemPromptRandomDataTemplate.st")
    private Resource promptTemplate;

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private VectorStore vectorStore;

    @Override
    public void executeUserMessage(AppChatRequest request, AppChatResponse response) {
        SearchRequest searchRequest = SearchRequest.builder()
                .query(request.getUserMessage())
                .build();
        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
        similarDocs.forEach(doc -> LOGGER.info("Found similar doc: {}", doc.getText()));

        String similarContext = similarDocs.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator()));

        String answer = chatClient.prompt().system(promptSystemSpec ->
                promptSystemSpec.text(promptTemplate).param("documents", similarContext)).
                advisors(a -> a.param(CONVERSATION_ID, request.getUserName())).
                user(request.getUserMessage()).call().content();

        response.addResult("UserMessage", request.getUserMessage());
        response.addResult("SystemResponse", answer);

        LOGGER.info("QdrantChatServiceImpl executed successfully.answer {} similarContext {}", answer, similarContext);
    }
}
