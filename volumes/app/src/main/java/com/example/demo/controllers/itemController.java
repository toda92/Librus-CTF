package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entitys.ItemEntity;
import com.example.demo.forms.InquiryForm;
import com.example.demo.forms.InquiryForm2;
import com.example.demo.forms.ItemForm;
import com.example.demo.repositries.InquiryRepository;
import com.example.demo.repositries.InquiryRepository2;
import com.example.demo.repositries.ItemRepository;
import com.example.demo.servises.ItemServise;

@Controller
//まずはここにアクセスする
//大分類↓にますは行く、『("/")』を適用
//リクエストマッピングが親、ゲットマッピングが子
@RequestMapping("/item/")
public class itemController {

	@Autowired
	ItemServise itemServise;

	// 商品登録のエリア
	@GetMapping("/create")
	public String create(ItemForm itemform) {
		return "item/create";
	}

	@PostMapping("/create")
	public String create(@Validated ItemForm itemform, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "item/create";
		}

		itemServise.create(itemform);
		itemform.clear();
		model.addAttribute("message", "商品を登録しました。");
		return "item/create";
	}



	// 商品一覧のエリア
	@GetMapping("/list")
	public String index(Model model) {
		List<ItemForm> itemlists = itemServise.list();
		model.addAttribute("itemlists", itemlists);
		return "item/list";
	}



	// 商品情報編集
	@GetMapping("/item/{id}/edit")
	public String edit(@PathVariable Long id, Model model ) {
		ItemForm foundFormItem = itemServise.findItem(id);
		model.addAttribute("foundItem", foundFormItem);
		return "item/edit";
	}
//editlistを変える


	// 商品情報更新
	@PostMapping("/item/{id}/edit")
	public String update(@PathVariable Long id, @ModelAttribute ItemForm editItem) {
		itemServise.saveItem(id,editItem);
		return "item/list";
	}



	// 商品情報削除
	@GetMapping("/item/{id}/delete-confirm")
	public String delete(@PathVariable Long id, Model model) {
		ItemForm foundFormItem = itemServise.findItem(id);
		model.addAttribute("foundItem", foundFormItem);
		return "item/delete-confirm";
	}
//メソッドを新規に作る必要なし


	@PostMapping("/item/{id}/delete-confirm")
	public String destroy(@PathVariable Long id, ItemForm itemform) {

		// idを使ってDBから読み込む
		// itemFormにセットする
		// そのitemFormオブジェクトを使ってdelete
		 itemServise.deleteItem(id);
		return "item/list";

		// delitem.setId(id);
		// itemrepository.delete(delitem);
		// return "item/list";
	}
	


}
