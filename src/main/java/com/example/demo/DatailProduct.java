package com.example.demo;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
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
	
	@Column(nullable = false)
	private String category;
	
	
//	@OneToMany(mappedBy = "ProductData")
//	private List<ProductData> productDataList;
	
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public int getName() {return price;}
	public void setName(int price) {this.price = price;}
	
	public int getPhot() {return bit;}
	public void setPhot(int bit) {this.bit = bit;}
	
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
}
