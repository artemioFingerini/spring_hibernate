package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW",7));
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", new Car("Mazda",626));
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", new Car("Reno",19));
      User user4 = new User("User4", "Lastname4", "user4@mail.ru",new Car("BMW",3));
      User user5 = new User("User5", "Lastname5", "user5@mail.ru",new Car("Mercedes",400));
      User user6 = new User("User5", "Lastname5", "user5@mail.ru");
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(user5);
      userService.add(user6);
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println((user.getCar() != null) ? "User has a car: " + user.getCar() : "User has no car");
         System.out.println();
      }
      userService.getUserByModelAndSeries("Mazda",626);

      context.close();
   }
}
