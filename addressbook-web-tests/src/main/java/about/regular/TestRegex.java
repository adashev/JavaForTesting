package about.regular;

public class TestRegex {
  public static void main(String[] args) {
    String a = "+23578";
    boolean match = a.matches("\\d"); // \\d - одна цифра
    boolean match1 = a.matches("-\\d*"); // + это 'одна или более';  * это '0 или более'
    boolean match2 = a.matches("-?\\d*"); // ? это '0 или 1 символов до'
    boolean match3 = a.matches("(\\+|-)?\\d*"); // ( +|- )  это 'или + или -'
    String b = "at7yu8ghre67468";
    boolean match4 = b.matches("t\\d*");
    boolean match5 = b.matches("[abctd]\\d*");//[abctd] это 'что-то из перечисленного в квад. скобках'
  // [0-9] это все цифры. То же самое что \\d
    boolean match6 = b.matches("(a|t)\\d*");
    boolean match7 = b.matches("[a-zA-Z789]+\\d+");// [a-zA-Z] это все английские буквы. (a|b|c) = [abc]
  // [a-zA-Z]+ это по сути любое отдельное слово на английском
    String c = "haello";
    boolean match8 = c.matches("[^abc]*"); // ^ задает условие 'кроме'
    String url = "http://www.google.com";
    boolean match9 = url.matches("http://www\\..+\\.(com|ru)"); // . это любой символ; .+ это любой набор символов
    String d = "123";
    boolean match10 = d.matches("\\d{3}");
    String e = "85573";
    boolean match11 = e.matches("\\d{2,}"); // {2,} это количество от 2 и более
    String f = "g3";
    boolean match12 = f.matches("\\w\\d"); // \\w это одна любая английская буква
    System.out.println();
  }
}
