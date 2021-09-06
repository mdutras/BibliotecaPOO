package src;

public class Requisicao extends Evento{

    public Requisicao(String nome, String requisitante, String categoria, String data, String cod){
        super(nome, requisitante, categoria, data, cod);
        if(!cod.substring(0,3).equals("REQ")){
            this.cod = "REQ" + this.cod.substring(3,7);
        }
    }

    void negado(){

    }

    void aceito(){
        

        // JsonManager.cadastroEvento
    }
}
