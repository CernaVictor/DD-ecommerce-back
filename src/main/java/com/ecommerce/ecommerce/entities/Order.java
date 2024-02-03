package com.ecommerce.ecommerce.entities;

import com.ecommerce.ecommerce.types.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cartId;

    @OneToOne
    @JoinColumn(name = "delivery_address")
    private Address deliveryAddress;

    @OneToOne
    @JoinColumn(name = "billing_address")
    private Address billingAddress;
}