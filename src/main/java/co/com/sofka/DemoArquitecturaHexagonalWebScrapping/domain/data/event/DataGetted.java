package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.event;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.DomainEvent;

import java.util.Objects;

public class DataGetted extends DomainEvent {

    private final String dataID;
    private final String articleID;
    private final String title;
    private final String authorName;

    public DataGetted( String dataID, String articleID, String title, String authorName) {
        super("sofka.data.datagetted");
        this.dataID = Objects.requireNonNull(dataID);
        this.articleID = Objects.requireNonNull(articleID);
        this.title = Objects.requireNonNull(title);
        this.authorName = Objects.requireNonNull(authorName);
    }

    public String getDataID() {
        return dataID;
    }

    public String getArticleID() {
        return articleID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }
}
