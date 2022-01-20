package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.entrypoint;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.Article;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.service.WebScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class WebScrapperQueryController {

    @Autowired
    WebScrapperService webScrapperService;

    @GetMapping(value = "/by-author/{authorName}", produces = "application/json")
    public List<Article> searchArticlesByAuthor(@PathVariable("authorName") String authorName) {
        return webScrapperService.searchArticlesByAuthor(authorName);
    }

    @GetMapping(value = "/authors", produces = "application/json")
    public List<String> listAuthors() {
        return webScrapperService.listAuthors();
    }

    @GetMapping(value = "/by-title/{title}", produces = "application/json")
    public List<Article> searchArticleByTitle(@PathVariable("title") String title) {
        return webScrapperService.searchArticleByTitle(title);
    }
}
