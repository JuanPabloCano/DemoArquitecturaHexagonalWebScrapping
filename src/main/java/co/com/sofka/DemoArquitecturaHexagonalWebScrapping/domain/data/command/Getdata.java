package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.command;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.generic.Command;
import lombok.AllArgsConstructor;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Getdata extends Command {

    private final String dataID;
    private final String articleID;
    private final String title;
    private final String authorName;
}
