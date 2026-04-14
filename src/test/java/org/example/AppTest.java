package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    @DisplayName("게시글 작성 시 제목이 저장된다")
    void t1() {
        Article article = app.write("제목1", "내용1");

        assertThat(article.getTitle()).isEqualTo("제목1");
    }

    @Test
    @DisplayName("게시글 작성 시 내용이 저장된다")
    void t2() {
        Article article = app.write("제목1", "내용1");

        assertThat(article.getContent()).isEqualTo("내용1");
    }

    @Test
    @DisplayName("게시글 작성 시 id가 1부터 시작한다")
    void t3() {
        Article article = app.write("제목1", "내용1");

        assertThat(article.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 두 개 작성 시 id가 순서대로 증가한다")
    void t4() {
        Article article1 = app.write("제목1", "내용1");
        Article article2 = app.write("제목2", "내용2");

        assertThat(article1.getId()).isEqualTo(1);
        assertThat(article2.getId()).isEqualTo(2);
    }

    @Test
    @DisplayName("id로 게시글을 조회할 수 있다")
    void t5() {
        app.write("제목1", "내용1");

        Article found = app.findById(1);

        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("제목1");
    }

    @Test
    @DisplayName("존재하지 않는 id로 조회 시 null을 반환한다")
    void t6() {
        app.write("제목1", "내용1");

        Article found = app.findById(999);

        assertThat(found).isNull();
    }

    @Test
    @DisplayName("게시글 제목을 수정할 수 있다")
    void t7() {
        Article article = app.write("제목1", "내용1");

        app.update(article, "수정된 제목", "내용1");

        assertThat(article.getTitle()).isEqualTo("수정된 제목");
    }

    @Test
    @DisplayName("게시글 내용을 수정할 수 있다")
    void t8() {
        Article article = app.write("제목1", "내용1");

        app.update(article, "제목1", "수정된 내용");

        assertThat(article.getContent()).isEqualTo("수정된 내용");
    }

    @Test
    @DisplayName("게시글을 삭제하면 조회 시 null을 반환한다")
    void t9() {
        Article article = app.write("제목1", "내용1");

        app.delete(article);

        assertThat(app.findById(1)).isNull();
    }

    @Test
    @DisplayName("게시글 삭제 후 다른 게시글은 남아있다")
    void t10() {
        Article article1 = app.write("제목1", "내용1");
        app.write("제목2", "내용2");

        app.delete(article1);

        assertThat(app.findById(2)).isNotNull();
        assertThat(app.findById(2).getTitle()).isEqualTo("제목2");
    }

    @Test
    @DisplayName("게시글 등록일이 null이 아니다")
    void t11() {
        Article article = app.write("제목1", "내용1");

        assertThat(article.getCreateDate()).isNotNull();
    }
}