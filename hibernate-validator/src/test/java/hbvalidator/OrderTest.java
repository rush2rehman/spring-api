package hbvalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorFactoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {

  private Validator validator;

  @BeforeEach
  void init()
  {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void name_should_not_be_null()
  {
    Order order = new Order( 1,null);
    Set<ConstraintViolation<Order>> constraintViolationSet =  validator.validate(order);

    assertEquals(1, constraintViolationSet.size());
    assertEquals("must not be null", constraintViolationSet.iterator().next().getMessage());

  }

}
