import java.util.ArrayList;
import java.util.List;

public class Receita {
    private String nome;
    private List<String> ingredientesNecessarios;
    private String modoPreparo;
    private int tempoPreparoMinutos;

    public Receita(String nome, int tempo, String modoPreparo) {
        this.nome = nome;
        this.tempoPreparoMinutos = tempo;
        this.modoPreparo = modoPreparo;
        this.ingredientesNecessarios = new ArrayList<>();
    }

    public void adicionarIngrediente(String ingrediente) {
        this.ingredientesNecessarios.add(ingrediente);
    }

        @Override
    public String toString() {
        // 1. Limpa a lista de ingredientes (Tira os colchetes [])
        String listaIngredientes = String.join(", ", ingredientesNecessarios);
    
        // 2. Tenta dar uma formatada no texto se ele vier num bloco só
        // (Troca "ponto final + espaço" por "ponto final + pular linha")
        String preparoFormatado = modoPreparo.replace(". ", ".\n");
    
        // 3. Monta o cartão bonito
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append("").append(nome.toUpperCase()).append("\n");
        sb.append("Tempo: ").append(tempoPreparoMinutos).append(" min\n");
        sb.append("----------------------------------------\n");
        sb.append("INGREDIENTES:\n");
        sb.append(listaIngredientes).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("MODO DE PREPARO:\n");
        sb.append(preparoFormatado).append("\n");
        sb.append("========================================\n");
    
        return sb.toString();
    }
}