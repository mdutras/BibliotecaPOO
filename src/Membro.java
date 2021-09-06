package src;

public class Membro {
    String nome, login, categoria, estado;
    MaterialAlugado alugado;

    Membro(String login, String nome, String categoria){
        this.login = login;
        this.nome = nome;
        this.categoria = categoria;
        this.estado = "Normal";
        this.alugado = null;
    }

    public void setAlugado(String code, String data) {
        alugado = new MaterialAlugado(code, data);
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEstado() {
        return estado;
    }

    public String getLogin() {
        return login;
    }

    public MaterialAlugado getAlugado() {
        return alugado;
    }
}
