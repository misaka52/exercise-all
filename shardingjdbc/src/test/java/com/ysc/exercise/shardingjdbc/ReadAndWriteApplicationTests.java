package com.ysc.exercise.shardingjdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReadAndWriteApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        batchInsert();
    }

    private void batchInsert() {
        List<Order> list = new ArrayList<>();
        int k = 0;
        for (int j = 130; k< 10 ; ++k, j++) {
            Order order = new Order();
            order.setUserId(100L);
            order.setOrderId((long) j);
            list.add(order);
        }
        for (Order order : list) {
            System.out.println("准备插入" +order);
            insert(order);
            System.out.println("插入成功");
        }
    }

    private void insert(Order order) {
        String sql = "insert into t_order_0(user_id, order_id) value (?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getOrderId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
