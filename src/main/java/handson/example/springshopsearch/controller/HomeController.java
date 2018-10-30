package handson.example.springshopsearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("about")
	public String getAbout() {
		return "about";
	}

	@Autowired
	ItemRepository itemRepository;

	//@RequestParamで引数に値を指定することでフォームで入力した値を取得することができる
	//URLに直接指定した場合、キーワードがないのでrequire属性をfalse にしている
	//コントローラの中であればキーワード検索、なければ全検索するようになっている(条件演算子の部分)
	@GetMapping
	public String index(
			Model model,
			@RequestParam(name = "keyword", required = false) Optional<String> keyword,
			@RequestParam(name = "radio", required = false) String radio) {

		//radioの値によって検索条件をいずれかに分岐
		List<Item> list = null;
		if(keyword.isPresent()) {
			switch(radio) {
			case "name" :
				list = itemRepository.findByNameContainsOrderByIdAsc(keyword.get());
				break;
			case "description" :
				list = itemRepository.findByDescriptionContainsOrderByIdAsc(keyword.get());
				break;
			case "both" :
				list = itemRepository.findByNameContainsOrDescriptionContainsOrderByIdAsc(keyword.get(), keyword.get());
				break;
			}
		} else {
			list = itemRepository.findAll();
		}


		/*
		List<Item> list = keyword.isPresent()
				? itemRepository.findByNameContainsOrderByIdAsc(keyword.get())
				: itemRepository.findAll();
		 */
		model.addAttribute("items", list);
		return "index";
	}

}