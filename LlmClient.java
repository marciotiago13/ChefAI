public interface LlmClient {
    String enviarPrompt(String prompt) throws ChefAiException;
}