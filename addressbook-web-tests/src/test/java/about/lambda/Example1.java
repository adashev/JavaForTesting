package about.lambda;

public class Example1 {
    public static void main(String[] args) {
        Operationable operation1, operation2; //Определение ссылки на функциональный интерфейс
        operation1 = (x,y) -> x + y; // создание лямбда-выражения, где выполняются действия
//параметры лямбда-выражения соответствуют параметрам метода интерфейса,
//а результат соответствует возвращаемому результату этого метода
        int result = operation1.calculate(10, 20); //применение лямбда-выражения в виде вызова метода интерфейса
        System.out.println("result: " + result); //30
        operation2 = (x,y) -> x - y;
        result = operation2.calculate(10, 20);
        System.out.println("result: " + result); //-10
    }
}
