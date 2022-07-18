package models;

public class UserInforMation {
  private final String nickName;
  private final String passWord;

  public UserInforMation(String nickName, String passWord) {
    this.nickName = nickName;
    this.passWord = passWord;
  }

  public String toCsvRow() {
    return nickName + "," + passWord;
  }
}
