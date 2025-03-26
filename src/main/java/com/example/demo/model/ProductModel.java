package com.example.demo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
	
	private long id;
	@NotNull(message = "Product name cannot be null")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters")
    private String name;

    @Positive(message = "Price must be greater than zero")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    @NotNull(message = "Country of origin cannot be null")
    @Size(min = 2, max = 50, message = "Country of origin must be between 2 and 50 characters")
    private String madeIn;

    @NotNull(message = "Brand cannot be null")
    @Size(min = 2, max = 30, message = "Brand must be between 2 and 30 characters")
    private String brand;


}
