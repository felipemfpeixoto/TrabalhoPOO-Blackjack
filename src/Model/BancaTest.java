package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BancaTest {

    private Banca banca;

    @BeforeEach
    void setUp() {
        // Inicializa a banca antes de cada teste
        banca = new Banca();
    }

    @Test
    void testCalcularTotalFichas() {
        // Teste para verificar se o total inicial de fichas está correto
        assertEquals(2400, banca.calcularTotalFichas(), "O total inicial de fichas deve ser 2400.");
    }

    @Test
    void testApostarFichaExistente() {
        // Teste para verificar se a aposta com uma ficha de $100 é bem-sucedida
        assertTrue(banca.apostar(100), "A aposta de $100 deve ser bem-sucedida.");
        // Verifica se o total de fichas diminuiu após a aposta
        assertEquals(2300, banca.calcularTotalFichas(), "O total de fichas após a aposta de $100 deve ser 2300.");
    }

    @Test
    void testApostarFichaInexistente() {
        // Teste para verificar o comportamento ao apostar um valor que não existe
        assertFalse(banca.apostar(5), "A aposta de $5 não deve ser bem-sucedida, pois não há fichas de $5.");
        // Verifica se o total de fichas não mudou
        assertEquals(2400, banca.calcularTotalFichas(), "O total de fichas deve continuar 2400.");
    }

    @Test
    void testGanhouAposta() {
        // Teste para verificar se ao ganhar uma aposta de $100, $200 são adicionados corretamente
        banca.ganhouAposta(100);
        // Verifica se o total de fichas aumentou após o ganho da aposta
        assertEquals(2600, banca.calcularTotalFichas(), "O total de fichas após ganhar uma aposta de $100 deve ser 2600.");
    }

    @Test
    void testApostarTodasAsFichasDeUmValor() {
        // Apostar todas as fichas de $100 (10 fichas)
        for (int i = 0; i < 10; i++) {
            assertTrue(banca.apostar(100), "A aposta de $100 deve ser bem-sucedida.");
        }

        // Verifica se não há mais fichas de $100
        assertFalse(banca.apostar(100), "Não deve ser possível apostar mais fichas de $100, pois todas foram apostadas.");
        // Verifica se o total de fichas foi reduzido corretamente
        assertEquals(1400, banca.calcularTotalFichas(), "O total de fichas após apostar todas as fichas de $100 deve ser 1400.");
    }
}
