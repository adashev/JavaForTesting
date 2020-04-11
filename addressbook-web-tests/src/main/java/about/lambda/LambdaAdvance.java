package about.lambda;

interface Executable {  void execute(); }

class Runner{
   public void run(Executable e){ //ждет как аргумент реализацию интерфейса Executable
      e.execute();
   }
}

class ExecutableImplements implements Executable {//1-й самый многословный способ
   @Override
   public void execute() {
      System.out.println("from ExecutableImplements override execute()");
   }
}

public class LambdaAdvance {
   public static void main(String[] args) {
      Runner runner = new Runner();
      runner.run(new ExecutableImplements());//аргументом передаем реализацию интерф. Executable
//применение 1-го способа

      runner.run(new Executable() {//2-й способ - реализовать прямо здесь анонимный класс (не создавая class Runner)
         @Override
         public void execute() {
            System.out.println("from anonim class");
         }
      }) ;

      runner.run(() -> System.out.println("from lambda-ex"));
   }
}
