package jackson;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jackson.entity.Employee;
import jackson.entity.Order;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JacksonFeaturesTest {

  private String orderString = "[{\"orderid\" : \"1\", \"name\" : \"n1\"},"
      + " {\"orderid\" : \"2\", \"name\" : \"n2\"}]";

  private String unknownProperties = "{\"orderid\" : \"1\", \"name\" : \"n1\" , \"unknown\" : \"xyz\"}";

  private String setterString = "{\"orderid\" : \"1\", \"name\" : \"n1\" , \"unknown\" : \"xyz\" , \"value\" : \"10\"}";

  private String empString = "[{\"orderid\" : \"1\", \"name\" : \"n1\"},"
      + " {\"orderid\" : \"2\", \"name\" : \"n2\"}]";

  private ObjectMapper mapper;

  @BeforeEach
  void setUp()
  {
    mapper = new ObjectMapper();

  }

  @Test
  void string_to_object_array() throws  IOException
  {
    Order[] order = mapper.readValue(orderString, Order[].class);
    System.out.println(order.toString());
    for(int i =1; i <= order.length; i++)
    {
      assertEquals(i, order[i-1].getOrderid());
      assertEquals("n"+i, order[i-1].getName());
    }

  }

  @Test
  void ignore_unknown_properties_while_deserialization() throws IOException
  {
    mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES,false);
    Order order = mapper.readValue(unknownProperties, Order.class);
    System.out.println(order.toString());
    assertEquals(1, order.getOrderid());
    assertEquals("n1", order.getName());
  }

  @Test
  void custom_deserializer() throws IOException
  {
    SimpleModule module = new SimpleModule("OrderDeserializer");
    module.addDeserializer(Order.class, new OrderDeserializer(Order.class));
    mapper.registerModule(module);
    Order order = mapper.readValue(orderString, Order.class);
    System.out.println(order.toString());
    assertEquals(1, order.getOrderid());
    assertTrue(order.getName().startsWith("I am from custom"));

  }

  @Test
  void serialization_string() throws IOException
  {
    int orderid = 0;
    String name = "";

    Order order = new Order(1,"n1","",0);


    String jsonString = mapper.writeValueAsString(order);
    System.out.println(jsonString);

    JsonFactory factory = new JsonFactory();
    JsonParser parser = factory.createParser(jsonString);
    while(!parser.isClosed())
    {
      JsonToken token = parser.nextToken();
      if(token.FIELD_NAME.equals(token))
      {
        String fieldName = parser.getCurrentName();

        token = parser.nextToken();
        if("orderid".equalsIgnoreCase(fieldName))
        {
          orderid = parser.getValueAsInt();
        }
        if("name".equalsIgnoreCase(fieldName))
        {
          name = parser.getValueAsString();
        }
      }
    }

    assertEquals(1, orderid);
    assertEquals("n1",name);

  }

  @Test
  void json_generator() throws IOException {
    JsonFactory factory = new JsonFactory();
    OutputStream outputStream = new ByteArrayOutputStream();
    JsonGenerator generator = factory.createGenerator(outputStream);

    generator.writeStartObject();
    generator.writeNumberField("orderid", 1);
    generator.writeStringField("name", "n1");
    generator.writeEndObject();

    generator.close();

    String jsonString = outputStream.toString();

    System.out.println(jsonString);

    Order order = mapper.readValue(jsonString, Order.class);
    assertEquals(1, order.getOrderid());
    assertEquals("n1", order.getName());


  }

  @Test
  void json_ignore() throws IOException {
    Order order = new Order(1,"n1","AB7",0);

    String jsonString = mapper.writeValueAsString(order);

    System.out.println(jsonString);

    Map<String, Object> jsonMap = mapper.readValue(jsonString,
        new TypeReference<Map<String,Object>>(){});

    assertEquals(1,jsonMap.get("orderid"));
    assertEquals("n1",jsonMap.get("name"));
    assertEquals(null,jsonMap.get("postcode"));

  }

  @Test
  void json_setter() throws IOException {

    mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    Order order = mapper.readValue(setterString, Order.class);

    assertEquals(10, order.getOrderTotal());

  }

  @Test
  void json_any_setter() throws IOException {

    Employee[] employees = mapper.readValue(empString, Employee[].class);

    for(int i =1; i <= employees.length; i++)
    {
      assertEquals(""+i, employees[i-1].getProperty("orderid"));
      assertEquals("n"+i, employees[i-1].getProperty("name"));
    }

  }





}
