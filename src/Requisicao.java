package src;

public class Requisicao extends Evento{

    public Requisicao(String nome, String requisitante, String categoria, String data, String cod){
        super(nome, requisitante, categoria, data, cod);
        if(cod.equals("")){
            this.cod = "REQ" + this.cod.substring(3,7);
        }else{
            this.cod = cod;
        }
    }

    public void rejeitado(){
        JsonManager.removerRequesicao(this);
    }

    public void aceito(){
        JsonManager.cadastroEvento(new Evento(
            this.nome, 
            this.requisitante, 
            this.categoria, 
            this.dataEvento, 
            "")
        );
        JsonManager.removerRequesicao(this);
    }
}
