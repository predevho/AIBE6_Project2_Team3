package org.example.domain.article;

import org.example.Article;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public Article write(String title, String content) {
        Article article = this.articleRepository.write(title, content);

        return article;
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }

    public void update(Article article, String title, String content) {
        this.articleRepository.update(article, title, content);
    }

    public Article findById(int id) {
        return articleRepository.findById(id);
    }


}
