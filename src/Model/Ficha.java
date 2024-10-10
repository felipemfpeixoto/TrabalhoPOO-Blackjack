package Model;

class Ficha {
    private int valor;  

    
    public Ficha(int valor) {
        this.valor = valor;
    }

    
    public int getValor() {
        return valor;
    }

    
    public void setValor(int valor) {
        this.valor = valor;
    }

    
    public void adicionar(Ficha outraFicha) {
        this.valor += outraFicha.getValor();
    }

    
    public void subtrair(Ficha outraFicha) {
        this.valor -= outraFicha.getValor();
    }

    
    @Override
    public String toString() {
        return "CHAMA" ;
    }
}