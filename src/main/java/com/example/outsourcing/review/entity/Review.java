package com.example.outsourcing.review.entity;

import com.example.outsourcing.common.entity.BaseEntity;
import com.example.outsourcing.order.entity.Order;
import com.example.outsourcing.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer star;
    private String contents;

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    private Long userId;
    private Long menuId;
    private String state;

    private Long storeId;
    private String menuName;
//

    public Review(Integer star, String contents, Long userId, Long menuId, String state, Long storeId, String menuName) {
        this.star = star;
        this.contents = contents;
        this.userId = userId;
        this.menuId = menuId;
        this.state = state;
        this.storeId = storeId;
        this.menuName = menuName;
    }


}
