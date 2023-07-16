package com.upload.web.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.upload.web.dto.ProductRepositry;
import com.upload.web.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductRepositry productRepo;
	
	public void  saveProductToDB(MultipartFile file,String name,String description
			,int price)
	{
		Product p = new Product();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setDescription(description);
		
        p.setName(name);
        p.setPrice(price);
        
        productRepo.save(p);
	}
	
	
	public List<Product> getAllProduct()
	{
		return productRepo.findAll();
	}
	
	
    public void deleteProductById(long id)
    {
    	productRepo.deleteById(id);
    }
    
    public Product updateProductById(@PathVariable long id) {
    	Product product = productRepo.findById(id).get();
		return product;
    	
    }


	public void updteProduct(MultipartFile file, String name, String desc, int price) {
		Product p = new Product();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setDescription(desc);
		
        p.setName(name);
        p.setPrice(price);
        
        productRepo.save(p);
		
	}
}
