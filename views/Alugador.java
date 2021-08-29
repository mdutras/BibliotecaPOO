package views;

public class Alugador {
    String leitor;
    String livro;
    String estado;

    Alugador(String leitor, String livro, String estado){
        this.leitor = leitor;
        this.livro = livro;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public String getLeitor() {
        return leitor;
    }

    public String getLivro() {
        return livro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setLeitor(String leitor) {
        this.leitor = leitor;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }
}
