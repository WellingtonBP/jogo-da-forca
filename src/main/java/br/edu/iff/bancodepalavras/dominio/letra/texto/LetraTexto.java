package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;

public class LetraTexto extends Letra {
    public LetraTexto(char codigo) {
        super(codigo);
    }

    @Override
    public void exibir(Object ignore) {
        System.out.print(this.getCodigo());
    }
}
