import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class NecessidadeTest {

    private Necessidade necessidade;
    private final int ID_INICIAL = 1;
    private final String DESCRICAO_INICIAL = "Medicamento X";
    private final String CATEGORIA_INICIAL = "Saude";

    @BeforeEach
    void setUp() {
        necessidade = new Necessidade(ID_INICIAL, DESCRICAO_INICIAL, CATEGORIA_INICIAL);
    }

    @Test
    void testConstrutorEGetters() {
        assertEquals(ID_INICIAL, necessidade.getId(), "O ID deve ser o inicial.");
        assertEquals(DESCRICAO_INICIAL, necessidade.getDescricao(), "A descricao deve ser a inicial.");
        assertEquals(CATEGORIA_INICIAL, necessidade.getCategoria(), "A categoria deve ser a inicial.");
        assertEquals(LocalDate.now(), necessidade.getDataRegistro(), "A data de registro deve ser a data atual.");
        assertEquals("Aberta", necessidade.getStatus(), "O status inicial deve ser 'Aberta'.");
    }

    @Test
    void testSetStatus() {
        necessidade.setStatus("Encaminhada");
        assertEquals("Encaminhada", necessidade.getStatus(), "O status deve ser alterado pelo setter.");
    }


    @Test
    void testRegistrarNecessidade() {
        assertTrue(necessidade.registrarNecessidade(), "O metodo deve retornar true.");
    }

    @Test
    void testFecharNecessidade() {
        assertTrue(necessidade.fecharNecessidade(), "O metodo fecharNecessidade deve retornar true.");
        assertEquals("Fechada", necessidade.getStatus(), "O status deve ser alterado para 'Fechada'.");
    }
}