package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FichaTest {

    private Ficha ficha;

    @BeforeEach
    void setUp() {
        ficha = new Ficha(100);
    }

    @Test
    void testGetValor() {
        assertEquals(100, ficha.getValor(), "O valor inicial da ficha deve ser 100");
    }

    @Test
    void testSetValor() {
        ficha.setValor(200);
        assertEquals(200, ficha.getValor(), "O valor da ficha deve ser alterado para 200");
    }

    @Test
    void testAdicionar() {
        Ficha outraFicha = new Ficha(50);
        ficha.adicionar(outraFicha);
        assertEquals(150, ficha.getValor(), "O valor da ficha deve ser 150 após adicionar 50");
    }

    @Test
    void testSubtrair() {
        Ficha outraFicha = new Ficha(30);
        ficha.subtrair(outraFicha);
        assertEquals(70, ficha.getValor(), "O valor da ficha deve ser 70 após subtrair 30");
    }

    @Test
    void testToString() {
        String esperado = "Ficha de valor: $100";
        assertEquals(esperado, ficha.toString(), "A representação em string da ficha deve ser 'Ficha de valor: $100'");
    }

    @Test
    void testAdicionarNegativo() {
        Ficha fichaNegativa = new Ficha(-50);
        ficha.adicionar(fichaNegativa);
        assertEquals(50, ficha.getValor(), "O valor da ficha deve ser 50 após adicionar -50");
    }

    @Test
    void testSubtrairNegativo() {
        Ficha fichaNegativa = new Ficha(-20);
        ficha.subtrair(fichaNegativa);
        assertEquals(120, ficha.getValor(), "O valor da ficha deve ser 120 após subtrair -20");
    }
}
