package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.service;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.Article;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.repository.WebScrapperRepository;
import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.useCase.WebScrapperUseCase;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class WebScrapperService implements WebScrapperRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final List<Article> articles = new ArrayList<>();

    @Value("${newspaper.thehindu.url}")
    private String newspaperUrl;

    @Value("${newspaper.thehindu.parse.timeout.ms}")
    Integer parseTimeoutMillis;

    @Value("${newspaper.thehindu.article.authortag}")
    String authorTagName;

    @Value("${newspaper.thehindu.article.titletag}")
    String titleTagName;

    @Value("#{'${newspaper.thehindu.article.searchtags}'.split(',')}")
    List<String> articleLinksSearchTags;


    @PostConstruct
    @Override
    public void loadContents() {
        LOGGER.info("loadContents()...start");
        articles.clear();
        List<String> articleDetailsSearchTags = Arrays.asList(authorTagName, titleTagName);
        WebScrapperUseCase scraperHelper = new WebScrapperUseCase(
                newspaperUrl, parseTimeoutMillis, articleDetailsSearchTags, articleLinksSearchTags);

        LOGGER.info("Extracting article details...start");

        scraperHelper.fetchAllLinkMetaDetailsFromPage()
                .thenAccept(list -> list.stream()
                        .filter(map -> map.get(authorTagName) != null && map.get(authorTagName)
                                .length() > 0)
                        .forEach(map -> articles.add(new Article(
                                map.get(titleTagName),
                                map.get(authorTagName)))));

        LOGGER.info("loadContents()...completed");
    }

    @Override
    public List<String> listAuthors() {
        return articles.stream().map(
                        Article::AuthorName)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> searchArticlesByAuthor(String authorName) {
        return articles.stream()
                .filter(a -> a.AuthorName()
                        .equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> searchArticleByTitle(String title) {
        return articles.stream()
                .filter(a -> a.Title()
                        .startsWith(title))
                .collect(Collectors.toList());
    }
}
