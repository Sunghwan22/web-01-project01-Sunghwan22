package models;

public class Post {
  public static final String PROGRESS = "PROGRESS";
  public static final String COMPLETE = "COMPLETE";

  private String title;
  private String content;
  private String state;

  public Post(String title, String content, String state) {
    this.title = title;
    this.content = content;
    this.state = state;
  }

  public void progressState() {

  }
}
