package src;

import java.util.Arrays;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;

public class adminScreenController {

    String state = "homeButton";
    int position = 0;

    @FXML
    private Label homeButton;

    @FXML
    private Label membrosButton;

    @FXML
    private Label eventosButton;

    @FXML
    private Label acervoButton;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private Label windowTitle;

    Label paneLabel(String title){
        Label l = new Label(title);
        l.setLayoutX(0);
        l.setLayoutX(0);
        l.setPrefWidth(444);
        l.setPrefHeight(40);
        l.getStyleClass().add("geral");
        l.setStyle("-fx-font-size: 24px; -fx-text-alignment: center; -fx-background-radius: 0px;");
        l.setAlignment(Pos.CENTER);
        return l;
    }

    public void home(){
        ArrayList<TableRow<String[]>> currentRowTbl = new ArrayList<>();
        ArrayList<TableRow<String[]>> currentRowEv = new ArrayList<>();

        Label panelTitle = this.paneLabel("Home");

        int counter = 0;

        Label welcome = new Label("Seja bem-vindo/a :)");
        welcome.setLayoutX(139);
        welcome.setLayoutY(50);
        welcome.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label tblTitle = new Label("Livros alugados");
        tblTitle.setLayoutX(152);
        tblTitle.setLayoutY(89);
        tblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label evTitle = new Label("Eventos");
        evTitle.setLayoutX(186);
        evTitle.setLayoutY(238);
        evTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TableView<String[]> tbl = new TableView<>();
        tbl.setPrefWidth(425);
        tbl.setPrefHeight(111);
        tbl.setLayoutX(11);
        tbl.setLayoutY(109);
        tbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableViewSelectionModel<String[]> selectionModel = tbl.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        for(TableColumn<String[], String> c:  Arrays.asList(
            new TableColumn<String[], String>("Leitor"), 
            new TableColumn<String[], String>("Livro"), 
            new TableColumn<String[], String>("Estado"))
        ){
            int i = counter;
            c.setCellValueFactory((s) -> {
                String[] x = s.getValue();
                return new SimpleStringProperty(x[i]);
            });
            tbl.getColumns().add(c);
            counter++;
        }

        tbl.setRowFactory(tableV -> {
            TableRow<String[]> row = new TableRow<>();
            
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent ev) {
                    if(ev.getClickCount() == 2){
                        System.out.println("Cliquei duas vezes!");
                        row.getStyleClass().add("selec");
                        if(!currentRowTbl.isEmpty()){
                            System.out.println("Dentro do if=> " + currentRowTbl.get(0));
                            currentRowTbl.get(0).getStyleClass().remove("selec");
                            currentRowTbl.remove(0);
                        }
                        System.out.println(row);
                        currentRowTbl.add(row);

                    }
                }
            });
            return row;
        });
        String[][] memberMatrix = {
            {"Genivaldo", "A volta dos que não foram", "Disponível"},
            {"Jurema", "Pooh 2", "Atrasado"},
            {"Jurema", "Pooh 2", "Atrasado"},
            {"Jurema", "Pooh 2", "Atrasado"},
            {"Jurema", "Pooh 2", "Atrasado"}
        };
        ObservableList<String[]> memberData = FXCollections.observableArrayList();
        memberData.addAll(Arrays.asList(memberMatrix));
        tbl.setItems(memberData);

        //Tabela de eventos
        TableView<String[]> evs = new TableView<>();
        evs.setPrefWidth(425);
        evs.setPrefHeight(111);
        evs.setLayoutX(11);
        evs.setLayoutY(258);
        evs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        counter = 0;
        for(TableColumn<String[], String> c:  Arrays.asList(
            new TableColumn<String[], String>("Nome"), 
            new TableColumn<String[], String>("Categoria"), 
            new TableColumn<String[], String>("Data"))
        ){
            int i = counter;
            c.setCellValueFactory((s) -> {
                String[] x = s.getValue();
                return new SimpleStringProperty(x[i]);
            });
            evs.getColumns().add(c);
            counter++;
        }

        evs.setRowFactory(tableV -> {
            TableRow<String[]> row = new TableRow<>();
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent ev) {
                    if(ev.getClickCount() == 2){
                        System.out.println("Cliquei duas vezes! ");
                        row.getStyleClass().add("selec");
                        if(!currentRowEv.isEmpty()){
                            System.out.println("Dentro do if=> " + currentRowEv.get(0));
                            currentRowEv.get(0).getStyleClass().remove("selec");
                            currentRowEv.remove(0);
                        }
                        System.out.println(row);
                        currentRowEv.add(row);

                    }
                }
            });
            return row;
        });

        String[][] eventMatrix = 
        {
            {"Seminário sobre surdez", "Seminário", "12.03.12"},
            {"Seminário sobre surdez", "Seminário", "12.03.12"},
            {"Seminário sobre surdez", "Seminário", "12.03.12"},
            {"Seminário sobre surdez", "Seminário", "12.03.12"},
            {"Seminário sobre surdez", "Seminário", "12.03.12"},
            {"Seminário sobre surdez", "Seminário", "12.03.12"}
        };
        ObservableList<String[]> eventData = FXCollections.observableArrayList();
        eventData.addAll(Arrays.asList(eventMatrix));
        evs.setItems(memberData);


        mainPanel.getChildren().addAll(panelTitle, welcome, tblTitle, evTitle, tbl, evs);
    }

    public void membros(){
        int counter = 0;
        Label panelTitle = this.paneLabel("Membros");

        TableView<Membro> membersTable = new TableView<>();
        membersTable.setPrefWidth(mainPanel.getWidth() - 20);
        membersTable.setPrefHeight(270);
        membersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        membersTable.setLayoutX(10);
        membersTable.setLayoutY(50);

        membersTable.setRowFactory(t ->{
            TableRow<Membro>row = new TableRow<>();
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent ev) {
                    if(ev.getClickCount() == 2){
                        detalhesMembros(row.getItem());
                    }
                    
                }
            });
            return row;
        });

        for(TableColumn<Membro, String> c:  Arrays.asList(
            new TableColumn<Membro, String>("Nome"), 
            new TableColumn<Membro, String>("Login"), 
            new TableColumn<Membro, String>("Estado"))
        ){
            switch(counter){
                case 0:
                    c.setCellValueFactory(new PropertyValueFactory<>("nome"));
                    break;
                case 1:
                    c.setCellValueFactory(new PropertyValueFactory<>("login"));
                    break;
                case 2:
                    c.setCellValueFactory(new PropertyValueFactory<>("estado"));
                    break;
            }
            counter++;
            membersTable.getColumns().add(c);
        }
        membersTable.setItems(JsonManager.getAllMembros());

        Button cadastro = new Button("Cadastrar membro");
        cadastro.setPrefHeight(30);
        cadastro.setPrefWidth(160);
        cadastro.setLayoutX(142);
        cadastro.setLayoutY(343);
        cadastro.getStyleClass().add("geral");
        cadastro.setStyle("-fx-font-weight: bold;");
        cadastro.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                cadastroMembros();
                System.out.println(mainPanel.getChildren().size());
            }
        });

        mainPanel.getChildren().addAll(panelTitle, membersTable, cadastro);
    }
    
    public void cadastroMembros(){
        int counter = 0;
        Rectangle recFundo = new Rectangle();
        recFundo.setWidth(444);
        recFundo.setHeight(358);
        recFundo.setX(0);
        recFundo.setY(40);
        recFundo.setStyle("-fx-fill: #404040; -fx-opacity: 0.8;");

        Rectangle recCadastro = new Rectangle();
        recCadastro.setWidth(350);
        recCadastro.setHeight(300);
        recCadastro.setLayoutX(47);
        recCadastro.setLayoutY(70);
        recCadastro.setStyle("-fx-fill: #A6A6A6;");

        Label title = new Label("Cadastro de Membro");
        title.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        title.setLayoutX(133);
        title.setLayoutY(80);

        Label x = new Label("X");
        x.setLayoutX(367);
        x.setLayoutY(80);
        x.setPrefWidth(20);
        x.setPrefHeight(20);
        x.setAlignment(Pos.CENTER);
        x.setStyle("-fx-background-color: red; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center; -fx-cursor: hand;");
        x.setCursor(Cursor.HAND);
        x.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                for(int i = 1; i <= 6; i++){
                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        GridPane cadastroGrid = new GridPane();
        cadastroGrid.setLayoutX(65);
        cadastroGrid.setLayoutY(105);
        cadastroGrid.setPrefWidth(300);
        cadastroGrid.setPrefHeight(220);
        cadastroGrid.setHgap(15);

        cadastroGrid.getColumnConstraints().addAll(
            new ColumnConstraints(300*0.4),
            new ColumnConstraints(300*0.6)
        );

        for(ColumnConstraints c: cadastroGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        for(Label l: Arrays.asList(
            new Label("Nome"), 
            new Label("Login"), 
            new Label("Categoria"), 
            new Label("Senha"),
            new Label("Confirme a senha")
            )
        ){
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: center;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            cadastroGrid.getRowConstraints().add(new RowConstraints(220*0.2));
            cadastroGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            cadastroGrid.add(l, 0, counter, 1, 1);
            counter++;
        }
        TextField nomeText = new TextField();
        nomeText.getStyleClass().add("geral");
        nomeText.setStyle("-fx-border-radius: 5px; -fx-border-width: 1.5px; -fx-border-color: #FFF8E7;");
        nomeText.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });
        cadastroGrid.add(nomeText, 1, 0, 1, 1);

        TextField loginText = new TextField();
        loginText.getStyleClass().add("geral");
        cadastroGrid.add(loginText, 1, 1, 1, 1);

        ChoiceBox<String> c = new ChoiceBox<>();
        c.getItems().addAll(
            "Leitor",
            "Bibliotecário"
        );
        c.getStyleClass().add("geral");
        c.setPrefWidth(300*0.6);
        cadastroGrid.add(c, 1, 2, 1, 1);
        
        PasswordField senhaField = new PasswordField();
        senhaField.getStyleClass().add("geral");
        cadastroGrid.add(senhaField, 1, 3, 1, 1);
        PasswordField senha2Field = new PasswordField();
        senha2Field.getStyleClass().add("geral");
        cadastroGrid.add(senha2Field, 1, 4, 1, 1);

        Button cadastrar = new Button("Cadastrar");
        cadastrar.setPrefHeight(30);
        cadastrar.setPrefWidth(160);
        cadastrar.setLayoutX(151);
        cadastrar.setLayoutY(330);
        cadastrar.getStyleClass().add("geral");
        cadastrar.setStyle("-fx-font-weight: bold;");
        cadastrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                System.out.println(c.getValue());
                for(int i = 1; i <= 6; i++){
                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, cadastroGrid, cadastrar);
    }

    public void detalhesMembros(Membro membro){
        int counter = 0;
        Rectangle recFundo = new Rectangle();
        recFundo.setWidth(444);
        recFundo.setHeight(358);
        recFundo.setX(0);
        recFundo.setY(40);
        recFundo.setStyle("-fx-fill: #404040; -fx-opacity: 0.8;");

        Rectangle recCadastro = new Rectangle();
        recCadastro.setWidth(350);
        recCadastro.setHeight(250);
        recCadastro.setLayoutX(47);
        recCadastro.setLayoutY(49);
        recCadastro.setStyle("-fx-fill: #A6A6A6;");

        Label title = new Label("Cadastro de Membro");
        title.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        title.setLayoutX(133);
        title.setLayoutY(59);

        Label x = new Label("X");
        x.setLayoutX(367);
        x.setLayoutY(59);
        x.setPrefWidth(20);
        x.setPrefHeight(20);
        x.setAlignment(Pos.CENTER);
        x.setStyle("-fx-background-color: red; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center; -fx-cursor: hand;");
        x.setCursor(Cursor.HAND);
        x.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                for(int i = 1; i <= 5; i++){
                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        GridPane membrosGrid = new GridPane();
        membrosGrid.setLayoutX(65);
        membrosGrid.setLayoutY(84);
        membrosGrid.setPrefWidth(300);
        membrosGrid.setPrefHeight(220);
        membrosGrid.setHgap(15);

        membrosGrid.getColumnConstraints().addAll(
            new ColumnConstraints(300*0.3),
            new ColumnConstraints(300*0.7)
        );

        for(ColumnConstraints c: membrosGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        for(Label l: Arrays.asList(
            new Label("Nome"),
            new Label("Login"),
            new Label("Estado"),
            new Label("Livros alugados")
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: violet;");
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.3);
            l.setWrapText(true);
            membrosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            membrosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            membrosGrid.add(l, 0, counter, 1, 1);
            counter++;
        }
        counter = 0;
        for(Label l: Arrays.asList(
            new Label(membro.nome),
            new Label(membro.login),
            new Label(membro.estado)
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: blue;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.7);
            membrosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            membrosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            membrosGrid.add(l, 1, counter, 1, 1);
            counter++;
        }
        String text = "";
        if(membro.alugado == null){
            text = "Nenhum";
        }else{
            text = String.format("%s em %s", membro.alugado.code, membro.alugado.dataAluguel);
        }
        Label l = new Label(text);
        l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: blue;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.7);
            membrosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            membrosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            membrosGrid.add(l, 1, 3, 1, 1);
        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, membrosGrid);
    }

    public void acervo(){
        int counter = 0;
        Label panelTitle = this.paneLabel("Acervo");

        TableView<Material> acervoTable = new TableView<>();
        acervoTable.setPrefWidth(mainPanel.getWidth() - 20);
        acervoTable.setPrefHeight(270);
        acervoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        acervoTable.setLayoutX(10);
        acervoTable.setLayoutY(50);

        acervoTable.setRowFactory(t ->{
            TableRow<Material>row = new TableRow<>();
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent ev) {
                    if(ev.getClickCount() == 2){
                        detalhesMaterial(row.getItem());
                    }
                }
            });
            return row;
        });

        for(TableColumn<Material, String> c:  Arrays.asList(
            new TableColumn<Material, String>("Código"), 
            new TableColumn<Material, String>("Nome"), 
            new TableColumn<Material, String>("Categoria"),
            new TableColumn<Material, String>("Estado"))
        ){
            switch(counter){
                case 0:
                    c.setCellValueFactory(new PropertyValueFactory<>("cod"));
                    break;
                case 1:
                    c.setCellValueFactory(new PropertyValueFactory<>("nome"));
                    break;
                case 2:
                    c.setCellValueFactory(new PropertyValueFactory<>("categoria"));
                    break;
                case 3:
                    c.setCellValueFactory(new PropertyValueFactory<>("estado"));
                    break;
            }
            counter++;
            acervoTable.getColumns().add(c);
        }
        acervoTable.setItems(JsonManager.getAcervo());

        Button cadastro = new Button("Cadastrar material");
        cadastro.setPrefHeight(30);
        cadastro.setPrefWidth(160);
        cadastro.setLayoutX(142);
        cadastro.setLayoutY(343);
        cadastro.getStyleClass().add("geral");
        cadastro.setStyle("-fx-font-weight: bold;");
        cadastro.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                cadastroMembros();
                System.out.println(mainPanel.getChildren().size());
            }
        });

        mainPanel.getChildren().addAll(panelTitle, acervoTable, cadastro);
    }

    public void cadastroMaterial(){
        int counter = 0;
        Rectangle recFundo = new Rectangle();
        recFundo.setWidth(444);
        recFundo.setHeight(358);
        recFundo.setX(0);
        recFundo.setY(40);
        recFundo.setStyle("-fx-fill: #404040; -fx-opacity: 0.8;");

        Rectangle recCadastro = new Rectangle();
        recCadastro.setWidth(350);
        recCadastro.setHeight(300);
        recCadastro.setLayoutX(47);
        recCadastro.setLayoutY(70);
        recCadastro.setStyle("-fx-fill: #A6A6A6;");

        Label title = new Label("Cadastro de Evento");
        title.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        title.setLayoutX(133);
        title.setLayoutY(80);

        Label x = new Label("X");
        x.setLayoutX(367);
        x.setLayoutY(80);
        x.setPrefWidth(20);
        x.setPrefHeight(20);
        x.setAlignment(Pos.CENTER);
        x.setStyle("-fx-background-color: red; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center; -fx-cursor: hand;");
        x.setCursor(Cursor.HAND);
        x.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                for(int i = 1; i <= 6; i++){
                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        GridPane cadastroGrid = new GridPane();
        cadastroGrid.setLayoutX(65);
        cadastroGrid.setLayoutY(105);
        cadastroGrid.setPrefWidth(300);
        cadastroGrid.setPrefHeight(220);
        cadastroGrid.setHgap(15);

        cadastroGrid.getColumnConstraints().addAll(
            new ColumnConstraints(300*0.4),
            new ColumnConstraints(300*0.6)
        );

        for(ColumnConstraints c: cadastroGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        for(Label l: Arrays.asList(
            new Label("Nome"), 
            new Label("Autor(es)"), 
            new Label("Categoria"), 
            new Label("Descrição"),
            new Label("Data de cadastro")
            )
        ){
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: center;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            cadastroGrid.getRowConstraints().add(new RowConstraints(220*0.2));
            cadastroGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            cadastroGrid.add(l, 0, counter, 1, 1);
            counter++;
        }
        TextField nomeText = new TextField();
        nomeText.getStyleClass().add("geral");
        cadastroGrid.add(nomeText, 1, 0, 1, 1);

        TextField requestText = new TextField();
        requestText.getStyleClass().add("geral");
        cadastroGrid.add(requestText, 1, 1, 1, 1);

        ChoiceBox<String> c = new ChoiceBox<>();
        c.getItems().addAll(
            "Romance",
            "Conto",
            "Cordel",
            "Biografia",
            "Enciclopédia",
            "Revista",
            "História em Quadrinhos"
        );
        c.getStyleClass().add("geral");
        c.setPrefWidth(300*0.6);
        cadastroGrid.add(c, 1, 2, 1, 1);
        TextArea descArea = new TextArea();
        descArea.setStyle("-fx-control-inner-background:#404040;");
        cadastroGrid.add(descArea, 1, 3, 1, 1);
        DatePicker date = new DatePicker();
        date.setStyle("-fx-control-inner-background:#404040;");
        cadastroGrid.add(date, 1, 4, 1, 1);

        Button cadastrar = new Button("Cadastrar");
        cadastrar.setPrefHeight(30);
        cadastrar.setPrefWidth(160);
        cadastrar.setLayoutX(151);
        cadastrar.setLayoutY(330);
        cadastrar.getStyleClass().add("geral");
        cadastrar.setStyle("-fx-font-weight: bold;");
        cadastrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                System.out.println(c.getValue());
                for(int i = 1; i <= 6; i++){

                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, cadastroGrid, cadastrar);
    }

    public void detalhesMaterial(Material material){
        int counter = 0;
        Rectangle recFundo = new Rectangle();
        recFundo.setWidth(444);
        recFundo.setHeight(358);
        recFundo.setX(0);
        recFundo.setY(40);
        recFundo.setStyle("-fx-fill: #404040; -fx-opacity: 0.8;");

        Rectangle recCadastro = new Rectangle();
        recCadastro.setWidth(350);
        recCadastro.setHeight(250);
        recCadastro.setLayoutX(47);
        recCadastro.setLayoutY(49);
        recCadastro.setStyle("-fx-fill: #A6A6A6;");

        Label title = new Label("Cadastro de Membro");
        title.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        title.setLayoutX(133);
        title.setLayoutY(59);

        Label x = new Label("X");
        x.setLayoutX(367);
        x.setLayoutY(59);
        x.setPrefWidth(20);
        x.setPrefHeight(20);
        x.setAlignment(Pos.CENTER);
        x.setStyle("-fx-background-color: red; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center; -fx-cursor: hand;");
        x.setCursor(Cursor.HAND);
        x.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                for(int i = 1; i <= 5; i++){
                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        GridPane acervoGrid = new GridPane();
        acervoGrid.setLayoutX(65);
        acervoGrid.setLayoutY(84);
        acervoGrid.setPrefWidth(300);
        acervoGrid.setPrefHeight(220);
        acervoGrid.setHgap(15);

        acervoGrid.getColumnConstraints().addAll(
            new ColumnConstraints(300*0.4),
            new ColumnConstraints(300*0.6)
        );

        for(ColumnConstraints c: acervoGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        for(Label l: Arrays.asList(
            new Label("Código"),
            new Label("Nome"),
            new Label("Categoria"),
            new Label("Estado")
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: violet;");
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.3);
            l.setWrapText(true);
            acervoGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            acervoGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            acervoGrid.add(l, 0, counter, 1, 1);
            counter++;
        }
        counter = 0;
        for(Label l: Arrays.asList(
            new Label(material.cod),
            new Label(material.nome),
            new Label(material.categoria),
            new Label(material.estado)
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: blue;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.7);
            acervoGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            acervoGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            acervoGrid.add(l, 1, counter, 1, 1);
            counter++;
        }

        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, acervoGrid);
    }

    public void evento(){
        int counter = 0;
        Label panelTitle = this.paneLabel("Eventos");

        TableView<Evento> eventTable = new TableView<>();
        eventTable.setPrefWidth(mainPanel.getWidth() - 20);
        eventTable.setPrefHeight(150);
        eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        eventTable.setLayoutX(10);
        eventTable.setLayoutY(50);

        eventTable.setRowFactory(t ->{
            TableRow<Evento>row = new TableRow<>();
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent ev) {
                    if(ev.getClickCount() == 2){
                        detalhesEvento(row.getItem());
                    }
                    
                }
            });
            return row;
        });

        for(TableColumn<Evento, String> c : Arrays.asList(
                new TableColumn<Evento, String>("Código"),
                new TableColumn<Evento, String>("Nome"), 
                new TableColumn<Evento, String>("Categoria"), 
                new TableColumn<Evento, String>("Data")
            )
        ){ 
            switch(counter){
                case 0:
                    c.setCellValueFactory(new PropertyValueFactory<>("cod"));
                    break;
                case 1:
                    c.setCellValueFactory(new PropertyValueFactory<>("nome"));
                    break;
                case 2:
                    c.setCellValueFactory(new PropertyValueFactory<>("categoria"));
                    break;
                case 3:
                    c.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));
                    break;
            }
            counter++;
            eventTable.getColumns().add(c);
        }
        eventTable.setItems(JsonManager.getAllEventos());

        TableView<Requisicao> requestTable = new TableView<>();
        requestTable.setPrefWidth(mainPanel.getWidth() - 20);
        requestTable.setPrefHeight(100);
        requestTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        requestTable.setLayoutX(10);
        requestTable.setLayoutY(220);

        requestTable.setRowFactory(t ->{
            TableRow<Requisicao>row = new TableRow<>();
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent ev) {
                    if(ev.getClickCount() == 2){
                        detalhesRequisicao(row.getItem());
                    }
                    
                }
            });
            return row;
        });

        counter = 0;
        for(TableColumn<Requisicao, String> c:  Arrays.asList(
            new TableColumn<Requisicao, String>("Requisitante"), 
            new TableColumn<Requisicao, String>("Nome"), 
            new TableColumn<Requisicao, String>("Categoria"),
            new TableColumn<Requisicao, String>("Data"))
        ){
            switch(counter){
                case 0:
                    c.setCellValueFactory(new PropertyValueFactory<>("requisitante"));
                    break;
                case 1:
                    c.setCellValueFactory(new PropertyValueFactory<>("nome"));
                    break;
                case 2:
                    c.setCellValueFactory(new PropertyValueFactory<>("categoria"));
                    break;
                case 3:
                    c.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));
                    break;
            }
            counter++;
            requestTable.getColumns().add(c);
        }
        
        requestTable.setItems(JsonManager.getAllReq());

        Button cadastro = new Button("Cadastrar evento");
        cadastro.setPrefHeight(30);
        cadastro.setPrefWidth(160);
        cadastro.setLayoutX(142);
        cadastro.setLayoutY(343);
        cadastro.getStyleClass().add("geral");
        cadastro.setStyle("-fx-font-weight: bold;");
        cadastro.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                cadastroEvento();
                System.out.println(mainPanel.getChildren().size());
            }
        });

        mainPanel.getChildren().addAll(panelTitle, eventTable, requestTable, cadastro);
    }
    
    public void cadastroEvento(){
        int counter = 0;
        Rectangle recFundo = new Rectangle();
        recFundo.setWidth(444);
        recFundo.setHeight(358);
        recFundo.setX(0);
        recFundo.setY(40);
        recFundo.setStyle("-fx-fill: #404040; -fx-opacity: 0.8;");

        Rectangle recCadastro = new Rectangle();
        recCadastro.setWidth(350);
        recCadastro.setHeight(300);
        recCadastro.setLayoutX(47);
        recCadastro.setLayoutY(70);
        recCadastro.setStyle("-fx-fill: #A6A6A6;");

        Label title = new Label("Cadastro de Evento");
        title.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        title.setLayoutX(133);
        title.setLayoutY(80);

        Label x = new Label("X");
        x.setLayoutX(367);
        x.setLayoutY(80);
        x.setPrefWidth(20);
        x.setPrefHeight(20);
        x.setAlignment(Pos.CENTER);
        x.setStyle("-fx-background-color: red; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center;");
        x.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                for(int i = 1; i <= 6; i++){
                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        GridPane cadastroGrid = new GridPane();
        cadastroGrid.setLayoutX(65);
        cadastroGrid.setLayoutY(105);
        cadastroGrid.setPrefWidth(300);
        cadastroGrid.setPrefHeight(220);
        cadastroGrid.setHgap(15);

        cadastroGrid.getColumnConstraints().addAll(
            new ColumnConstraints(300*0.4),
            new ColumnConstraints(300*0.6)
        );

        for(ColumnConstraints c: cadastroGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        for(Label l: Arrays.asList(
            new Label("Nome do evento"), 
            new Label("Requisitante"), 
            new Label("Categoria"), 
            new Label("Data")
            )
        ){
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: center;");
            l.setWrapText(true);
            cadastroGrid.getRowConstraints().add(new RowConstraints(220*0.2));
            cadastroGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            cadastroGrid.add(l, 0, counter, 1, 1);
            counter++;
        }
        TextField nomeText = new TextField();
        nomeText.getStyleClass().add("geral");
        cadastroGrid.add(nomeText, 1, 0, 1, 1);

        TextField requestText = new TextField();
        requestText.getStyleClass().add("geral");
        cadastroGrid.add(requestText, 1, 1, 1, 1);

        ChoiceBox<String> c = new ChoiceBox<>();
        c.getItems().addAll(
            "Palestra",
            "Mesa Redonda", 
            "Oficina",
            "Leitura de História",
            "Sarau Literário",
            "Exposição",
            "Outros");
        c.getStyleClass().add("geral");
        c.setPrefWidth(300*0.6);
        cadastroGrid.add(c, 1, 2, 1, 1);

        DatePicker date = new DatePicker();
        date.setStyle("-fx-control-inner-background:#404040;");
        cadastroGrid.add(date, 1, 3, 1, 1);

        Button cadastrar = new Button("Cadastrar");
        cadastrar.setPrefHeight(30);
        cadastrar.setPrefWidth(160);
        cadastrar.setLayoutX(151);
        cadastrar.setLayoutY(330);
        cadastrar.getStyleClass().add("geral");
        cadastrar.setStyle("-fx-font-weight: bold;");
        cadastrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                System.out.println(c.getValue());
                for(int i = 1; i <= 6; i++){

                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, cadastroGrid, cadastrar);
    }

    public void detalhesEvento(Evento evento){
        int counter = 0;
        Rectangle recFundo = new Rectangle();
        recFundo.setWidth(444);
        recFundo.setHeight(358);
        recFundo.setX(0);
        recFundo.setY(40);
        recFundo.setStyle("-fx-fill: #404040; -fx-opacity: 0.8;");

        Rectangle recCadastro = new Rectangle();
        recCadastro.setWidth(350);
        recCadastro.setHeight(250);
        recCadastro.setLayoutX(47);
        recCadastro.setLayoutY(49);
        recCadastro.setStyle("-fx-fill: #A6A6A6;");

        Label title = new Label("Evento");
        title.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        title.setLayoutX(133);
        title.setLayoutY(59);

        Label x = new Label("X");
        x.setLayoutX(367);
        x.setLayoutY(59);
        x.setPrefWidth(20);
        x.setPrefHeight(20);
        x.setAlignment(Pos.CENTER);
        x.setStyle("-fx-background-color: red; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center; -fx-cursor: hand;");
        x.setCursor(Cursor.HAND);
        x.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                for(int i = 1; i <= 5; i++){
                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        GridPane eventosGrid = new GridPane();
        eventosGrid.setLayoutX(65);
        eventosGrid.setLayoutY(84);
        eventosGrid.setPrefWidth(300);
        eventosGrid.setPrefHeight(220);
        eventosGrid.setHgap(15);

        eventosGrid.getColumnConstraints().addAll(
            new ColumnConstraints(300*0.3),
            new ColumnConstraints(300*0.7)
        );

        for(ColumnConstraints c: eventosGrid.getColumnConstraints()){
            c.setHalignment(HPos.LEFT);
        }
        
        for(Label l: Arrays.asList(
            new Label("Nome do evento:"),
            new Label("Requisitante:"),
            new Label("Categoria:"), 
            new Label("Data:")
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: violet;");
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.3);
            l.setWrapText(true);
            eventosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            eventosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            eventosGrid.add(l, 0, counter, 1, 1);
            counter++;
        }
        counter = 0;
        for(Label l: Arrays.asList(
            new Label(evento.nome),
            new Label(evento.requisitante),
            new Label(evento.categoria), 
            new Label(evento.dataEvento)
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: blue;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.7);
            eventosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            eventosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            eventosGrid.add(l, 1, counter, 1, 1);
            counter++;
        }
        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, eventosGrid);
    }

    public void detalhesRequisicao(Requisicao req){
        int counter = 0;
        Rectangle recFundo = new Rectangle();
        recFundo.setWidth(444);
        recFundo.setHeight(358);
        recFundo.setX(0);
        recFundo.setY(40);
        recFundo.setStyle("-fx-fill: #404040; -fx-opacity: 0.8;");

        Rectangle recCadastro = new Rectangle();
        recCadastro.setWidth(350);
        recCadastro.setHeight(250);
        recCadastro.setLayoutX(47);
        recCadastro.setLayoutY(49);
        recCadastro.setStyle("-fx-fill: #A6A6A6;");

        Label title = new Label("Requisição de Evento");
        title.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        title.setLayoutX(133);
        title.setLayoutY(59);

        Label x = new Label("X");
        x.setLayoutX(367);
        x.setLayoutY(59);
        x.setPrefWidth(20);
        x.setPrefHeight(20);
        x.setAlignment(Pos.CENTER);
        x.setStyle("-fx-background-color: red; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center; -fx-cursor: hand;");
        x.setCursor(Cursor.HAND);
        x.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                int size = mainPanel.getChildren().size();
                for(int i = 1; i <= 5; i++){
                    mainPanel.getChildren().remove(size - i);
                }
            }
        });

        GridPane eventosGrid = new GridPane();
        eventosGrid.setLayoutX(65);
        eventosGrid.setLayoutY(84);
        eventosGrid.setPrefWidth(300);
        eventosGrid.setPrefHeight(220);
        eventosGrid.setHgap(15);

        eventosGrid.getColumnConstraints().addAll(
            new ColumnConstraints(300*0.3),
            new ColumnConstraints(300*0.7)
        );

        for(ColumnConstraints c: eventosGrid.getColumnConstraints()){
            c.setHalignment(HPos.LEFT);
        }

        for(Label l: Arrays.asList(
            new Label("Nome do evento"),
            new Label("Requisitante"),
            new Label("Categoria"), 
            new Label("Data")
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: violet;");
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.3);
            l.setWrapText(true);
            eventosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            eventosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            eventosGrid.add(l, 0, counter, 1, 1);
            counter++;
        }
        counter = 0;
        for(Label l: Arrays.asList(
            new Label(req.nome),
            new Label(req.requisitante),
            new Label(req.categoria), 
            new Label(req.dataEvento)
        )){
            l.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: blue;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.7);
            eventosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            eventosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            eventosGrid.add(l, 1, counter, 1, 1);
            counter++;
        }
        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, eventosGrid);
    }

    public void initialize() {
        this.home();
    }

    @FXML
    void changeColorEnter(MouseEvent event) {
        Label a = (Label) event.getSource();
        a.setStyle("-fx-text-fill: #FFF8E7; -fx-background-color: #707070;");
    }

    @FXML
    void changeColorExit(MouseEvent event){
        Label a = (Label) event.getSource();
        a.setStyle("-fx-background-color: #c1c1c1; -fx-text-fill: black; -fx-background-color: #989898;");
    }

    @FXML
    void changeMainPanel(MouseEvent event){
        String b =  ((Label) event.getSource()).getId();
        System.out.println("Bitch =>" + b);
        if(!this.state.equals(b)){
            this.state = b;
            mainPanel.getChildren().clear();
            switch(b){
                case "homeButton":
                    this.home();
                    break;
                case "membrosButton":
                    this.membros();
                    break;
                case "eventosButton":
                    this.evento();
                    break;
                case "acervoButton":
                    this.acervo();
                    break;
            }
        }
        
    }

}
