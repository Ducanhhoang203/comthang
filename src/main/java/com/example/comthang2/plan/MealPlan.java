package com.example.comthang2.plan;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "meal_plans")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int mealsPerMonth;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    protected MealPlan() {
    }

    public MealPlan(String name, int mealsPerMonth, BigDecimal price) {
        this.name = name;
        this.mealsPerMonth = mealsPerMonth;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMealsPerMonth() {
        return mealsPerMonth;
    }

    public BigDecimal getPrice() {
        return price;
    }
}