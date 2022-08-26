package com.example.demo;
import com.example.demo.model.entity.Address;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Orders;
import com.example.demo.model.entity.Product;
import com.example.demo.service.AddressService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final String EMAIL_ONE_CUSTOMER = "pp@abv.bg";
    private final CustomerService customerService;
    private final AddressService addressService;
    private final ProductService productService;
    private final OrderService orderService;

    public CommandLineRunnerImpl(CustomerService customerService, AddressService addressService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //CUSTOMER TESTS:
        verifyThatCustomerTableNotEmptyBeforeTests();

        assertThereIsNoCustomerWithoutAddress();

        assertThereIsNoCustomerWithSamePhoneOtEmail();

        createCustomerSaveInDBAndAssetrItIsSavedSuccessfully();

        verifiesThatYouCannotCreateAndSaveCustomerWithoutMandatoryFields();

        //ADDRESS TESTS:
        verifyThatAddressTableNotEmptyBeforeTests();

        getRandomCustomerVerifyHisAddressesHaveAllMandatoryFieldsFilled();

        verifiesThatYouCannotCreateAndSaveAddressWithoutMandatoryFields();

        getRandomCustomersVerifyTheirOrdersHaveAllMandatoryFields();

        //ORDERS TESTS:
        verifyThatOrdersTableNotEmptyBeforeTests();

        getRandomOrdersVerifyTheyHaveCustomer();

        createNewOrderVerifyThatItWasSavedSuccessfully();

        verifiesThatYouCannotCreateAndSaveOrderWithoutMandatoryFields();

        //PRODUCTS TESTS:
        verifyThatProductsTableIsNotEmptyBeforeTests();

        getRandomOrdersVerifyTheirProductsHaveAllMandatoryFields();

        createNewProductVerifyThatItWasSavedSuccessfullyAndThereAreNoOrdersForIt();

        verifiesThatYouCannotCreateAndSaveProductWithoutMandatoryFields();
    }

    private void createNewProductVerifyThatItWasSavedSuccessfullyAndThereAreNoOrdersForIt() throws IOException {
        Product savedProduct = productService.createAndSaveProduct();
        Long idSavedProduct = savedProduct.getId();
        Assertions.assertNotNull(productService.findById(idSavedProduct),
                String.format("Test fail: The product with id %d is not saved successfully", idSavedProduct));

        for (Orders order : orderService.getAllOrders()) {

            for (Product product : order.getProducts()) {
                Long id = product.getId();
                Assertions.assertNotEquals(id, idSavedProduct,
                        String.format("Test fail: There is order with id %d for the new created product.", order.getId()));
            }
        }
    }

    private void getRandomOrdersVerifyTheirProductsHaveAllMandatoryFields() {
        List<Orders> randomOrders = orderService.getRandomOrders();
        for (Orders order : randomOrders) {
            Set<Product> products = order.getProducts();
            for (Product product : products) {
                boolean testPass = product.getId() != null && product.getName() != null
                        && product.getQuantity() != null && product.getType() != null &&
                        product.getPrice() != null && product.getProductInStock() != null &&
                        product.getWarehouse() != null;
                Assertions.assertTrue(testPass,
                        String.format("Test fail: The product with id %d is without mandatory fields",
                                product.getId()));
            }
        }
    }

    private void getRandomCustomerVerifyHisAddressesHaveAllMandatoryFieldsFilled() {
        Customer randomCustomer = customerService.findRandomCustomer();
        Long id = randomCustomer.getAddress().getId();
        Address addressById = addressService.findAddressById(id);
        boolean testPass = addressById.getId() != null &&
                addressById.getCity() != null &&
                addressById.getCountry() != null;
        Assertions.assertTrue(testPass, "Test fail: There is address without mandatory fields");
    }

    private void verifyThatProductsTableIsNotEmptyBeforeTests() {
        List<Product> allProducts = productService.findAllProducts();
        Assertions.assertTrue(allProducts.stream().count() > 0,
                "Test fail: There is no product records in DB");
    }

    private void createNewOrderVerifyThatItWasSavedSuccessfully() throws IOException {
        Orders savedOrder = orderService.createAndSaveOrder();
        Long idSavedOrder = savedOrder.getId();
        Assertions.assertNotNull(orderService.findById(idSavedOrder),
                String.format("The order with id %d is not saved successfully", idSavedOrder));
    }

    private void verifiesThatYouCannotCreateAndSaveProductWithoutMandatoryFields() {
        Product productWithoutMandatoryFields = productService.createProductWithoutMandatoryFields();
        System.out.println();
        boolean testPass = false;
        try {
            try {
                productService.saveProduct(productWithoutMandatoryFields);
            } catch (Exception e) {
                testPass = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (testPass) {
            System.out.println("Test pass: you cannot save product without mandatory fields");
        } else {
            System.out.println("Test fail: You can save product without mandatory fields");
        }
        System.out.println("-------------------------------");
    }


    private void verifyThatOrdersTableNotEmptyBeforeTests() {
        Assertions.assertTrue(orderService.getAllOrders().stream().count() > 0,
                "Test fail: There is no orders records in DB");
    }


    private void getRandomOrdersVerifyTheyHaveCustomer() {
        for (Orders order : orderService.getRandomOrders()) {
            Assertions.assertNotNull(order.getCustomer(),
                    String.format("Test fail: The order with id %d is without customer",
                            order.getCustomer().getId()));
        }
    }

    private void verifiesThatYouCannotCreateAndSaveOrderWithoutMandatoryFields() {
        Orders orderWithoutMandatoryFields = orderService.createOrderWithoutMandatoryFields();
        System.out.println();
        boolean testPass = false;
        try {
            try {
                orderService.saveOrder(orderWithoutMandatoryFields);
            } catch (Exception e) {
                testPass = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (testPass) {
            System.out.println("Test pass: you cannot save order without mandatory fields");
        } else {
            System.out.println("Test fail: You can save order without mandatory fields");
        }
        System.out.println();
    }

    private void getRandomCustomersVerifyTheirOrdersHaveAllMandatoryFields() {
        List<Customer> randomCustomers = customerService.findRandomCustomers();
        List<Orders> orders = new ArrayList<>();

        for (Customer customer : randomCustomers) {
            Long id = customer.getId();

            for (Orders order : orderService.getAllOrders()) {
                Long customerId = order.getCustomer().getId();
                if (customerId == id) {
                    orders.add(order);
                }
            }
        }

        if (orders.stream().count() > 0) {
            for (Orders order : orders) {
                boolean testPass = order.getId() != null &&
                        order.getOrderCompleted() != null &&
                        order.getOrderPayed() != null &&
                        order.getDateOfOrder() != null &&
                        order.getCustomer() != null;
                Assertions.assertTrue(testPass, String.format("There is order %d without mandatory fields.", order.getId()));
            }
        }
    }

    private void verifiesThatYouCannotCreateAndSaveAddressWithoutMandatoryFields() {
        Address addressWithoutMandatoryFields = addressService.createAddressWithoutMandatoryFields();
        boolean testPass = false;
        try {
            try {
                addressService.saveAddress(addressWithoutMandatoryFields);
            } catch (Exception e) {
                testPass = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (testPass) {
            System.out.println("------------------------");
            System.out.println("Test pass: you cannot save address without mandatory fields");
            System.out.println("------------------------");
        } else {
            System.out.println("------------------------");
            System.out.println("Test fail: You can save address without mandatory fields");
            System.out.println("------------------------");
        }
    }

    private void verifyThatAddressTableNotEmptyBeforeTests() {
        List<Address> allAddresses = addressService.findAllAddresses();
        Assertions.assertTrue(allAddresses.stream().count() > 0,
                "Test fail: There is no address records in DB");
    }


    private void verifyThatCustomerTableNotEmptyBeforeTests() {
        List<Customer> allCustomers = customerService.findAllCustomers();
        Assertions.assertTrue(allCustomers.stream().count() > 0,
                "Test fail: There is no customer records in DB");
    }

    private void verifiesThatYouCannotCreateAndSaveCustomerWithoutMandatoryFields() {
        Customer customerWithoutMandatoryFields = customerService.createCustomerWithoutMandatoryFields();
        System.out.println();
        boolean testPass = false;
        try {
            try {
                customerService.saveCustomerWithoutMandatoryFields(customerWithoutMandatoryFields);
            } catch (Exception e) {
                testPass = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (testPass) {
            System.out.println("Test pass: you cannot save customer without mandatory fields");
        } else {
            System.out.println("Test fail: You can save customer without mandatory fields");
        }
        System.out.println();
    }


    private void seedData() throws IOException {
        addressService.seedAddressData();
        customerService.seedCustomersData();
        productService.seedProductData();
        orderService.seedOrderDate();
    }

    private void assertThereIsNoCustomerWithoutAddress() throws IOException {
        for (Customer customer : customerService.findAllCustomers()) {
            Assertions.assertNotNull(customer.getAddress(),
                    "There is Customer without Address");
        }
    }

    private void assertThereIsNoCustomerWithSamePhoneOtEmail() {
        List<String> phones = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        for (Customer customer : customerService.findAllCustomers()) {
            phones.add(customer.getPhone());
            emails.add(customer.getEmail());
        }
        Set<String> duplicatePhones = findDuplicates(phones);
        Set<String> duplicateEmails = findDuplicates(emails);
        Assertions.assertTrue(duplicatePhones.isEmpty(),
                "Test fail: There is Customers with the same phone");
        Assertions.assertTrue(duplicateEmails.isEmpty(),
                "Test fail: There is Customers with the same email");
    }

    private <T> Set<T> findDuplicates(List<T> list) {
        Set<T> set = new HashSet<>();
        for (T t : set) {
            if (Collections.frequency(list, t) > 1) {
                set.add(t);
            }
        }
        return set;
    }

    private void createCustomerSaveInDBAndAssetrItIsSavedSuccessfully() throws IOException {
        customerService.saveCustomer();
        String searchedEmail = null;
        for (Customer customer : customerService.findAllCustomers()) {
            String email = customer.getEmail();
            if (email.equals(EMAIL_ONE_CUSTOMER)) {
                searchedEmail = email;
            }
        }
        Assertions.assertEquals(EMAIL_ONE_CUSTOMER, searchedEmail,
                "Test fail: The customer is not saved successfully");
    }
}
