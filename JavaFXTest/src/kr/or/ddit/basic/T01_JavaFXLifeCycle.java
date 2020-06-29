package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.stage.Stage;

public class T01_JavaFXLifeCycle extends Application{

   public T01_JavaFXLifeCycle() {
      System.out.println(Thread.currentThread().getName()
             + " : 생성자 메서드 호출");
   }
   
   @Override
   public void init() throws Exception {
      System.out.println(Thread.currentThread().getName()
             + " : init() 메서드 호출");
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      System.out.println(Thread.currentThread().getName()
            + " : start() 메서드 호출");
      primaryStage.show();
      
   }
   
   @Override
   public void stop() throws Exception {
      System.out.println(Thread.currentThread().getName()
             + " : stop() 메서드 호출");
   }

   public static void main(String[] args) {
      System.out.println(Thread.currentThread().getName()
             + " : main() 메서드 호출");
      launch(args);
      
   }
}