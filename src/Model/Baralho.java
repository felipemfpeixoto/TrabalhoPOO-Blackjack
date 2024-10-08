package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A classe Baralho representa um baralho de cartas padrão com 52 cartas.
 * Inclui métodos para criar o baralho, embaralhá-lo e distribuir cartas.
 * 
 * <p>Nota: Como a lista de cartas é declarada como estática, todos os baralhos
 * criados compartilharão o mesmo conjunto de cartas.</p>
 */
class Baralho {
    /**
     * Lista de cartas do baralho.
     * Este atributo é estático, então será compartilhado entre todas as instâncias da classe Baralho.
     */
    private static List<Carta> cards;
    
    /**
     * Construtor da classe Baralho.
     * Inicializa a lista de cartas e cria o baralho sorteado completo com 52 cartas.
     */
    public Baralho() {
        cards = new ArrayList<>();
        criarBaralho();
        sortBaralho();
    }
    
    /**
     * Método privado para criar o baralho com 52 cartas, considerando os 4 naipes 
     * (Espadas, Ouros, Paus, Copas) e os 13 valores (Ás, 2-10, Valete, Dama, Rei).
     * 
     * <p>Cada carta tem um valor numérico associado:
     * - Cartas de 2 a 10 têm seus respectivos valores numéricos.
     * - Valetes, Damas e Reis valem 10.
     * - Ases podem valer 11 (por padrão, neste código).</p>
     */
    private void criarBaralho() {
        String[] naipes = {"Espadas", "Ouros", "Paus", "Copas"};
        String[] valores = {"Ás", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei"};
        
        for (String naipe : naipes) {
            for (String nome : valores) {
                int value;
                
                // Define o valor da carta: Valetes, Damas e Reis valem 10;
                // Ases valem 11 por padrão.
                switch (nome) {
                    case "Valete":
                    case "Dama":
                    case "Rei":
                        value = 10;
                        break;
                    case "Ás":
                        value = 11;
                        break;
                    default:
                        value = Integer.parseInt(nome);
                }
                
                cards.add(new Carta(nome, naipe, value));
            }
        }
    }
    
    /**
     * Embaralha o baralho, randomizando a ordem das cartas usando {@code Collections.shuffle()}.
     */
    public void sortBaralho() {
        Collections.shuffle(cards);
    }
    
    /**
     * Retira a primeira carta do baralho e a retorna.
     * Se o baralho estiver vazio, imprime uma mensagem e retorna {@code null}.
     * 
     * @return A primeira carta do baralho, ou {@code null} se o baralho estiver vazio.
     */
    public static Carta giveCard() {
        if (cards.isEmpty()) {
            System.out.println("O baralho acabou");
            return null;
        }
        return cards.remove(0);
    }
}
