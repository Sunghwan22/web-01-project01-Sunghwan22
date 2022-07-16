package models;

public class Transaction {
  private String type;
  private int amount;

  public Transaction(String type, int amount) {


    this.type = type;
    this.amount = amount;
  }


  public String type() {
    return type;
  }

  public Integer amount() {
    return amount;
  }

  public int process(int amount) {
    if (type.equals("입금")) {
      return this.amount + amount;
    }
    if (type.equals("출금")) {
      return amount - this.amount;
    }
    if (type.equals("잔액")) {
      return this.amount;
    }
    return amount;
  }
}