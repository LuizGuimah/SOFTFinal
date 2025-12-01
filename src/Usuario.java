import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Usuario {
    private int id;
    private String login;
    private String senha; 
    private String cargo; //faltou no uml importante para geracao de relatorio

    public Usuario(int id, String login, String senha, String cargo) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    public boolean autenticar(String senhaTentativa) {
        return this.senha.equals(senhaTentativa);
    }

public String criarRelatorio(List<Familia> familias) throws SecurityException {
    if (!this.cargo.equalsIgnoreCase("Coordenador")) {
        throw new SecurityException("Usuario " + this.login + " nao tem permissao de Coordenador para gerar relatorios.");
    }
    Map<String, Integer> contagemNecessidades = new HashMap<>();

    for (Familia familia : familias) {
        for (Necessidade necessidade : familia.getNecessidades()) {
            String categoria = necessidade.getCategoria();
            contagemNecessidades.put(
                categoria, 
                contagemNecessidades.getOrDefault(categoria, 0) + 1
            );
        }
    }

    String categoriaMaisFrequente = "";
    int maiorContagem = 0;
    if (!contagemNecessidades.isEmpty()) {
        for (Map.Entry<String, Integer> entry : contagemNecessidades.entrySet()) {
            if (entry.getValue() > maiorContagem) {
                maiorContagem = entry.getValue();
                categoriaMaisFrequente = entry.getKey();
            }
        }
    } else {
        return String.format("Nenhuma necessidade registrada nas famílias.");
    }
    
    return String.format(
        "Necessidade mais cadastrada: %s (total: %d).", 
        categoriaMaisFrequente, 
        maiorContagem
    );
}

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getCargo() {
        return this.cargo;
    }
}