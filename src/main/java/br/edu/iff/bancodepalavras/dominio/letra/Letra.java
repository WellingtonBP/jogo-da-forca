package br.edu.iff.bancodepalavras.dominio.letra;

import java.util.Objects;

public abstract class Letra {
    private char codigo;

    protected Letra(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return this.codigo;
    }

    public void exibir(Object contexto) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean equals(Object o) {
        return Objects.equals(o, this.codigo);
    }

    @Override
    public int hashCode() {
        return Character.hashCode(this.codigo);
    }

    @Override
    public final String toString() {
        return "Letra{\n"+
                " \"codigo\": "+this.codigo+
                "\n}";
    }
}
