package Model;

import Model.Baralho;
import Model.Carta;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Baralho baralho = new Baralho();
        Jogador jogador = new Jogador();
        Dealer dealer = new Dealer();
        int jogar = 1;
        
        Scanner scanner = new Scanner(System.in);
        
        while (jogar == 1) {
            iniciaJogada(dealer, jogador, baralho);

            System.out.println("Iniciando turno do dealer");
            dealer.decidirAcao();

            System.out.println("Jogar novamente? (Sim - 1 // Não - 0)");
            jogar = scanner.nextInt();

            if (jogar == 1) {
                jogador.restartPoints();
                dealer.restartPoints();
            }
        }
        
        scanner.close();
    }
    
    private static void iniciaJogada(Dealer dealer, Jogador jogador, Baralho baralho) {
    	System.out.println("Iniciando jogada\n");

    	dealInicial(dealer, jogador, baralho);
    	
	    int retorno = jogador.decidirAcao();
	    while (retorno != -1) {
	    	if (!jogador.estourou()) {
		    	retorno = jogador.decidirAcao();
	    	} else {
	    		System.out.println("Limite de pontuação ultrapassado, VOCÊ PERDEU!");
	    		return;
	    	}
	    }
	    System.out.println("Turno no jogador terminado");
    }
    
    private static void dealInicial(Dealer dealer, Jogador jogador, Baralho baralho) {
    	
    	recebemCartas(dealer, jogador, baralho);
    	recebemCartas(dealer, jogador, baralho);
    	
    	System.out.println("Mão do dealer: " + dealer.mao.get(0).getNome() + " " + dealer.mao.get(1).getNome());
    	System.out.println("Mão do jogador: " + jogador.mao.get(0).getNome() + " " + jogador.mao.get(1).getNome());
    	return;
    }
    
    private static void recebemCartas(Dealer dealer, Jogador jogador, Baralho baralho) {
    	System.out.printf("Dealer");
    	dealer.receberCarta(Baralho.giveCard());
    	System.out.printf("Jogador");
    	jogador.receberCarta(Baralho.giveCard());
    }
}
