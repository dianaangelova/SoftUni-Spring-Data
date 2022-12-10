package com.example._08exercise_jsonprocessing.entities.products;

import com.example._08exercise_jsonprocessing.entities.categories.Category;
import com.example._08exercise_jsonprocessing.entities.users.User;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private User buyer;

    @NonNull
    @ManyToOne
    private User seller;

    @ManyToMany
    private Set<Category> categories;

    public Product() {
        categories = new HashSet<Category>();
    }

    public Product(String name, BigDecimal price, User buyer, @NonNull User seller, Set<Category> categories) {

        this();
        this.name = name;
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @NonNull
    public User getSeller() {
        return seller;
    }

    public void setSeller(@NonNull User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
