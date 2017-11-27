package restapi.Domain;

import com.fasterxml.jackson.annotation.JsonSetter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class Order {

  @NotNull
  private int id;
  @NotNull
  @Size(min = 1, max = 4)
  private String name;

  @JsonSetter("id")
  public void setOrderId(String orderId)
  {
    this.id = Integer.parseInt(orderId+123);
  }



}
