package Model;

/**
 * A classe Carta representa uma carta de um baralho com um nome, um naipe, e um valor.
 * Esta classe é usada para manipular as cartas no jogo de Blackjack.
 */
class Carta {
	
    /**
     * O nome da carta (ex: Ás, 2, 3, ..., Valete, Dama, Rei).
     */
    private String nome;
    
    /**
     * O naipe da carta (ex: Espadas, Ouros, Paus, Copas).
     */
    private String naipe;
    
    /**
     * O valor numérico da carta, considerando as regras do jogo de Blackjack.
     */
    private int valor;
    
    /**
     * Construtor da classe Carta.
     * 
     * @param nome O nome da carta (ex: Ás, 2, 3, Valete, etc).
     * @param naipe O naipe da carta (ex: Espadas, Ouros, Paus, Copas).
     * @param valor O valor da carta, sendo 1-10 para cartas numéricas, e 11 para Ás.
     */
    public Carta(String nome, String naipe, int valor) {
        this.nome = nome;
        this.naipe = naipe;
        this.valor = valor;
    }
	
    // MARK: Métodos get.
    
    /**
     * Retorna o nome da carta.
     * 
     * @return O nome da carta (ex: Ás, 2, 3, Valete, etc).
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Retorna o naipe da carta.
     * 
     * @return O naipe da carta (ex: Espadas, Ouros, Paus, Copas).
     */
    public String getNaipe() {
        return naipe;
    }
    
    /**
     * Retorna o valor numérico da carta.
     * 
     * @return O valor numérico da carta, de acordo com as regras do jogo de Blackjack.
     */
    public int getValor() {
        return valor;
    }
    
    // MARK: Métodos set.
    
    /**
     * Define o valor da carta. Útil para casos especiais, como mudar o valor do Ás.
     * 
     * @param valor O novo valor da carta.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
}
