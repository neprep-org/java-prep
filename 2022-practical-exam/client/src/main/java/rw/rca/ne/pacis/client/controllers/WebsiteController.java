package rw.rca.ne.pacis.client.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import rw.rca.ne.pacis.client.models.Link;
import rw.rca.ne.pacis.client.models.Website;
import rw.rca.ne.pacis.client.utils.ApiResponse;
import rw.rca.ne.pacis.client.utils.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class WebsiteController {

    @GetMapping
    public String mainEntry(){
        return "Download";
    }

    @PostMapping("/download")
    public String download(HttpServletRequest request, Model model, HttpSession session) throws JsonProcessingException {
        try{
            RestTemplate restTemplate = new RestTemplate();
            Map<String,String> requestBody = new HashMap<>();

            request.getParameterMap().forEach((key,value)-> requestBody.put(key,value[0]));

            ResponseEntity<ApiResponse> res= restTemplate.postForEntity(Utility.formatURL("/api/v1/websites/download"), requestBody, ApiResponse.class);

            return "redirect:/report";
        }
        catch(Exception e){
            ApiResponse response = new ObjectMapper().readValue(e.getMessage().substring(7, e.getMessage().length() - 1), ApiResponse.class);
            model.addAttribute("error", response.getMessage());
            return "Download";
        }
    }

    @GetMapping("/report")
    public String report(HttpServletRequest request,Model model){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(null,headers);

        ResponseEntity<Website[]> res = restTemplate.exchange(Utility.formatURL("/api/v1/websites"), HttpMethod.GET,entity, Website[].class);

        model.addAttribute("websites", Objects.requireNonNull(res.getBody()));

        return "Report";
    }

    @GetMapping("/links/{id}")
    public String links(HttpServletRequest request, Model model, @PathVariable UUID id){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(null,headers);

        ResponseEntity<Link[]> res = restTemplate.exchange(Utility.formatURL("/api/v1/websites/links/"+id),HttpMethod.GET, entity, Link[].class);

        model.addAttribute("links", Objects.requireNonNull(res.getBody()));

        return "Links";
    }
}
