package models;

public class Comment {
  public static String DISPLAY = "DISPLAY";
  public static String DELETION = "DELETION";

  private int registraionNumber;
  private String nickName;
  private String password;
  private String content;
  private String state;

  public Comment(String nickName, String password, String content,
                 String state, int registraionNumber) {
    this.nickName = nickName;
    this.password = password;
    this.content = content;
    this.state = state;
    this.registraionNumber = registraionNumber;
  }

  public String nickName() {
    return this.nickName;
  }

  public String password() {
    return this.password;
  }

  public String state() {
    return this.state;
  }

  public void delete() {
    this.state = DELETION;
  }

  public String content() {
    return this.content;
  }

  public int registraionNumber() {
    return this.registraionNumber;
  }

  public String toCsvRow() {
    return nickName + "," + password + "," + content + "," +
        state + "," + registraionNumber;
  }
}
