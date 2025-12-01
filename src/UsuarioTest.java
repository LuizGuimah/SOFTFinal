import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
    private Usuario coordenador;
    private Usuario assistente;

    @BeforeEach
    void setUp() {
        coordenador = new Usuario(1, "coordenador_log", "senha_c", "Coordenador");
        assistente = new Usuario(2, "assistente_log", "senha_a", "Assistente Social");
    }


    @Test
    void testConstrutorEGetters() {
        assertEquals(1, coordenador.getId(), "O ID deve ser 1.");
        assertEquals("coordenador_log", coordenador.getLogin(), "O Login deve ser 'coordenador_log'.");
        assertEquals("Coordenador", coordenador.getCargo(), "O Cargo deve ser 'Coordenador'.");
    }
    

    @Test
    void testAutenticar_Sucesso() {
        assertTrue(coordenador.autenticar("senha_c"), "A autenticação deve ser bem-sucedida com a senha correta.");
    }

    @Test
    void testAutenticar_Falha() {
        assertFalse(coordenador.autenticar("senha_errada"), "A autenticação deve falhar com a senha incorreta.");
    }

    @Test
    void testCriarRelatorio_PermissaoNegada() {
        List<Familia> familiasVazias = new ArrayList<>();
        
        SecurityException exception = assertThrows(SecurityException.class, () -> {
            assistente.criarRelatorio(familiasVazias);
        }, "Um usuário sem cargo de Coordenador deve lançar SecurityException.");

        String expectedMessage = "Usuario " + assistente.getLogin() + " nao tem permissao de Coordenador para gerar relatorios.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testCriarRelatorio_NecessidadeMaisFrequente() throws SecurityException {
        Necessidade nAlimento1 = new Necessidade(10, "Cesta Básica", "Alimentos");
        Necessidade nAlimento2 = new Necessidade(11, "Leite em pó", "Alimentos");
        Necessidade nAlimento3 = new Necessidade(12, "Feijão", "Alimentos");
        Necessidade nVestuario1 = new Necessidade(20, "Casaco", "Vestuário");
        Necessidade nVestuario2 = new Necessidade(21, "Calçados", "Vestuário");
        Necessidade nSaude1 = new Necessidade(30, "Insulina", "Saúde");

        Familia f1 = new Familia(1, "Rua A", "Ativa"); 
        f1.adicionarNecessidade(nAlimento1);
        f1.adicionarNecessidade(nVestuario1);

        Familia f2 = new Familia(2, "Rua B", "Ativa"); 
        f2.adicionarNecessidade(nAlimento2);
        f2.adicionarNecessidade(nAlimento3);
        f2.adicionarNecessidade(nSaude1);

        Familia f3 = new Familia(3, "Rua C", "Ativa"); 
        f3.adicionarNecessidade(nVestuario2);
        
        List<Familia> familias = List.of(f1, f2, f3);

        String resultado = coordenador.criarRelatorio(familias);

        String esperado = "Necessidade mais cadastrada: Alimentos (total: 3).";
        assertEquals(esperado, resultado, "A categoria 'Alimentos' deve ser a mais frequente com 3 ocorrências.");
    }

    @Test
    void testCriarRelatorio_NenhumaNecessidade() throws SecurityException {
        Familia f1 = new Familia(1, "Rua D", "Ativa"); 
        Familia f2 = new Familia(2, "Rua E", "Ativa"); 
        
        List<Familia> familias = List.of(f1, f2);

        String resultado = coordenador.criarRelatorio(familias);
        
        String esperado = "Nenhuma necessidade registrada nas famílias.";
        assertEquals(esperado, resultado, "Deve retornar a mensagem de que não há necessidades registradas.");
    }
    
    @Test
    void testCriarRelatorio_EmpateNecessidades() throws SecurityException {       
        Necessidade nAlimentoA = new Necessidade(10, "Cesta Básica", "Alimentos");
        Necessidade nAlimentoB = new Necessidade(11, "Leite em pó", "Alimentos");
        Necessidade nVestuarioA = new Necessidade(20, "Casaco", "Vestuário");
        Necessidade nVestuarioB = new Necessidade(21, "Calçados", "Vestuário");
        
        Familia f1 = new Familia(1, "Rua F", "Ativa"); 
        f1.adicionarNecessidade(nAlimentoA);
        f1.adicionarNecessidade(nVestuarioA);

        Familia f2 = new Familia(2, "Rua G", "Ativa"); 
        f2.adicionarNecessidade(nAlimentoB);
        f2.adicionarNecessidade(nVestuarioB);
        
        List<Familia> familias = List.of(f1, f2);
        String resultado = coordenador.criarRelatorio(familias);
        boolean isAlimentos = resultado.equals("Necessidade mais cadastrada: Alimentos (total: 2).");
        boolean isVestuario = resultado.equals("Necessidade mais cadastrada: Vestuário (total: 2).");
        
        assertTrue(isAlimentos || isVestuario, "Em caso de empate, o resultado deve ser qualquer uma das categorias empatadas.");
    }
}