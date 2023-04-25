package com.example.demo.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.ItemEntity;
import com.example.demo.forms.InquiryForm;
import com.example.demo.forms.InquiryForm2;
import com.example.demo.forms.ItemForm;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String>{
	Optional<ItemEntity> findById(Long id);
	List<ItemEntity> findAll();

}