package org.example;

public class Main {

    public static int fibonacci(int number){
        int t = 0;
        if(number==0){
            return 0;
        } else if(number==1){
            return 1;
        } else {
             t += fibonacci((number-2));
            return fibonacci((number-1)) + t;
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
//interface IA
//{
//    int DoSomething();
//}
//
//interface IB
//{
//    bool DoSomething();
//}
//
//class A : IA{
//        int DoSomething()
//        {
//        int result =0
//        // traitement
//        return result;
//        }
//        }
//
//        @Test
//class B : IB
//        {
//private IA myA;
//public B(IA a)
//        {
//        myA = a;
//        }
//
//        bool DoSomething()
//        {
//        var result = myA.DoSomething();
//        //traitement
//        return result%2 == 0;
//        }
//        }