package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.entrypoint;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.Article;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.service.WebScrapperServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class WebScrapperController {

    @Autowired
    WebScrapperServiceImp webScrapperServiceImp;

    @GetMapping(value = "/by-author/{authorName}", produces = "application/json")
    public List<Article> searchArticlesByAuthor(@PathVariable("authorName") String authorName) {
        return webScrapperServiceImp.searchArticlesByAuthor(authorName);
    }

    @GetMapping(value = "/authors", produces = "application/json")
    public List<String> listAuthors() {
        return webScrapperServiceImp.listAuthors();
    }

    @GetMapping(value = "/by-title/{title}", produces = "application/json")
    public List<Article> searchArticleByTitle(@PathVariable("title") String title) {
        return webScrapperServiceImp.searchArticleByTitle(title);
    }
}
