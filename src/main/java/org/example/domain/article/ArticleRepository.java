package org.example.domain.article;

import org.example.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
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

    public Article findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Article write(String title, String content) {
        Article article = new Article(++lastId, title, content);
        articles.add(article);
        return article;
    }


}
