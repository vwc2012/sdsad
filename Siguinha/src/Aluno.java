public class Aluno {

    // atributos (regra geral: todos private)

    private String nome;
    private final long dre;
    private final int anoDeMatricula;
    private int creditosAcumulados;  //       Somatorio_d [creditos(d)]

    private float cra;               // cra = Somatorio_d [media(d) * creditos(d) /

    private int quantDisciplinasCursadas;
    private ItemDeHistorico[] disciplinasCursadas;

    // construtor
    public Aluno(long dre, int anoDeMatricula, String nome) {
        this.dre = dre;
        this.anoDeMatricula = anoDeMatricula;
        this.nome = nome;
        this.disciplinasCursadas = new ItemDeHistorico[16];
    }

    // métodos

    String retornarHistoricoAsString() {
       String resultado = "";

       for (int i = 0; i < this.quantDisciplinasCursadas; i++) {
           ItemDeHistorico item = this.disciplinasCursadas[i];
           // MAB001 - média 6.5 - 4 créditos - 2020.1
           resultado = resultado + item.getDisciplina().getCodigo() +
                   " - média " + item.getMediaFinal() +
                   " - " + item.getDisciplina().getCreditos() + " créditos" +
                   " - " + item.getAno() + "." + item.getSemestre();
           if (i < this.quantDisciplinasCursadas - 1) {
               resultado = resultado + "\n";
           }
       }
       return resultado;
    }

    public ItemDeHistorico[] getDisciplinasCursadas() {
        return disciplinasCursadas;
    }

    public int getQuantDisciplinasCursadas() {
        return quantDisciplinasCursadas;
    }

    public String getNome() {
        return nome == null ? "Sem Nome" : nome;

        // equivalentemente...
//        if (nome == null) {
//            return "Sem Nome";
//        }
//        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 30) {
            // ToDo: lançar uma exceção
            return;
        }

        this.nome = nome;
    }  // setter

    public int getAnoDeMatricula() {
        return anoDeMatricula;
    }

    public int getCreditosAcumulados() {
        return creditosAcumulados;
    }

    public long getDre() {
        return dre;
    }

    public float getCra() {   // accessor method (getters and setters)
        return cra;
    }    // getter

    /* NÃO QUEREMOS UM SETTER COMO ESTE ABAIXO!!!!!
       PORQUE NÃO FAZ SENTIDO PERMITIR ESCREVER DIRETAMENTE NO CRA DO ALUNO,
       UMA VEZ QUE SE TRATA DE UM CAMPO QUE DEVE SER CALCULADO EM FUNÇÃO DE OUTROS */

//    public void setCra(float cra) {
//        if (cra < 0 || cra > 10) {
//            // ToDo: lançar uma exceção
//            return;
//        }
//        this.cra = cra;
//    }

    public void registrarConclusaoDisciplina(Disciplina disciplina,
                                             float mediaFinal,
                                             int anoConclusao,  // ToDo default para ano corrente
                                             int semestreConclusao) {

        ItemDeHistorico novoItem = new ItemDeHistorico(
                disciplina, this, anoConclusao, semestreConclusao, mediaFinal);

        this.disciplinasCursadas[this.quantDisciplinasCursadas] = novoItem;
        this.quantDisciplinasCursadas++;  // incrementa a quant já cursada

        // recupero o numerador corrente (antes da nova disciplina)
        float numeradorCorrenteCra = this.cra * this.creditosAcumulados;

        int creditos = disciplina.getCreditos();
        this.creditosAcumulados += creditos;

        // atualizar o CRA do aluno
        float novaParcela = creditos * mediaFinal;
        this.cra = (numeradorCorrenteCra + novaParcela) / this.creditosAcumulados;
    }

    @Override
    public String toString() {
        return String.format("%s (DRE: %d)", this.nome, this.dre);
    }
}
