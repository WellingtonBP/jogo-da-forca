package br.edu.iff.bancodepalavras.dominio.palavra.emmemoria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;

public class MemoriaPalavraRepository implements PalavraRepository {
  private static MemoriaPalavraRepository soleInstance;
  private final Map<Long, Palavra> map;
  private long sequence = 0;

  private MemoriaPalavraRepository() {
    this.map = new HashMap<>();
  }

  public static MemoriaPalavraRepository getSoleInstance() {
    if (MemoriaPalavraRepository.soleInstance == null) {
      MemoriaPalavraRepository.soleInstance = new MemoriaPalavraRepository();
    }

    return MemoriaPalavraRepository.soleInstance;
  }

  @Override
  public long getProximoId() {
    return sequence + 1;
  }

  @Override
  public Palavra getPorId(long id) {
    return map.get(id);
  }

  @Override
  public Palavra[] getPorTema(Tema tema) {
    List<Palavra> palavras = new ArrayList<>();

    for (var palavra : map.values()) {
      if (palavra.getTema().equals(tema)) {
        palavras.add(palavra);
      }
    }

    return palavras.toArray(new Palavra[palavras.size()]);
  }

  @Override
  public Palavra[] getTodas() {
    return map.values().toArray(new Palavra[map.values().size()]);
  }

  @Override
  public Palavra getPalavra(String palavra) {
    return map.values().stream().filter(p -> p.comparar(palavra)).findFirst().orElse(null);
  }

  @Override
  public void inserir(Palavra palavra) throws RepositoryException {
    var existente = this.getPalavra(
        Arrays.asList(palavra.getLetras()).stream().map(letra -> String.valueOf(letra.getCodigo()))
            .collect(Collectors.joining("")));
    if (existente != null)
      throw new RepositoryException();
    map.put(this.getProximoId(), palavra);
    this.sequence++;
  }

  @Override
  public void atualizar(Palavra palavra) throws RepositoryException {
    var existente = map.get(palavra.getId());
    if (existente == null) {
      throw new RepositoryException();
    }
    map.put(palavra.getId(), palavra);
  }

  @Override
  public void remover(Palavra palavra) throws RepositoryException {
    var existente = map.get(palavra.getId());
    if (existente == null) {
      throw new RepositoryException();
    }
    map.remove(palavra.getId());
  }

}
