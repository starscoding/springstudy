package com.azxx.demon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@Controller
@RequestMapping("/abc")
public class ControllerTest {


    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @GetMapping("/getMethod")
    public String handle(Model model) {
        model.addAttribute("message", "getMethod");
        return "index";
    }

    @GetMapping("/getJson")
    @ResponseBody
    public String getJson(Model model) {
        Map<String,String> map=new HashMap<String, String>();
        map.put("001", "111");
        map.put("002", "222");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(map);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("message", "getMethod");
        return "index";
    }


    @GetMapping("/getWithUrl/{id}")
    public String getWithUrl(@PathVariable Long id, Model model){
        model.addAttribute("message","getMethod with args"+id);
        return "index";
    }

    @GetMapping("/getWithUri/{id1}/temp/id2")
    public String getWithUri(@PathVariable Long id1,@PathVariable Long id2,Model model){
        model.addAttribute("message","getMethod with args"+id1+","+id2);
        return "index";
    }

    @PostMapping("/form")
    public String handleFormUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // store the bytes somewhere
            return "redirect:uploadSuccess";
        }
        return "redirect:uploadFailure";
    }
}
