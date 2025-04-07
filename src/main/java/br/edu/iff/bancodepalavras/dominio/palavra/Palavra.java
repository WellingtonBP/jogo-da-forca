package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Palavra extends ObjetoDominioImpl {
    private Tema tema;
    private static LetraFactory letraFactory;
    private Letra[] letras;
    private String palavra;

    private Palavra(long id, String palavra, Tema tema) {
        super(id);
        this.tema = tema;
        this.letras = new Letra[palavra.length() - 1];
        var charArr = palavra.toCharArray();
        for(int i = 0; i < charArr.length; i++) {
            this.letras[i] = Palavra.letraFactory.getLetra(charArr[i]);
        }
        this.palavra = palavra;
    }

    public static Palavra criar(long id, String palavra, Tema tema) {
        if(Palavra.letraFactory == null) {
            return null;
        };
        return new Palavra(id, palavra, tema);
    }

    public static Palavra reconstituir(long id, String palavra, Tema tema) {
        if(Palavra.letraFactory == null) {
            return null;
        };
        return new Palavra(id, palavra, tema);
    }

    public static LetraFactory getLetraFactory() {
        return Palavra.letraFactory;
    }

    public static void setLetraFactory(LetraFactory letraFactory) {
        Palavra.letraFactory = letraFactory;
    }

    public Letra[] getLetras() {
        return this.letras;
    }

    public Letra getLetra(int posicao) {
        return this.letras[posicao];
    }

    public void exibir(Object ignore) {
        for (Letra l : this.letras) {
            l.exibir(null);
        }
    }

    public void exibir(Object ignore, boolean[] posicoes) {
        for (int i = 0; i < this.letras.length; i ++) {
            if(posicoes[i]) {
                this.letras[i].exibir(null);
            }
        }
    }

    public int[] tentar(char codigo) {
        List<Integer> pos = new ArrayList<>();
        Letra t = Palavra.letraFactory.getLetra(codigo);
        for(int i = 0; i < this.letras.length; i++) {
            if(this.letras[0].equals(t)) {
                pos.add(i);
            }
        }
        return pos.stream().mapToInt(Integer::intValue).toArray();
    }

    public Tema getTema() {
        return this.tema;
    }

    public boolean comparar(String palavra) {
        return this.palavra.equals(palavra);
    }

    public int getTamanho() {
        return this.letras.length;
    }

    @Override
    public String toString() {
        return "Palavra{" +
                "tema=" + tema +
                ", letras=" + Arrays.toString(letras) +
                ", palavra='" + palavra + '\'' +
                '}';
    }
}
