package about.interfaces;

public interface ISomeInterface extends OtherInterface {
  int SOME_VARIABLE = 5; // это всегда final
  void addTwoDigits(int one, int two);
}

interface OtherInterface { //интерфейсы применяются для описания именно поведения (без состояний)
  void somemethod();
}

class Example implements ISomeInterface {
  @Override
  public void addTwoDigits(int one, int two) {
    
  }

  public void somemethod() {

  }
}
