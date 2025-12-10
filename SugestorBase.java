import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class SugestorBase {
    protected LlmClient llmClient;

    public SugestorBase(LlmClient llmClient) {
        this.llmClient = llmClient;
    }

    public List<Receita> sugerirReceitas(List<Ingrediente> disponiveis) throws ChefAiException {
        String prompt = construirPrompt(disponiveis);
        
        //Envelope
        String respostaApi = llmClient.enviarPrompt(prompt);
        
        //Extrai o texto da IA e Converte para Objetos Java
        return processarResposta(respostaApi);
    }

    protected abstract String construirPrompt(List<Ingrediente> ingredientes);

    private List<Receita> processarResposta(String jsonEnvelope) {
        List<Receita> receitas = new ArrayList<>();
        
        try {
            // === PASSO 1: Extrair o texto da IA de dentro do JSON da API do Google ===
            JSONObject envelope = new JSONObject(jsonEnvelope);
            String textoDaIA = envelope.getJSONArray("candidates")
                                       .getJSONObject(0)
                                       .getJSONObject("content")
                                       .getJSONArray("parts")
                                       .getJSONObject(0)
                                       .getString("text");

            // Limpeza de segurança (caso a IA mande ```json no começo)
            textoDaIA = textoDaIA.replace("```json", "").replace("```", "").trim();

            // === PASSO 2: Converter o texto da IA em Objetos Receita ===
            JSONArray arrayReceitas = new JSONArray(textoDaIA);

            for (int i = 0; i < arrayReceitas.length(); i++) {
                JSONObject obj = arrayReceitas.getJSONObject(i);

                String nome = obj.getString("nome");
                int tempo = obj.getInt("tempo");
                String preparo = obj.getString("modoPreparo");

                Receita receita = new Receita(nome, tempo, preparo);

                // Preenche a lista de ingredientes da receita
                JSONArray ingArray = obj.getJSONArray("ingredientesUsados");
                for (int j = 0; j < ingArray.length(); j++) {
                    receita.adicionarIngrediente(ingArray.getString(j));
                }

                receitas.add(receita);
            }

        } catch (Exception e) {
            System.err.println("Erro ao processar resposta da IA: " + e.getMessage());
            // Opcional: Imprimir o jsonEnvelope para debug se der erro
        }

        return receitas;
    }
}
