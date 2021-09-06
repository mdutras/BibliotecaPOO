package src;

import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JsonManager {

    public static Membro getMembro(String login){
        JSONObject file = new JSONObject(), cadastro, livroAlugado;
        Membro membro;
		JSONParser parser = new JSONParser();
        try{
            file = (JSONObject) parser.parse(new FileReader("src/data/Membros.json"));
            
        }catch(Exception e){
            System.out.println(e);
        }
        cadastro = (JSONObject) file.get(login);
        membro = new Membro(
            login, 
            (String) cadastro.get("nome"),
            (String) cadastro.get("categoria")
        );
        livroAlugado = (JSONObject) cadastro.get("materialAlugado");
        if(!livroAlugado.isEmpty()){
            membro.estado = "Alugou livro";
            membro.setAlugado(
                (String) livroAlugado.get("código"),
                (String) livroAlugado.get("data do aluguel")
            );
        }
        return membro;
    }

    public static void cadastroMembro(String login, String nome, String senha){
        JSONObject membro = new JSONObject();
        JSONParser parser = new JSONParser();
        FileWriter writeFile = null;

        membro.put("nome", nome);
        membro.put("senha", senha);
        membro.put("materialAlugado", new JSONObject());
        try{
            JSONObject file = (JSONObject) parser.parse(new FileReader("src/data/Membros.json"));
            file.put(login, membro);
            writeFile = new FileWriter("src/data/Membros.json");
            writeFile.write(file.toJSONString());
            writeFile.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ObservableList<Membro> getAllMembros(){
        JSONObject file = new JSONObject();
        ArrayList<Membro> membros = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            file = (JSONObject) parser.parse(new FileReader("src/data/Membros.json"));
            Set<String> names = file.keySet();
            for(String s: names){
                membros.add(getMembro(s));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return FXCollections.observableArrayList(membros);
    }

    public static Evento getEvento(String code){
        JSONObject file = new JSONObject(), cadastro;
		JSONParser parser = new JSONParser();
        try{
            file = (JSONObject) parser.parse(new FileReader("src/data/Eventos.json"));
            
        }catch(Exception e){
            System.out.println(e);
        }
        cadastro = (JSONObject) file.get(code);
        return new Evento(
            (String) cadastro.get("nome"),
            (String) cadastro.get("requisitante"),
            (String) cadastro.get("categoria"),
            (String) cadastro.get("dataEvento"),
            code
        );
    }

    public static Requisicao getReq(String code){
        JSONObject file = new JSONObject(), cadastro;
		JSONParser parser = new JSONParser();
        try{
            file = (JSONObject) parser.parse(new FileReader("src/data/requisicaoEvento.json"));
        }catch(Exception e){
            System.out.println(e);
        }
        cadastro = (JSONObject) file.get(code);
        return new Requisicao(
            (String) cadastro.get("nome"),
            (String) cadastro.get("requisitante"),
            (String) cadastro.get("categoria"),
            (String) cadastro.get("dataEvento"),
            code
        );
    }

    public static ObservableList<Evento> getAllEventos(){
        JSONObject file = new JSONObject();
        ArrayList<Evento> eventos = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            file = (JSONObject) parser.parse(new FileReader("src/data/Eventos.json"));
            Set<String> names = file.keySet();
            for(String s: names){
                System.out.println(s);
                Evento m = getEvento(s);
                eventos.add(m);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return FXCollections.observableArrayList(eventos);
    }

    public static ObservableList<Requisicao> getAllReq(){
        JSONObject file = new JSONObject();
        ArrayList<Requisicao> reqs = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            
                file = (JSONObject) parser.parse(new FileReader("src/data/requisicaoEvento.json"));
            Set<String> names = file.keySet();
            for(String s: names){
                reqs.add(getReq(s));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return FXCollections.observableArrayList(reqs);
    }

    public static void cadastroEventoOrReq(Evento ev, Boolean isReq){
        JSONObject evento = new JSONObject(), file;
        JSONParser parser = new JSONParser();
        FileWriter writeFile = null;

        evento.put("nome", ev.nome);
        evento.put("requisitante", ev.requisitante);
        evento.put("categoria", ev.categoria);
        evento.put("data", ev.dataEvento);

        try{
            if(isReq){
                file = (JSONObject) parser.parse(new FileReader("src/data/requisicaoEvento.json"));
            }else{
                file = (JSONObject) parser.parse(new FileReader("src/data/Eventos.json"));
            }
            file.put(ev.cod, evento);
            if(isReq){
                writeFile = new FileWriter("src/data/requisicaoEvento.json");
            }else{
                writeFile = new FileWriter("src/data/Eventos.json");
            }
            writeFile.write(file.toJSONString());
            writeFile.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Material getMaterial(String code){
        JSONObject file = new JSONObject(), cadastro;
		JSONParser parser = new JSONParser();
        try{
            file = (JSONObject) parser.parse(new FileReader("src/data/Acervo.json"));
            
        }catch(Exception e){
            System.out.println(e);
        }
        cadastro = (JSONObject) file.get(code);
        return new Material(
            (String) cadastro.get("nome"),
            (String) cadastro.get("autores"),
            (String) cadastro.get("categoria"),
            (String) cadastro.get("dataCadastro"),
            (String) cadastro.get("estado"),
            code
        );
    }

    public static void cadastroMaterial(Material mat){
        JSONObject material = new JSONObject(), file;
        JSONParser parser = new JSONParser();
        FileWriter writeFile = null;

        material.put("nome", mat.nome);
        material.put("autor", mat.autor);
        material.put("categoria", mat.categoria);
        material.put("data do cadastro", mat.dataCadastro);
        material.put("Estado", mat.estado);

        try{
            file = (JSONObject) parser.parse(new FileReader("src/data/Acervo.json"));
            file.put(mat.cod, material);
            writeFile = new FileWriter("src/data/Acervo.json");
            writeFile.write(file.toJSONString());
            writeFile.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ObservableList<Material> getAcervo(){
        JSONObject file = new JSONObject();
        ArrayList<Material> acervo = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            file = (JSONObject) parser.parse(new FileReader("src/data/Acervo.json"));
            Set<String> names = file.keySet();
            for(String s: names){
                acervo.add(getMaterial(s));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return FXCollections.observableArrayList(acervo);
    }
}
