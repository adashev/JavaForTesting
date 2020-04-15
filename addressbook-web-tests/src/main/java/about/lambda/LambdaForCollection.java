package about.lambda;


import java.util.*;
import java.util.stream.Collectors;

public class LambdaForCollection {
   public static void main(String[] args) {
      int[] arr1 = new int[10];
      List<Integer> ls1 = new ArrayList<>();
      fillArr(arr1);
      fillList(ls1);
      System.out.println(Arrays.toString(arr1));
      System.out.println(ls1.toString());
    // о методе map()
      arr1 = Arrays.stream(arr1).map(a -> a * 2).toArray();
      ls1 = ls1.stream().map(a -> a * 2).collect(Collectors.toList());
      System.out.println(Arrays.toString(arr1));
      System.out.println(ls1);
      arr1 = Arrays.stream(arr1).map(a -> a + 1).toArray();
      System.out.println("еще раз: " + Arrays.toString(arr1));

   // о методе filter()
      int[] arr2 = new int[10];
      List<Integer> ls2 = new ArrayList<>();
      fillArr(arr2);
      fillList(ls2);
      arr2 = Arrays.stream(arr2).filter(a -> a % 2 == 0).toArray();
      ls2 = ls2.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());
      System.out.println("filter arr: " + Arrays.toString(arr2));
      System.out.println("filter list: " + ls2);
   // о методе forEach()
      Arrays.stream(arr2).forEach(System.out::println);
      ls2.stream().forEach(System.out::println);
   // о методе reduce() - берет массив и сжимает его до одного элемента
      int[] arr3 = new int[10];
      List<Integer> ls3 = new ArrayList<>();
      fillArr(arr3);
      fillList(ls3);
      int sumArr = Arrays.stream(arr3).reduce((acc, cur) -> acc + cur).getAsInt();//acc - аккумулирует конечный результат операции
      int sumList = ls3.stream().reduce((acc, cur) -> acc * cur).get();//по дефолту аккумулятор = 1-му элементу в списке
      System.out.println("reduce arr: " + sumArr);
      System.out.println("reduce list: " + sumList);
// методы вытягиваются в цепочу
      int[] arr4 = new int[10];
      fillArr(arr4);
      int[] array = Arrays.stream(arr4).filter(a -> a % 2 != 0).map(a -> a * 2).toArray();
      System.out.println("методы вытягиваются в цепочу: " +Arrays.toString(array));

      Set<Integer> set = new HashSet<>();
      set.add(1);
      set.add(2);
      set.add(5);
      System.out.println("set1: " + set);
      set = set.stream().map(a -> a*3).collect(Collectors.toSet());
      System.out.println("set2: " + set);
   }

   private static void fillArr(int[] arr) {
      for(int i = 0; i < arr.length; i++) {
         arr[i] = i + 1;
      }
   }

   private static void fillList(List<Integer> ls) {
      for(int i = 0; i < 10; i++) {
         ls.add(i + 1);
      }
   }


}
