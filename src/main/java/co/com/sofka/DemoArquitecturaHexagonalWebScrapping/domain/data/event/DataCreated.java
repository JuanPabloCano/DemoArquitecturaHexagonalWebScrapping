package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.event;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.DomainEvent;

import java.util.Objects;

public class DataCreated extends DomainEvent {

    private final String name;

    public DataCreated(String name) {
        super("sofka.data.datacreated");
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }
}
