package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.event.DataCreated;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.event.DataGetted;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.AggregateRoot;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.DomainEvent;

import java.util.List;

public class Data extends AggregateRoot {

    protected Article article;
    protected String name;

    public Data(String id, String name) {
        super(id);
        subscribe(new DataEventChange(this));
        appendChange(new DataCreated(name)).apply();
    }

    private Data(String id){
        super(id);
        subscribe(new DataEventChange(this));
    }

    public static Data from(String id, List<DomainEvent> events){
        var data = new Data(id);
        events.forEach(data::applyEvent);
        return data;
    }

    public void getData(String articleID, String title, String authorName){
        appendChange(new DataGetted(articleID, title, authorName)).apply();
    }

    public Article Article() {
        return article;
    }

    public String Name() {
        return name;
    }
}
