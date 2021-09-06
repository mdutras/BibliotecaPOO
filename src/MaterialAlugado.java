package src;

public class MaterialAlugado{
    String code;
    String dataAluguel;
    MaterialAlugado(String code, String data){
        this.code = code;
        this.dataAluguel = data;
    }

    public String getCode() {
        return code;
    }

    public String getDataAluguel() {
        return dataAluguel;
    }
}
