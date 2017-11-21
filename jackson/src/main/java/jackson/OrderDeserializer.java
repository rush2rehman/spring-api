package jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import jackson.entity.Order;
import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDeserializer extends StdDeserializer<Order> {

  protected OrderDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Order deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {

    Order order = new Order();
    order.setName("I am from custom deserializer");
    order.setOrderid(1);
    return order;

  }
}
