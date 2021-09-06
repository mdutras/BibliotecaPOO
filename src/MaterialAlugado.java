package src;

public class MaterialAlugado{
    public String code;
    public String dataAluguel;
    public MaterialAlugado(String code, String data){
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
