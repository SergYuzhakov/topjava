package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
// Test MealResrController metods
            MealRestController mrc = appCtx.getBean(MealRestController.class);
            Meal meal = mrc.create(new Meal(LocalDateTime.of(2020, Month.MARCH, 29, 10, 0), "Завтрак", 499,1));
            System.out.println(meal);
            mrc.get(meal.getId());
            mrc.getAll().stream().forEach(System.out::println);
            Meal meal2 = new Meal(8,LocalDateTime.of(2019, Month.MARCH, 29, 10, 0), "Ужин", 1099, 1);
            mrc.update(meal2, meal.getId());

            System.out.println(mrc.get(meal.getId()));
            mrc.delete(meal.getId());

        }
    }
}
