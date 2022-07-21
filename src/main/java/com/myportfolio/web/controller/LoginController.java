package com.myportfolio.web.controller;

import com.myportfolio.web.dao.UserDao;
import com.myportfolio.web.domain.User;
import com.myportfolio.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

//1. GET으로 loginForm으로 이동하는 메서드 작성
//2. POST로 작성
//      1. id와 pwd를 loginCheck메서드로 확인.
//      2-1. id와 pwd가 일치하지 않으면 loginForm으로 리다이렉트하고 에러메세지를 출력.
//      2-2. 일치하면 session에 id를 저장하고, rememberId를 확인.
//          rememberId가 true면 쿠키를 생성하고 toURL로 이동
//          rememberId가 false면 쿠키를 삭제하고 toURL로 이동
// 			    toURL이 null이거나 빈문자열이면 홈으로, 아니면 toURL로 가도록 한다.
//3. GET으로 로그아웃을 하는 메서드 작성
//	3-1 세션을 종료하고
//	3-2 홈으로 이동

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //1. 세션을 종료하고
        session.invalidate();
        //2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId, Model m, HttpServletResponse response, HttpSession session) throws Exception {
        if(!loginCheck(id, pwd)) {
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
            m.addAttribute("msg", msg);
            return "redirect:/login/login";
        }
        //id와 pwd가 일치하면 세션 객체에 id를 저장
        session.setAttribute("id", id);

        if(rememberId) {
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        //toURL이 null이거나 빈문자열이면 홈으로, 아니면 toURL로 가도록 한다.
        toURL = toURL==null || toURL.equals("") ? "/" : toURL;
        return "redirect:"+toURL;
    }

    private boolean loginCheck(String id, String pwd) {
        User user = null;
        try {
            user = userService.read(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return user != null && user.getPwd().equals(pwd);
//        return "asdf".equals(id) && "1234".equals(pwd);
    }
}
