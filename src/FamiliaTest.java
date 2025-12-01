import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FamiliaTest {
    private Familia familia;
    private final String ENDERECO_INICIAL = "Rua Principal, 10";

    @BeforeEach
    void setUp() {
        familia = new Familia(101, ENDERECO_INICIAL, "Ativa");
    }

    @Test
    void testConstrutorEGettersVazios() {
        assertEquals(101, familia.getId(), "O ID deve ser 101.");
        assertEquals(ENDERECO_INICIAL, familia.getEndereco(), "O endereço deve ser o inicial.");
        assertEquals("Ativa", familia.getStatus(), "O status deve ser 'Ativa'.");
        assertEquals(LocalDate.now(), familia.getDataCadastro(), "A data de cadastro deve ser a data atual.");
        assertTrue(familia.getMembros().isEmpty(), "A lista de membros deve estar vazia.");
        assertTrue(familia.getNecessidades().isEmpty(), "A lista de necessidades deve estar vazia.");
        assertTrue(familia.getDoacoes().isEmpty(), "A lista de doações deve estar vazia.");
        assertNull(familia.getResponsavel(), "O responsável deve ser null inicialmente.");
    }

    @Test
    void testSetStatus() {
        familia.setStatus("Inativa");
        assertEquals("Inativa", familia.getStatus(), "O status deve ser alterado para 'Inativa'.");
    }

    @Test
    void testAdicionarMembro_Sucesso() {
        Pessoa membro = new Pessoa(2, "Ana", LocalDate.now(), "Filha", "doc", "esc", "prof", "contato");
        
        familia.adicionarMembro(membro, 0);
        
        List<Pessoa> membros = familia.getMembros();
        assertEquals(1, membros.size(), "A lista deve ter 1 membro.");
        assertTrue(membros.contains(membro), "O membro Ana deve estar na lista.");
        assertNull(familia.getResponsavel(), "O responsável ainda deve ser null.");
    }

    @Test
    void testAdicionarMembro_DefinirResponsavel() {
        Pessoa responsavel = new Pessoa(3, "Carlos", LocalDate.now(), "Pai", "doc", "esc", "prof", "contato");
        familia.adicionarMembro(responsavel, 1);
        assertEquals(1, familia.getMembros().size(), "A lista deve ter 1 membro.");
        assertNotNull(familia.getResponsavel(), "O responsável não deve ser null.");
        assertEquals("Carlos", familia.getResponsavel().getNome(), "Carlos deve ser definido como responsável.");
    }

    @Test
    void testAdicionarMembro_EvitarDuplicacao() {
        Pessoa membro = new Pessoa(4, "Bia", LocalDate.now(), "Filha", "doc", "esc", "prof", "contato");
        familia.adicionarMembro(membro, 0); 
        familia.adicionarMembro(membro, 0);
        assertEquals(1, familia.getMembros().size(), "O membro não deve ser adicionado duas vezes.");
    }

    @Test
    void testAdicionarNecessidade() {
        Necessidade n1 = new Necessidade(50, "Alimentos", "Alimentação");
        familia.adicionarNecessidade(n1);
        assertEquals(1, familia.getNecessidades().size(), "A lista deve ter 1 necessidade.");
        assertEquals(50, familia.getNecessidades().get(0).getId(), "A necessidade adicionada deve ser a correta.");
    }

    @Test
    void testAdicionarDoacao() {
        Doacao d1 = new Doacao(60, "Roupas", 5);
        familia.adicionarDoacao(d1);
        
        assertEquals(1, familia.getDoacoes().size(), "A lista deve ter 1 doacao.");
        assertEquals("Roupas", familia.getDoacoes().get(0).getItemDoado(), "A doacao adicionada deve ser a correta.");
    }


    @Test
    void testCadastrarFamilia() {
        assertTrue(familia.cadastrarFamilia(), "O metodo cadastrarFamilia deve retornar true.");
    }
    
    @Test
    void testBuscaDeListas() {
        Pessoa p = new Pessoa(2, "Ana", LocalDate.now(), "Filha", "doc", "esc", "prof", "contato");
        Necessidade n = new Necessidade(50, "Alimentos", "Alimentação");
        Doacao d = new Doacao(60, "Roupas", 5);

        familia.adicionarMembro(p, 0);
        familia.adicionarNecessidade(n);
        familia.adicionarDoacao(d);
        assertDoesNotThrow(() -> familia.buscarMembros(), "Buscar membros não deve lançar excecao.");
        assertDoesNotThrow(() -> familia.buscarNecessidades(), "Buscar necessidades não deve lançar excecao.");
        assertDoesNotThrow(() -> familia.buscarHistoricoDoacoes(), "Buscar doações não deve lançar excecao.");
    }
}