package com.capstone.ecommerce.controllers;


import com.capstone.ecommerce.model.*;
import com.capstone.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.capstone.ecommerce.repositories.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.util.ArrayList;
import java.util.List;

@Controller
@Secured(SecurtiyUtil.MANAGE_ORDERS)
public class OrdersController extends Transaction{
    {

    private static final String viewPrefix = "/orders";
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;
    private final TransactionRepository transactionRepo;

        @Autowired protected OrderService orderService;
        @Autowired private TemplateEngine templateEngine;

        @Override
            protected String getHeaderTitle()
        {
            return "Orders";
        }
    public OrdersController(UserRepository userRepo, ProductRepository productRepo, OrderRepository orderRepo, Transaction transRepo, TransactionRepository transactionRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderRepo= orderRepo;
        this.transactionRepo = transactionRepo;
    }

    @GetMapping("/users/orders")
    @ResponseBody
    public Object order(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "login";
        }
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) this.userRepo.getOne(person.getId());

        @Controller
        @Secured(SecurityUtil.MANAGE_ORDERS)
        public class OrderController extends JCartAdminBaseController
        {
            private static final String viewPrefix = "orders/";

            @Autowired protected EmailService emailService;
            @Autowired protected OrderService orderService;
            @Autowired private TemplateEngine templateEngine;

            @Override
            protected String getHeaderTitle()
            {
                return "Manage Orders";
            }
            @RequestMapping(value="/orders", method= RequestMethod.GET)
            public String listOrders(Model model) {
                List<Order> list = orderService.getAllOrders();
                model.addAttribute("orders",list);
                return viewPrefix+"orders";
            }

            @RequestMapping(value="/orders/{orderNumber}", method=RequestMethod.GET)
            public String editOrderForm(@PathVariable String orderNumber, Model model) {
                Order order = orderService.getOrder(orderNumber);
                model.addAttribute("order",order);
                return viewPrefix+"edit_order";
            }

            @RequestMapping(value="/orders/{orderNumber}", method=RequestMethod.POST)
            public String updateOrder(@ModelAttribute("order") Order order, BindingResult result,
                                      Model model, RedirectAttributes redirectAttributes) {
                Order persistedOrder = orderService.updateOrder(order);
                this.sendOrderStatusUpdateEmail(persistedOrder);
                logger.debug("Updated order with orderNumber : {}", persistedOrder.getOrderNumber());
                redirectAttributes.addFlashAttribute("info", "Order updated successfully");
                return "redirect:/orders";
            }

            protected void sendOrderStatusUpdateEmail(Order order)
            {
                try {
                    final Context ctx = new Context();
                    ctx.setVariable("order", order);
                    final String htmlContent = this.templateEngine.process("order-status-update-email", ctx);

                    emailService.sendEmail(order.getCustomer().getEmail(),
                            "QuilCartCart - Order Status Update",
                            htmlContent);
                } catch (MemeteesException e) {
                    logger.error(e);
                }
            }

        //create 3 orders

        //create 3 items
        //put the three orders in a list
        //add this list to the first order

        //create 3 items
        //put the three orders in a list
        //add this list to the second order

        //create 3 items
        //put the three orders in a list
        //add this list to the third order

        //put the 3 orders in a list
        //add the order list to the model attribute
        return "orders";
    }


}




