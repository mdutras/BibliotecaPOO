package src;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Registro {
    String nome, categoria, cod, path;

    void generateCode(){
        JSONParser parser = new JSONParser();
        String c = this.categoria.toUpperCase().substring(0, 3);
        try{
            JSONObject file = (JSONObject) parser.parse(new FileReader(this.path));
            do{
                int random = (int) ((Math.random() * (9999 - 1000)) + 1000);
                c += String.valueOf(random);
            }while(file.get(c) != null);
        }catch(Exception e){
            System.out.println(e);
        }
        this.cod = c;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getCod() {
        return cod;
    }
}
