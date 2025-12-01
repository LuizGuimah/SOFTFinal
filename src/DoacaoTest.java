import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DoacaoTest {
    private Doacao doacao;
    private final int ID_INICIAL = 500;
    private final String ITEM_INICIAL = "Chocolate";
    private final int QUANTIDADE_INICIAL = 2;

    @BeforeEach
    void setUp() {
        doacao = new Doacao(ID_INICIAL, ITEM_INICIAL, QUANTIDADE_INICIAL);
    }

    @Test
    void testGetters() {
        assertEquals(ID_INICIAL, doacao.getId(), "O ID deve ser o inicial.");
        assertEquals(ITEM_INICIAL, doacao.getItemDoado(), "O item doado deve ser o inicial.");
        assertEquals(QUANTIDADE_INICIAL, doacao.getQuantidade(), "A quantidade deve ser a inicial.");
        assertEquals(LocalDate.now(), doacao.getDataEntrega(), "A data de entrega deve ser a data atual.");
    }

    @Test
    void testRegistrarEntrega() {
        assertTrue(doacao.registrarEntrega(), "O metodo registrarEntrega deve retornar true.");
    }

    @Test
    void testGerarRecibo() {
        String recibo = doacao.gerarRecibo();
        assertNotNull(recibo, "O recibo nao deve ser nulo.");
        assertFalse(recibo.isEmpty(), "O recibo nao deve ser vazio.");

        assertTrue(recibo.contains("Recibo da Entrega " + ID_INICIAL), "O recibo deve conter o ID da doacao.");
        assertTrue(recibo.contains("Item: " + ITEM_INICIAL), "O recibo deve conter o nome do item.");
        assertTrue(recibo.contains("Quantidade: " + QUANTIDADE_INICIAL), "O recibo deve conter a quantidade.");
        assertTrue(recibo.contains("Data: " + LocalDate.now().toString()), "O recibo deve conter a data atual.");
    }
}