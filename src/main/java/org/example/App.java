package org.example;

import org.example.domain.article.ArticleController;
import org.example.domain.system.SystemController;

import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);


    void run() {
        SystemController systemController = new SystemController();
        ArticleController articleController = new ArticleController(scanner);
        while (true) {
            System.out.print("명령어: ");
            String cmd = scanner.nextLine().trim();


            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "write" -> articleController.writeArticle();
                case "list" -> articleController.listArticles();
                case "detail" -> articleController.showDetail(rq);
                case "update" -> articleController.updateArticle(rq);
                case "delete" -> articleController.deleteArticle(rq);
                case "exit" -> {
                    systemController.actionExit();
                    return;
                }
            }
        }
    }


}
