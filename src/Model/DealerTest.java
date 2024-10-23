package Model;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DealerTest {

    private Dealer dealer;
    private Baralho baralho;

    @Before
    public void setUp() {
        dealer = new Dealer();
        baralho = new Baralho();  
        baralho.sortBaralho();   
    }

    @Test
    public void testDealerJogaAte17Pontos() {
        
        dealer.receberCarta(new Carta("Cinco", "Copas", 5));  
        dealer.receberCarta(new Carta("Seis", "Paus", 6));  
        dealer.jogar();  

        
        int pontos = dealer.calculaPontos();
        assertTrue(pontos >= 17);
    }

    @Test
    public void testDealerParaQuandoAtinge17Pontos() {
        
        dealer.receberCarta(new Carta("Dez", "Copas", 10));  
        dealer.receberCarta(new Carta("Sete", "Paus", 7));          
        
        dealer.jogar();
        assertEquals(17, dealer.calculaPontos());
    }

    @Test
    public void testDealerEstouraSePassarDe21Pontos() {
        
        dealer.receberCarta(new Carta("Dez", "Copas", 10)); 
        dealer.receberCarta(new Carta("Seis", "Espadas", 6));  
        dealer.receberCarta(new Carta("Seis", "Ouros", 6));  
        
        
        assertTrue(dealer.estourou());
        assertTrue(dealer.calculaPontos() > 21);
    }

}