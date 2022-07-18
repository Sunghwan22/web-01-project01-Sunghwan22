package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
  @Test
  void creation() {
    Post post = new Post("안녕하세요", "으아아아", "PROGRESS");

    assertEquals("안녕하세요",post.title());
    assertEquals("으아아아",post.content());
    assertEquals("PROGRESS",post.state());

  }
  @Test
  void changeState() {
    Post post = new Post("안녕하세요", "으아아아", Post.PROGRESS);
    post.delete();

    assertEquals(Post.DELETION , post.state());
  }
}
