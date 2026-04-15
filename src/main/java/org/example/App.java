package org.example;

import org.example.domain.system.SystemController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    void run() {
        SystemController systemController = new SystemController();

        while (true) {
            System.out.print("명령어: ");
            String cmd = scanner.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "write" -> writeArticle();
                case "list" -> listArticles();
                case "detail" -> showDetail(rq);
                case "update" -> updateArticle(rq);
                case "delete" -> deleteArticle(rq);
                case "exit" -> {
                    systemController.actionExit();
                    return;
                }
            }
        }
    }

    void writeArticle() {
        System.out.print("제목 : ");
        String title = scanner.nextLine().trim();

        System.out.print("내용 : ");
        String content = scanner.nextLine().trim();

        write(title, content);

        System.out.println("=> 게시글이 등록되어습니다.");

    }

    Article write(String title, String content) {
        Article article = new Article(++lastId, title, content);
        articles.add(article);
        return article;
    }

    void listArticles() {
        System.out.println("번호 | 제목 | 등록일");
        System.out.println("-----------------------------");
        IntStream.range(0, articles.size())
                .map(i -> articles.size() - 1 - i)
                .mapToObj(articles::get)
                .forEach(
                        article -> System.out.printf("%d | %s | %s\n".formatted(article.getId(), article.getTitle(), article.getCreateDate()))
                );

    }

    void showDetail(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) return;

        Article article = findById(id);
        if (article == null) return;

        System.out.printf("번호 : %d\n".formatted(article.getId()));
        System.out.printf("제목 : %s\n".formatted(article.getTitle()));
        System.out.printf("내용 : %s\n".formatted(article.getContent()));
        System.out.printf("등록일 : %s\n".formatted(article.getCreateDate()));
    }

    void updateArticle(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) return;

        Article article = findById(id);
        if (article == null) return;

        System.out.printf("제목 (현재: %s): \n".formatted(article.getTitle()));
        String title = scanner.nextLine().trim();

        System.out.printf("내용 (현재: %s): \n".formatted(article.getContent()));
        String content = scanner.nextLine().trim();

        update(article, title, content);

        System.out.printf("=>%d번 게시글이 존재하지 않습니다.\n".formatted(id));
    }

    void update(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

    }

    void deleteArticle(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) return;

        Article article = findById(id);

        if (article == null) return;

        delete(article);
        System.out.printf("=>%d번 게시글이 존재하지 않습니다.\n".formatted(id));
    }

    void delete(Article article) {
        articles.remove(article);
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

}
