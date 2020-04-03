package about.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
  public static void main(String[] args) {
    String text = "My.com, международный бренд Mail.Ru Group, представляет новый\n" +
          "бесплатный мобильный почтовый сервис с адресами в домене ml@mail.com. Аккаунты account@my.com можно\n" +
          "создавать с телефонов и планшетов на Android.\n" +
          "Благодаря тому что пользователям dima@mail.ru не придется придумывать\n" +
          "и запоминать пароль во время регистрации.";
    Pattern email = Pattern.compile("\\w*@\\w{2,10}\\.\\w{2,3}");
    Pattern email1 = Pattern.compile("\\w*@(my|mail)\\.(ru|com)");
    Matcher matcher = email1.matcher(text);
    while (matcher.find()){
      System.out.println(matcher.group());
    }
    email1 = Pattern.compile("(\\w+)@(my|mail)\\.(ru|com)");
    matcher = email1.matcher(text);
    while (matcher.find()){
      System.out.println(matcher.group(3));//group(3) - вывести группы с id=3
    }
  }
}
