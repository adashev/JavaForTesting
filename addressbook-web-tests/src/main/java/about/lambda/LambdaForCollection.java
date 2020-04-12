package about.lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
      System.out.println(ls1.toString());
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
