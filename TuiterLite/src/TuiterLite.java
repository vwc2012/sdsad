import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 *
 *
 */
public class TuiterLite<T> {
    public Usuario usuario;
    public Map<Usuario, String> UsuáriosCadastrados = new HashMap<Usuario, String>();
    public ArrayList<Hashtag> hashtags = new ArrayList();
    public Tuite tuite;

    public static final int TAMANHO_MAXIMO_TUITES = 120;
    private Hashtag HashtagMaisComum;

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o email informado já estiver em uso, não faz nada e retorna null.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {
        usuario = new Usuario(nome, email);
        for(int i=0; i < UsuáriosCadastrados.size(); i++){
            if (UsuáriosCadastrados.containsKey(email)){
                return null;
            }
        }
        UsuáriosCadastrados.put(usuario, email);
        return usuario;

    }

    /**
     * Tuíta algo, retornando o objeto Tuíte criado.
     * Se o tamanho do texto exceder o limite pré-definido, não faz nada e retorna null.
     * Se o usuário não estiver cadastrado, não faz nada e retorna null.
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        tuite = new Tuite(usuario, texto);
            if(!UsuáriosCadastrados.containsKey(usuario)) {
                return null;
            }

        if (texto.length() > TAMANHO_MAXIMO_TUITES){
            return null;
        }
        usuario.addTuitesFeitos();
        setandoHashtag(tuite);
        return tuite;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        return this.HashtagMaisComum.getNome();
    }

    private void setandoHashtag(Tuite tuite){
        int maior = 0;
        for(Object s : tuite.getArrayHash()){
            Hashtag h = (Hashtag) s;
            if(!hashtags.contains(h))
                hashtags.add(h);
            else{
                hashtags.get(hashtags.indexOf(h)).addQuantidade();
            }
        }
        for(Hashtag h: hashtags){
            if(h.getQuant() > maior){
                maior = h.getQuant();
                this.HashtagMaisComum = h;
            }
        }
    }

    // Mainzinho bobo, apenas ilustrando String.split(regexp), e o String.startsWith()

//    public static void main(String[] args) {
//        String frase = "Testando algo,sdf com #hashtags no meio #teste vamos ver!fdfgf";
//        String[] palavras = frase.split("[\\s,!]");
//        for (String palavra : palavras) {
//            if (palavra.startsWith("#")) {
//                System.out.println(palavra);
//            }
//        }
//    }





}
