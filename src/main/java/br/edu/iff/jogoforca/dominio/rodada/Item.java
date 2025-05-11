package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Item extends ObjetoDominioImpl {
    private Palavra palavra;
    private boolean[] posicoesDescobertas;
    private String palavraArriscada = null;

    private Item(long id, Palavra palavra) {
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = new boolean[palavra.getTamanho()];
    }

    private Item(long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = posicoesDescobertas;
        this.palavraArriscada = palavraArriscada;
    }

    Item criar(long id, Palavra palavra) {
        boolean[] posicoesDescobertas = new boolean[palavra.getTamanho()];
        return new Item(id, palavra, posicoesDescobertas, null);
    }

    public Item reconstruir(long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

    public Palavra getPalavra() {
        return this.palavra;
    }

    public Letra[] getLetrasDescobertas() {
        Letra[] letrasDescobertas = new Letra[this.palavra.getTamanho()];
        for (int i = 0; i < this.palavra.getTamanho(); i++) {
            if (this.posicoesDescobertas[i]) {
                letrasDescobertas[i] = this.palavra.getLetra(i);
            }
        }
        return letrasDescobertas;
    }

    public Letra[] getLetrasEncobertas() {
        Letra[] letrasEncobertas = new Letra[this.palavra.getTamanho()];
        for (int i = 0; i < this.palavra.getTamanho(); i++) {
            if (!this.posicoesDescobertas[i]) {
                letrasEncobertas[i] = this.palavra.getLetra(i);
            }
        }
        return letrasEncobertas;
    }

}
