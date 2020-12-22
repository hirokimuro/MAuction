package com.example.demo;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

@Entity(name = "product")
@Table
public class ProductData {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column private long id;
	
	@NotBlank(message = "必須項目です")
	@Column (length = 20, nullable = false) 
	private String name;
	
	
	@Column (length = 1000000,nullable = true)
	private String phot;
	
	
	@Column ( nullable = false)
	private String date;
	
	@Column(nullable = false)
	private String category;
	
	
	@ManyToOne
	private UserData userData;
	
//	@ManyToOne
//	private ProductData productData;
	
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getPhot() {return phot;}
	public void setPhot(String phot) {this.phot = phot;}
	
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	
	
//	public ProductData getProductData() {
//		return productData;
//	}
//	public void setProductData(ProductData productData) {
//		this.productData = productData;
//	}
	
}
