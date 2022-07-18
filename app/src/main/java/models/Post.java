package models;

public class Post {
  public static final String PROGRESS = "PROGRESS";
  public static final String COMPLETE = "COMPLETE";
  public static final String DELETION = "DELETION";

  private String title;
  private String content;
  private String state;
  private String nickName;
  private String passWord;

  public Post(String title, String content, String state, String nickName, String passWord) {
    this.title = title;
    this.content = content;
    this.state = state;
    this.nickName = nickName;
    this.passWord = passWord;
  }

  public String title() {
    return title;
  }

  public String content() {
    return content;
  }

  public String state() {
    return this.state;
  }

  public String nickName() {
    return this.nickName;
  }

  public String passWord() {
    return this.passWord;
  }

  public void delete() {
    this.state = DELETION;
  }

  public void modifyTitle(String modifyTitle) {
    this.title = modifyTitle;
  }

  public void modifyContent(String modifyedContent) {
    this.content = modifyedContent;
  }

  public String toCsvRow() {
    return title + "," + content + "," + nickName + "," + passWord + "," + state;
  }
}
