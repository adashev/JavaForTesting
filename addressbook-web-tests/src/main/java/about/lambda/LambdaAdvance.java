package about.lambda;


interface Executable {
  void execute();
}

class Runner{
  public void run(Executable e){

  }
}

class ExecutableImplements implements Executable {
  @Override
  public void execute() {
    System.out.println("hello");
  }
}

public class LambdaAdvance {
  public static void main(String[] args) {
    Runner runner = new Runner();
    runner.run(new ExecutableImplements());
    runner.run(new Executable() {
      @Override
      public void execute() {
        System.out.println("hello");
      }
    });
  }
}
