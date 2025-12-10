import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeminiClient implements LlmClient {

    @Override
    public String enviarPrompt(String prompt) throws ChefAiException {
        String apiKey = ConfigLoader.get("api.key");
        String baseUrl = ConfigLoader.get("api.url");

        if (apiKey == null || baseUrl == null) {
             throw new ChefAiException("Configuração (Chave/URL) não encontrada.");
        }
        
        // Garante que não tem espaços extras
        String fullUrl = baseUrl.trim() + apiKey.trim();

        try {
            // Monta o JSON para o Gemini
            String jsonBody = String.format(
                "{\"contents\": [{\"parts\": [{\"text\": \"%s\"}]}]}", 
                prompt.replace("\"", "'").replace("\n", " ")
            );

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() != 200) {
                throw new ChefAiException("Erro na API (" + response.statusCode() + "): " + response.body());
            }
            
            return response.body();

        } catch (Exception e) {
            throw new ChefAiException("Falha na comunicação: " + e.getMessage());
        }
    }
}