package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable{

    private String id = UUID.randomUUID().toString();
    private String title;
    private String authorName;

    public Article(String title, String authorName){
        this.title = Objects.requireNonNull(title);
        this.authorName = Objects.requireNonNull(authorName);
    }

    public String Id() {
        return id;
    }

    public String Title() {
        return title;
    }

    public String AuthorName() {
        return authorName;
    }
}
