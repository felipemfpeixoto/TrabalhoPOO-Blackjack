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
    
    
    public void setSaldo(int saldo) {
    	banca.setSaldo(saldo);
    }
    
    public int getSaldo() {
        return banca.calcularTotalFichas();
    }
    
    public int getAposta() {
    	return jogador.getApostaAtual();
    }
    
    
    public int aposta(int valor) {
    	if (banca.apostar(valor) == true) {
    		jogador.setApostaAtual(valor);
    		return valor;
    	}
    	jogador.setApostaAtual(0);
    	return 0;
    }
    
    public void ganhouAposta(int valor) {
    	banca.ganhouAposta(valor);
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

    public int dealerTurno() { // Função retorna 0 se o dealer ainda vai jogar mais, 1 se o dealer parou, e 2 se o dealer estourou
        notifyObservers("Turno do dealer terminado");
        
        if (dealer.pontos < 17) {
        	return 0;
        } else if (dealer.pontos >= 17 && dealer.pontos <= 21) {
        	return 1;
        } else {
        	return 2;
        }
    }

    private void dealInicial() {
        recebemCartas();
        recebemCartas();
        notifyObservers("Cartas iniciais distribuídas");
    }
    
    
    public void daCartasJeD(String cartasJogador, String cartasDealer) {
    	 System.out.println(cartasJogador);
  
        // Remover colchetes e separar as cartas
        String[] partes = cartasJogador.replace("[", "").replace("]", "").split(", ");

        for (String parte : partes) {
            String nome = parte.substring(0, parte.length() - 1); 
            String naipe = parte.substring(parte.length() - 1);

            // Determinar o valor da carta
            int valor;
            switch (nome) {
                case "j":
                case "q":
                case "k":
                case "t":
                    valor = 10;
                    break;
                case "a":
                    valor = 11;
                    break;
                default:
                    valor = Integer.parseInt(nome);
            }
            
            jogador.receberCarta(new Carta(nome, naipe, valor));
        }
        
        
        partes = cartasDealer.replace("[", "").replace("]", "").split(", ");
        
        for (String parte : partes) {
            String nome = parte.substring(0, parte.length() - 1); 
            String naipe = parte.substring(parte.length() - 1);

            // Determinar o valor da carta
            int valor;
            switch (nome) {
                case "j":
                case "q":
                case "k":
                case "t":
                    valor = 10;
                    break;
                case "a":
                    valor = 11;
                    break;
                default:
                    valor = Integer.parseInt(nome);
            }
            
            dealer.receberCarta(new Carta(nome, naipe, valor));
        }
 
    }

    private void recebemCartas() {
        dealer.receberCarta(Baralho.giveCard());
        jogador.receberCarta(Baralho.giveCard());
    }

    public boolean pedirCartaJogador() {
    	jogador.receberCarta(Baralho.giveCard());
    	return jogador.estourou();
    }
    
    public void pedirCartaDealer() {
    	dealer.receberCarta(Baralho.giveCard());
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
