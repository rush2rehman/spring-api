package restapi;


import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

  public static void main(String...args)
  {
    SpringApplication.run(App.class,args);
  }

  @Bean
  public CommandLineRunner appCommandLineRunner(ApplicationContext ctx)
  {
    return args -> {

      String[] beanList = ctx.getBeanDefinitionNames();
      Arrays.sort(beanList);
      for(String beanName : beanList)
      {
        System.out.println(beanName);
      }

    };
  }


}
