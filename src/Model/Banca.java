package Model;

import java.util.ArrayList;
import java.util.List;

class Banca {
    public static List<Ficha> fichas;

    public Banca() {
        this.fichas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            fichas.add(new Ficha(100));
        }

        for (int i = 0; i < 10; i++) {
            fichas.add(new Ficha(50));
        }

        for (int i = 0; i < 20; i++) {
            fichas.add(new Ficha(20));
        }

        for (int i = 0; i < 50; i++) {
            fichas.add(new Ficha(10));
        }
    }

    public int calcularTotalFichas() {
        int total = 0;
        for (Ficha ficha : fichas) {
            total += ficha.getValor();
        }
        return total;
    }
    
    public boolean apostar(int valor) {
        int[] valoresFichas = {100, 50, 20, 10, 5, 1};
        List<Ficha> fichasParaRemover = new ArrayList<>();
        int valorRestante = valor;

        for (int valorFicha : valoresFichas) {
            if (valorRestante <= 0) {
                break;
            }
            
            
            for (Ficha ficha : new ArrayList<>(fichas)) {
                if (ficha.getValor() == valorFicha && valorRestante >= valorFicha) {
                    fichasParaRemover.add(ficha); 
                    valorRestante -= valorFicha;  
                    fichas.remove(ficha);         
                    
                    if (valorRestante <= 0) {
                        break;
                    }
                }
            }
        }

        
        if (valorRestante > 0) {
            fichas.addAll(fichasParaRemover); 
            return false; // Aposta invalida
        }

        return true; // Aposta valida
    }

    
    public void ganhouAposta(int valor) {
        int valorGanho = valor * 2;
        
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
