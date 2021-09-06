package src;

public class Evento extends Registro{
    public String requisitante, dataEvento;

    public Evento(String nome, String requisitante, String categoria, String data, String cod){
        this.path = "src/data/Eventos.json";
        this.nome = nome;
        this.requisitante = requisitante;
        this.categoria = categoria;
        this.dataEvento = data;
        if(cod.equals("")){
            this.generateCode();
        }else{
            this.cod = cod;
        }
    }

    public String getDataEvento() {
        return dataEvento;
    }
    
    public String getRequisitante() {
        return requisitante;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }
}

