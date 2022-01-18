package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.event.DataCreated;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.event.DataGetted;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.EventChange;

public class DataEventChange implements EventChange {
    public DataEventChange(Data data) {

        listener((DataCreated event) -> data.name = event.getName());

        listener((DataGetted event) -> data.article = new Article(
                event.getArticleID(),
                event.getTitle(),
                event.getAuthorName()));
    }
}
