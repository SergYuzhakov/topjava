package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.TestMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends MealServiceTest {

    public static TestMatcher<Meal> MEAL_MATCHER_WITH_USER = TestMatcher.usingFieldsComparator();

    @Test
    public void getMealWithUser() throws Exception {
        Meal actual = service.getMealWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        Meal expected = ADMIN_MEAL1;
        expected.setUser(ADMIN);
        MEAL_MATCHER_WITH_USER.assertMatch(actual, expected);

    }

    @Test
    public void getNotFoundMealUser() throws Exception {
        assertThrows(NotFoundException.class, () -> service.getMealWithUser(NOT_FOUND, USER_ID));
    }

}
