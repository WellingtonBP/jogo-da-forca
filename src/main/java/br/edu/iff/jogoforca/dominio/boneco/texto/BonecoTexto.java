package br.edu.iff.jogoforca.dominio.boneco.texto;

public class BonecoTexto implements Boneco {
    private static BonecoTexto soleInstance;
    private long id;

    // Construtor privado impede instâncias externas
    private BonecoTexto() {
        this.id = 1; // Exemplo de id, pode ser parametrizado
    }

    // Retorna a única instância de bonecoTexto
    public static BonecoTexto getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoTexto();
        }
        return soleInstance;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void exibir(Object contexto, int partes) {
        // Implementação simplificada para exibir uma representação textual do boneco.
        if (partes == 0) {
            System.out.println("sem boneco.");
        } else {
            System.out.println("o boneco está com: " + partes);
        }
    }
}
// Exemplo de uso
// BonecoTexto boneco = BonecoTexto.getSoleInstance();
// boneco.exibir(null, 3); // Exibe: "o boneco está com: 3"
// System.out.println("ID do boneco: " + boneco.getId()); // Exibe: "ID do
// boneco: 1"
// BonecoTexto boneco2 = BonecoTexto.getSoleInstance();
// System.out.println(boneco == boneco2); // Exibe: "true", pois é a mesma
// instância

// BonecoTexto boneco3 = new BonecoTexto(); // Erro: o construtor é privado e
// não pode ser acessado fora da classe
// boneco3.exibir(null, 3); // Erro: o construtor é privado e não pode ser
// acessado fora da classe
// System.out.println("ID do boneco: " + boneco3.getId()); // Erro: o construtor
// é privado e não pode ser acessado fora da classe
// boneco3.exibir(null, 3); // Erro: o construtor é privado e não pode ser
// acessado fora da classe
// System.out.println(boneco == boneco3); // Erro: o construtor é privado e não
// pode ser acessado fora da classe
