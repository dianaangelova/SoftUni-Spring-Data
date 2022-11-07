package hasEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany(targetEntity = Article.class, mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Article> articles;


    public User() {
        articles=new ArrayList<>();
    }

    public User(String name) {
        this();
        this.name = name;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
    }
}
