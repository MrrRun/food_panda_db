import model.dao.CustomerDaoImpl;
import model.dao.OrderDaoImpl;
import model.entity.Customer;
import model.entity.Order;

import java.sql.Date;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
//        new CustomerDaoImpl()
//                .addNewCustomer(new Customer(
//                        4,
//                        "Brando",
//                        "asdfg5432@gmail.com",
//                        "poiuyt12345",
//                        false,
//                        Date.valueOf(LocalDate.now())
//                ));

//        new CustomerDaoImpl()
//                .queryAllCustomers()
//                .forEach(System.out::println);

//        new CustomerDaoImpl()
//                .deletedCustomerById(3);

//        new CustomerDaoImpl()
//                .updateCustomerById(4);

//        new OrderDaoImpl()
//                .addNewOrder(
//                        Order.builder()
//                                .id(1)
//                                .orderName("Bay Srorb")
//                                .orderDescription("Double eggs with extra meat")
//                                .customer(
//                                        Customer.builder()
//                                                .id(4)
//                                        .build())
//                                .orderedAt(Date.valueOf(LocalDate.now()))
//                        .build());

        new OrderDaoImpl()
                .queryAllOrders()
                .forEach(System.out::println);
    }
}