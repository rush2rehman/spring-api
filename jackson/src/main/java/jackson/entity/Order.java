package jackson.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private int orderid;
  private String name;
  @JsonIgnore
  private String postcode;
  @JsonSetter("value")
  private int orderTotal;

  @Override
  public String toString()
  {
    return "name -->" +this.name + "\nid-->"+this.orderid+ "\npostcode-->"+this.postcode;
  }

}
