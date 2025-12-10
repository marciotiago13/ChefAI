**TÍTULO DO PROJETO:** ChefAI



**OBJETIVO DO PROJETO:**

O \*\*ChefAI\*\* é uma aplicação Java que utiliza Inteligência Artificial (Google Gemini) para sugerir receitas culinárias baseadas exatamente nos ingredientes que o usuário possui em casa. O sistema aplica conceitos sólidos de Orientação a Objetos (POO) para oferecer diferentes perfis de chefs (Rápido vs. Saudável).



**VERSÃO ou DATA:** 10/122025 .v1



**FUNCIONALIDADES:**

\- \*\*Cadastro de Dispensa:\*\* O usuário insere os ingredientes e quantidades disponíveis.

\- \*\*Seleção de Perfil:\*\* Escolha entre um \*Chef Rápido\* (foco em praticidade) ou um \*Nutricionista\* (foco em saúde e baixas calorias).

\- \*\*Integração com IA:\*\* Conecta-se à API do Google Gemini para gerar receitas criativas.

\- \*\*Parser Inteligente:\*\* Converte a resposta da IA (JSON) em objetos Java estruturados automaticamente.



**EXEMPLO DE SESSÃO:**

=== Bem-vindo ao ChefAI ===

Digite um ingrediente (ou 'fim'): cenoura

Quantidade: 1

Digite um ingrediente (ou 'fim'): leite

Quantidade: 1

Digite um ingrediente (ou 'fim'): fim

Qual é sua preferência de receita?

Digite '1' - para receitas rápidas (30min)

Digite '2' - para receitas saudáveis

1

Consultando o Chef IA... (isso pode levar alguns segundos)



=== RECEITAS RÁPIDAS ENCONTRADAS ===





========================================

CREME DE CENOURA RÁPIDO

Tempo: 25 min

----------------------------------------

INGREDIENTES:

Cenoura, Leite, Cebola, Alho, Azeite, Sal, Pimenta

----------------------------------------

MODO DE PREPARO:

1\.

Descasque e pique a cenoura em rodelas finas.

Refogue meia cebola picada e um dente de alho em um fio de azeite numa panela.

2\.

Adicione a cenoura picada e refogue por 2 minutos.

Cubra com água ou caldo de legumes (aprox.

500ml) e cozinhe até a cenoura ficar bem macia (10-15 minutos).

3\.

Retire do fogo, adicione o leite (aprox.

150ml) e bata tudo com um mixer de mão ou liquidificador até obter um creme liso.

4\.

Volte ao fogo baixo, tempere com sal e pimenta a gosto.

Sirva quente.

========================================



**REQUISITOS:**

\- \*\*Java JDK\*\* (versão 11 ou superior).

\- \*\*BlueJ\*\* (ou qualquer outra IDE como IntelliJ/Eclipse).

\- \*\*Biblioteca `org.json`\*\*: Necessária para processar as respostas da IA.

\- \*\*API Key do Google Gemini\*\*: Uma chave válida para fazer as requisições.





