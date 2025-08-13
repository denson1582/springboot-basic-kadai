package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {
	@GetMapping("/form")
	public String form(Model model) {
		// フラッシュ属性から値があればセット
		if (!model.containsAttribute("contactForm")) {
			ContactForm contactForm = new ContactForm();
			model.addAttribute("contactForm", contactForm);
		}
		return "contactFormView";
	}
	
	@PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute ContactForm contactForm,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        // バリデーションエラーがある場合
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", bindingResult);
            redirectAttributes.addFlashAttribute("contactForm", contactForm);
            return "redirect:/form";
        }

        // バリデーションOKの場合、確認画面を表示
        // contactFormは自動的にModelに追加されており、confirmViewで参照可能
        return "confirmView"; // confirmView.html を表示
    }
}