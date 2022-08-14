package com.nc.jpa_exercise1;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService services;
	
	@GetMapping("/products")
	public String showIndexPage(Model model) {
		
		model.addAttribute("products", services.getProducts());
		return "products/index";
	}
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
				
		model.addAttribute("product", new Product());
		return "products/create";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product,@RequestParam("file") MultipartFile file) throws IOException {
		
		 String photo_name=file.getOriginalFilename();
		 byte[] photo_data = file.getBytes();
		 product.setName(photo_name);
		 product.setPhoto_data(photo_data);
		 
		 services.saveProduct(product);
		 
		
		
		return "redirect:/products";
	}
	
	@GetMapping("/image")
	 public void showImage(@RequestParam("id") Long id, HttpServletResponse response, Optional<Product> product)
	   throws ServletException, IOException {
	  
	  product = services.getProductById(id);
	  response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
	  response.getOutputStream().write(product.get().getPhoto_data());
	  response.getOutputStream().close();
	 }
	
	@GetMapping("/show")
	public String showDetailPage(Model model,@RequestParam Long id) {
		
		model.addAttribute("product", services.getProduct(id));
		
		return "products/details";
	}
	
	@GetMapping("/edit")
	public ModelAndView showEditPage(@RequestParam Long id) {
		
		ModelAndView mav=new ModelAndView("products/edit");
	    
        mav.addObject("product", services.getProduct(id));
		
		return mav;
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam Long id) {
		
		services.deleteProduct(id);
		
		
		return "redirect:/products";
	}

}
