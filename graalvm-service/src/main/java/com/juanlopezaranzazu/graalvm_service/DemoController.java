package com.juanlopezaranzazu.graalvm_service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService){
        this.demoService = demoService;
    }

    @PostMapping("/longest-unique-substring")
    public DemoResponseDTO getLongestUniqueSubstring(@RequestBody DemoRequestDTO request) {
        int result = demoService.lengthOfLongestSubstring(request.getInput());
        return new DemoResponseDTO(result);
    }
}
