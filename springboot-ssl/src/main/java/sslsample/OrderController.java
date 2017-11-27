package sslsample;

import javax.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  @RequestMapping("/hello")
  public String printHelloWorld(@QueryParam("name") String name)
  {
    return "hello "+((name==null || name.equalsIgnoreCase(""))?"friend":name);
  }

}
