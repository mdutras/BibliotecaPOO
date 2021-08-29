package src;

public class Leitor{
    String nome;
    String login;
    String[] a = {"a", "b"};

    Leitor(String login, String nome){
        this.login = login;
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }
}