package com.example.demo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table
public class ProductData {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column private long id;
	
	@Column (length = 20, nullable = false)
	private String name;
	
	@Column (length = 20,nullable = false)
	private String phot;
	
	@Column (length = 20, nullable = false)
	private String date;
	
	
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getPhot() {return phot;}
	public void setPhot(String phot) {this.phot = phot;}
	
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
}
