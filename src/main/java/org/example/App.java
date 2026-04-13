package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    List<Article> articles = new ArrayList<>();
    int lastId = 0;

    void run() {

        while (true) {
            System.out.print("명령어: ");
            String cmd = scanner.nextLine().trim();
            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (cmd.equals("write")) {
                actionWrite();
            } else if (cmd.equals("list")) {
                actionList();
            } else if (cmd.startsWith("detail")) {
                actionDetail(cmd);
            }else if (cmd.startsWith("update")){
                actionUpdate(cmd);
            }else if (cmd.startsWith("delete")){
                actionDelete(cmd);
            }
        }
    }
    void actionWrite () {
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

    void actionList(){
        System.out.println("번호 | 제목 | 등록일");
        System.out.println("-----------------------------");
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.println("%d | %s | %s".formatted(article.id, article.title, article.createDate.format(article.formatter)));
        }
    }

    void actionDetail(String cmd) {
        int id = cmdSplitId(cmd);
        if(id == -1)return;

        Article article = findById(id);
        if(article == null)return;

        System.out.println("번호 : %d".formatted(article.id));
        System.out.println("제목 : %s".formatted(article.title));
        System.out.println("내용 : %s".formatted(article.content));
        System.out.println("등록일 : %s".formatted(article.createDate.format(article.formatter)));
    }

    void actionUpdate(String cmd) {
        int id = cmdSplitId(cmd);
        if(id == -1)return;

        Article article = findById(id);
        if(article == null)return;

        System.out.print("제목 (현재: %s): ".formatted(article.title));
        String title = scanner.nextLine().trim();

        System.out.print("내용 (현재: %s): ".formatted(article.content));
        String content = scanner.nextLine().trim();

        update(article, title, content);

        System.out.println("=> 게시글이 수정되었습니다.");
    }

    void update(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

    }

    void actionDelete(String cmd) {
        int id = cmdSplitId(cmd);
        if(id == -1)return;

        Article article = findById(id);
        if(article == null)return;

        delete(article);
        System.out.println("=> 게시글이 삭제되었습니다.");
    }

    void delete(Article article) {
        articles.remove(article);
    }

    Article findById(int id) {
        for (int i = 0; i <articles.size(); i++) {
            if (articles.get(i).id == id) {
                return articles.get(i);
            }
        }
        System.out.println("게시글이 존재하지 않습니다.");
        return null;
    }

    int cmdSplitId(String cmd) {
        String[] cmdBits = cmd.split(" ");
        if(cmdBits.length < 2 || cmdBits[1].isEmpty()) {
            System.out.println("명령어를 확인해주세요.");
            return -1;
        }

        try {
            return Integer.parseInt(cmdBits[1]);
        }catch (Exception e) {
            System.out.println("명령어를 확인해주세요.");
            return -1;
        }
    }
}
