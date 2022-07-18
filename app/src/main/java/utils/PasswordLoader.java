package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordLoader {
  public List<String> loadPassword() throws FileNotFoundException {
    List<String> passwords = new ArrayList<>();

    File file = new File("Passwords.csv");

    Scanner scanner = new Scanner(file);

    while(scanner.hasNextLine()){
      String line = scanner.nextLine();

      passwords.add(line);

    }
    return passwords;
  }

  public void passwordWriter(List<String> passwords) throws IOException {
    FileWriter fileWriter = new FileWriter("Passwords.csv");
    for (String line : passwords) {
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }
}
