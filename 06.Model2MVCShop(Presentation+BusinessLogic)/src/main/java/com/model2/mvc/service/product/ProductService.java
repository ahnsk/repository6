package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;


//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition  
public interface ProductService {
	
//	// 회원가입
//	public void insertProduct(Product product) throws Exception;
//	
//	// 내정보확인 / 로그인
//	public ProductgetUser(String userId) throws Exception;
//	
//	// 회원정보리스트 
//	public Map<String , Object> getUserList(Search search) throws Exception;
//	
//	// 회원정보수정
//	public void updateUser(User user) throws Exception;
//	
	// INSERT
	public void insertProduct(Product product) throws Exception ;

	// SELECT ONE
	public Product findProduct(int prodNo) throws Exception ;

	// SELECT LIST
	public Map<String, Object> getProductList(Search search) throws Exception ;

	// UPDATE
	//public void updateUser(User user) throws Exception ;
	public void updateProduct(Product product) throws Exception ;

	
}