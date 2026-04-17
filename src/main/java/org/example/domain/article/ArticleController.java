package org.example.domain.article;

import org.example.Article;
import org.example.Rq;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ArticleController {
    private final ArticleService articleService;
    private final Scanner scanner;

    public ArticleController(Scanner scanner) {
        this.articleService = new ArticleService();
        this.scanner = scanner;
    }

    public void writeArticle() {
        System.out.print("제목 : ");
        String title = scanner.nextLine().trim();

        System.out.print("내용 : ");
        String content = scanner.nextLine().trim();

        articleService.write(title, content);

        System.out.println("=> 게시글이 등록되어습니다.");
    }


    public void listArticles() {
        List<Article> articles = articleService.getArticles();
        System.out.println("번호 | 제목 | 등록일");
        System.out.println("-----------------------------");
        IntStream.range(0, articles.size())
                .map(i -> articles.size() - 1 - i)
                .mapToObj(articles::get)
                .forEach(
                        article -> System.out.printf("%d | %s | %s\n".formatted(article.getId(), article.getTitle(), article.getCreateDate()))
                );
    }

    public void showDetail(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) return;

        Article article = articleService.findById(id);
        if (article == null) return;

        System.out.printf("번호 : %d\n".formatted(article.getId()));
        System.out.printf("제목 : %s\n".formatted(article.getTitle()));
        System.out.printf("내용 : %s\n".formatted(article.getContent()));
        System.out.printf("등록일 : %s\n".formatted(article.getCreateDate()));
    }

    public void updateArticle(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) return;

        Article article = articleService.findById(id);
        if (article == null) return;

        System.out.printf("제목 (현재: %s): \n".formatted(article.getTitle()));
        String title = scanner.nextLine().trim();

        System.out.printf("내용 (현재: %s): \n".formatted(article.getContent()));
        String content = scanner.nextLine().trim();

        articleService.update(article, title, content);

        System.out.printf("=>%d번 게시글이 존재하지 않습니다.\n".formatted(id));
    }



    public void deleteArticle(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) return;

        Article article = articleService.findById(id);

        if (article == null) return;

        articleService.delete(article);
        System.out.printf("=>%d번 게시글이 존재하지 않습니다.\n".formatted(id));
    }





}
