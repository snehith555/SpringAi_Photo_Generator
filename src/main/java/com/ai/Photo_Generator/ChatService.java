package com.ai.Photo_Generator;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    public String getResponse(String prompt){
        try{
            return chatModel.call(prompt);
        }catch (Exception e){
            return "AI service is temporarily unavailable. Please try again later.";
        }

    }


    public String getResponseOptions(String prompt){
        try{
            ChatResponse response = chatModel.call(
                    new Prompt(
                            prompt,
                            OpenAiChatOptions.builder()
                                    .model("gpt-4o-mini")
                                    .temperature(0.4)
                                    .build()
                    ));
            return response.getResult().getOutput().getText();
        }catch (Exception e){
            return "AI service is temporarily unavailable. Please try again later.";
        }

    }
}
