package ru.job4j.carsalesplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.carsalesplatform.model.Seller;
import ru.job4j.carsalesplatform.model.SellingCar;
import ru.job4j.carsalesplatform.service.ValidateSellingCar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UpdateCarController {

    @Autowired
    ValidateSellingCar validateSellingCar;

    @PostMapping("/update")
    public String updateCars(@ModelAttribute("carId") Integer carId,
                             HttpServletRequest req, Model model) {
        SellingCar car = validateSellingCar.findCarById(carId);
        Seller seller = car.getSeller();

        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (seller.getLogin().equals(login)) {
            validateSellingCar.changeSaleStatus(carId);
            model.addAttribute("carUpdate", "Car status is changed");
        } else {
            model.addAttribute("error", "Not enough rights");
        }
        model.addAttribute("allCars", validateSellingCar.findAllCars());
        return "CarsView";
    }

}
