package com.ai.Photo_Generator;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class imageService {

    private final OpenAiImageModel openAiImageModel;

    public imageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt,String quality,int n,int height, int width){
        try{
//            ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(prompt);
            ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(prompt, OpenAiImageOptions.builder()
                    .quality(quality)
                    .N(n)
                    .height(height)
                    .width(width).build())
            );
            return imageResponse;
        }catch (Exception e){
            throw new RuntimeException("AI image service is temporarily unavailable",e);
        }
    }
}
