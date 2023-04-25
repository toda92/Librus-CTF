package com.example.demo.forms;

//import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


public class ItemForm implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;
	private long id;

	@NotBlank
	// Nullも空文字も空白のみも許容しない
	@Size
	// 文字数指定 minなら最小文字数 maxなら最大文字数
	(max = 10)
	private String name;

	@NotNull
	// Nullじゃなければヨシ！
	private int price;

	@NotBlank
	@Size(max = 400)
	private String content;

	public void clear() {
		name = null;
		price = 0;
		content = null;
	}

	public void setId(Long id2) {
		// TODO Auto-generated method stub
		this.id = id2;
	}
	//ここからEntityの話

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
