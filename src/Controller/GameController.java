package Controller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import Model.*;
import Observer.*;
import java.util.List;

public class GameController implements Observer {

    private static GameController instanciaUnica;
    private static ModelAPI modelAPI;
    
    private int dealerStatus = -1; // Começa com -1 pois o dealer ainda não jogou

    // Construtor privado para o padrão Singleton
    private GameController() {
        modelAPI = ModelAPI.getInstancia();
        modelAPI.addObserver(this);  // Registra-se como observador da ModelAPI
    }

    // Método para obter a única instância do GameController (Singleton)
    public static GameController getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new GameController();
        }
        return instanciaUnica;
    }
    
    // Funções para atualizar as cartas em jogo para a View
    public static List<String> getMaoJogador() {
    	return modelAPI.getCartasJogador();
    }
    
    public static List<String> getMaoDealer() {
    	return modelAPI.getCartasDealer();
    }
    
 // Funções para atualizar os pontos em jogo para a View
    public int getPontosJogador() {
    	return modelAPI.getPontosJogador();
    }
    
    public int getPontosDealer() {
    	return modelAPI.getPontosDealer();
    }
    

    // Implementação do método update do Observer
    @Override
    public void update(String evento) {
        // Notifica a View sobre o evento ocorrido na ModelAPI
        // Este método será chamado automaticamente quando a ModelAPI chamar notifyObservers()
        notificarView(evento);
    }

    // Método que simula a notificação para a View
    private void notificarView(String evento) {
        // Este método deve ser implementado na View futuramente para exibir atualizações
        System.out.println("Notificação para a View: " + evento);
    }
    
    public void reinicia() {
    	modelAPI.reinicia();
    }

    // Métodos de controle para os botões da interface
    public void onExit() {
        System.out.println("O jogador saiu do jogo.");
        notificarView("Exit - Jogo Encerrado");
        System.exit(0);  // Encerra o jogo
    }

    public void onDouble() {
        // Ação para dobrar a aposta do jogador
        System.out.println("Jogador dobrou a aposta.");
        notificarView("Double - Aposta Dobrada");
//        modelAPI.dobrarAposta();
    }

    public void onSplit() {
        // Ação para realizar o split das cartas do jogador
        System.out.println("Jogador escolheu Split.");
        notificarView("Split - Cartas Divididas");
//        modelAPI.dividirCartas();
    }

    public void onDeal() {
        // Ação para iniciar uma nova rodada, distribuindo as cartas
        System.out.println("Iniciando uma nova rodada com Deal.");
        notificarView("Deal - Rodada Iniciada");
        modelAPI.iniciarNovoJogo();
    }

    public boolean onHit() {
        // Ação para pedir mais uma carta
        System.out.println("Jogador pediu uma carta (Hit).");
        notificarView("Hit - Carta Pedida");
        return modelAPI.pedirCartaJogador();
    }

    public int onStand() {
        // Ação para encerrar o turno do jogador
        System.out.println("Jogador escolheu Stand (não quer mais cartas).");
        notificarView("Stand - Turno Encerrado");
        
        modelAPI.pedirCartaDealer();
    	return modelAPI.dealerTurno();
    }

    public void onSurrender() {
        // Ação para o jogador se render, iniciando uma nova rodada
        System.out.println("Jogador escolheu Surrender (desistiu da rodada).");
        notificarView("Surrender - Rodada Reiniciada");
//        modelAPI.jogadorDesistir();
    }
    
    public int getSaldo() {
        return modelAPI.getSaldo();
    }
    
    public int aposta(int valor) {
    	return modelAPI.aposta(valor);
    }
    
    public void ganhouAposta(int valor) {
    	modelAPI.ganhouAposta(valor);
    }
    
    public int getUltimaAposta() {
    	return modelAPI.getAposta();
    }
    
    public void salvarEmTxt() {
    	String nomeArq = "dados.txt";
    	String conteudo = "Saldo: " + getSaldo() + "\n"
    			+ "UltimaAposta: " + getUltimaAposta() + "\n"
    			+ "CartasJ: " + getMaoJogador() + "\n"
    			+ "CartasD: " + getMaoDealer() + "\n"
    			;
    	
        try (FileWriter writer = new FileWriter(nomeArq)) {
            writer.write(conteudo);
            System.out.println("Dados salvos no arquivo: " + nomeArq);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //+ "Aposta: " + 
    
    public static int leTxteSetGame() {
    	// Parte da leitura do TXT
    	
    	String nomeArq = "dados.txt";
    	String saldo = null;
        String cartasJogador = null;
        String cartasDealer = null;
        String ultimaAposta = null;

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArq))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Saldo:")) {
                    saldo = linha.split(":")[1].trim();
                } else if (linha.startsWith("UltimaAposta:")) {
                	ultimaAposta = linha.split(":")[1].trim();
                } else if (linha.startsWith("CartasJ:")) {
                    cartasJogador = linha.split(":")[1].trim();
                } else if (linha.startsWith("CartasD:")) {
                    cartasDealer = linha.split(":")[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        System.out.println("Dados lidos do arquivo:");
        System.out.println("Saldo: " + saldo);
        System.out.println("UltimaAposta: " + ultimaAposta);
        System.out.println("Cartas do Jogador: " + cartasJogador);
        System.out.println("Cartas do Dealer: " + cartasDealer);
        
        if (cartasJogador != null && cartasDealer != null &&
    	    !cartasJogador.equals("[]") && !cartasDealer.equals("[]")) {
        	
        	if (!(Integer.parseInt(saldo) == 2400) && !(Integer.parseInt(ultimaAposta) == 0)) {
        		modelAPI.daCartasJeD(cartasJogador, cartasDealer);
        	}
        	else {
        		modelAPI.daCartasJeD(cartasJogador, cartasDealer);
        		return -2;
        	}
    	    
    	} else {
    	    return -1;
    	}
        
        // Realiza a aposta
        int aposta = Integer.parseInt(ultimaAposta);
        
        if (aposta >= 1){
        	modelAPI.aposta(aposta);
        }
        
        return 0;
    }
   
}

