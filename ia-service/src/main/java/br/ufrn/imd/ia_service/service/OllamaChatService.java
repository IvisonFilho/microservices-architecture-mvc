package br.ufrn.imd.ia_service.service;

import java.util.Arrays;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.ufrn.imd.ia_service.dto.TicketCategory;
import br.ufrn.imd.ia_service.dto.TicketClassification;

@Service
@Primary
public class OllamaChatService implements ChatService {
   private final ChatClient chatClient;

   public OllamaChatService(ChatClient.Builder chatClientBuilderOllamaBuilder) {


      OllamaOptions ollamaOptions = OllamaOptions.builder()
            .format("json")
            .model("phi3:mini")
            .build();

      this.chatClient = chatClientBuilderOllamaBuilder.defaultOptions(ollamaOptions).build();
   }

   @Override
   public TicketClassification classification(String descriptionTicket) {
      String promptTemplate = """
                     Sua tarefa é classificar um ticket de helpdesk.
                     Classifique o ticket e determine a confiança (0.0 a 1.0).
                     Escolha a categoria mais apropriada em MAIÚSCULAS dentre: {categorias}.

                     Descrição: "{descricao}"
                        """;

      Prompt prompt = new PromptTemplate(promptTemplate)
            .create(Map.of(
                  "descricao", descriptionTicket,
                  "categorias", Arrays.toString(TicketCategory.values())));

      return chatClient.prompt(prompt)
            .call()
            .entity(TicketClassification.class);
   }

}