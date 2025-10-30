package com.jk.labs.ai.sb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringAiApplicationTests {

    @Autowired
    private ChatModel chatModel;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void contextLoads() {
    }

}
