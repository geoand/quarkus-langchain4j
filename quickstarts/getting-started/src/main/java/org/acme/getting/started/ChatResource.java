package org.acme.getting.started;

import static dev.langchain4j.data.message.SystemMessage.*;
import static dev.langchain4j.data.message.UserMessage.*;

import java.util.List;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/whois")
public class ChatResource {

    @Inject
    ChatLanguageModel chatbot;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String greeting(String name) {
        return chatbot.generate(
            List.of(
                systemMessage("You are a tourist guide. A tourist is asking questions, be nice and answer as if the person asked to know about Denmark."),
                userMessage("Who is " + name + "?"))
        ).content().text();
    }

}