package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public void delete(int id) {
        log.info("Delete meal id = {}, userID = {}", id, SecurityUtil.authUserId());
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal create(Meal meal) {
        log.info("Create meal for userID = {}", SecurityUtil.authUserId());
        return service.create(meal, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        log.info("Get Meal id = {}, for userId = {}", id, SecurityUtil.authUserId());
        return service.get(id, SecurityUtil.authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("Update meal with id = {}, for userId = {}", id, SecurityUtil.authUserId());
        service.update(meal, SecurityUtil.authUserId());
    }

    public List<MealTo> getAll() {
        log.info("Get All Meals from service");
        return service.getAll(SecurityUtil.authUserId());
    }

    public List<MealTo> getAllFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        log.info("GetAllFiltered from service by Date start = {},  end = {} and Time start = {}, end = {}", startDate, endDate, startTime, endTime);
        return service.getAllFiltered(SecurityUtil.authUserId(),startDate, endDate, startTime, endTime);
    }

}