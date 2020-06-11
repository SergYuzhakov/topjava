package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static Logger log;
    private MealDao mealDao;

    @Override
    public void init() throws ServletException {
        log = getLogger(MealServlet.class);
        mealDao = new MealDaoImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        action = (action == null) ? "" : action;
        int id;
        Meal meal;
        switch (action) {

            case "delete":
                id = getId(req);
                log.info("Delete meal with id = {}",id);
                mealDao.removeMeal(id);
                resp.sendRedirect("meals");
                break;
            case "edit":
                id = getId(req);
                log.debug("Update meal with id = {}",id);
                meal = mealDao.getById(id);
                req.setAttribute("action", "Edit");
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
                break;
            case "add":
                log.debug("Add new meal");
                meal = new Meal(0, LocalDateTime.now(),"", 1000);
                req.setAttribute("meal", meal);
                req.setAttribute("action", "Add");
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
                break;

            default:
                log.info("Get Meals & forward to meals.jsp");
                List<MealTo> mealTos = MealsUtil.filteredByStreams(mealDao.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
                RequestDispatcher requestDisp = req.getRequestDispatcher("meals.jsp");
                req.setAttribute("meals", mealTos);
                requestDisp.forward(req, resp);
        }
    }

    private int getId(HttpServletRequest req){
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = req.getParameter("id").isEmpty() ? 0 : Integer.parseInt(req.getParameter("id"));
        int calories = req.getParameter("calories").isEmpty() ? 0 : Integer.parseInt(req.getParameter("calories"));
        String date = req.getParameter("date");
        String description = req.getParameter("description");
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        log.info("Get data - {}, {}, {}, {}",id,calories,date,description);
        Meal meal = new Meal(id, localDateTime, description, calories);
        mealDao.save(meal);
        log.debug("Redirect to meals");
        resp.sendRedirect("meals");
    }
}
