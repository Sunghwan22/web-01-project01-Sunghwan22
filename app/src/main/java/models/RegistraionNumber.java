package models;

public class RegistraionNumber {
  private static int RegistraionNumber = 0;

  public static int giveRegistraionNumber() {
    RegistraionNumber += 1;
    return RegistraionNumber;
  }
}
