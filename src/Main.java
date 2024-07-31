import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Random random1 = new Random();
        Faker f = new Faker();

        List<Product> prodotti = new ArrayList<>();
        prodotti.add(new Product(random.nextLong(1000000, 1900000), f.harryPotter().book(), "book", 120.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "Marching Powder", "book", 99.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "Pannolini", "baby", 20.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "Skateboard", "boys", 130.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "Couch", "furniture", 820.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "T-Shirt", "computer", 1920.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "bed", "furniture", 1100.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "The Hobbit", "book", 220.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "Biberon", "baby", 20.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "BabyBath", "baby", 160.00));
        prodotti.add(new Product(random.nextLong(1000000, 1900000), "SurfBoard", "boys", 320.00));

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer(random.nextLong(1000000, 1900000), f.backToTheFuture().character() + " " + f.elderScrolls().creature(), random1.nextInt(1, 3)));
        }
        System.out.println("---------------------------------------Lista Customers------------------------------------");
        customers.forEach(System.out::println);


        List<Product> orderProducts1 = new ArrayList<>();
        orderProducts1.add(prodotti.get(0));
        orderProducts1.add(prodotti.get(1));


        Order order1 = new Order(random.nextLong(1000000, 1900000), Status.shipped, LocalDate.now(), LocalDate.now().plusDays(5), customers.get(0), orderProducts1);
        System.out.println("---------------------------------------Ordine di Prova------------------------------------");
        System.out.println("Ordine prova order1: " + order1);

        //-----------------lista di ordini------------
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            // Genera una lista casuale di prodotti per l'ordine
            List<Product> randomProducts = new ArrayList<>();
            int numProducts = random.nextInt(prodotti.size()) + 1; // Numero casuale di prodotti per l'ordine
            for (int j = 0; j < numProducts; j++) {
                randomProducts.add(prodotti.get(random.nextInt(prodotti.size())));
            }
            // Assegna un cliente casuale
            Customer randomCustomer = customers.get(random.nextInt(customers.size()));

            // Genera una data casuale per l'ordine e la data di consegna
            LocalDate orderDate = LocalDate.now().minusDays(random.nextInt(30));
            LocalDate deliveryDate = orderDate.plusDays(random.nextInt(10) + 1);

            // Crea l'ordine con dati casuali
            orders.add(new Order(random.nextLong(1000000, 1900000), Status.shipped, orderDate, deliveryDate, randomCustomer, randomProducts));
        }

        System.out.println("---------------------------------------Ordini generati random------------------------------------");
        for (Order order : orders) {
            System.out.println(order);
        }


        System.out.println("---------------------------------------ESERCIZIO 1 ------------------------------------");

        List<Product> booksOver100 = prodotti.stream()
                .filter(element -> element.getCategory().equalsIgnoreCase("book") && element.getPrice() > 100)
                .toList();

        System.out.println(booksOver100);

        System.out.println("---------------------------------------ESERCIZIO 2 ------------------------------------");

        List<Order> ordiniBaby = orders.stream().filter(order -> order.getProducts().stream()
                .anyMatch(product -> product.getCategory().equals("baby"))).toList();

        ordiniBaby.forEach(System.out::println);

        System.out.println("---------------------------------------ESERCIZIO 3 ------------------------------------");

        List<Product> boysProduct = prodotti.stream()
                .filter(product -> product.getCategory()
                        .equals("boys"))
                .map(product -> new Product(
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getPrice() * 0.9
                )).toList();

        boysProduct.forEach(System.out::println);


        System.out.println("---------------------------------------ESERCIZIO 4 ------------------------------------");

        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);

        List<Product> prodottiDaCustomersLivello2 = orders.stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> !order.getOrderDate().isBefore(startDate) && !order.getOrderDate().isAfter(endDate))
                .flatMap(order -> order.getProducts().stream())
                .toList();

        prodottiDaCustomersLivello2.forEach(System.out::println);

    }
}

