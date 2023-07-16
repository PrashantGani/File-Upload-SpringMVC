package com.upload.web.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.upload.web.dto.ProductRepositry;
import com.upload.web.model.Product;

@Controller
public class GetViewController {
	@Autowired 
	private ProductRepositry productRepo;
	
	@RequestMapping(value = "/addProduct",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnAddProduct()
	{
	  ModelAndView mv = new ModelAndView();
	  mv.setViewName("addProduct");
	  //mv.addObject("var", "halim");
	  return mv;
	  
	}
	
	@RequestMapping(value = "/listProduct",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnListProduct()
	{
	  ModelAndView mv = new ModelAndView();
	  List<Product> products = productRepo.findAll();
	  mv.setViewName("listProducts");
	  mv.addObject("products", products);
	  return mv;
	  
	}

	   @RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	    @ResponseBody
	    public ModelAndView returnUpdateProduct(@RequestParam("id") Long id) {
	        ModelAndView mv = new ModelAndView();
	        Product product = productRepo.findById(id).get();
	        if (product != null) {
	            mv.setViewName("updateProduct");
	            mv.addObject("product", product);
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "Product not found!");
	        }
	        return mv;
	    }
}
