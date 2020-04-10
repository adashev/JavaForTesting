package about.interfaces;

public class MyArrayDudarSecond implements ArrayDudar {
   private int array[] = new int[4];
   private int size = 0;

   @Override
   public int Get(int i) {
      if(i < array.length) {
         return array[array.length - i -1];
      } else return 0;
   }

   @Override
   public boolean Add(int value) {
      if (size != array.length) {
         array[size] = value;
         size++;
         return true;
      } else return false;
   }
}
