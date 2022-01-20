package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.handle;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.command.Getdata;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.DomainEvent;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.generic.UseCaseHandle;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.useCase.WebScrapperUseCase;
import io.quarkus.vertx.ConsumeEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@AllArgsConstructor
@ApplicationScope
public class WebScrapperUseCaseHandle extends UseCaseHandle {

    private final WebScrapperUseCase webScrapperUseCase;

    @ConsumeEvent("sofka.data.getdata")
    void consumeBlocking(Getdata getdata){
        var events = webScrapperUseCase.fetchAllLinkMetaDetailsFromPage();
        assert false;
        saveArticle(getdata.getDataID(), (List<DomainEvent>) events);
    }
}
