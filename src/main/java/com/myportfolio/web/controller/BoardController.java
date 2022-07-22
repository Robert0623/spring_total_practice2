package com.myportfolio.web.controller;

import com.myportfolio.web.domain.BoardDto;
import com.myportfolio.web.domain.PageHandler;
import com.myportfolio.web.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//1. GET으로 boardList를 보여주는 메서드 작성
//2. 로그인체크로 확인.
//  2-1. 로그인을 안했으면 toURL로 URL값을 가지고 로그인 화면으로 이동
//  2-2. 로그인 했으면 게시판 화면으로 이동
//3. 로그인체크는
//  3-1. 세션을 얻어서
//  3-2. 세션에 id가 있는지 확인. 있으면 true 없으면 false를 반환.

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        try {
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);
            int rowCnt = boardService.remove(bno, writer);
            if(rowCnt!=1)
                throw new Exception("Remove Failed");
            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
        if (!loginCheck(request)) {
            //toURL로 loginForm.jsp로 URL을 넘겨준다.
            return "redirect:/login/login?toURL="+request.getRequestURL();
        }
        if(page==null) page=1;
        if(pageSize==null) pageSize=10;
        try {
            int totalCnt = boardService.getCount();
            PageHandler ph = new PageHandler(totalCnt, page, pageSize);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);

            List<BoardDto> list = boardService.getPage(map);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;
    }
}
