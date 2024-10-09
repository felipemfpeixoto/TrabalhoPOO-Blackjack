package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Baralho {
    private static List<Carta> cards;
    
    public Baralho() {
        cards = new ArrayList<>();
        criarBaralho();
        sortBaralho();
    }
    
    private void criarBaralho() {
        String[] naipes = {"Espadas", "Ouros", "Paus", "Copas"};
        String[] valores = {"Ás", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei"};
        
        for (int i = 0; i < 8; i ++) { // método sendo aplicado para a criação de 8 baralhos
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
    }
    
    public void sortBaralho() {
        Collections.shuffle(cards);
    }
    
    public static Carta giveCard() {
        if (cards.isEmpty()) {
            System.out.println("O baralho acabou");
            return null;
        }
        return cards.remove(0);
    }
    
    // Método criado com o objetivo de checar se 10% das cartas já foram utilizadas
    // Esse método deve ser chamado ao final de cada jogada
    public void checkCards() {
    	if (cards.size() / (8 * 52) <= 0.9) {
    		criarBaralho();
    		sortBaralho();
    	}
    }
}
