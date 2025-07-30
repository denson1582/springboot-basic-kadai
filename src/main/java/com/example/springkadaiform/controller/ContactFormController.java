package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {
	@GetMapping("/form")
	public String form(Model model) {
		ContactForm contactForm = new ContactForm();
		model.addAttribute("contactForm", contactForm);
		return "contactFormView";
	}
	
	@PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute ContactForm contactForm,
                          BindingResult bindingResult,
                          Model model) {

        // バリデーションエラーがある場合
        if (bindingResult.hasErrors()) {
            // エラー情報をモデルに追加し、元のフォームページに戻る
            // contactForm (エラー情報を含む) は自動的にモデルに追加されている
            return "contactFormView";
        }

        // バリデーションOKの場合、確認画面を表示
        // contactFormは自動的にModelに追加されており、confirmViewで参照可能
        return "confirmView"; // confirmView.html を表示
    }
	
	
}
