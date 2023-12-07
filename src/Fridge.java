import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Fridge {
    HashMap<String , Integer> products = new HashMap<>();
    Map <String, Integer> order = new HashMap<>();


    public void addProducts(String name, Integer count) {
        if (products.containsKey(name)) {
            Integer alreadyInFridge = products.get(name);
            products.put(name, alreadyInFridge + count);
        } else {
            products.put(name, count);
        }
    }


    // name - это имя продукта (ключ), какой продукт хотят забрать из холодильника
    // count - это сколько в штуках хотят забрать
    public void take(String name, Integer count) {
        // Если продукта в холодильнике нет
        if (!products.containsKey(name)) {
            System.out.println(name + " в холодильнике нет");
            return;
        }

        // Если продукта в холодильнике не хватает

        // alreadyInFridge - сколько у нас уже есть этого продукта по имени (ключу) в нашем холодильнике
        Integer alreadyInFridge = products.get(name);
        if (alreadyInFridge < count) {
            System.out.println("Вы хотите забрать " + count + ". У нас столько нет. Есть только " + alreadyInFridge);
            while (true) {
                System.out.println("Сколько вы хотите забрать?");
                Scanner scanner = new Scanner(System.in);
                Integer takeCount = scanner.nextInt();
                if (takeCount > alreadyInFridge) {
                    System.out.println("Вы хотите забрать" + name + " " + takeCount +
                            ". У нас столько нет. Есть только " + alreadyInFridge);
                } else if (takeCount < alreadyInFridge) {
                    products.put(name, alreadyInFridge - takeCount);
                    break;
                } else {
                    products.remove(name);
                    break;
                }
            }
        }
    }

    public void all() {
        for (String s : products.keySet()) {
            System.out.println(s + " : " + products.get(s));
        }
    }

      public  void addOrders (String name,Integer count)  {
          Scanner scanner = new Scanner(System.in);

          if (products.containsKey(name)) {
              Integer alreadyInFridge = products.get(name);
              System.out.println("Продукт " + name + " уже есть в холодильнике в кол-ве " +
                      alreadyInFridge + " штук. Хотите добавить еще " + count + " штук. ?");
              System.out.println(" Если подтверждаете заказ - введите 1, отменяете - 0 :");
              while (true) {
                  Integer confirm = scanner.nextInt();
                  if (confirm == 1) {
                      System.out.println("Ваш заказ подтвержден !");
                      order.put(name, count);
                      break;
                  } else if (confirm == 0) {
                      System.out.println("Ваш заказ отменен!");
                      break;
                  }
                  else
                      System.out.println("Если подтверждаете заказ - введите 1, отменяете - 0 : ");
              }

          }
          else order.put(name,count);

      }
      public  void doOrder() {
        order.forEach((k,v) -> System.out.println(" Добовляем " + k + " в кол-ве " +
                v + " штук. "));
        order.forEach((k,v) -> addProducts(k,v));
    }



}