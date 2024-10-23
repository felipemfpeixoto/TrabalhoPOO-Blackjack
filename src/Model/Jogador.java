package Model;

import java.util.Scanner;

public class Jogador extends Participante {
	
	private int apostaAtual;
    private Banca banca;

    public Jogador(Banca banca) {
        this.banca = banca;
        this.apostaAtual = 0;
    }

    public int getApostaAtual() {
        return apostaAtual;
    }

    public void setApostaAtual(int aposta) {
        this.apostaAtual = aposta;
    }
    
    public int decidirAcao() {
        int escolha = -1;
        while (escolha == -1) {
        	 escolha = lerValoresDoTeclado();
        }
        
        switch (escolha) {
        case 1:
        	receberCarta(Baralho.giveCard());
        	return 0;
        case 2: // Ficar com as cartas que tem
        	break;
        case 3:
        	if (dobrarAposta()) {
                receberCarta(Baralho.giveCard()); // O jogador recebe mais uma carta e a aposta é dobrada
                return 1; // Sinaliza que o jogador fez o double down
            } else {
                System.out.println("Não há fichas suficientes para dobrar a aposta.");
                return -1; // Falha na aposta
            }
        case 4:
        	// implementar método de split
        case 0:
        	break;
        }
        return -1;
    }
    
    private static int lerValoresDoTeclado() {
		Scanner scanner = new Scanner(System.in);
		int opcao;
	    System.out.println("Escolha uma das opções abaixo:");
	    System.out.println("1 - Hit (Pedir outra carta)");
	    System.out.println("2 - Stand (Ficar com as cartas que tem)");
	    System.out.println("3 - Double (Receber mais uma carta e dobrar a aposta)");
	    System.out.println("4 - Split (Dividir um par de cartas iguais)");
	    System.out.println("0 - Sair");
	    System.out.print("Digite a opção desejada: ");
	
	    opcao = scanner.nextInt();
	
	    switch (opcao) {
	        case 1:
	        case 2:
	        case 3:
	        case 4:
	        case 0:
	        	return opcao;
	        default:
	            System.out.println("Opção inválida. Tente novamente.");
	    }
	
		scanner.close();
		return -1;
    }
    
    private boolean dobrarAposta() {
        int valorParaDobrar = apostaAtual;

        if (banca.apostar(valorParaDobrar)) {
            apostaAtual += valorParaDobrar;
            System.out.println("Aposta dobrada! A nova aposta é: $" + apostaAtual);
            return true;
        } else {
            System.out.println("Não há fichas suficientes na banca para dobrar a aposta.");
            return false;
        }
    }
}


//Check Value