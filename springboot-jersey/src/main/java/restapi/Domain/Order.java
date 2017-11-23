package restapi.Domain;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Getter
@Setter
public class Order {

  private int id;
  private String name;

  @JsonSetter("id")
  public void setOrderId(String orderId)
  {
    this.id = Integer.parseInt(orderId+123);
  }

}
