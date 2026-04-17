package org.example.domain.article;

import org.example.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    public List<Article> getArticles() {
        return articles;
    }

    public void delete(Article article) {
        articles.remove(article);
    }

    public void update(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

    }

    Article findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.printf("=>%d번 게시글이 존재하지 않습니다.\n".formatted(id));
                    return null;
                });
    }

    Article write(String title, String content) {
        Article article = new Article(++lastId, title, content);
        articles.add(article);
        return article;
    }

}
