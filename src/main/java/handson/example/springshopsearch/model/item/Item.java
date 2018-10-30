package handson.example.springshopsearch.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity			/*このクラスがSpring BootのEntityとして扱える様になる*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    @Id			//主キーとして扱えるようになる
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")	//@Columnを指定することでDBのカラム名を明示的に指定
    public Long id;

    @Column(name = "name")	//@Columnを指定することでDBのカラム名を明示的に指定
    public String name;

    @Min(value = 0)
    @Column(name = "price")	//@Columnを指定することでDBのカラム名を明示的に指定
    public int price;

    @Column(name = "description", columnDefinition = "TEXT")	//@Columnを指定することでDBのカラム名を明示的に指定
    public String description;
}