package com.springai.edu.module01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Service responsible for discovering and routing requests to different ChatModel beans.
 * Demonstrates model portability and vendor decoupling.
 */
@Service
public class ModelRoutingService {

    private static final Logger log = LoggerFactory.getLogger(ModelRoutingService.class);

    private final ChatModel primaryChatModel;
    private final Map<String, ChatModel> registeredModels;

    public ModelRoutingService(ChatModel primaryChatModel, ApplicationContext applicationContext) {
        this.primaryChatModel = primaryChatModel;
        this.registeredModels = discoverModels(applicationContext);
    }

    private Map<String, ChatModel> discoverModels(ApplicationContext context) {
        Map<String, ChatModel> beans = context.getBeansOfType(ChatModel.class);
        Map<String, ChatModel> resolved = new LinkedHashMap<>();

        beans.forEach((name, model) -> {
            String normalizedKey = normalizeBeanName(name);
            resolved.put(normalizedKey, model);
            log.info("Discovered ChatModel bean: '{}' registered as provider key '{}'", name, normalizedKey);
        });

        return Collections.unmodifiableMap(resolved);
    }

    private String normalizeBeanName(String beanName) {
        String lower = beanName.toLowerCase();
        if (lower.contains("ollama")) return "ollama";
        if (lower.contains("openai")) return "openai";
        if (lower.contains("mock")) return "mock";
        return beanName;
    }

    public ChatModel getModel(String providerName) {
        if (providerName == null || providerName.isBlank()) {
            return primaryChatModel;
        }

        String key = providerName.trim().toLowerCase();
        ChatModel model = registeredModels.get(key);
        if (model != null) {
            return model;
        }

        log.warn("Requested provider '{}' not found. Falling back to primary ChatModel: {}",
                providerName, primaryChatModel.getClass().getSimpleName());
        return primaryChatModel;
    }

    public Map<String, ChatModel> getAllAvailableModels() {
        return registeredModels;
    }
}
