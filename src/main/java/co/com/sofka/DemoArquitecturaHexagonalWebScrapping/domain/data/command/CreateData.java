package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.command;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.Command;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class CreateData extends Command {

    private String dataId;
    private String name;

}
