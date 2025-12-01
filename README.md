# Sistema de Cadastro e Acompanhamento de Famílias para Suporte Social
O projeto visa desenvolver um sistema centralizado para gerenciar e acompanhar famílias assistidas por uma instituição de suporte social. O objetivo é otimizar a distribuição de recursos e melhorar a avaliação dos programas de apoio, substituindo métodos informais e registros dispersos.

## Escopo e Funcionalidades
O sistema foca nas seguintes áreas principais:

### RF1:Cadastrar nova família com membros, endereços e contatos, para formalizar a informação.

### RF2: Permite cadastrar as necessidades específicas da família (ex: alimentos, saúde, moradia).

### RF3: Permite registrar os itens/serviços entregues a cada família, informando item e data, para manter um histórico completo de auxílio.

### RF4: Permite buscar familias pelo nome do responsavel ou por endereco

### RF5: Visualizar um relatório das necessidades mais cadastradas para planejar campanhas de arrecadação.

## Requisitos Não Funcionais Abordados

### RNF1: O sistema deve ser simples e intuitivo para minimizar o tempo de treinamento dos assistentes.

### RNF2: Os dados devem ser protegidos contra acesso não autorizado, visando o respeito à privacidade das famílias.

### RNF3: A busca por famílias deve retornar resultados em menos de 3 segundos.

## Estrutura do Projeto (Diagrama UML)
O projeto é baseado em cinco entidades principais: Usuário, Familia, Pessoa, NecessidadeEspecifica e Doacao.

### Familia 	
-> possui Pessoa e NecessidadeEspecifica
-> recebe Doacao.

### Pessoa 
-> Representa os membros da família, com dados detalhados (nome, documento, escolaridade, etc.).

### NecessidadeEspecifica 
-> Registra a descrição e categoria das necessidades solicitadas.

### Doacao 
-> Registra os itens doados e a quantidade.
-> Gera um recibo.


### Usuário 
Representa os colaboradores do sistema, sendo a permissão (cargo) essencial para a geração de relatórios.

## Cobertura de Testes Unitários (JUnit)
Todos os arquivos de código-fonte (Usuario.java, Pessoa.java, Familia.java, Doacao.java, Necessidade.java) foram submetidos a testes unitários com o objetivo de alcançar 100% de cobertura.

1. UsuarioTest.java
Lógica de Permissão: Testa se apenas o usuário com cargo "Coordenador" pode executar o método criarRelatorio().

Testa a contagem das necessidades mais frequentes (pela Categoria) em múltiplas famílias e cenários de lista vazia/empate.

Acesso: Testa o método autenticar() para sucesso e falha de login.

2. PessoaTest.java
Atualização Seletiva: Testa o método atualizarDados(), garantindo que apenas os campos não-nulos sejam alterados.

Inicialização: Verifica a correta inicialização de todos os 8 atributos da pessoa.

3. FamiliaTest.java
Gerenciamento de Listas: Testa a adição de Pessoa, Necessidade e Doacao às listas internas da Familia.

Responsável: Testa a lógica de definição do Responsavel (via parâmetro isResponsavel == 1).

Consistência: Garante que a adição de membros duplicados seja ignorada.

4. DoacaoTest.java
Recibo: Testa o método gerarRecibo(), validando que todos os dados da doação estejam formatados corretamente na string de saída.

5. NecessidadeTest.java
Ciclo de Vida: Testa se o status inicial é "Aberta" e se o método fecharNecessidade() altera o status para "Fechada".

Inicialização: Confirma se a dataRegistro é definida como a data atual.

Execução e Dependências

javac -cp "lib/*;classes" src/*.java -d classes
java -jar lib/junit-platform-console-standalone-1.10.1.jar -cp classes -c UsuarioTest
java -jar lib/junit-platform-console-standalone-1.10.1.jar -cp classes -c PessoaTest
java -jar lib/junit-platform-console-standalone-1.10.1.jar -cp classes -c NecessidadeTest
java -jar lib/junit-platform-console-standalone-1.10.1.jar -cp classes -c DoacaoTest
java -jar lib/junit-platform-console-standalone-1.10.1.jar -cp classes -c FamiliaTest
java -jar lib/junit-platform-console-standalone-1.10.1.jar -cp classes -c NomeDaClasseTest

Dependências Necessárias: lib/junit-platform-console-standalone-1.10.1.jar

## Problemas enfrentados

 - UML não foi completamente seguido e se mostrou incompleto, visto que muitos metodos e atributos se mostraram necessários durante o desenvolvimento(e.g. usuário não registra uma doação como consta no UML do projeto)
 - Tempo de desenvolvimento do "esqueleto" do projeto excedeu o previsto tomando muito tempo que deveria ser direcionado aos testes
 - Devido ao problema anterior algumas questões mais relacionadas à lógica de negócio e casos de uso ficaram de fora dos testes (e.g. quando uma familia é criada, ela não tem responsável, então ao buscar uma família pelo nome do responsável uma exceção é lançada. Essa falha foi encontrada em testes manuais e seria proveitoso incluir esse tipo de teste nos testes unitários.)
