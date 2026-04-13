package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Article {
    int id;
    String title;
    String content;
    LocalDateTime createDate;
    LocalDateTime updateDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Article(int id, String title, String content) {
        LocalDateTime now = LocalDateTime.now();
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = now;
        this.updateDate = now;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }
}
