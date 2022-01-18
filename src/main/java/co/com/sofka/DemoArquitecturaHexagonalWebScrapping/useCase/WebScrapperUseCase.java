package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.useCase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class WebScrapperUseCase {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private String pageUrl;
    private Integer pageParseTimeoutMillis;
    private List<String> detailsSearchTag;
    private List<String> linksSearchTags;

    public CompletableFuture<List<Map<String, String>>> fetchAllLinkMetaDetailsFromPage() {
        List<Map<String, String>> metaDetailsList = new ArrayList<>();
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getAllLinksFromPage();
            } catch (IOException e) {
                LOGGER.error("Error in getting links.", e);
            }
            return null;
        }).thenApplyAsync(links -> {
            links.forEach(lnk -> {
                CompletableFuture<Map<String, String>> detailsFuture = CompletableFuture.supplyAsync(() -> {
                    try {
                        return getLinkDetails(lnk);
                    } catch (IOException e) {
                        LOGGER.error("Error in getting link details.", e);
                    }
                    return null;
                });
                try {
                    metaDetailsList.add(detailsFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    LOGGER.error("Error in extracting results after task completion.", e);
                }
            });
            return metaDetailsList;
        }).toCompletableFuture();
    }

    private Map<String, String> getLinkDetails(String url) throws IOException {
        Document document = Jsoup.parse(new URL(url), pageParseTimeoutMillis);
        Map<String, String> tagDetails = new HashMap<>();
        detailsSearchTag.forEach(tag -> tagDetails.put(tag, document.select(tag).get(0).attr("content")));
        return tagDetails;
    }

    private Set<String> getAllLinksFromPage() throws IOException {
        Document document = Jsoup.parse(new URL(pageUrl), pageParseTimeoutMillis);
        return searchLinkTags(document, linksSearchTags);
    }

    private Set<String> searchLinkTags(Document doc, List<String> searchTags) {
        Set<String> links = new HashSet<>();
        searchTags.forEach(tag -> {
            Elements elements = doc.select(tag);
            elements.forEach(e -> links.add(e.select("a[href]").attr("href")));
        });
        return links;
    }
}
