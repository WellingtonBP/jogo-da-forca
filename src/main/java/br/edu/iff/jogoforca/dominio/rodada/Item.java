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

    static Item criar(long id, Palavra palavra) {
        return new Item(id, palavra);
    }

    public static Item reconstruir(long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

    public Palavra getPalavra() {
        return this.palavra;
    }

    public Letra[] getLetrasDescobertas() {
        Letra[] letrasDescobertas = new Letra[this.posicoesDescobertas.length];
        int j = 0;
        for (int i = 0; i < this.palavra.getTamanho(); i++) {
            if (this.posicoesDescobertas[i]) {
                letrasDescobertas[j] = this.palavra.getLetra(i);
                j++;
            }
        }
        return letrasDescobertas;
    }

    public Letra[] getLetrasEncobertas() {
        Letra[] letrasEncobertas = new Letra[this.qtdeLetrasEncobertas()];
        int j = 0;
        for (int i = 0; i < this.palavra.getTamanho(); i++) {
            if (!this.posicoesDescobertas[i]) {
                letrasEncobertas[j] = this.palavra.getLetra(i);
                j++;
            }
        }
        return letrasEncobertas;
    }

    public int qtdeLetrasEncobertas() {
        int descobertas = 0;
        for (int i = 0; i < this.posicoesDescobertas.length; i++) {
            if (this.posicoesDescobertas[i]) {
                descobertas++;
            }
        }
        return this.palavra.getTamanho() - descobertas;
    }

    public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta) {
        return valorPorLetraEncoberta * this.qtdeLetrasEncobertas();
    }

    public boolean descobriu() {
        return this.qtdeLetrasEncobertas() == 0;
    }

    public void exibir(Object contexto) {
        this.palavra.exibir(contexto);
    }

    boolean tentar(char codigo) {
        var pos = this.palavra.tentar(codigo);
        boolean achou = pos.length != 0;
        for (int i : pos) {
            this.posicoesDescobertas[i] = true;
        }
        return achou;
    }

    void arriscar(String palavra) {
        this.palavraArriscada = palavra;
        if (!this.palavra.comparar(palavra)) {
            for (int i = 0; i < palavra.toCharArray().length; i++) {
                this.tentar(palavra.charAt(i));
            }
        }
    }

    public String getPalavraArriscada() {
        return this.palavraArriscada;
    }

    public boolean arriscou() {
        return this.palavraArriscada != null;
    }

    public boolean acertou() {
        return this.palavra.comparar(this.palavraArriscada);
    }
}
