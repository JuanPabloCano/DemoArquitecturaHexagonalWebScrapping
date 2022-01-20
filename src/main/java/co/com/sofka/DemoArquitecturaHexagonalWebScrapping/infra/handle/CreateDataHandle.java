package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.handle;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.command.CreateData;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.generic.UseCaseHandle;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.useCase.CreateDataUseCase;
import io.quarkus.vertx.ConsumeEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.context.annotation.ApplicationScope;

@AllArgsConstructor
@ApplicationScope
public class CreateDataHandle extends UseCaseHandle {

        private final CreateDataUseCase createDataUseCase;

        @ConsumeEvent("sofka.data.datacreated")
        void consumeBlocking(CreateData createData){
            var events = createDataUseCase.apply(createData);
            save(createData.getDataId(), events);
        }
    }
