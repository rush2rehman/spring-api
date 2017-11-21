package jackson.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class Employee {

  private Map<String, Object> propertyMap = new HashMap<>();

  @JsonAnySetter
  public void setproperties(String fieldName, Object value)
  {
    this.propertyMap.put(fieldName, value);
  }

  public Object getProperty(String fieldName)
  {
    return this.propertyMap.get(fieldName);
  }

}
