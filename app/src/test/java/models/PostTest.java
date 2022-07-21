package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
  @Test
  void creation() {
    Post post = new Post("안녕하세요", "으아아아", "PROGRESS", "안녕", "123", 1);

    assertEquals("안녕하세요", post.title());
    assertEquals("으아아아", post.content());
    assertEquals("PROGRESS", post.state());

  }

  @Test
  void changeState() {
    Post post = new Post("안녕하세요", "으아아아", Post.PROGRESS, "안녕", "123", 1);
    post.delete();

    assertEquals(Post.DELETION, post.state());
  }

  @Test
  void modifyPost() {
    Post post = new Post("안녕하세요", "으아아아", Post.PROGRESS, "안녕", "123", 1);
    post.modifyTitle("반갑습니다");
    post.modifyContent("상남자되버리기");

    assertEquals("반갑습니다", post.title());
    assertEquals("상남자되버리기", post.content());
  }

  @Test
  void toCsvRow() {
    Post post = new Post("안녕하세요", "으아아아", Post.PROGRESS, "안녕", "123", 1);

    assertEquals("안녕하세요,으아아아,안녕,123,PROGRESS,1", post.toCsvRow());
  }
  @Test
  void registraionNumber() {
    Post post1 = new Post("안녕하세요", "으아아아", Post.PROGRESS, "안녕", "123", 1);
    Post post2 = new Post("안녕하세요", "으아아아", Post.PROGRESS, "안녕", "123", 2);
    Post post3 = new Post("안녕하세요", "으아아아", Post.PROGRESS, "안녕", "123", 3);

    assertEquals(1, post1.registrationNumber());
    assertEquals(2, post2.registrationNumber());
    assertEquals(3, post3.registrationNumber());
  }
}
