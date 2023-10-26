package com.example.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@Slf4j //log 터미널에 남기기
@RequestMapping("/news")
public class NewsWebController {
    final NewsDAO dao;

    @Value("${news.imgdir}")
    String imgDir;
    //생성자: Ctrl+Insert
    @Autowired
    public NewsWebController(NewsDAO dao) {
        this.dao = dao;
    }

    //등록, 목록보기, 삭제, 상세보기
    @GetMapping("/list")
    public String list(Model m) {
        List<News> newsList;
        try {
            newsList = dao.getAll();
            m.addAttribute("newList", newsList);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info(("뉴스 목록 가져오기 문제 발생"));
//            m.addAttribute("error", "뉴스 목록보기 정상 처리됨");
        }
        return "/news/newsList";
    }
}
