package com.springai.edu.capstone.config;

import com.springai.edu.common.mock.MockChatModel;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CapstoneConfig {

    @Bean
    @ConditionalOnMissingBean(ChatModel.class)
    public ChatModel capstoneChatModel() {
        return new MockChatModel();
    }
}
