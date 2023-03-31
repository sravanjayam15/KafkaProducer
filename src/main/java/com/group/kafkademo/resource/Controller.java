package com.group.kafkademo.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.group.kafkademo.config.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class Controller {

    @Autowired
    private KafkaConfig config;

    @Autowired
    private final KafkaTemplate<String, Object> template;

    public Controller(final KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    @PostMapping("/produce/inventory/json")
    @ResponseBody
    public String postInventoryMessage(@RequestBody JsonNode json) {
        template.send(config.getInventoryTopic(), json);
        return "Message published successfully";
    }

    @PostMapping("/produce/wavelength/json")
    @ResponseBody
    public String postWavelengthMessage(@RequestBody JsonNode json) {
        template.send(config.getWavelengthTopic(), json);
        return "Message published successfully";
    }

}
