package com.myportfolio.web.controller;

import com.myportfolio.web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

//1. GET, POST를 처리할 메서드를 각각 만든다.
//2. POST를 처리할 메서드는 User를 받아서 유효성 검사를 한다.
//      이 때 메세지를 보내는데 URLEncoder를 사용한다.
//      유효성검사를 통과하지 못하면 회원가입화면으로 다시 가게 하고,
//      유효성검사를 통과하면 회원정보를 보여주도록 한다.

@Controller
@RequestMapping("/register")
public class RegisterController {
    @GetMapping("/add")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/add")
    public String save(User user, Model m) throws Exception {
        if(!isValid(user)) {
            String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
            m.addAttribute("msg", msg);
            return "redirect:/register/add";
        }
        return "registerInfo";
    }

    private boolean isValid(User user) {
        return true;
    }
}
