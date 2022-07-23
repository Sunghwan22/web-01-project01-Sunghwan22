package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
  @Test
  void creation() {
    Comment comment = new Comment("조성환", "123", "야이꽃미남아 때려치아라", "DISPLAY", 1);
  }

  @Test
  void changeState() {
    Comment comment = new Comment("조성환", "123", "야이꽃미남아 때려치아라", "DISPLAY", 1);
    comment.delete();

    assertEquals("DELETION", comment.state());
  }

  @Test
  void toCsvRow() {
    Comment comment = new Comment("조성환", "123", "야이꽃미남아 때려치아라", "DISPLAY", 1);

    assertEquals("조성환,123,야이꽃미남아 때려치아라,DISPLAY,1",comment.toCsvRow());
  }
}