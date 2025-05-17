package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Lada", 14);
      Car car2 = new Car("Wuaz", 15);
      Car car3 = new Car("Kamaz", 1);

      User user1 = new User("Dmitriy", "Myasnikov", "user1@mail.ru");
      User user2 =  new User("Igor", "Rodjeykoyv", "user2@mail.ru");
      User user3 = new User("Oleg", "Krivov", "user3@mail.ru");

      userService.add(user1, car1);
      userService.add(user2, car2);
      userService.add(user3, car3);

      System.out.println("Все пользователи:");
      userService.listUsers().forEach(user -> {
         System.out.println(user);
         if (user.getCar() != null) {
            System.out.println("  Автомобиль: " + user.getCar().getModel() + " " + user.getCar().getSeries());
         }
      });

      System.out.println("\nПоиск пользователя по автомобилю (Lada 14):");
      User foundUser = userService.getUserByCarModelAndSeries("Lada", 14);
      System.out.println(foundUser);

      context.close();
   }
}
