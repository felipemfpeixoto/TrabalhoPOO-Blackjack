package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParticipanteTest {

    class ParticipanteTeste extends Participante {
        @Override
        public int decidirAcao() {
            return 0;
        }
    }

    private ParticipanteTeste participante;

    @BeforeEach
    void setUp() {
        participante = new ParticipanteTeste();
    }

    @Test
    void testReceberCarta() {
        Carta carta = new Carta("Ás", "Copas", 11); 
        participante.receberCarta(carta);
        assertEquals(11, participante.calculaPontos(), "Os pontos devem ser 11 após receber um Ás.");
        assertEquals(1, participante.mao.size(), "A mão deve conter 1 carta após receber uma carta.");
    }

    @Test
    void testRestartPoints() {
        Carta carta = new Carta("Rei", "Espadas", 10);
        participante.receberCarta(carta);
        participante.restartPoints();
        assertEquals(0, participante.pontos, "Os pontos devem ser reiniciados para 0.");
    }

    @Test
    void testCalculaPontos() {
        participante.receberCarta(new Carta("Rei", "Copas", 10));
        participante.receberCarta(new Carta("Valete", "Espadas", 10));
        assertEquals(20, participante.calculaPontos(), "Os pontos totais devem ser 20.");
    }

    @Test
    void testEstourou() {
        participante.receberCarta(new Carta("Rei", "Copas", 10));
        participante.receberCarta(new Carta("Dama", "Espadas", 10));
        participante.receberCarta(new Carta("Dois", "Ouros", 2));
        assertTrue(participante.estourou(), "O jogador deve estourar com 22 pontos.");
    }
    
    @Test
    void testNaoEstourou() {
        participante.receberCarta(new Carta("Rei", "Copas", 10));
        participante.receberCarta(new Carta("Dama", "Espadas", 10));
        assertFalse(participante.estourou(), "O jogador não deve estourar com 20 pontos.");
    }
    
    @Test
    void testCheckBlackJack() {
        participante.receberCarta(new Carta("Ás", "Copas", 11));
        participante.receberCarta(new Carta("Rei", "Espadas", 10));
        assertTrue(participante.checkBlackJack(), "O jogador deve ter BlackJack com 21 pontos em 2 cartas.");
    }
}
