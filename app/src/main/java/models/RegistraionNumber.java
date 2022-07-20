package models;

public class RegistraionNumber {
  public static int RegistraionNumber = 0;

  public static void setRegistraionNumber(int loadRegisatioNumber) {
    RegistraionNumber = loadRegisatioNumber;
  }

  public static int registraionNumber() {
    return RegistraionNumber;
  }

  public static int giveRegistraionNumber() {
    RegistraionNumber += 1;
    return RegistraionNumber;
  }
}
