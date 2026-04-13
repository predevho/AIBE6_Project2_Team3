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
                System.out.print("제목 : ");
                String title = scanner.nextLine().trim();

                System.out.print("내용 : ");
                String content = scanner.nextLine().trim();

                int id = ++lastId;

                Article article = new Article(id, title, content);

                articles.add(article);
                System.out.println("=> 게시글이 등록되어습니다.");
            } else if (cmd.equals("list")) {
                System.out.println("번호 | 제목 | 등록일");
                System.out.println("-----------------------------");
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    System.out.println("%d | %s | %s".formatted(article.id, article.title, article.createDate.format(article.formatter)));
                }
            } else if (cmd.startsWith("detail")) {
                String[] cmdBits = cmd.split(" ");
                if(cmdBits.length < 2 || cmdBits[1].isEmpty()) {
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }

                int id;
                try {
                    id = Integer.parseInt(cmdBits[1]);
                }catch (Exception e) {
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }

                Article article = null;

                for (int i = 0; i <articles.size(); i++) {
                    if (articles.get(i).id == id) {
                        article  = articles.get(i);
                    }
                }

                if(article == null) {
                    System.out.println("게시글이 존재하지 않습니다.");
                    continue;
                }

                System.out.println("번호 : %d".formatted(article.id));
                System.out.println("제목 : %s".formatted(article.title));
                System.out.println("내용 : %s".formatted(article.content));
                System.out.println("등록일 : %s".formatted(article.createDate.format(article.formatter)));
            }else if (cmd.startsWith("update")){
                String[] cmdBits = cmd.split(" ");
                if(cmdBits.length < 2 || cmdBits[1].isEmpty()) {
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }

                int id;
                try {
                    id = Integer.parseInt(cmdBits[1]);
                }catch (Exception e) {
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }

                Article article = null;

                for (int i = 0; i <articles.size(); i++) {
                    if (articles.get(i).id == id) {
                        article  = articles.get(i);
                    }
                }

                if(article == null) {
                    System.out.println("게시글이 존재하지 않습니다.");
                    continue;
                }

                System.out.print("제목 (현재: %s): ".formatted(article.title));
                article.title = scanner.nextLine().trim();

                System.out.print("내용 (현재: %s): ".formatted(article.content));
                article.content = scanner.nextLine().trim();
                System.out.println("=> 게시글이 수정되었습니다.");
            }else if (cmd.startsWith("delete")){
                String[] cmdBits = cmd.split(" ");
                if(cmdBits.length < 2 || cmdBits[1].isEmpty()) {
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }

                int id;
                try {
                    id = Integer.parseInt(cmdBits[1]);
                }catch (Exception e) {
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }

                Article article = null;

                for (int i = 0; i <articles.size(); i++) {
                    if (articles.get(i).id == id) {
                        article  = articles.get(i);
                    }
                }

                if(article == null) {
                    System.out.println("게시글이 존재하지 않습니다.");
                    continue;
                }
                articles.remove(article);
                System.out.println("=> 게시글이 삭제되었습니다.");
            }
        }
    }
}
