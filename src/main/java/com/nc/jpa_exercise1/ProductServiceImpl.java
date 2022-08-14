package com.nc.jpa_exercise1;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return this.productRepo.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		return this.productRepo.save(product);

	}

	@Override
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		Optional<Product> optional=productRepo.findById(id);
		Product p=null;
		if(optional.isPresent()) {
			p=optional.get();
		}
		else {
			throw new RuntimeException(" Product not found for id :: " + id);
		}
		
				
		return p;
	}

	@Override
	public void deleteProduct(Long id) {
		
		 this.productRepo.deleteById(id);

	}

	
	

	@Override
	public Optional<Product> getProductById(Long id) {
		 return this.productRepo.findById(id);
	}
	

}
