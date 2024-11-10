package Controller;

import Model.*;
import Observer.*;
import java.util.List;


public class GameController implements Observer {

    private static GameController instanciaUnica;
    private ModelAPI modelAPI;

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
    public List<String> getMaoJogador() {
    	return modelAPI.getCartasJogador();
    }
    
    public List<String> getMaoDealer() {
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

    public void onStand() {
        // Ação para encerrar o turno do jogador
        System.out.println("Jogador escolheu Stand (não quer mais cartas).");
        notificarView("Stand - Turno Encerrado");
//        modelAPI.finalizarTurnoJogador();
    }

    public void onSurrender() {
        // Ação para o jogador se render, iniciando uma nova rodada
        System.out.println("Jogador escolheu Surrender (desistiu da rodada).");
        notificarView("Surrender - Rodada Reiniciada");
//        modelAPI.jogadorDesistir();
    }
}

