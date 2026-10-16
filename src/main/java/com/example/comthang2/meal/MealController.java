package com.example.comthang2.meal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealRepository mealRepository;

    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public record MealRequest(
            @NotBlank String name,
            String description,
            @NotBlank String category,
            @NotNull @PositiveOrZero BigDecimal price
    ) {}

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @GetMapping("/{id}")
    public Meal getMeal(@PathVariable Long id) {
        return findMeal(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Meal createMeal(@Valid @RequestBody MealRequest request) {
        Meal meal = new Meal(
                request.name(),
                request.description(),
                request.category(),
                request.price()
        );

        return mealRepository.save(meal);
    }

    @PutMapping("/{id}")
    public Meal updateMeal(
            @PathVariable Long id,
            @Valid @RequestBody MealRequest request
    ) {
        Meal meal = findMeal(id);
        meal.setName(request.name());
        meal.setDescription(request.description());
        meal.setCategory(request.category());
        meal.setPrice(request.price());

        return mealRepository.save(meal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable Long id) {
        mealRepository.delete(findMeal(id));
    }

    private Meal findMeal(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy món ăn có id " + id
                ));
    }
}