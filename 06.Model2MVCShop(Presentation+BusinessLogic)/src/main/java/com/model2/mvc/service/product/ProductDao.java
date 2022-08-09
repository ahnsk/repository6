package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;


//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface ProductDao {
	
	// INSERT
	public void insertProduct(Product product) throws Exception ;

	// SELECT ONE
	public Product findProduct(int prodNo) throws Exception ;

	// SELECT LIST
	public List<Product>  getProductList(Search search) throws Exception ;

	// UPDATE
	//public void updateUser(User user) throws Exception ;
	public void updateProduct(Product product) throws Exception ;
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	//public int getTotalCount(Search search) throws Exception ;
	public int getTotalCount(Search search) throws Exception ;
	// 게시판 currentPage Row 만  return 
	public String makeCurrentPageSql(String sql, Search search);
}