package Model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

class BaralhoTest {

    @Test
    void testBaralhoCreation() {
        Baralho baralho = new Baralho();
        List<Carta> cards = Baralho.cards;  
        assertEquals(8 * 52, cards.size(), "O baralho deveria ter 8 baralhos (8 * 52 cartas)");
    }

    @Test
    void testEachCardAppearsEightTimes() throws NoSuchFieldException, IllegalAccessException {
        // Acessa o campo 'cards' da classe Baralho
        Field cardsField = Baralho.class.getDeclaredField("cards");
        cardsField.setAccessible(true); // Permite acessar o campo privado
        
        // Obtém o valor de 'cards'
        List<Carta> cards = (List<Carta>) cardsField.get(null); // null, pois cards é static
        
        // Mapa para contar as ocorrências de cada carta
        Map<String, Integer> cardCountMap = new HashMap<>();
        
        // Conta quantas vezes cada carta aparece
        for (Carta carta : cards) {
            String key = carta.getNome() + " de " + carta.getNaipe();
            cardCountMap.put(key, cardCountMap.getOrDefault(key, 0) + 1);
        }

        // Verifica se cada carta aparece exatamente 8 vezes
        for (Map.Entry<String, Integer> entry : cardCountMap.entrySet()) {
            assertEquals(8, entry.getValue(), "A carta " + entry.getKey() + " deveria aparecer exatamente 8 vezes");
        }
    }
    
    @Test
    void testSortBaralho() {
        Baralho baralho = new Baralho();
        
        // Copia o estado atual do baralho para comparação
        List<Carta> originalOrder = new ArrayList<>(Baralho.cards);

        // Chama o método sortBaralho para embaralhar as cartas
        baralho.sortBaralho();

        // Verifica se o baralho foi realmente embaralhado
        assertNotEquals(originalOrder, Baralho.cards, "O baralho deveria estar em uma ordem diferente após ser embaralhado");
        
        // Adicionalmente, pode-se verificar se o tamanho do baralho não mudou
        assertEquals(8 * 52, Baralho.cards.size(), "O número de cartas deve permanecer o mesmo após embaralhar");
    }

    @Test
    void testGiveCard() {
        Baralho baralho = new Baralho();
        int initialSize = Baralho.cards.size();

        // Pega a primeira carta do baralho
        Carta firstCard = Baralho.cards.get(0);
        
        // Verifica se giveCard retorna a mesma carta e a remove do baralho
        assertEquals(firstCard, Baralho.giveCard(), "A carta retornada deveria ser a primeira do baralho");
        
        // Verifica se o tamanho do baralho foi reduzido em 1
        assertEquals(initialSize - 1, Baralho.cards.size(), "O número de cartas no baralho deveria diminuir em 1 após giveCard");
    }

    @Test
    void testGiveCardEmptyDeck() {
        Baralho baralho = new Baralho();
        
        // Remove todas as cartas do baralho
        while (!Baralho.cards.isEmpty()) {
            Baralho.giveCard();
        }

        // Verifica se giveCard retorna null quando o baralho está vazio
        assertNull(Baralho.giveCard(), "Quando o baralho estiver vazio, giveCard deveria retornar null");
    }

    @Test
    void testCheckCardsRecreateDeck() {
        Baralho baralho = new Baralho();

        // Remove 90% das cartas
        for (int i = 0; i < (int)(8 * 52 * 0.9); i++) {
            Baralho.giveCard();
        }
        
        // Chama checkCards
        int currentSize = Baralho.cards.size();
        baralho.checkCards();
        
        // Verifica se o baralho foi recriado (se o tamanho foi restaurado)
        assertTrue(Baralho.cards.size() > currentSize, "O baralho deveria ter sido recriado após remover mais de 90% das cartas");
    }

    @Test
    void testCheckCardsNoRecreation() {
        Baralho baralho = new Baralho();
        
        // Remove menos de 90% das cartas
        for (int i = 0; i < (int)(42); i++) {
            Baralho.giveCard();
        }
        
        // Verifica o tamanho antes de chamar checkCards
        int currentSize = Baralho.cards.size();
        
        // Chama checkCards
        baralho.checkCards();
        
        // Verifica se o baralho não foi recriado (tamanho continua igual)
        assertEquals(8 * 52, Baralho.cards.size(), "O baralho não deveria ser recriado quando menos de 90% das cartas foram usadas");
    }
}
