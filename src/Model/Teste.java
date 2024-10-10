package Model;

import Model.Baralho;
import Model.Carta;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Baralho baralho = new Baralho();
        Jogador jogador = new Jogador();
        Dealer dealer = new Dealer();
        
        // ****************************************** Bloco responsável pela interação com o usuário ******************************************
        Scanner scanner = new Scanner(System.in);
        int jogar = 1;
        while (jogar == 1) {
            int acontecido = iniciaJogada(dealer, jogador, baralho);

            // Se 'acontecido' for igual a 1, pule para "Jogar novamente?"
            if (acontecido == 1) {
                System.out.println("Jogar novamente? (Sim - 1 // Não - 0)");
                jogar = scanner.nextInt();

                if (jogar == 1) {
                    jogador.restartPoints();
                    dealer.restartPoints();
                }
                continue;  // Volta para o início do loop
            }

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
        // ****************************************** Bloco responsável pela interação com o usuário ******************************************
    }
    
    private static int iniciaJogada(Dealer dealer, Jogador jogador, Baralho baralho) { // Retorno: 1 - BlackJack aconteceu
    	System.out.println("Iniciando jogada\n");

    	dealInicial(dealer, jogador, baralho);
    	boolean dealerBlackjack = dealer.checkBlackJack();
    	boolean jogadorBlackjack = jogador.checkBlackJack();
    	
    	// Checar se algum dos dois conseguiu um blackjack
    	if (dealerBlackjack && !jogadorBlackjack) {
    		// Coleta a aposta e termina a rodada
    		System.out.println("***************************** O dealer conseguiu um BlackJack ***********************************");
    		return 1;
    	} else if (!dealerBlackjack && jogadorBlackjack) {
    		System.out.println("***************************** Winner Winner Chicken Dinner ***********************************");
    		System.out.println("***************************** Você conseguiu um BlackJack ***********************************");
    		return 1;
    	} else if (dealerBlackjack && jogadorBlackjack) {
    		System.out.println("***************************** Ambos os jogadores conseguiram BlackJacks ***********************************");
    		return 1;
    	}
    	
	    int retorno = jogador.decidirAcao();
	    while (retorno != -1) {
	    	if (!jogador.estourou()) {
		    	retorno = jogador.decidirAcao();
	    	} else {
	    		System.out.println("Limite de pontuação ultrapassado, VOCÊ PERDEU!");
	    		return 0;
	    	}
	    }
	    System.out.println("Turno no jogador terminado");
		return 0;
    }
    
    private static void dealInicial(Dealer dealer, Jogador jogador, Baralho baralho) { // Nessa função falta verifiar se o jogador ou o dealer conseguiram 21 pontos no deal inicial (Blackjack)
    	
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
