package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartaTest {

    private Carta carta;

    @BeforeEach
    void setUp() {
        carta = new Carta("Ás", "Copas", 1);
    }

    @Test
    void testGetNome() {
        assertEquals("Ás", carta.getNome(), "O nome da carta deve ser Ás");
    }

    @Test
    void testGetNaipe() {
        assertEquals("Copas", carta.getNaipe(), "O naipe da carta deve ser Copas");
    }

    @Test
    void testGetValor() {
        assertEquals(1, carta.getValor(), "O valor da carta deve ser 1");
    }

    @Test
    void testSetValor() {
        carta.setValor(10); // Alterando o valor da carta
        assertEquals(10, carta.getValor(), "O valor da carta deve ser alterado para 10");
    }

    @Test
    void testCartaConstrutor() {
        Carta novaCarta = new Carta("Rei", "Espadas", 13);
        assertEquals("Rei", novaCarta.getNome(), "O nome da carta deve ser Rei");
        assertEquals("Espadas", novaCarta.getNaipe(), "O naipe da carta deve ser Espadas");
        assertEquals(13, novaCarta.getValor(), "O valor da carta deve ser 13");
    }
}
