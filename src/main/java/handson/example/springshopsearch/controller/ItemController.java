package handson.example.springshopsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Controller //コントローラとして扱うことができるようになる
@RequestMapping("/items") //任意のURLを指定することができるよになる
public class ItemController {

	@GetMapping
	public String listItem(Model model) {
		List<Item> list = itemRepository.findAll();
		/*(
		new Item(1L, "商品1", 100, "説明1"),
		new Item(2L, "商品2", 200, "説明2"),
		new Item(3L, "商品3", 300, "説明3"),
		new Item(4L, "商品4", 400, "説明4"),
		new Item(5L, "うまい棒", 10, "あまりおいしくない"),
		new Item(6L, "たらたンらしてんじゃね＾ーよ",ポタージュ", 300000 20, "たまに食べたくなる"),
		new Item(7L, "コー00, "寒い冬の朝に飲みたい"));
		*/
		model.addAttribute("items", list);
		return "list_item";
	}

	//フォームから送信された値を受け取るためのメソッド定義
	//引数にモデルを指定することで送信されたフォームのname属性をもとに自動的に値をセットしてくれる
	@Autowired
	ItemRepository itemRepository;

	//追加処理（？）ビューを返すだけ
	@GetMapping("add")
	public String getForm() {
		return "item_form";
	}

	//登録するアレ
	@PostMapping("/add")
	public String registerItem(Item item) {
		//受け取ったモデルは対応したリポジトリのsave()メソッドを呼ぶことでDBに保存できる
		itemRepository.save(item);
		return "redirect:/items";
	}

	//詳細を得るアレ
	@GetMapping("{id:[0-9]+}")
	public String getDetail(Model model, @PathVariable("id") Long id) {
		model.addAttribute("item", itemRepository.getOne(id));
		return "detail";
	}

	//編集処理（？）ビューを返すだけ
	@GetMapping("edit/{id:[0-9]+}")
	public String getEdit() {
		return "edit_form";
	}

	//編集するアレ
	@PostMapping("/edit/{id:[0-9]+}")
	public String editItem(Item item, @PathVariable("id") Long id) {
		itemRepository.save(item);
		return "redirect:/items";
	}

}