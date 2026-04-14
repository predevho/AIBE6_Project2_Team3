package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Article {
    private final int id;
    private String title;
    private String content;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Article(int id, String title, String content) {
        this.id = id;
        LocalDateTime now = LocalDateTime.now();
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

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getCreateDate() {
        return createDate.format(formatter);
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
}
