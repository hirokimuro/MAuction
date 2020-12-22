package com.example.demo;

import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class UserData {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@Column private long id;
//	nullable=falseでNOTNULLという意味
	@Column(length = 20, nullable = false)
	private String username;
	
	@Column(length = 50, nullable = false)
	private String mail;
	
	@Column(nullable = false)
	private String pass;
	
	@OneToMany(mappedBy = "userData")
	private List<ProductData> productDataList;
	
	
	public long getId(){ return id; }
	public void setId(long id) { this.id = id; }
	
	public String getUsername(){ return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getMail(){ return mail; }
	public void setMail(String mail) { this.mail = mail; }
	
	public String getPass(){ return pass; }
	public void setPass(String pass) { this.pass = pass; }
	
	public List<ProductData> getProductDataList() {
		return productDataList;
	}
	public void setProductDataList(List<ProductData> productDataList) {
		this.productDataList = productDataList;
	}
	
	
}
