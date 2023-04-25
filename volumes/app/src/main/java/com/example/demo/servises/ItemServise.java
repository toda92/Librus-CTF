package com.example.demo.servises;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entitys.ItemEntity;
import com.example.demo.forms.ItemForm;
import com.example.demo.repositries.ItemRepository;


@Service

public class ItemServise {

	@Autowired
	ItemRepository itemRepository;

	public void create(ItemForm itemform) {
		itemRepository.saveAndFlush(refillToEntity(itemform));
	}

	// 修飾子の後は戻り値の型を指定する
	// 修飾子 型指定 メソッド
	public List<ItemForm> list() {
		List<ItemEntity> listofItemEntity = itemRepository.findAll();
		List<ItemForm> listofItemForm = new ArrayList<>();
		for (ItemEntity targetItemEntity : listofItemEntity) {
			listofItemForm.add(refillToForm(targetItemEntity));
			// FOR文？でfindAllしてきた全件(ENTITY型)に対して
			// FORM形に変換する(サービスクラス内でしかできない)
			// 同じクラス内で別のMethodを呼び出す方法がある
			// List<ItemForm> itemlists =listForForm
		}
		// FORM形の全件が含まれているLIST<ITEMENTITY> = new List<ItemForm> itemlists
		return listofItemForm;
	}

	public ItemForm findItem(Long id) {
		ItemEntity foundEntityItem = itemRepository.findById(id).orElse(null);
		return refillToForm(foundEntityItem);
	}

//47と48を1行にする
	public void saveItem(Long id, ItemForm editItem) {
		ItemEntity itemEntity = refillToEntity(editItem);
		itemEntity.setId(id);
		itemRepository.saveAndFlush(itemEntity);
	}

	public void deleteItem(Long id) {
		ItemEntity delitem = itemRepository.findById(id).orElse(null);
		itemRepository.delete(delitem);
	}

	private ItemEntity refillToEntity(ItemForm itemform) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setId(itemform.getId());
		itemEntity.setName(itemform.getName());
		itemEntity.setPrice(itemform.getPrice());
		itemEntity.setContent(itemform.getContent());
		return itemEntity;
	}

	private ItemForm refillToForm(ItemEntity itemEntity) {
		ItemForm itemForm = new ItemForm();
		itemForm.setId(itemEntity.getId());
		itemForm.setName(itemEntity.getName());
		itemForm.setPrice(itemEntity.getPrice());
		itemForm.setContent(itemEntity.getContent());
		return itemForm;
	}
}
