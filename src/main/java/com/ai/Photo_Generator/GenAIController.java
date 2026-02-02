package com.ai.Photo_Generator;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GenAIController {


    private final ChatService chatService;
    private final imageService imageService;
    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, imageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("askAi")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("askAi-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

//    @GetMapping("generate-image")
//    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
//       ImageResponse imageResponse =  imageService.generateImage(prompt);
//       String imageUrl = imageResponse.getResult().getOutput().getUrl();
//       response.sendRedirect(imageUrl);
//    }

    @GetMapping("generate-image")
    public List<String> generateImage(HttpServletResponse response, @RequestParam String prompt,
                                      @RequestParam(defaultValue = "hd") String quality,
                                      @RequestParam(defaultValue = "3") int n,
                                      @RequestParam(defaultValue = "1024") int height,
                                      @RequestParam(defaultValue = "1024") int width) throws IOException {
        ImageResponse imageResponse =  imageService.generateImage(prompt,quality,n,height,width);

        // using streams to get urls from ImageResponse
        List<String> imageurls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return imageurls;
    }

    @GetMapping("create-recipe")
    public String recipeCreator(@RequestParam String ingredients,
                                     @RequestParam(defaultValue = "any") String cuisine,
                                     @RequestParam(defaultValue = "") String dietaryRestrictions){
    return recipeService.createRecipe(ingredients,cuisine,dietaryRestrictions);
    }

}

