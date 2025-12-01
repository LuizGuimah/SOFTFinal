import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Familia {
    private int id;
    private Pessoa Responsavel;
    private String endereco;
    private LocalDate dataCadastro;
    private String status; // "Ativa", "Inativa"

    private List<Pessoa> membros;
    private List<Necessidade> necessidades;
    private List<Doacao> doacoes;

    public Familia(int id, String endereco, String status) {
        this.id = id;
        this.endereco = endereco;
        this.dataCadastro = LocalDate.now();
        this.status = status;

        this.membros = new ArrayList<>();
        this.necessidades = new ArrayList<>();
        this.doacoes = new ArrayList<>();

    }

    public void buscarHistoricoDoacoes() {
        for(Doacao doacao : this.doacoes){
            System.out.println(doacao);
        }
    }

    public void buscarNecessidades() {
        for(Necessidade necessidade : this.necessidades){
            System.out.println(necessidade);
        }
    }

    public void buscarMembros() {
        for(Pessoa membro : this.membros){
            System.out.println(membro);
        }
    }

    public boolean cadastrarFamilia(){
        //implementacao real faria conexao com banco de dados
        System.out.println("Familia " + this.id);
        System.out.println("Endereco " + this.endereco);
        System.out.println("Status " + this.status);
        buscarHistoricoDoacoes();
        buscarNecessidades();
        buscarMembros();

        return true;
    }

    public void adicionarMembro(Pessoa novoMembro, int isResponsavel) {
        if (!this.membros.contains(novoMembro)) {
            this.membros.add(novoMembro);
        }
        if(isResponsavel == 1){
            this.Responsavel = novoMembro;
        }
    }

    public void adicionarDoacao(Doacao doacao) {
        this.doacoes.add(doacao);
    }
    
    public void adicionarNecessidade(Necessidade necessidade) {
        this.necessidades.add(necessidade);
    }

    public int getId() {
        return this.id;
    }
    
    public Pessoa getResponsavel() {
        return this.Responsavel;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pessoa> getMembros() {
        return this.membros;
    }

    public List<Necessidade> getNecessidades() {
        return this.necessidades;
    }

    public List<Doacao> getDoacoes() {
        return this.doacoes;
    }
}