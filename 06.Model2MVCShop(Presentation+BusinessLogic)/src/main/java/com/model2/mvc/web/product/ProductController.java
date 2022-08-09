package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {
@Autowired
@Qualifier("productServiceImpl")
private ProductService productService;

	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	@RequestMapping("/addProductView.do")
	public String addProductView() throws Exception{
		
		System.out.println("'/addProductView.do");
	
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product") Product product, Model model) throws Exception{
		System.out.println("addProduct.do");

		productService.insertProduct(product);
			
		System.out.println(product);
		model.addAttribute("prodNo",	product.getProdNo());
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model)throws Exception{
		
		System.out.println("/getProduct");
		
		Product product=productService.findProduct(prodNo);
		System.out.println(prodNo);
		model.addAttribute("product", product);
		
		return "forward:/product/getProduct.jsp";
	}
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("prodNo")int prodNo,Model model, HttpSession session) throws Exception{
		System.out.println("/updateProductView.do");
		Product product=productService.findProduct(prodNo);
		//productService.updateProduct(product);		
		//String sessionId=((Product)session.getAttribute("product"))
		model.addAttribute("product",product);
		
		return "forward:/product/updateProductView.jsp";
		
	}
	@RequestMapping("/updateProduct.do")
	public String updateProduct(@ModelAttribute("product")Product product,Model model, HttpSession session)throws Exception{
	System.out.println("updateProduct.do");
	productService.updateProduct(product);
	System.out.println("???"+product);
	model.addAttribute("prodNo", product.getProdNo());
	return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct(@ModelAttribute("search") Search search,Model model,HttpServletRequest request) throws Exception{
		System.out.println("/listProduct.do");
		
		if(search.getCurrentPage()==0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map=productService.getProductList(search);
		
		Page resultPage=new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(),pageUnit,pageSize);
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		return "forward:/product/listProduct.jsp";
	}
	
}

