package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public class BonecoImagemFactory implements BonecoFactory {
  private static BonecoImagemFactory soleInstance;

  private BonecoImagemFactory() {
  }

  public static BonecoImagemFactory getSoleInstance() {
    if (BonecoImagemFactory.soleInstance == null) {
      BonecoImagemFactory.soleInstance = new BonecoImagemFactory();
    }
    return BonecoImagemFactory.soleInstance;
  }

  @Override
  public Boneco getBoneco() {
    return BonecoImagem.getSoleInstance();
  }

}
