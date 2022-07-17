package models;

public class Post {
  public static final String PROGRESS = "PROGRESS";
  public static final String COMPLETE = "COMPLETE";
  public static final String DELETION = "DELETION";

  private String title;
  private String content;
  private String state;

  public Post(String title, String content, String state) {
    this.title = title;
    this.content = content;
    this.state = state;
  }

  public String title() {
    return title;
  }

  public String content() {
    return content;
  }

  public void delete() {
    this.state = "DELETION";
  }

  public void modifyTitle(String modifyTitle) {
    this.title = modifyTitle;
  }

  public void modifyContent(String modifyedContent) {
    this.content = modifyedContent;
  }
}
