import java.time.LocalDate;

public class Doacao {
    private int id;
    private String itemDoado;
    private int quantidade;
    private LocalDate dataEntrega;

    public Doacao(int id, String itemDoado, int quantidade) {
        this.id = id;
        this.itemDoado = itemDoado;
        this.quantidade = quantidade;
        this.dataEntrega = LocalDate.now();
    }

    public boolean registrarEntrega() {
        //implementacao real faria conexao com banco de dados
        System.out.println("Doação ID " + this.id + " (" + this.itemDoado + " x" + this.quantidade + ")");
        return true;
    }

    public String gerarRecibo() {
        return String.format("Recibo da Entrega %d | Item: %s | Quantidade: %d | Data: %s", this.id, this.itemDoado, this.quantidade, this.dataEntrega.toString());
    }

    public int getId() {
        return this.id;
    }

    public String getItemDoado() {
        return this.itemDoado;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public LocalDate getDataEntrega() {
        return this.dataEntrega;
    }
}