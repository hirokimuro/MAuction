package com.example.demo;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "Datail")
@Table
public class DatailProduct {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column private long id;
	
	@Column ( nullable = false)
	private int price;
	
	@Column (nullable = false)
	private int bit;
	
	@Column ( nullable = false)
	private String date;
	
//	@Column(nullable = false)
//	private String category;
	
	
//	@OneToMany(mappedBy = "productData")
//	private List<ProductData> productDataList;
	
	
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	
	public int getBit() {return bit;}
	public void setBit(int bit) {this.bit = bit;}
	
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	
//	public String getCategory() {return category;}
//	public void setCategory(String category) {this.category = category;}
	
//	public List<ProductData> getProductDataList() {
//		return productDataList;
//	}
//	public void setProductDataList(List<ProductData> productDataList) {
//		this.productDataList = productDataList;
//	}
	
	
}
