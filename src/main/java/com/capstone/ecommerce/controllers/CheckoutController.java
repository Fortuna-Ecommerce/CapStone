package com.capstone.ecommerce.controllers;



import com.capstone.ecommerce.model.*;
import com.capstone.ecommerce.repositories.AddressRepository;
import com.capstone.ecommerce.repositories.ProductRepository;
import com.capstone.ecommerce.repositories.TransactionRepository;

import com.capstone.ecommerce.repositories.*;
import com.capstone.ecommerce.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@SessionAttributes("products")
public class CheckoutController{

    private final UserRepository userRepo;
    private final TransactionRepository transactionRepo;
    private final AddressRepository addressRepo;
    private final ProductRepository productRepo;
    private final TransactionProductRepository transProdRepo;

    public CheckoutController(UserRepository userRepo, TransactionRepository transactionRepo, AddressRepository addressRepo, ProductRepository productRepo, TransactionProductRepository transProdRepo) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
        this.addressRepo = addressRepo;
        this.productRepo = productRepo;
        this.transProdRepo = transProdRepo;
    }

    public List<Product> comparison(List<Product> purchasableProducts) {
        List<Product> comparing = new ArrayList<>();

        for (Product product : purchasableProducts) {
            comparing.add(this.productRepo.getOne(product.getId()));
        }
        return comparing;
    }

//    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey = "pk_test_B1PgkqwpndJUHJCdlY0I9leL00c395TbE5";


//    @GetMapping("/baddress")
//    public String goToBillAddress(Model model) {
//        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
//            return "home";
//        }
//        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Address bill_address = this.addressRepo.findByUserAndAddresstype(shopper, "Billing");
//        if (bill_address != null) {
//            model.addAttribute("bill_address", bill_address);
//        } else {
//            bill_address = new Address();
//            bill_address.setAddresstype("Billing");
//            bill_address.setUser(shopper);
//            model.addAttribute("bill_address", bill_address);
//        }
//        return "purchases/baddress";
//    }

//    @PostMapping("/baddress")
//    public String submitBillAddress(Model model,
//                                    Address bill_address) {
//        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
//            return "home";
//        }
//        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        if (bill_address.getId() > 0) {
////            this.addressRepo.save(bill_address);
////        } else {
////            bill_address.setUser(shopper);
//            this.addressRepo.save(bill_address);
////        }
//        model.addAttribute("bill_address", bill_address);
//        Address ship_address = this.addressRepo.findByUserAndAddresstype(shopper, "Shipping");
//        if (ship_address != null) {
//            model.addAttribute("ship_address", ship_address);
//        } else {
//            ship_address = new Address();
//            ship_address.setAddresstype("Shipping");
//            ship_address.setUser(shopper);
//            model.addAttribute("ship_address", ship_address);
//        }
//        return "purchases/saddress";
//    }

//    @PostMapping("/saddress")
//    public String submitShipAddress(Model model,
//                                    Address ship_address,
//                                    @ModelAttribute("products") ShoppingCart products) {
//        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
//            return "home";
//        }
//        double total = 0.00;
//        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        if (ship_address.getId() > 0) {
////            this.addressRepo.save(ship_address);
////        } else {
//            this.addressRepo.save(ship_address);
////        }
//
//        Address bill_address = this.addressRepo.findByUserAndAddresstype(shopper, "Billing");
//        for (Product product : products) {
//            total = total + product.getPrice();
//        }
//        total = Math.round(total * 100.00) / 100.00;
//        model.addAttribute("total", total);
//        model.addAttribute("bill_address", bill_address);
//        model.addAttribute("ship_address", ship_address);
//        model.addAttribute("products", products);
//        model.addAttribute("stripePublicKey", stripePublicKey);
//        model.addAttribute("currency", "USD");
//        model.addAttribute("email", shopper.getEmail());
//        return "purchases/checkout";
//    }


    @GetMapping("/addresses")
    public String goToAddressEntryCheckout(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "home";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User shopper = this.userRepo.getOne(user.getId());
        Address ship_address = this.addressRepo.findByUserAndAddresstype(shopper, "Shipping");
        Address bill_address = this.addressRepo.findByUserAndAddresstype(shopper, "Billing");
        model.addAttribute("user", shopper);
        if (ship_address != null) {
            model.addAttribute("ship_address", ship_address);
        } else {
            ship_address = new Address();
            ship_address.setAddresstype("Shipping");
            ship_address.setUser(shopper);
            model.addAttribute("ship_address", ship_address);
        }
        if (bill_address != null) {
            model.addAttribute("bill_address", bill_address);
        } else {
            bill_address = new Address();
            bill_address.setAddresstype("Billing");
            bill_address.setUser(shopper);
            model.addAttribute("bill_address", bill_address);
        }

        System.out.println(ship_address.getId());
        System.out.println(bill_address.getId());

        return "purchases/addresses";

    }
 //Look up modelattributes for better research and simpler code when have time
    @PostMapping("/addresses")
    public String submitAddresses(Model model,
                                  @RequestParam(name = "shipBillAddressCheck", required = false) String check,
                                  @RequestParam(name = "Baddressid") Integer bId,
                                  @RequestParam(name="Bfirst_name") String bFN,
                                  @RequestParam(name="Blast_name") String bLN,
                                  @RequestParam(name = "Bcity") String bCity,
                                  @RequestParam(name = "Baddress1") String bAdd1,
                                  @RequestParam(name = "Baddress2", required = false) String bAdd2,
                                  @RequestParam(name = "Bstate") String bState,
                                  @RequestParam(name = "Bzip") Integer bZip,
                                  @RequestParam(name = "Saddressid") Integer sId,
                                  @RequestParam(name="Sfirst_name", required = false) String sFN,
                                  @RequestParam(name="Slast_name", required = false) String sLN,
                                  @RequestParam(name = "Scity", required = false) String sCity,
                                  @RequestParam(name = "Saddress1", required = false) String sAdd1,
                                  @RequestParam(name = "Saddress2", required = false) String sAdd2,
                                  @RequestParam(name = "Sstate", required = false) String sState,
                                  @RequestParam(name = "Szip", required = false) Integer sZip,
                                  @ModelAttribute("products") ShoppingCart products) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "home";
        }

        double total = 0.00;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User shopper = this.userRepo.findByUsername(user.getUsername());
        Address temp_bill_address = new Address();
        Address temp_ship_address = new Address();
        Address ship_address = new Address();
        if(bId > 0){
            temp_bill_address = this.addressRepo.findByUserAndAddresstype(shopper, "Billing");
        } else {
            temp_bill_address.setUser(shopper);
            temp_bill_address.setAddresstype("Billing");
        }

        temp_bill_address.setCity(bCity);
        temp_bill_address.setFirstname(bFN);
        temp_bill_address.setLastname(bLN);
        temp_bill_address.setStreet1(bAdd1);
        temp_bill_address.setStreet2(bAdd2);
        temp_bill_address.setState(bState);
        temp_bill_address.setZipcode(bZip);

        Address bill_address = this.addressRepo.save(temp_bill_address);


        if(sId > 0){
            temp_ship_address = this.addressRepo.findByUserAndAddresstype(shopper, "Shipping");
        } else {
            temp_ship_address.setUser(shopper);
            temp_ship_address.setAddresstype("Shipping");
        }

         if (check == null){
            System.out.println("Test2");
            temp_ship_address.setCity(sCity);
            temp_ship_address.setFirstname(sFN);
            temp_ship_address.setLastname(sLN);
            temp_ship_address.setStreet1(sAdd1);
            temp_ship_address.setStreet2(sAdd2);
            temp_ship_address.setState(sState);
            temp_ship_address.setZipcode(sZip);
            ship_address = addressRepo.save(temp_ship_address);
         } else if (check.equals("on")) {
            System.out.println("Test1");
             temp_ship_address.setCity(bCity);
             temp_ship_address.setFirstname(bFN);
             temp_ship_address.setLastname(bLN);
             temp_ship_address.setStreet1(bAdd1);
             temp_ship_address.setStreet2(bAdd2);
             temp_ship_address.setState(bState);
             temp_ship_address.setZipcode(bZip);
            ship_address = addressRepo.save(temp_ship_address);
        }


        for (Product product : products) {
            total = total + product.getPrice();
        }

        total = Math.round(total * 100.00) / 100.00;
        model.addAttribute("total", total);
        model.addAttribute("bill_address", bill_address);
        model.addAttribute("ship_address", ship_address);
        model.addAttribute("products", products);
        model.addAttribute("products", products);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", "USD");
        model.addAttribute("email", user.getEmail());
        return "purchases/checkout";
    }


    @Autowired
    private StripeService stripeService;
//
//    @PostMapping("/create-charge")
//    public @ResponseBody
//    Response createCharge(String email, String token) {
//        //validate data
//        if (token == null) {
//            return new Response(false, "Stripe payment token is missing. Please, try again later.");
//        }
//
//        //create charge
//        String chargeId = stripeService.createCharge(email, token, 999); //$9.99 USD
//        if (chargeId == null) {
//            return new Response(false, "An error occurred while trying to create a charge.");
//        }
//
//        // You may want to store charge id along with order information
//
//        return new Response(true, "Success! Your charge id is " + chargeId);
//    }

    @PostMapping(value = "/charge", produces = "application/json")
    @ResponseBody
    public String charge(Model model,
                         Address bill_address,
                         Address ship_address,
                         @ModelAttribute("products") ShoppingCart products,
                         @RequestParam (name= "total") double total,
                        @RequestParam (name = "token") String token) throws Exception {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "home";
        }
        List<Product> originals = new ArrayList<>();
        originals = comparison(products);
        boolean isError = false;
        String errMessage = "";
        Transaction newTransaction = new Transaction();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date saleDate = new Date();
        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User testShopper = this.userRepo.getOne(shopper.getId());
        String name = ship_address.getFirstname() + " " + ship_address.getLastname();
        String email = shopper.getEmail();
        model.addAttribute("total", total);
        System.out.println("Checkout test");
        if(testShopper.getStripeToken() == null){
            String customerId = stripeService.createCustomer(token, email);
            testShopper.setStripeToken(customerId);
            this.userRepo.save(testShopper);
        }
        try {
            String id = stripeService.chargeExistingCard(testShopper.getStripeToken(), total);
            newTransaction.setUser(testShopper);
            newTransaction.setStripeTransID(id);
            newTransaction.setCreated_at(formatter.format(saleDate));
            newTransaction.setTransactionStatus("Paid - Pending shipment");
            newTransaction.setTransactionType("Sale");
            newTransaction.setFinalAmount(total);
            this.transactionRepo.save(newTransaction);
            Transaction thisTransaction = this.transactionRepo.findByStripeTransID(id);
            for(Product product: products){
                Transactions_Product TransProd = new Transactions_Product();
                TransProd.setProduct(product);
                TransProd.setTransaction(thisTransaction);
                TransProd.setQuantity(product.getQuantity());
                this.transProdRepo.save(TransProd);
            }
//            for(int i = 0; i < originals.size(); i++){
//                originals.get(i).setQuantity(originals.get(i).getQuantity() - products.get(i).getQuantity());
//                this.productRepo.save(originals.get(i));
//            }
            products = new ShoppingCart();
            model.addAttribute("products", products);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            isError = true;
            errMessage = "There was a problem with your card - please try again or contact your issuing agency!";
        }
//        model.addAttribute("tId", newTransaction.getId());
//        model.addAttribute("status", newTransaction.getTransactionStatus());
//        model.addAttribute("balance_transaction", id);

        return "{" +
                    "\"url\":\"/result\"," +
                    "\"error\":{"+
                        "\"isError\":\""+isError+"\","+
                        "\"message\":\""+errMessage+"\""+
                    "}"+
                "}";

    }


    @GetMapping("/result")
    private String goToResult(Model model){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "home";
        }
        return "purchases/result";
    }

//        @ExceptionHandler(StripeException.class)
//    public String handleError(Model model, StripeException ex) {
//        model.addAttribute("error", ex.getMessage());
//        return "result";


//    @GetMapping("/checkout")
//    public String checkout(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("amount", 50 * 100); // in cents
//        model.addAttribute("stripePublicKey", stripePublicKey);
//        model.addAttribute("currency", ChargeRequest.Currency.EUR);
//        return "checkout";
//    }


    }
