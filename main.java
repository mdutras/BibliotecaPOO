import org.json.simple.JSONObject;
// import java.io.FileWriter;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

class library{
    // @SuppressWarnings("unchecked")
    public static void main(String Args[]){
        JSONObject obj1 = new JSONObject();
        JSONArray obj2 = new JSONArray();
        JSONParser parser = new JSONParser();
        try{
            obj1 = (JSONObject) parser.parse(new FileReader("data.json"));
            obj2 = (JSONArray) obj1.get("A");
            System.out.println(obj1);
            if(((JSONObject) obj2.get(1)).get("batata") == null){
                System.out.println(123);
            }
            if("aaaa".equals("aaaa")){
                System.out.println(123);
            }
            System.out.println(obj2.get(10));
        }catch(Exception e){
            System.out.println(e);
        }
    }
}