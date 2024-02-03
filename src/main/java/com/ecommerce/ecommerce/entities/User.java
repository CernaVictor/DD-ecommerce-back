package com.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // maybe change to uuid?
    private UUID id;

    @Column(name = "first_name", length = 256, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 256, nullable = false)
    private String lastName;

    @Column(name = "email", length = 256, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name= "phone_number", length = 15, nullable = false)
    private String phoneNumber;

    @Column(name= "is_admin", nullable = false)
    private Boolean isAdmin;

    @Column(name="reset_token")
    private String resetToken;

    @Column(name="reset_token_expiry")
    private Date resetTokenExpiry;

    @OneToOne
    @JoinColumn(name = "delivery_address")
    private Address deliveryAddress;

    @OneToOne
    @JoinColumn(name = "billing_address")
    private Address billingAddress;
}
