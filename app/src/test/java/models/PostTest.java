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
    post.modifyContent("으아아아");

    assertEquals("반갑습니다", post.title());
    assertEquals("으아아아아", post.content());
  }
  @Test
  void toCsvRow() {
    Post post = new Post("안녕하세요", "으아아아", Post.PROGRESS, "안녕", "123", 4,0,0);

    assertEquals("안녕하세요,으아아아,안녕,123,PROGRESS,4,0,0", post.toCsvRow());
  }
}
