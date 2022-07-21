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
  private int registrationNumber;

  //todo 등록번호를 여기서 여기서 매개변수로 받아서 쓰는게 맞을까?
  public Post(String title, String content, String state, String nickName,
              String passWord, int registrationNumber) {
    this.title = title;
    this.content = content;
    this.state = state;
    this.nickName = nickName;
    this.passWord = passWord;

    this.registrationNumber = RegistraionNumber.giveRegistraionNumber();
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

  public int registrationNumber() {
    return this.registrationNumber;
  }

  public void delete() {
    this.state = DELETION;
  }

  public void complete() {
    this.state = COMPLETE;
  }

  public void modifyTitle(String modifyTitle) {
    this.title = modifyTitle;
  }

  public void modifyContent(String modifyedContent) {
    this.content = modifyedContent;
  }

  public String toCsvRow() {
    return title + "," + content + "," + nickName + "," + passWord + ","
        + state +  "," + registrationNumber ;
  }
}
