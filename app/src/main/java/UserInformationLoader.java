import models.UserInforMation;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInformationLoader {
  public List<UserInforMation> loadInformation() throws FileNotFoundException {
    List<UserInforMation> userInforMations = new ArrayList<>();

    File file = new File("UserInformation.csv");

    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      UserInforMation userInforMation = ParseUserInformation(line);

      userInforMations.add(userInforMation);
    }
    return userInforMations;
  }

  public void userInformationWriter(List<UserInforMation> userInforMations)
      throws IOException {
    FileWriter fileWriter = new FileWriter("UserInformation.csv");
    for (UserInforMation userInforMation : userInforMations) {
      String line = userInforMation.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }

  private UserInforMation ParseUserInformation(String line) {
    String[] words = line.split(",");
    String nickName = words[0];
    String passWord = words[1];

    return new UserInforMation(nickName, passWord);
  }
}
