package co.com.sofka.DemoArquitecturaHexagonalWebScrapping.infra.entrypoint;

import co.com.sofka.DemoArquitecturaHexagonalWebScrapping.domain.data.command.AddArticle;
import io.vertx.core.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/articles")
public class WebScrapperController {

    @Autowired
    EventBus eventBus;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/data")
    public Response executor(AddArticle addArticle) {
        eventBus.publish(addArticle.getType(), addArticle);
        return Response.ok().build();
    }
}
