package com.example.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class NewsApiController {
    final NewsDAO dao;

    public NewsApiController(NewsDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/list")
    public List<News> list() {
        List<News> newsList = new ArrayList<>(); //null이면 expetion 위험
        try {
            newsList = dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            log.info(("뉴스 목록 가져오기 문제 발생"));
            //error 객체 추가
        }
        return newsList;
    }

//    @GetMapping("/{aid}")
//    public News getNews(PathVariable("aid") int aid) {
//        News news = new News();
//        try {
//            news = getNews();
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info(("뉴스 가져오기 문제 발생"));
//        }
//    }
}
