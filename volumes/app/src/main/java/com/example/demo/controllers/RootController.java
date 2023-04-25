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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.forms.InquiryForm;
import com.example.demo.forms.InquiryForm2;
import com.example.demo.forms.ItemForm;
import com.example.demo.repositries.InquiryRepository;
import com.example.demo.repositries.InquiryRepository2;
import com.example.demo.repositries.ItemRepository;

@Controller
//まずはここにアクセスする
//大分類↓にますは行く、『("/")』を適用
//リクエストマッピングが親、ゲットマッピングが子
@RequestMapping("/")
public class RootController {

	@Autowired
	InquiryRepository repository;
	@Autowired
	InquiryRepository2 repository2;

	@GetMapping
	public String index() {
		return "root/index";
		// まずはここが出る
	}

	// formに返す
	@GetMapping("/form")
	public String form(InquiryForm inquiryForm) {
		return "root/form";
		// rootの下のformにいく
		// form.html６７行目が送信される
	}

	@PostMapping("/form")
	public String form(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form";
		}

		String aaa;
		aaa = "アイウエオ";
		// RDBと連携できることを確認しておきます。
		repository.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form";
	}

	// form2のエリア
	@GetMapping("/form2")
	public String form2(InquiryForm2 inquiryForm2) {
		return "root/form2";
	}

	@PostMapping("/form2")
	public String form2(@Validated InquiryForm2 inquiryForm2, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form2";
		}

		String aaa;
		aaa = "アイウエオ";
		// RDBと連携できることを確認しておきます。
		repository2.saveAndFlush(inquiryForm2);
		inquiryForm2.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form2";
	}
	@GetMapping("/security-test1")
	public String test(Model model) {
	    model.addAttribute("message", "これはテストページです。");
	    return "root/security-test1";
	}
	@GetMapping("/security-test2")
	public String test2(Model model) {
	    model.addAttribute("message", "これはテストページです。");
	    return "root/security-test2";
	}
	@GetMapping("/security-test3")
	public String test3(Model model) {
	    model.addAttribute("message", "<script>document.getElementById('greeting').style.display='block'; var name = prompt('Enter your name:'); document.getElementById('greeting-text').textContent = 'Hello, ' + name + '!';</script>");
	    return "root/security-test3";
	}
	@GetMapping("/security-test4")
	public String test4(Model model) {
	    model.addAttribute("message", "<script>document.getElementById('greeting').style.display='block'; var name = prompt('Enter your name:'); document.getElementById('greeting-text').textContent = 'Hello, ' + name + '!';</script>");
	    return "root/security-test4";
	}
    @GetMapping("/security-test4-1")
    public String getSecurityTest4() {
        // GETリクエストの処理
        return "root/security-test4-1";
    }

    @PostMapping("/security-test4-1")
    public String postSecurityTest4() {
        // POSTリクエストの処理
        return "root/security-test4-1";
    }
    @GetMapping("/security-test5")
    public String login() {
      return "root/security-test5";
    }

    @PostMapping("/security-test5")
    public String doLogin(
        @RequestParam String username,
        @RequestParam String password,
        RedirectAttributes attributes) {
      if ("user".equals(username) && "password".equals(password)) {
        return "root/security-test5";
      } else {
        attributes.addFlashAttribute("error", "ユーザー名またはパスワードが違います");
        return "root/security-test5";
      }
    }
  }

