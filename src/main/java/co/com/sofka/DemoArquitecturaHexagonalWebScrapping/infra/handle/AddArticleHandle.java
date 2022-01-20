package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.handle;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.command.AddArticle;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.DomainEvent;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.generic.UseCaseHandle;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.useCase.AddArticleUseCase;
import io.quarkus.vertx.ConsumeEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@AllArgsConstructor
@ApplicationScope
public class AddArticleHandle extends UseCaseHandle {

    private final AddArticleUseCase addArticleUseCase;

    @ConsumeEvent("sofka.data.articleadded")
    void consumeBlocking(AddArticle addArticle){
        var events = addArticleUseCase.fetchAllLinkMetaDetailsFromPage();
        assert false;
        save(addArticle.getDataID(), (List<DomainEvent>) events);
    }
}
