package models;

import utils.RegistraionNumber;

public class Post {
  public static final String PROGRESS = "PROGRESS";
  public static final String COMPLETE = "COMPLETE";
  public static final String DELETION = "DELETION";

  private int views;
  private String likes;
  private String title;
  private String content;
  private String state;
  private String nickName;
  private String passWord;
  private int registrationNumber;
  private int like;

  public Post(String title, String content, String state, String nickName,
              String passWord, int registrationNumber) {
    this.title = title;
    this.content = content;
    this.state = state;
    this.nickName = nickName;
    this.passWord = passWord;
    this.views = 0;
    this.like = 0;

    this.registrationNumber = RegistraionNumber.giveRegistraionNumber();
  }

  public Post(String title, String content, String state, String nickName,
              String passWord, int registrationNumber, int views, int like) {
    this.title = title;
    this.content = content;
    this.state = state;
    this.nickName = nickName;
    this.passWord = passWord;
    this.views = views;
    this.like = like;

    this.registrationNumber = RegistraionNumber.giveRegistraionNumber();
  }

  public Post() {

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

  public void plusViews() {
    this.views += 1;
  }

  public int views() {
    return this.views;
  }

  public int like() {
    return this.like;
  }

  public void plusLike() {
    this.like += 1;
  }

  public void modifyTitle(String modifyTitle) {
    this.title = modifyTitle;
  }

  public void modifyContent(String modifyedContent) {
    this.content = modifyedContent;
  }

  public String toCsvRow() {
    return title + "," + content + "," + nickName + "," + passWord + ","
        + state + "," + registrationNumber + "," + views + "," + like;
  }

  @Override
  public String toString() {
    return title + "," + content + "," + nickName + "," + passWord + ","
        + state + "," + registrationNumber + "," + views + "," + like;
  }
}
