package Model;

import java.util.ArrayList;
import java.util.List;

class Banca {
    public static List<Ficha> fichas;

    public Banca() {
        this.fichas = new ArrayList<>();

        // Adicionando 10 fichas de $100
        for (int i = 0; i < 10; i++) {
            fichas.add(new Ficha(100));
        }

        // Adicionando 10 fichas de $50
        for (int i = 0; i < 10; i++) {
            fichas.add(new Ficha(50));
        }

        // Adicionando 20 fichas de $20
        for (int i = 0; i < 20; i++) {
            fichas.add(new Ficha(20));
        }

        // Adicionando 50 fichas de $10
        for (int i = 0; i < 50; i++) {
            fichas.add(new Ficha(10));
        }
    }

    // Método para calcular o total das fichas na banca
    public int calcularTotalFichas() {
        int total = 0;
        for (Ficha ficha : fichas) {
            total += ficha.getValor();
        }
        return total;
    }
    
    public boolean apostar(int valor) {
        for (Ficha ficha : fichas) {
            if (ficha.getValor() == valor) {
                fichas.remove(ficha);
                return true; // Aposta feita com sucesso
            }
        }
        return false; // Não havia ficha com o valor solicitado
    }
    
    public void ganhouAposta(int valor) {
        int valorGanho = valor * 2;
        
        // Tentando adicionar o valor ganho com as fichas disponíveis
        while (valorGanho >= 100) {
            fichas.add(new Ficha(100));
            valorGanho -= 100;
        }
        while (valorGanho >= 50) {
            fichas.add(new Ficha(50));
            valorGanho -= 50;
        }
        while (valorGanho >= 20) {
            fichas.add(new Ficha(20));
            valorGanho -= 20;
        }
        while (valorGanho >= 10) {
            fichas.add(new Ficha(10));
            valorGanho -= 10;
        }
    }
}
