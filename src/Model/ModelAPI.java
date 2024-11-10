package Model;
import Observer.*;

import java.util.ArrayList;
import java.util.List;

public class ModelAPI {
    private static ModelAPI instanciaUnica;

    private Baralho baralho;
    private Banca banca;
    private Jogador jogador;
    private Dealer dealer;
    private List<Observer> observers;

    // Construtor privado para o Singleton
    private ModelAPI() {
        baralho = new Baralho();
        banca = new Banca();
        jogador = new Jogador(banca);
        dealer = new Dealer();
        observers = new ArrayList<>();
    }

    // Método para obter a única instância da classe (Singleton)
    public static ModelAPI getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new ModelAPI();
        }
        return instanciaUnica;
    }

    // Adiciona um observador
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove um observador
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notifica todos os observadores sobre mudanças
    private void notifyObservers(String evento) {
        for (Observer observer : observers) {
            observer.update(evento);
        }
    }

    // Inicia um novo jogo
    public void iniciarNovoJogo() {
        dealer.restartPoints();
        jogador.restartPoints();
        iniciaJogada();
        notifyObservers("Novo jogo iniciado");
    }

    // Lógica de jogada inicial
    private void iniciaJogada() {
        dealInicial();
        boolean dealerBlackjack = dealer.checkBlackJack();
        boolean jogadorBlackjack = jogador.checkBlackJack();

        // Notifica sobre BlackJack, se ocorrer
        if (dealerBlackjack || jogadorBlackjack) {
            if (dealerBlackjack && jogadorBlackjack) {
                notifyObservers("Empate: Ambos com BlackJack");
            } else if (dealerBlackjack) {
                notifyObservers("Dealer ganhou com BlackJack");
            } else {
                notifyObservers("Jogador ganhou com BlackJack");
            }
            return;
        }

        // Permite ao jogador decidir a ação
//        jogadorTurno();
        return;
    }
    
    public void reinicia() {
    	jogador.reinicia();
    	dealer.reinicia();
    }

    private void jogadorTurno() {
        int retorno = jogador.decidirAcao();
        while (retorno != -1) {
            if (!jogador.estourou()) {
                retorno = jogador.decidirAcao();
            } else {
                notifyObservers("Jogador estourou, perdeu a rodada");
                return;
            }
        }
        notifyObservers("Turno do jogador terminado");
        dealerTurno();
    }

    private void dealerTurno() {
        dealer.decidirAcao();
        notifyObservers("Turno do dealer terminado");
    }

    private void dealInicial() {
        recebemCartas();
        recebemCartas();
        notifyObservers("Cartas iniciais distribuídas");
    }

    private void recebemCartas() {
        dealer.receberCarta(Baralho.giveCard());
        jogador.receberCarta(Baralho.giveCard());
    }

    public boolean pedirCartaJogador() {
    	jogador.receberCarta(Baralho.giveCard());
    	return jogador.estourou();
    }
    
    // Métodos de acesso para cartas do dealer e do jogador (para visualização futura)
    public List<String> getCartasDealer() {
        return dealer.getMao();
    }

    public List<String> getCartasJogador() {
        return jogador.getMao();
    }
    
    public int getPontosJogador() {
    	return jogador.pontos;
    }
    
    public int getPontosDealer() {
    	return dealer.pontos;
    }
}
