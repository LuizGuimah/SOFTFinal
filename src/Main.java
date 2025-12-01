import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Familia familiaAtual;
    private static List<Familia> familias = new ArrayList<>();
    private static Usuario usuarioLogado;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("### 1. Inicializando Sistema de Gerenciamento ###");

        Usuario usuarioCoordenador = new Usuario(1, "admin", "12345", "Coordenador");
        if (realizarLogin(usuarioCoordenador)) {
            exibirMenuInicial();
        }
        
        System.out.println("\nSistema encerrado.");
        scanner.close();
    }

    private static boolean realizarLogin(Usuario usuarioTeste) {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        if (usuarioTeste.getLogin().equals(login) && usuarioTeste.autenticar(senha)) {
            System.out.println("Login bem-sucedido! Bem-vindo(a), " + usuarioTeste.getLogin() + " (" + usuarioTeste.getCargo() + ")");
            usuarioLogado = usuarioTeste;
            return true;
        } else {
            System.out.println("Falha no login. Login ou senha incorretos.");
            return false;
        }
    }

    private static void exibirMenuInicial() {
        int opcao = -1;

        while (opcao != 4) {
            System.out.println("\n-------------------------------------------");
            System.out.println("1. Cadastrar Familia");
            System.out.println("2. Buscar Familia");
            System.out.println("3. Gerar Relatório (Apenas Coordenador)");
            System.out.println("4. Sair");
            System.out.println("-------------------------------------------");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarFamilia();
                    break;
                case 2:
                    System.out.print("Buscar por responsavel(1) ou por endereco(2)?: ");
                    Integer NomeOuEndereco = scanner.nextInt();
                    
                    if(NomeOuEndereco == 1){
                        System.out.print("Insira o Nome do responsavel da familia: ");
                        String nomeBusca = scanner.nextLine();
                        familiaAtual = buscarFamiliaNome(nomeBusca);
                    }else{
                        System.out.print("Insira o Nome do responsavel da familia: ");
                        String enderecoBusca = scanner.nextLine();
                        familiaAtual = buscarFamiliaEndereco(enderecoBusca);
                    }
                    if(familiaAtual != null){
                        exibirMenuFamilia();
                    }
                    else{
                        System.out.println("Nao encontrada");
                    }
                    break;
                case 3:
                    gerarRelatorio();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } 
    }

    private static void exibirMenuFamilia(){
        int opcao = -1;
        while (opcao != 4) {
            System.out.println("-------------------------------------------");
            System.out.println("1. Adicionar Membro a Família");
            System.out.println("2. Registrar Necessidade para a Família");
            System.out.println("3. Registrar Doação Recebida");
            System.out.println("4. Sair");
            System.out.println("-------------------------------------------");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch(opcao){
                case 1:
                    adicionarMembro();
                    break;
                case 2:
                    registrarNecessidade();
                    break;
                case 3:
                    registrarDoacao();
                    break;
                case 4:
                    System.out.println("Voltando");
                    exibirMenuInicial();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");

            }
        }
    }

    private static void cadastrarFamilia() {
        System.out.println("\n--- Cadastro de Família ---");
        System.out.print("ID da Família (número): ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Status Inicial (Ativa, Inativa): ");
        String status = scanner.nextLine();
        
        Familia novaFamilia = new Familia(id, endereco, status);
        novaFamilia.cadastrarFamilia();
        familias.add(novaFamilia);
    }
    
    private static void adicionarMembro() {
        if (familiaAtual == null) {
            System.out.println("Nenhuma familia cadastrada/selecionada. Cadastre a família primeiro (Opção 1).");
            return;
        }

        System.out.println("\n--- Adicionar Membro a Familia ID " + familiaAtual.getId() + " ---");
        System.out.print("ID da Pessoa (numero): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Grau de Parentesco: ");
        String parentesco = scanner.nextLine();
        System.out.print("Documento (ex: RG/CPF): ");
        String documento = scanner.nextLine();
        System.out.print("Escolaridade: ");
        String escolaridade = scanner.nextLine();
        System.out.print("Profissão: ");
        String profissao = scanner.nextLine();
        System.out.print("Contato: ");
        String contato = scanner.nextLine();
        Pessoa novoMembro = new Pessoa(id, nome, dataNascimento, parentesco, documento, escolaridade, profissao, contato);
        
        System.out.print("Essa pessoa eh responsavel pela familia?(0,1): ");
        int isResponsavel = scanner.nextInt();
        familiaAtual.adicionarMembro(novoMembro, isResponsavel);
        System.out.println("Membro " + nome + " adicionado a Familia ID " + familiaAtual.getId() + ".");
    }
    
    private static void registrarNecessidade() {
        if (familiaAtual == null) {
            System.out.println("Nenhuma familia cadastrada/selecionada. Cadastre a familia primeiro (Opcao 1).");
            return;
        }

        System.out.println("\n--- Registrar Necessidade para Família ID " + familiaAtual.getId() + " ---");
        System.out.print("ID da Necessidade (número): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Categoria (ex: Alimentação, Vestuário): ");
        String categoria = scanner.nextLine();

        Necessidade novaNecessidade = new Necessidade(id, descricao, categoria);
        novaNecessidade.registrarNecessidade();
        
        familiaAtual.adicionarNecessidade(novaNecessidade);
        System.out.println("Necessidade registrada e vinculada à Família ID " + familiaAtual.getId() + ".");
    }

    private static void registrarDoacao() {
        System.out.println("\n--- Registrar Doação Recebida ---");
        System.out.print("ID da Doação (número): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Item Doado: ");
        String itemDoado = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Doacao novaDoacao = new Doacao(id, itemDoado, quantidade);
        novaDoacao.registrarEntrega();
        
        if (familiaAtual != null) {
            familiaAtual.adicionarDoacao(novaDoacao);
            System.out.println("Doação registrada. Recibo: " + novaDoacao.gerarRecibo());
            System.out.println("Doação adicionada ao histórico da Família ID " + familiaAtual.getId() + ".");
        } else {
                System.out.println("Doação registrada. Recibo: " + novaDoacao.gerarRecibo());
        }
    }
    
    private static void gerarRelatorio() {
        String relatorio = usuarioLogado.criarRelatorio(familias);
        System.out.println("Relatorio gerado com sucesso:");
        System.out.println(">> " + relatorio);
    }

    private static Familia buscarFamiliaNome(String nome){
        for(Familia familia : familias){
            if(familia.getResponsavel().getNome() == nome){
                return familia;
            }
        }
        return null;
    }
    private static Familia buscarFamiliaEndereco(String endereco){
        for(Familia familia : familias){
            if(familia.getEndereco() == endereco){
                return familia;
            }
        }
        return null;
    }
}