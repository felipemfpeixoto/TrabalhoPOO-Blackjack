package Model;

import Model.Baralho;
import Model.Carta;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Baralho baralho = new Baralho();
        Jogador jogador = new Jogador();
        Dealer dealer = new Dealer();
        iniciaJogada(dealer, jogador, baralho);
    }
    
    private static void iniciaJogada(Dealer dealer, Jogador jogador, Baralho baralho) {
    	System.out.println("Iniciando jogada");
    	System.out.println("Primeira carta para o dealer");
    	dealer.receberCarta(Baralho.giveCard());
    	System.out.println("Primeira carta para o jogador");
    	jogador.receberCarta(Baralho.giveCard());
    	
    	System.out.println("Segunda carta para o dealer");
    	dealer.receberCarta(Baralho.giveCard());
    	System.out.println("Segunda carta para o jogador");
    	dealer.receberCarta(Baralho.giveCard());
    }
}
