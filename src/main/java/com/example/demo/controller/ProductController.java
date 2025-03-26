package com.example.demo.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;



@Controller
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/productform")
     public String getForm(Model model)
    {
		
		 ProductModel productModel=new ProductModel();
		//productModel.setPrice(0);
		 model.addAttribute("productModel" ,productModel);
		 
		  
	      return "add-product";
     }
	
	
	// send the data to database
	@PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute ProductModel productModel ,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "add-product";
		}
        productService.saveProduct(productModel);
        return "success";
    }
	
	
	//reading the all data from database
	@GetMapping("/getAllProducts")
	public String getAllProducts(Model model)
	{
		List<ProductEntity> products=productService.getAllProducts();
		model.addAttribute("products",products);
		return "productList";
	}
	// finding product by it's id
	@GetMapping("/getProduct/{id}")
	public String getProductById(@PathVariable long id, Model model) {
		Optional<ProductEntity> product= productService.getProductById(id);
		if(product.isPresent()) {
			ProductEntity productEntity=product.get();
			model.addAttribute("product" ,productEntity);
		}
		else {
			model.addAttribute("errorMessage","Product not found with ID: "+id );
		}
		return "productDetail";
	}
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.deletProduct(id);
		return "redirect:/getAllProducts";
	}
	@GetMapping("/editProduct/{id}")
	public String editProduct(@PathVariable long id, Model model) {
	    ProductModel productModel = productService.editProduct(id);

	    // Add the product details to the model
	    model.addAttribute("productModel", productModel);

	    return "editform"; // Return to the edit form for the user to edit product details
	}

	// Add the @PostMapping for updating the product
	@PostMapping("/updateProduct")
	public String updateProduct(@Valid @ModelAttribute("productModel") ProductModel productModel, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "editform"; // Return to the edit form if validation fails
	    }
	    productService.updateProduct(productModel);
	    return "redirect:/getAllProducts";
	}

}
