package about.regular;

import java.util.Arrays;

public class TestRegexPart2 {
  public static void main(String[] args) {
    String a = "Hello.there.hey";
    String[] words = a.split("\\.");
    System.out.println(Arrays.toString(words));
    String b = "Hello345623there577826734hey6795Dima";
    String[] words1 = b.split("\\d+");
    System.out.println(Arrays.toString(words1));

    String c = "Hello here hey Dima";
    c.replace(" ", "-");
    System.out.println(c);
    System.out.println(b.replaceAll("\\d+", "-"));
  }
}
