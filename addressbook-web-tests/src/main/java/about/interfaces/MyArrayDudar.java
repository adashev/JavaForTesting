package about.interfaces;

public class MyArrayDudar implements ArrayDudar {
   private int array[] = new int[3];
   private int size = 0;

   @Override
   public int Get(int i) {
      return array[i];
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
