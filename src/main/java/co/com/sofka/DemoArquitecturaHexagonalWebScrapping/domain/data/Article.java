package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Article implements Serializable {

    private String id = UUID.randomUUID().toString();
    private String title;
    private String authorName;

    public Article(String title, String authorName) {
        this.title = Objects.requireNonNull(title);
        this.authorName = Objects.requireNonNull(authorName);
    }

    public void addId(String id) {
        this.id = id;
    }

    public void addTitle(String title) {
        this.title = title;
    }

    public void addAuthorName(String authorName) {
        this.authorName = authorName;
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
