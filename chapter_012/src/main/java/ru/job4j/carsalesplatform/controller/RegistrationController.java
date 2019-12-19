package ru.job4j.carsalesplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.carsalesplatform.model.Seller;
import ru.job4j.carsalesplatform.service.ValidateSeller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    ValidateSeller validateSeller;

    @GetMapping("/signin")
    public ModelAndView loginView() {
        return new ModelAndView("LoginView", "seller", new Seller());
    }

    @PostMapping("/signin")
    public String loginPost(@ModelAttribute("seller") Seller sellerFront,
                            HttpServletRequest req,
                            Model model) {
        Seller seller = validateSeller.findSellerByLogin(sellerFront.getLogin());
        if (seller != null && seller.getPassword().equals(sellerFront.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("login", sellerFront.getLogin());
            return "redirect: /carslist";

        } else {
            model.addAttribute("error", "Credential invalid");
            return "LoginView";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/signin";
    }

}
