package model.dao;

import model.entity.Customer;
import model.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{
    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO "order" (order_name, order_description, cus_id, ordered_at)
                VALUES (?, ?, ?, ?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "099820077Run@"
                );
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql);
                ){
            preparedStatement.setString(1,order.getOrderName());
            preparedStatement.setString(2, order.getOrderDescription());
            preparedStatement.setInt(3,order.getCustomer().getId());
            preparedStatement.setDate(4,order.getOrderedAt());
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected>0 ? "Insert successfully" : "Insert failed";
            System.out.println(message);

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int deletedOrderById(Integer id) {
        return 0;
    }

    @Override
    public int updateOrderById(Integer id) {
        return 0;
    }

    @Override
    public int searchOrderById(Integer id) {
        return 0;
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = """
                SELECT * FROM "order"
                INNER JOIN customer c ON "order".cus_id = c.id
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "099820077Run@"
                );
                Statement statement = connection.createStatement();
                ){
            ResultSet resultSet = statement.executeQuery(sql);
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()){
                orderList.add(
                        Order.builder()
                                .id(resultSet.getInt("id"))
                                .orderName(resultSet.getString("order_name"))
                                .orderDescription(resultSet.getString("order_description"))
                                .orderedAt(resultSet.getDate("ordered_at"))
                                .customer(Customer.builder()
                                        .id(resultSet.getInt("cus_id"))
                                        .name(resultSet.getString("name"))
                                        .email(resultSet.getString("email"))
                                        .password(resultSet.getString("password"))
                                        .isDeleted(resultSet.getBoolean("is_deleted"))
                                        .createdDate(resultSet.getDate("created_date"))
                                        .build())
                                .build()
                );
            }
            return orderList;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return new ArrayList<>();
    }
}
