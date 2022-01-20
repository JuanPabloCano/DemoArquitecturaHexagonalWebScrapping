package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.generic;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.DomainEvent;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.EventStoreRepository;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.StoredEvent;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.message.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Date;
import java.util.List;

@ApplicationScope
public abstract class UseCaseHandle {

    @Autowired
    private EventStoreRepository repository;

    @Autowired
    private BusService busService;

    public void save(String dataId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("data", dataId, storedEvent));
        events.forEach(busService::send);
    }
}