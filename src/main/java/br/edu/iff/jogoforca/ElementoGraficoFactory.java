package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public interface ElementoGraficoFactory extends BonecoFactory, LetraFactory {
  public Boneco getBoneco();

  public Letra getLetra(char codigo);

  public Letra getLetraEncoberta();
}
