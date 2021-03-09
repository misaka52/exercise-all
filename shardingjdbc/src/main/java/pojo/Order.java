package pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author yuanshancheng
 * @date 2021/2/9
 */
@Data
public class Order {
    private Long id;
    private Long userId;
    private Long orderId;
    private Date createTime;
}
