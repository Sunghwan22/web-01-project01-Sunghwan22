package utils;

import models.Comment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommentLoader {
  public List<Comment> loadComment() throws FileNotFoundException {
    List<Comment> comments = new ArrayList<>();

    File file = new File("Comments.csv");

    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      Comment comment = parseComment(line);

      comments.add(comment);
    }
    return comments;
  }
  public void commentWriter(List<Comment> comments) throws IOException {
    FileWriter fileWriter = new FileWriter("Comments.csv");
    for(Comment comment : comments){
      String line = comment.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }

  private Comment parseComment(String line) {
    String[] words = line.split(",");
    String nickname = words[0];
    String password = words[1];
    String content = words[2];
    String state = words[3];
    //todo 고유 번호 값을 받으면 인트형에서 스트링으로 바꿔줘야함
    return new Comment(nickname, password, content, state);
  }
}
