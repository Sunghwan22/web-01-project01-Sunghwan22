package utils;

public class RegistraionNumber {
  public static int RegistraionNumber = 0;

  public static int giveRegistraionNumber() {
    RegistraionNumber += 1;
    return RegistraionNumber;
  }

  public static void setRegistraionNumber(int loadRegisatioNumber) {
    RegistraionNumber = loadRegisatioNumber;
  }

  public static int registraionNumber() {
    return RegistraionNumber;
  }
}
