package com.capstone.ecommerce;

import com.capstone.ecommerce.model.*;
import com.capstone.ecommerce.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final AddressRepository addressRepository;

    @Value("${app.env}")
    private String environment;

    public DatabaseSeeder(ProductRepository productRepository, UserRepository userRepository, TransactionRepository transactionRepository, AddressRepository addressRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.addressRepository = addressRepository;
    }

    // generate a list of users and return it after saving
    private List<User> seedUsers() {

               User user1 = new User("Jeremy", "j@gmail.com", "$2a$12$wT4XqVfLKl51xFIu2QGbau2qjg9BOAFYNWt9h.7EepGAXcbESEF0O",
                true);
        User user2 = new User("Ruben", "r@gmail.com", "$2a$12$zNUWK4B1CXfqR4epRENOJ.T/LBsJbJUSUFrq/utWO5qNlB2zagk32",
                        true);//password = password123
        User user3 = new User("Victor", "v@gmail.com", "$2a$12$3jmqcQo/sKpthafly1Eo7O2D36VlGZSfjQO47.qcXt1h09Or0T.va",
                        true);//password = TestPassword1
        User user4 = new User("Michael", "m@gmail.com", "$2a$12$m2t1Nbdb4hJHiv1vQ8CM5uFrUCdL9A3fF2VCwJijdO2pj" +
                "/u2X1BKG",
                        true);//password = PassWord0
        User user5 = new User("JoeShmoe", "joe@gmail.com", "$2a$12$vNqhZD22GKRvRWnb0R4FRuoazB" +
                "/88CCzvG2oSugPEs71Pa7hinFza",
                        false);//password = DumbPassword1
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        return users;
    }


    //generate a handful of products and return it after saving
    private List<Product> seedProducts(){

               Product product1 = new Product("Rage meme", "Blue", "XL", "Shirt", 22.22, "Angry guy melting down",
                       false,
                       5);
        Product product2 = new Product("Pepe punch", "Gray", "L", "Shirt", 28.22, "Frog person threatening pose", false,
                23);
        Product product3 = new Product("Pepe sad", "Purple", "XL", "Hoodie", 35.78, "Frog person very down", true, 1000);
        Product product4 = new Product("NPC face", "Red", "OSFM", "Hat", 15.99, "Fellow with straight line mouth and " +
                "angly " +
                        "eyebrows", false, 9);
        Product product5 = new Product("Rage meme", "White", "S", "Hoodie", 35.99, "Angry guy melting down", false, 0);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        return products;
    }

    private List<Address> seedAddresses(List<User> users){
               Address address1 = new Address("Shipping", "Jeremy", "T", "10101 Nolan Dr", "San Antonio", "TX",78230);
        Address address2 =  new Address("Billing", "Jeremy", "T", "10101 Nolan Dr", "San Antonio", "TX", 78230);
        Address address3 = new Address("Shipping", "Joe", "Shmoe", "131 Telari St", "New York", "NY", 10043);
        Address address4 = new Address("Billing", "Joe", "Shmoe", "131 Telari St", "New York", "NY", 10043);
        address1.setUser(users.get(1));
        address2.setUser(users.get(1));
        address3.setUser(users.get(4));
        address4.setUser(users.get(4));
        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);
        addressRepository.save(address4);
        List<Address> addresses = new ArrayList<>();
       addresses.add(address1);
        addresses.add(address2);
        addresses.add(address3);
        addresses.add(address4);
        return addresses;
    }

//    //generate a handful of shopping carts
//
//    private List<ShoppingCart> seedShoppingCarts(List<Product> products){
//        List<Product> testingProducts = new ArrayList<>();
//        ShoppingCart test1 = new ShoppingCart();
//        ShoppingCart test2 = new ShoppingCart();
//        ShoppingCart test3 = new ShoppingCart();
//        ShoppingCart test4 = new ShoppingCart();
//
//        test1.setProductList(products);
//        test2.setProductList(products);
//        test3.setProductList(products);
//        test4.setProductList(products);
//        shoppingCartRepository.save(test1);
//        shoppingCartRepository.save(test2);
//        shoppingCartRepository.save(test3);
//        shoppingCartRepository.save(test4);
//        List<ShoppingCart> shoppingCarts = new ArrayList<>();
//        shoppingCarts.add(test1);
//        shoppingCarts.add(test2);
//        shoppingCarts.add(test3);
//        shoppingCarts.add(test4);
//        return shoppingCarts;
//    }




    // generate a handful of transactions, and assign a user/ShoppingCart to each one
    private void seedTransactions(List<Product> products, List<User> users, List<Address> addresses) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date testDate = new Date();
        Transaction test1 = new Transaction("Sale", "Complete", formatter.format(testDate), null);
        Transaction test2 = new Transaction("Return", "Pending", formatter.format(testDate), formatter.format(testDate));
        Transaction test3 = new Transaction("Sale", "Cancelled", formatter.format(testDate),
                formatter.format(testDate));
        Transaction test4 = new Transaction("Return", "Complete", formatter.format(testDate),
                formatter.format(testDate));

        products.remove(4);

        test1.setUser(users.get(0));
        test1.setProducts(products);

        test2.setUser(users.get(0));
        test2.setProducts(products);

        test3.setUser(users.get(4));
        test3.setProducts(products);

        test4.setUser(users.get(4));
        test4.setProducts(products);

        transactionRepository.save(test1);
        transactionRepository.save(test2);
        transactionRepository.save(test3);
        transactionRepository.save(test4);
    }





    @Override
    public void run(String... strings) throws Exception {
        if (! environment.equals("development")) {
            log.info("app.env is not development, doing nothing.");
            return;
        }

        log.info("Deleting transactions...");
        transactionRepository.deleteAll();
        log.info("Deleting addresses...");
        addressRepository.deleteAll();
        log.info("Deleting products...");
        productRepository.deleteAll();
        log.info("Deleting users...");
        userRepository.deleteAll();
        log.info("Seeding users...");
        List<User> users = seedUsers();
        log.info("Seeding products...");
        List<Product> products = seedProducts();
        log.info("Seeding addresses...");
        List<Address> addresses = seedAddresses(users);
        log.info("Seeding transactions...");
        seedTransactions(products, users, addresses);


        log.info("Finished running seeders!");
    }
}