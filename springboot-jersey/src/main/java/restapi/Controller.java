package restapi;


import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import restapi.Domain.Order;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@RestController
public class Controller {


  @RequestMapping("/index")
  public String index(@QueryParam("name") String name) {
    return "Hello Irfana!";
  }

  @PostMapping(value = "/Order", consumes = APPLICATION_JSON)
  @ResponseStatus(HttpStatus.CREATED)
  public void createOrder(@RequestBody @Valid Order order)
  {
    System.out.println("id-->"+order.getId());
    System.out.println("name-->"+order.getName());
  }

}
