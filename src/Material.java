package src;

public class Material extends Registro {
    String autor, dataCadastro, estado;

    public Material(String nome, String autor, String categoria, String data, String estado, String cod){
        this.path = "src/data/Membros.json";
        this.nome = nome;
        this.autor = autor;
        this.categoria = categoria;
        this.dataCadastro = data;
        this.estado = estado;
        if(cod.equals("")){
            this.generateCode();
        }else{
            this.cod = cod;
        }
    }

    public String getAutor() {
        return autor;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public String getEstado() {
        return estado;
    }
}
