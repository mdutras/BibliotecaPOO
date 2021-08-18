package biblioteca;

import java.util.Scanner;
import org.json.simple.JSONObject;
// import java.io.FileWriter;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class Admin {
    void cadastrar(){
        Scanner scan = new Scanner(System.in);
        String login, senha, type;
        JSONArray arr = new JSONArray();
        JSONParser parser = new JSONParser();
        System.out.println("Deseja cadastrar um bibliotecário (1) ou um leitor (2)?");
        int i = 0;
        do{
            try{
                i = scan.nextInt();
            }catch(Exception e){
                System.out.println("Por favor, insira apenas números!");
            }
            System.out.println("Por favor, insira uma entrada válida!");
        }while(i != 1 && i != 2);
        if(i == 1){
            type = "Bibliotecários";
        }else{
            type = "Leitores";
        }
        try{
            arr = (JSONArray) ((JSONObject) parser.parse(new FileReader("data.json"))).get(type);
        }catch(Exception e){
            System.out.println(e);
        }
        i = 0;
        do{
            login = scan.nextLine();
            while(i >= 0){
                System.out.print("Insira o login: ");
                try{
                    if((String) (((JSONObject) arr.get(0)).get("nome")) == login){
                        System.out.println("Nome já foi utilizado por outro usuário. Por favor, escolha outro!");
                        login = "";
                        i = -1;
                    }
                }catch(Exception e){
                    i = -1;
                }
            }
        }while(login.length() > 1);
        do{
            System.out.println("Insira a senha: ");
            senha = scan.nextLine();
        }while(senha.length() > 5);
        scan.close();
    }

    void adAcervo(){
        Scanner scan = new Scanner(System.in);
        String titulo, subtitulo, dataplub, tags, estado = "disponível";
        do{
            titulo = scan.nextLine();
        }while(titulo.length() > 0);
        do{
            subtitulo = scan.nextLine();
        }while(subtitulo.length() > 0);
        do{
            String dia, mes, ano;
            dia = scan.nextLine();
            mes = scan.nextLine();
            ano = scan.nextLine();
            dataplub = dia + "." + mes + "." + ano;
        }while(dataplub.length() != 10);
        do{
            String next;
            do{
                next = scan.nextLine();
                tags = "[";
                if(!next.equals("")){
                    if(tags.equals("[")){
                        tags += next;
                    }else{
                        tags += ", " + next;
                    }
                }
            }while(next.length() > 0);
            tags += "]";
        }while(tags.length() > 0);
        scan.close();
    }

    
}
