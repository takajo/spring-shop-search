package handson.example.springshopsearch.model.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
	//商品の一覧から名前で絞り込みができるようにリポジトリクラスにメソッドを定義
	//この例では名前が含まれているか(findByNameContains)で検索して、ヒットしたものをIDソート
	List<Item> findByNameContainsOrderByIdAsc(String keyword);

	//商品説明で検索
	List<Item> findByDescriptionContainsOrderByIdAsc(String keyword);

	//両方で検索
	List<Item> findByNameOrDescriptionContainsOrderByIdAsc(String keyword, String keyword2);

}
