package models;

public class Comment {
  public static String DISPLAY = "DISPLAY";
  public static String DELETION = "DELETION";

  private String nickName;
  private String password;
  private String content;
  private String state;

  public Comment(String nickName, String password, String content,String state) {
    this.nickName = nickName;
    this.password = password;
    this.content = content;
    this.state = state;
  }

  public void delete() {
    this.state = DELETION;
  }

  public String toCsvRow() {
    return nickName + "," + password + "," + content + "," + state;
  }
}
