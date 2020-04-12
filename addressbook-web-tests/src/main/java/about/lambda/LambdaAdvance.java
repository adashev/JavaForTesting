package about.lambda;

interface Executable {  int execute(int x, int y); }

class Runner{
   public void run(Executable e){ //ждет как аргумент реализацию интерфейса Executable
      int a = e.execute(10, 15);
      System.out.println(a);
   }
}

class ExecutableImplements implements Executable {//1-й самый многословный способ
   @Override
   public int execute(int x, int y) {
      System.out.println("from ExecutableImplements override execute()");
      return 1;
   }
}

public class LambdaAdvance {
   public static void main(String[] args) {
      Runner runner = new Runner();
      runner.run(new ExecutableImplements());//1-й способ - аргументом передаем реализацию интерф. Executable
      runner.run(new Executable() {//2-й способ - реализовать прям здесь анонимный класс (не создавая class Runner)
         @Override
         public int execute(int x, int y) {
            System.out.println("from anonim class-1");
            System.out.println("from anonim class-2");
            return x + y;
         }
      }) ;

      int a = 1;
      runner.run((x,y) -> {
         return x + y + a;
      });
   }
}
