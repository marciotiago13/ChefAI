import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Ingrediente> dispensa = new ArrayList<>();
        SugestorBase sugestor;
        String opcao;
        String tipo;

        System.out.println("=== Bem-vindo ao ChefAI ===");
        
        //Aqui ocorre o input dos dados por parte do usuário
        while (true) {
            System.out.print("Digite um ingrediente (ou 'fim'): ");
            String nome = scanner.nextLine();
            if (nome.equalsIgnoreCase("fim")){
                System.out.print("Qual é sua preferência de receita?\n");
                System.out.print("Digite '1' - para receitas rápidas (30min)\n");
                System.out.print("Digite '2' - para receitas saudáveis\n");
                opcao = scanner.nextLine();
                break;  
            }
            
            System.out.print("Quantidade: ");
            String qtd = scanner.nextLine();
            dispensa.add(new Ingrediente(nome, qtd));
        }
        
        //criacao do objeto GeminiClient que irá fazer a ponte com a API do Google Gemini AI
        LlmClient client = new GeminiClient();
        
        //Aqui a busca será direcionada entre opção sudável e opção rápida
        if(opcao.equalsIgnoreCase("1")){
            sugestor = new SugestorRapido(client);
            tipo = "\n=== RECEITAS RÁPIDAS ENCONTRADAS ===\n";
        }
        else{
            sugestor = new SugestorSaudavel(client);
            tipo = "\n=== RECEITAS SAUDÁVEIS ENCONTRADAS ===\n";
        }
        
        //Aqui inicia a busca de fato
        try {
            System.out.println("Consultando o Chef IA... (isso pode levar alguns segundos)");
            
            // AQUI MUDOU: Agora capturamos a lista!
            List<Receita> sugestoes = sugestor.sugerirReceitas(dispensa);

            System.out.println(tipo);
            
            if (sugestoes.isEmpty()) {
                System.out.println("O Chef não conseguiu gerar receitas estruturadas desta vez.");
            } else {
                for (Receita r : sugestoes) {
                    // Aqui o Java usa o toString() da classe Receita automaticamente
                    System.out.println(r); 
                    System.out.println("-----------------------------------");
                }
            }

        } catch (ChefAiException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }
}