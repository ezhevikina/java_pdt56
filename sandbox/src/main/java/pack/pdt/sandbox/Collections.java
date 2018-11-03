package pack.pdt.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Ruby","Java","C++","Python"};

    List<String> languages = new ArrayList<>();
    languages.add("Ruby");
    languages.add("Java");

    List<String> languages1 = Arrays.asList("Ruby","Java","Python");

    for (String l : languages1) {
      System.out.println("I want to learn " + l);
    }
  }
}
