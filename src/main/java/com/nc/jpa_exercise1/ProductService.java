package com.nc.jpa_exercise1;

import java.util.List;
import java.util.Optional;

public interface ProductService {
	
	public List<Product> getProducts();
	public Product saveProduct(Product product);
	public Product getProduct(Long id);
	public Optional<Product> getProductById(Long id);
	public void deleteProduct(Long id);
	
	

}
