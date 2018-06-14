package com.codegym.myblog.controller;

import com.codegym.myblog.model.Login;
import com.codegym.myblog.model.User;
import com.codegym.myblog.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("login", new Login());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView submitLogin(@ModelAttribute("login") Login login, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userService.findByUsername(login.getUsername());
        ModelAndView modelAndView = null;
        if (user != null && login.getPassword().equals(user.getPassword())) {
            modelAndView = new ModelAndView("/index");
            session.setAttribute("user", user);
        } else {
            modelAndView = new ModelAndView("/login");
            modelAndView.addObject("message", "Login fail!");
        }
        return modelAndView;
    }
}
