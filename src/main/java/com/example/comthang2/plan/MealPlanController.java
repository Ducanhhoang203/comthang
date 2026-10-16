package com.example.comthang2.plan;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class MealPlanController {

    private final MealPlanRepository planRepository;

    public MealPlanController(MealPlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public record PlanRequest(
            @NotBlank String name,
            @Min(1) int mealsPerMonth,
            @NotNull @Positive BigDecimal price
    ) {}

    @GetMapping
    public List<MealPlan> getAllPlans() {
        return planRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealPlan createPlan(@Valid @RequestBody PlanRequest request) {
        MealPlan plan = new MealPlan(
                request.name(),
                request.mealsPerMonth(),
                request.price()
        );

        return planRepository.save(plan);
    }
}