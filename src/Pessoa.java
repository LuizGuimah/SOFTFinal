import java.time.LocalDate;

public class Pessoa {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String grauParentesco; 
    private String documento;
    private String escolaridade;
    private String profissao;
    private String contato;

    public Pessoa(int id, String nome, LocalDate dataNascimento, String grauParentesco, String documento, String escolaridade, String profissao, String contato) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.grauParentesco = grauParentesco;
        this.documento = documento;
        this.escolaridade = escolaridade;
        this.profissao = profissao;
        this.contato = contato;
    }
    
    public boolean cadastrarPessoa(){
        //implementacao real faria conexao com banco de dados
        System.out.println("Cadastrado: " + this.nome);
        System.out.println("Data de nascimento: " + this.dataNascimento);
        System.out.println("Grau de parentesco: " + this.grauParentesco);
        System.out.println("documento: " + this.documento);
        System.out.println("escolaridade: " + this.escolaridade);
        System.out.println("profissao: " + this.profissao);
        return true;
    }

    public boolean atualizarDados(String nome, LocalDate dataNascimento, String grauParentesco, String documento, String escolaridade, String profissao) {
        // Implementação real faria conexao com banco de dados.
        if (nome != null) {
            this.nome = nome;
        }        
        if (dataNascimento != null) {
            this.dataNascimento = dataNascimento;
        }
        if (grauParentesco != null) {
            this.grauParentesco = grauParentesco;
        }        
        if (documento != null) {
            this.documento = documento;
        }
        if (escolaridade != null) {
            this.escolaridade = escolaridade;
        }
        if (profissao != null) {
            this.profissao = profissao;
        }

        System.out.println("Dados do membro " + this.nome + " atualizados.");
        return true;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public String getGrauParentesco() {
        return this.grauParentesco;
    }

    public String getDocumento() {
        return this.documento;
    }

    public String getEscolaridade() {
        return this.escolaridade;
    }
    
    public String getProfissao() {
        return this.profissao;
    }
}