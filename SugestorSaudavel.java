import java.util.List;

public class SugestorSaudavel extends SugestorBase {

    public SugestorSaudavel(LlmClient llmClient) {
        super(llmClient);
    }

    @Override
    protected String construirPrompt(List<Ingrediente> ingredientes) {
        StringBuilder sb = new StringBuilder();
        
        // 1. Contexto e Ingredientes
        sb.append("Aja como um chef. Tenho estes ingredientes disponíveis: ");
        for (Ingrediente i : ingredientes) {
            sb.append(i.getNome()).append(" (").append(i.getQuantidade()).append("), ");
        }
        
        // 2. A ORDEM INEGOCIÁVEL (O "Schema")
        sb.append(". \n\n");
        sb.append("Gere 3 sugestões de receitas saudáveis (preparo de até 30 minutos) usando o máximo possível desses ingredientes. As receitas precisam ser executáveis em uma cozinha doméstica \n");
        sb.append("IMPORTANTE: Sua resposta deve ser EXCLUSIVAMENTE um Array JSON cru.");
        sb.append("Não use Markdown (```json). Não inclua texto introdutório.\n");
        sb.append("Siga estritamente este formato para cada objeto do array:\n");
        sb.append("[\n");
        sb.append("  {\n");
        sb.append("    \"nome\": \"Nome da Receita\",\n");
        sb.append("    \"tempo\": 30, (número inteiro em minutos)\n");
        sb.append("    \"modoPreparo\": \"Use quebras de linha (\\n) para separar cada passo. Ex: 1. Faça isso.\\n2. Faça aquilo.\",\n");
        sb.append("    \"ingredientesUsados\": [\"Ovo\", \"Farinha\"] (lista de strings)\n");
        sb.append("  }\n");
        sb.append("]");

        return sb.toString();
    }
}