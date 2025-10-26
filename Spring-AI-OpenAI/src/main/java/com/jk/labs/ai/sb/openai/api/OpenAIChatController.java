package com.jk.labs.ai.sb.openai.api;

import com.jk.labs.ai.sb.common.dto.AppChatRequest;
import com.jk.labs.ai.sb.common.dto.AppChatResponse;
import com.jk.labs.ai.sb.openai.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sb-ai/openai/v1", produces = "application/json")
public class OpenAIChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenAIChatController.class);

    @Autowired
    private ChatService chatService;

    @PostMapping(path = "/chat/hello")
    public ResponseEntity<AppChatResponse> executeUserMessage(@RequestBody AppChatRequest appChatRequest) {
        LOGGER.info("STARTED executeUserMessage API");

        AppChatResponse appChatResponse = new AppChatResponse();
        chatService.executeUserMessage(appChatRequest, appChatResponse);

        LOGGER.info("COMPLETED executeUserMessage API");
        return ResponseEntity.ok(appChatResponse);
    }

    @PostMapping(path = "/chat-memory/hello")
    public ResponseEntity<AppChatResponse> executeUserMessageWithMemory(@RequestBody AppChatRequest appChatRequest) {
        LOGGER.info("STARTED executeUserMessageWithMemory API");

        AppChatResponse appChatResponse = new AppChatResponse();
        chatService.executeUserMessageWithMemory(appChatRequest, appChatResponse);

        LOGGER.info("COMPLETED executeUserMessageWithMemory API");
        return ResponseEntity.ok(appChatResponse);
    }

}
