package hbvalidator;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
public class Order {
  @NotNull
  @Range(min = 1, max=10 )
  private int id;
  @NotNull
  private String name;

}
