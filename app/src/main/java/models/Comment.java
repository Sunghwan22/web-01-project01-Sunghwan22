package models;

public class Comment {
  private String nickName;
  private String password;
  private String content;

  public Comment(String nickName, String password, String content) {

    this.nickName = nickName;
    this.password = password;
    this.content = content;
  }
}
