package com.example.demo.entitys;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.forms.ItemForm;

import lombok.Data;

@Entity
//エンティティクラスであることを指定
@Table(name = "item")
//エンティティクラスに，プライマリテーブルを指定するアノテーション
@Data
//対象クラス内のインスタンス変数に対してgetter/setterでアクセスすることが可能
public class ItemEntity implements Serializable {

	private static final long serialVersionUID = -6647247658748349084L;

	@Id
	// エンティティクラスのプライマリキーのプロパティまたはフィールドであることを示すアノテーション
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// プライマリキーカラムにユニークな値を自動で生成，付与する方法を指定するアノテーション
	// @Idを持つエンティティクラスまたはマップドスーパークラスのプライマリキーのフィールド
	// またはプロパティに適用
	private long id;
	
	private String name;
	private int price;
	private String content;
	//ここからFormの話
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
