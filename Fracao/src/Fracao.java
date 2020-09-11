public class Fracao {

    /**
     * Construtor.
     * O sinal da fração é passado no parâmetro específico.
     *
     * @param numerador O numerador (inteiro não-negativo)
     * @param denominador O denominador (inteiro positivo)
     * @param positiva se true, a fração será positiva; caso contrário, será negativa
     */

    int numerador, denominador, sinal;
    boolean positiva;
    public boolean nulo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fracao fracao = (Fracao) o;

        if(nulo && fracao.nulo) {
            return true;
        }

        return this.getFracaoGeratriz().getNumerador() == fracao.getFracaoGeratriz().getNumerador() &&
                this.getFracaoGeratriz().getDenominador() == fracao.getFracaoGeratriz().getDenominador() &&
                this.sinal== fracao.sinal;
    }

    private double fracao;

    public Fracao(int numerador, int denominador, boolean positiva) {
        this.numerador = numerador;
        this.denominador = denominador;
        this.positiva = positiva;


        if (positiva) {
            sinal = 1;
        } else{
            sinal = -1;
        }

        this.fracao = (sinal*((double)this.numerador)) / (double)this.denominador;

        if(numerador == 0){
            nulo = true;
        }else{
            nulo = false;
        }

        if(nulo){
            this.positiva = false;
        }
    }

    /**
     * @return um double com o valor numérico desta fração
     */
    public double getValorNumerico() {return this.fracao;}

    /**
     * Retorna uma fração que é equivalente a esta fração (this),
     * e que é irredutível (numerador e denominador primos entre si).
     * Em outras palavras, retorna a fração geratriz desta fração.
     *
     * @return uma fração irredutível equivalente a esta;
     *         no caso desta fração JÁ SER ela própria irredutível, retorna this
     */


    public int mdc (int dividendo, int divisor){
        if ((dividendo % divisor == 0 )){
            return divisor;
        }else{
            return mdc(divisor,(dividendo % divisor));
        }
    }

    public Fracao getFracaoGeratriz() {
        int mdc = mdc(this.numerador, this.denominador);
        if (mdc == 1){
            return this;
        }
        int Numerador = this.numerador/ mdc;
        int Denominador = this.denominador / mdc;
        return new Fracao(Numerador, Denominador, this.isPositiva());
    }

    public int getNumerador() {
        return this.numerador;
    }

    public int getDenominador() {
        return this.denominador;
    }

    public boolean isPositiva() {
        if (nulo){
            return false;
        }
        return this.positiva;
    }
}
