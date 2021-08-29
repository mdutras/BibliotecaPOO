package views;

public class Evento {
    String nome;
    String categoria;
    String data;

    Evento(String nome, String categoria, String data){
        this.nome = nome;
        this.categoria = categoria;
        this.data = data;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getData() {
        return data;
    }

    public String getNome() {
        return nome;
    }
}
