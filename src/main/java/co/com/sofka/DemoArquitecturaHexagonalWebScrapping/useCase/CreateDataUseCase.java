package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.useCase;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.Data;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.command.CreateData;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CreateDataUseCase implements Function<CreateData, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(CreateData createData) {
        var data = new Data(createData.getDataId(), createData.getName());
        return data.getUncommittedChanges();
    }
}
