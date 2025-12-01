import java.time.LocalDate;

public class Necessidade {
    private int id;
    private String descricao;
    private String categoria;
    private LocalDate dataRegistro;
    private String status; //  ("Aberta", "Fechada", "Encaminhada")
    
    public Necessidade(int id, String descricao, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataRegistro = LocalDate.now();
        this.status = "Aberta"; 
    }

    public boolean registrarNecessidade(){
        //implementacao real faria conexao com banco de dados
        System.out.println("Necessidade ID " + this.id + " (" + this.categoria + ") Data: " + this.dataRegistro );
        return true;
    }

    public boolean fecharNecessidade() {
        this.status = "Fechada";
        System.out.println("Necessidade ID " + this.id + " fechada.");
        return true;
    }
    
    public int getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public LocalDate getDataRegistro() {
        return this.dataRegistro;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}