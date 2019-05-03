package guidstatus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    @RequestMapping(value = "/")
    public String swaggerHtml(){
        return "redirect:swagger-ui.html";
    }
}
