package com.example.demo.service;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.controller.ProductController;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    
	@Autowired
	ProductRepository productRepository;

	public void saveProduct(ProductModel productModel) {
		double taxAmount=productModel.getPrice()*(0.18);
		double totalAmount=(productModel.getPrice()*productModel.getQuantity())+taxAmount;
	    ProductEntity product=new ProductEntity();
	    product.setName(productModel.getName());
	    product.setPrice(productModel.getPrice());
	    product.setQuantity(productModel.getQuantity());
	    product.setBrand(productModel.getBrand());
	    product.setMadeIn(productModel.getMadeIn());
	    product.setCreatedAt(LocalDateTime.now());
	    product.setCreatedBy("vipul");
	    product.setTaxAmount(taxAmount);
	    product.setTotalAmount(totalAmount);
	   
	    productRepository.save(product);
    }
	public List<ProductEntity> getAllProducts() {
		  List<ProductEntity> products=productRepository.findAll();
		  return products;
		
	}
	public Optional<ProductEntity> getProductById(long id) {
		Optional<ProductEntity> product=productRepository.findById(id);
		return product;

	}
	public void deletProduct(long id) {
		productRepository.deleteById(id);
		
	}
	public ProductModel editProduct(long id) {
	    ProductEntity productEntity = productRepository.findById(id).get();
	    ProductModel productModel = new ProductModel();
	    
	    productModel.setId(productEntity.getId());
	    productModel.setName(productEntity.getName());
	    productModel.setPrice(productEntity.getPrice());
	    productModel.setBrand(productEntity.getBrand());
	    productModel.setMadeIn(productEntity.getMadeIn());
	    productModel.setQuantity(productEntity.getQuantity());
	    
	    return productModel;
	}

	public ProductEntity findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

	public void updateProduct(ProductModel productModel) {
	   
	    Optional<ProductEntity> existingProduct = productRepository.findById(productModel.getId());

	    if (existingProduct.isPresent()) {
	        ProductEntity productEntity = existingProduct.get();
	        productEntity.setName(productModel.getName());
	        productEntity.setPrice(productModel.getPrice());
	        productEntity.setQuantity(productModel.getQuantity());
	        productEntity.setMadeIn(productModel.getMadeIn());
	        productEntity.setBrand(productModel.getBrand());
	        double taxAmount = productModel.getPrice() * 0.18;
	        double totalAmount = (productModel.getPrice() * productModel.getQuantity()) + taxAmount;
	        productEntity.setTaxAmount(taxAmount);
	        productEntity.setTotalAmount(totalAmount);
	        productRepository.save(productEntity);
	    }
	}

			 
			
		
		
	}	

	
		

		

