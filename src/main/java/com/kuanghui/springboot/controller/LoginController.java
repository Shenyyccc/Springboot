package com.kuanghui.springboot.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.Properties;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){
        if(!StringUtils.isEmpty(username)&&password.equals("123")){
            session.setAttribute("LoginUser",username);
            return "redirect:/main.html";
        }
        model.addAttribute("msg","密码错误");
        return "index";
    }

    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index.html";
    }

}
