package com.myportfolio.web.controller;

import com.myportfolio.web.dao.UserDao;
import com.myportfolio.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

//[RegisterController.java]
//1. GET, POST를 처리할 메서드를 각각 만든다.
//2. POST를 처리할 메서드는 User를 받아서 유효성 검사를 한다.
//  	이 때 메세지를 보내는데 URLEncoder를 사용한다.
//  	유효성검사를 통과하지 못하면 회원가입화면으로 다시 가게 하고,
//  	유효성검사를 통과하면 회원정보를 보여주도록 한다.
//3. 검증할 객체의 바로뒤에 BindingResult를 붙인다.
//4. @InitBinder를 붙여서 WebDataBinder를 매개변수로 갖는 메서드를 만들어서 타입변환을 위해 CustomEditor를 등록한다.
//5. 컨트롤러에서 isValid메서드를 사용한 유효성체크 대신,
//	 UserValidator 클래스를 작성해서 자동으로 등록한다.(@Initbinder, @Valid) (수동 등록도 가능)
//6. error가 있으면 registerForm으로 가게 한다.

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserDao userDao;

    final int FAIL = 0;

    //@InitBinder로 CustomEditor로 Date타입 패턴을 등록 - 타입 변환
    @InitBinder
    public void toDate(WebDataBinder binder) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
        //UserValidator를 WebDataBinder의 로컬 validator로 등록
        binder.setValidator(new UserValidator());
    }

    @GetMapping("/add")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/add")
    //BindingResult를 사용해서 Controller에서 에러를 처리하도록 함.
    //@Valid를 앞에 붙여서 검증할 객체를 등록
    public String save(@Valid User user, BindingResult result, Model m) throws Exception {
        System.out.println("result = " + result);
        //수동 검증 - Vlalidator를 직접 생성하고, validate()를 직접 호출
//        UserValidator userValidator = new UserValidator();
//        userValidator.validate(user, result);

        //User객체를 검증한 결과 에러가 있으면 registerForm을 이용해서 에러를 보여줌.
        if (result.hasErrors()) {
            return "registerForm";
        }

//        if(!isValid(user)) {
//            String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
//            m.addAttribute("msg", msg);
//            return "redirect:/register/add";
//        }
        int rowCnt = userDao.insertUser(user);

        if(rowCnt!=FAIL) {
            return "registerInfo";
        }

        return "registerForm";
    }

//    private boolean isValid(User user) {
//        return true;
//    }
}
