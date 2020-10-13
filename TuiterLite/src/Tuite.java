import java.util.ArrayList;

public class Tuite<T> {

    private final Usuario autor;
    private final String texto;
    public ArrayList<String> hashtags = new ArrayList();
    public T anexo;


    public Tuite(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
        String[] palavras = this.texto.split("[\\s,!]");
        for (String palavra : palavras) {
            if (palavra.startsWith("#")) {
                this.hashtags.add(palavra);
            }
        }
    }

    public void anexarAlgo(T anexo) {
        this.anexo = anexo;
    }

    public Object getAnexo() {
        return this.anexo;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getTexto() {
        return this.texto;
    }

    public ArrayList<String> getHashtags() {
        return this.hashtags;
    }

    public ArrayList<Hashtag> getArrayHash(){
        ArrayList<Hashtag> arrayHash = new ArrayList<>();
        for(String s : hashtags){
            arrayHash.add(new Hashtag(s));
        }
        return arrayHash;
    }
}
