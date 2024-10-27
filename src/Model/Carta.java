package Model;

public class Carta { // Originalmente era private, mas foi mudado para public para realizar o pacote View
	
    private String nome;
    private String naipe;
    private int valor;
    
    public Carta(String nome, String naipe, int valor) {
        this.nome = nome;
        this.naipe = naipe;
        this.valor = valor;
    }
	
    public String getNome() {
        return nome;
    }
    
    public String getNaipe() {
        return naipe;
    }
    
    public int getValor() {
        return valor;
    }
    
    // MARK: Set Methods.
    public void setValor(int valor) {
        this.valor = valor;
    }
}
