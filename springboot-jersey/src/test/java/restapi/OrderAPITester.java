package restapi;

import static com.jayway.restassured.path.json.JsonPath.given;

import com.jayway.restassured.builder.RequestSpecBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    classes = {App.class}
    )
public class OrderAPITester {

  private RequestSpecification requestSpecification;

  @Before
  public void init()
  {
    requestSpecification = new RequestSpecBuilder().setPort(8080).setBasePath("/").build();
  }

  @Test
  public void index_returns_hello_world()
  {

    given()
        .spec(requestSpecification)
        .when()
        .get("index")
        .then()
        .body(is("Hello World!"));

  }

  @Test
  public void post_order_returns_201()
  {
    given()
        .spec(requestSpecification)
        .contentType("application/json\r\n")
        .body("{            \"id\" : \"1\",      \"name\" : \"n1\"}")
        .when()
        .post("Order")
        .then()
        .statusCode(is(201));
  }

  @Test
  public void test_id_validation()
  {
    given()
        .spec(requestSpecification)
        .contentType("application/json\r\n")
        .body("{            \"id\" : \"1\",      \"name\" : \"n1tuewt\"}")
        .when()
        .post("Order")
        .then()
        .statusCode(400);
  }

}
