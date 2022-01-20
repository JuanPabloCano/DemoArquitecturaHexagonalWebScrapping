package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.repository;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.Article;

import java.io.IOException;
import java.util.List;

public interface WebScrapperRepository {

    void loadContents() throws IOException;

    List<String> listAuthors();

    List<Article> searchArticlesByAuthor(String authorName);

    List<Article> searchArticleByTitle(String title);
}
