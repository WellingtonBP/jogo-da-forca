package br.edu.iff.bancodepalavras.dominio.letra;

import java.util.HashMap;
import java.util.Map;

public abstract class LetraFactoryImpl implements LetraFactory {
  private final Map<Integer, Letra> pool;
  private final Letra encoberta;

  protected LetraFactoryImpl() {
    pool = new HashMap<>();
    encoberta = this.getLetra('*');
  }

  @Override
  public final Letra getLetra(char codigo) {
    var existente = pool.values().stream().filter(letra -> letra.getCodigo() == codigo).findFirst();

    if (existente.isPresent()) {
      return existente.get();
    }

    var novaLetra = this.criarLetra(codigo);
    pool.put(novaLetra.hashCode(), novaLetra);
    return novaLetra;
  }

  @Override
  public final Letra getLetraEncoberta() {
    return encoberta;
  }

  protected abstract Letra criarLetra(char codigo);
}
