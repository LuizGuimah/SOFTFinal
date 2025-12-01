import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {

    private Pessoa pessoa;
    private final LocalDate DATA_NASCIMENTO_INICIAL = LocalDate.of(1985, 10, 26);
    private final String CONTATO_INICIAL = "9999-8888";

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa(
            1,
            "Joao Silva",
            DATA_NASCIMENTO_INICIAL,
            "Pai",
            "123.456.789-00",
            "Ensino Fundamental Completo",
            "Pedreiro",
            CONTATO_INICIAL
        );
    }

    @Test
    void testConstrutorEGetters() {
        assertEquals(1, pessoa.getId(), "O ID deve ser 1.");
        assertEquals("Joao Silva", pessoa.getNome(), "O nome deve ser 'Joao Silva'.");
        assertEquals(DATA_NASCIMENTO_INICIAL, pessoa.getDataNascimento(), "A data de nascimento deve ser a data inicial.");
        assertEquals("Pai", pessoa.getGrauParentesco(), "O grau de parentesco esta incorreto.");
        assertEquals("123.456.789-00", pessoa.getDocumento(), "O documento está incorreto.");
        assertEquals("Ensino Fundamental Completo", pessoa.getEscolaridade(), "A escolaridade está incorreta.");
        assertEquals("Pedreiro", pessoa.getProfissao(), "A profissao está incorreta.");
    }

    @Test
    void testCadastrarPessoa() {
        assertTrue(pessoa.cadastrarPessoa(), "O método cadastrarPessoa deve retornar true se teve sucesso.");
    }


    @Test
    void testAtualizarDados_TodosCampos() {
        LocalDate novaDataNascimento = LocalDate.of(1990, 1, 1);

        boolean resultado = pessoa.atualizarDados(
            "Maria Oliveira",
            novaDataNascimento,
            "Mae",
            "987.654.321-99",
            "Ensino Superior Completo",
            "Cozinheira"
        );

        assertTrue(resultado, "A atualização de dados deve retornar true.");
        assertEquals("Maria Oliveira", pessoa.getNome(), "O nome deve ser atualizado.");
        assertEquals(novaDataNascimento, pessoa.getDataNascimento(), "A data de nascimento deve ser atualizada.");
        assertEquals("Mae", pessoa.getGrauParentesco(), "O grau de parentesco deve ser atualizado.");
        assertEquals("987.654.321-99", pessoa.getDocumento(), "O documento deve ser atualizado.");
        assertEquals("Ensino Superior Completo", pessoa.getEscolaridade(), "A escolaridade deve ser atualizada.");
        assertEquals("Cozinheira", pessoa.getProfissao(), "A profissão deve ser atualizada.");
    }

    @Test
    void testAtualizarDados_NenhumCampo() {
        String nomeOriginal = pessoa.getNome();
        String profissaoOriginal = pessoa.getProfissao();
        pessoa.atualizarDados(
            null,
            null,
            null,
            null,
            null,
            null
        );

        assertEquals(nomeOriginal, pessoa.getNome(), "O nome não deve ser alterado quando null é passado.");
        assertEquals(profissaoOriginal, pessoa.getProfissao(), "A profissão não deve ser alterada quando null é passado.");
        assertEquals(DATA_NASCIMENTO_INICIAL, pessoa.getDataNascimento(), "A data de nascimento não deve ser alterada.");
    }

    @Test
    void testAtualizarDados_AtualizacaoSeletiva() {
        String novoNome = "Jose da Silva";
        String novoDocumento = "444.444.444-44";

        pessoa.atualizarDados(
            novoNome,
            null, //nascimento
            null, //parentesco
            novoDocumento,
            null,// escolaridade
            "Técnico de TI"
        );

        // Verifica os campos alterados
        assertEquals(novoNome, pessoa.getNome(), "O nome deve ser atualizado.");
        assertEquals(novoDocumento, pessoa.getDocumento(), "O documento deve ser atualizado.");
        assertEquals("Técnico de TI", pessoa.getProfissao(), "A profissão deve ser atualizada.");

        // Verifica os campos não alterados
        assertEquals("Pai", pessoa.getGrauParentesco(), "O grau de parentesco não deve ter mudado.");
        assertEquals(DATA_NASCIMENTO_INICIAL, pessoa.getDataNascimento(), "A data de nascimento não deve ter mudado.");
    }
}