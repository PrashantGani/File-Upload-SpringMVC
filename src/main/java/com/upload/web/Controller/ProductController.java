package com.upload.web.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.upload.web.model.Product;
import com.upload.web.service.ProductService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	  @GetMapping("/")
	    public String showAddProduct()
	    {
	    	
	    	return "/addProduct.html";
	    }
	  
	@GetMapping("/listProducts.html")
	public String showExampleView(Model model)
	{
		List<Product> products = productService.getAllProduct();
		model.addAttribute("products", products);
		return "/listProducts.html";
	}
  
    
    @PostMapping("/addP")
    public String saveProduct(@RequestParam("file") MultipartFile file,
    		@RequestParam("pname") String name,
    		@RequestParam("price") int price,
    		@RequestParam("desc") String desc)
    {
    	productService.saveProductToDB(file, name, desc, price);
    	return "redirect:/listProducts.html";
    }
    
    @RequestMapping("/all")
	public String getAllEmployee(Model model) {
		List<Product> allProducts = productService.getAllProduct();
		model.addAttribute("products", allProducts);
		return "listProducts.html";
    }
    
    @GetMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") long id){
    	productService.deleteProductById(id);
    	return "redirect:/listProducts.html";
    }

    @RequestMapping("/updateProd/{id}")
    public String updateProduct(@PathVariable("id") long  id,Model model) {
    	Product product = productService.updateProductById(id);
    	model.addAttribute("product", product);
		return"updateProduct.html";
    }
    
    @RequestMapping("/updateProduct")
	public String updateTheProduct(
	@RequestParam("file") MultipartFile file,
	@RequestParam("pname") String name,
	@RequestParam("price") int price,
	@RequestParam("desc") String desc) {
    	System.out.println("working fine");
    	productService.updteProduct(file, name, desc, price);
    	
		return "redirect:/listProduct.html";
	}
}
