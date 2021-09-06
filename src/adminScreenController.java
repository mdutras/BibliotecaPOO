package src;

import java.util.Arrays;

import java.time.LocalDate;
import java.util.ArrayList;

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
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;

public class adminScreenController {

    String state = "";
    String login = "";

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

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return this.login;
    }

    Label paneLabel(String title){
        Label l = new Label(title);
        l.setLayoutX(0);
        l.setLayoutX(0);
        l.setPrefWidth(444);
        l.setPrefHeight(40);
        l.setStyle(" -fx-background-color: #404040;-fx-text-fill: #FFF8E7;-fx-font-size: 24px; -fx-text-alignment: center; -fx-background-radius: 0px;");
        l.setAlignment(Pos.CENTER);
        return l;
    }

    public void home(){

        Label panelTitle = this.paneLabel("Home");

        int counter = 0;
        Label user = new Label("Usuário: " + getLogin());
        user.setLayoutX(0);
        user.setLayoutY(40);
        user.setPrefWidth(mainPanel.getPrefWidth());
        user.setAlignment(Pos.CENTER_RIGHT);
        user.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        Label tblTitle = new Label("Livros alugados");
        tblTitle.setLayoutX(152);
        tblTitle.setLayoutY(89);
        tblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label evTitle = new Label("Eventos");
        evTitle.setLayoutX(186);
        evTitle.setLayoutY(238);
        evTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TableView<Material> tbl = new TableView<>();
        tbl.setPrefWidth(425);
        tbl.setPrefHeight(111);
        tbl.setLayoutX(11);
        tbl.setLayoutY(109);
        tbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableViewSelectionModel<Material> selectionModel = tbl.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        tbl.setRowFactory(t ->{
            TableRow<Material>row = new TableRow<>();
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent ev) {
                    if(ev.getClickCount() == 2){
                        devolucao(row.getItem());
                    }
                    
                }
            });
            return row;
        });

        for(TableColumn<Material, String> c:  Arrays.asList(
            new TableColumn<Material, String>("Livro"), 
            new TableColumn<Material, String>("Estado"),
            new TableColumn<Material, String>("Leitor")
            )
        ){
            switch(counter){
                case 0:
                    c.setCellValueFactory(new PropertyValueFactory<>("nome"));
                    break;
                case 1:
                    c.setCellValueFactory(new PropertyValueFactory<>("estado"));
                    break;
                case 2:
                    c.setCellValueFactory(new PropertyValueFactory<>("leitor"));
                    break;
            }
            counter++;
            tbl.getColumns().add(c);
        }

        tbl.setItems(JsonManager.getAcervoAlugado());

        //Tabela de eventos
        TableView<Evento> evs = new TableView<>();
        evs.setPrefWidth(425);
        evs.setPrefHeight(111);
        evs.setLayoutX(11);
        evs.setLayoutY(258);
        evs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        counter = 0;
        for(TableColumn<Evento, String> c:  Arrays.asList(
            new TableColumn<Evento, String>("Código"), 
            new TableColumn<Evento, String>("Nome"), 
            new TableColumn<Evento, String>("Categoria"))
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
            }
            counter++;
            evs.getColumns().add(c);
        }
        evs.setItems(JsonManager.getAllEventos());


        mainPanel.getChildren().addAll(panelTitle, user, tblTitle, evTitle, tbl, evs);
    }

    public void devolucao(Material material){
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
            public void handle(MouseEvent ev) {
                mainPanel.getChildren().clear();
                home();
            }
        });

        GridPane membrosGrid = new GridPane();
        membrosGrid.setLayoutX(65);
        membrosGrid.setLayoutY(84);
        membrosGrid.setPrefWidth(300);
        membrosGrid.setPrefHeight(100);
        membrosGrid.setHgap(15);

        membrosGrid.getColumnConstraints().addAll(
            new ColumnConstraints(300*0.3),
            new ColumnConstraints(300*0.7)
        );

        for(ColumnConstraints c: membrosGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        for(Label l: Arrays.asList(
            new Label("Código"),
            new Label("Estado"),
            new Label("Leitor")
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: #A6B7B7;");
            l.setPrefHeight(30);
            l.setPrefWidth(300*0.3);
            l.setWrapText(true);
            membrosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            membrosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            membrosGrid.add(l, 0, counter, 1, 1);
            counter++;
        }
        counter = 0;
        for(Label l: Arrays.asList(
            new Label(material.cod),
            new Label(material.estado),
            new Label(material.leitor)
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: #A69595;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.7);
            membrosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            membrosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            membrosGrid.add(l, 1, counter, 1, 1);
            counter++;
        }

        Button devolver = new Button("Devolver");
        devolver.setPrefHeight(30);
        devolver.setPrefWidth(160);
        devolver.setLayoutX(151);
        devolver.setLayoutY(260);
        devolver.getStyleClass().add("geral");
        devolver.setStyle("-fx-font-weight: bold;");
        devolver.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                JsonManager.devolverMaterial(material);
                mainPanel.getChildren().clear();
                home();
                }
            });
        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, membrosGrid, devolver);
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
            new TableColumn<Membro, String>("Categoria"), 
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
                    c.setCellValueFactory(new PropertyValueFactory<>("categoria"));
                    break;
                case 3:
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
        ArrayList<Boolean> forbid = new ArrayList<Boolean>();
        for(int i = 0; i < 5; i++){
            forbid.add(false);
        }
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
                mainPanel.getChildren().clear();
                membros();
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
        nomeText.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(nomeText.getText().equals("")){
                    nomeText.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(0, false);
                }else{
                    nomeText.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(0)){
                        forbid.set(0, true);
                    }
                }
            }
        });
        cadastroGrid.add(nomeText, 1, 0, 1, 1);

        TextField loginText = new TextField();
        loginText.getStyleClass().add("geral");
        loginText.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(loginText.getText().equals("") || JsonManager.membroExists(loginText.getText())){
                    loginText.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(1, false);
                }else{
                    loginText.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(1)){
                        forbid.set(1, true);
                    }
                }
            }
        });
        cadastroGrid.add(loginText, 1, 1, 1, 1);

        ChoiceBox<String> c = new ChoiceBox<>();
        c.getItems().addAll(
            "Leitor",
            "Bibliotecário"
        );
        c.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(c.getValue().equals("")){
                    c.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(2, false);
                }else{
                    c.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(2)){
                        forbid.set(2, true);
                    }
                }
            }
        });
        c.getStyleClass().add("geral");
        c.setPrefWidth(300*0.6);
        cadastroGrid.add(c, 1, 2, 1, 1);
        
        PasswordField senhaField = new PasswordField();
        senhaField.getStyleClass().add("geral");
        senhaField.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(senhaField.getText().equals("")){
                    senhaField.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(3, false);
                }else{
                    senhaField.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(3)){
                        forbid.set(3, true);
                    }
                }
            }
        });
        cadastroGrid.add(senhaField, 1, 3, 1, 1);

        PasswordField senha2Field = new PasswordField();
        senha2Field.getStyleClass().add("geral");
        senha2Field.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(senha2Field.getText().equals("") || !senha2Field.getText().equals(senhaField.getText())){
                    senha2Field.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(4, false);
                }else{
                    senha2Field.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(4)){
                        forbid.set(4, true);
                    }
                }
            }
        });
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
                Boolean allow = true;
                for(int i = 0; i < 5; i++){
                    if(!forbid.get(i)){
                        allow = false;
                    }
                }
                if(allow){
                    JsonManager.cadastroMembro(
                    loginText.getText(), 
                    nomeText.getText(), 
                    senhaField.getText(),
                    c.getValue()
                );
                mainPanel.getChildren().clear();
                membros();
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
                mainPanel.getChildren().clear();
                membros();
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
            new Label("Nome:"),
            new Label("Login:"),
            new Label("Estado:"),
            new Label("Livros alugados:")
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: #A6B7B7;");
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
            l.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: #A69595;");
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
            l.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: #A69595;");
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
            public void handle(MouseEvent ev) {
                cadastroMaterial();
                System.out.println(mainPanel.getChildren().size());
            }
        });

        mainPanel.getChildren().addAll(panelTitle, acervoTable, cadastro);
    }

    public void cadastroMaterial(){
        int counter = 0;
        ArrayList<Boolean> forbid = new ArrayList<Boolean>();
        for(int i = 0; i < 3; i++){
            forbid.add(false);
        }

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
                mainPanel.getChildren().clear();
                acervo();
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
            new Label("Categoria")
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
        nomeText.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(nomeText.getText().equals("")){
                    nomeText.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(0, false);
                }else{
                    nomeText.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(0)){
                        forbid.set(0, true);
                    }
                }
            }
        });
        cadastroGrid.add(nomeText, 1, 0, 1, 1);

        TextField autorText = new TextField();
        autorText.getStyleClass().add("geral");
        autorText.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(autorText.getText().equals("")){
                    autorText.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(1, false);
                }else{
                    autorText.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(1)){
                        forbid.set(1, true);
                    }
                }
            }
        });
        cadastroGrid.add(autorText, 1, 1, 1, 1);

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
        c.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(c.getValue().equals("")){
                    c.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(2, false);
                }else{
                    c.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(2)){
                        forbid.set(2, true);
                    }
                }
            }
        });
        c.setPrefWidth(300*0.6);
        cadastroGrid.add(c, 1, 2, 1, 1);

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
                Boolean allow = true;
                for(int i = 0; i < 3; i++){
                    if(!forbid.get(i)){
                        allow = false;
                    }
                }
                if(allow){
                    JsonManager.cadastroMaterial(new Material(
                        nomeText.getText(), 
                        autorText.getText(), 
                        c.getValue(), 
                        LocalDate.now().toString(),
                        "",
                        "",
                        ""
                    ));
                mainPanel.getChildren().clear();
                acervo();
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
                mainPanel.getChildren().clear();
                acervo();
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
            new Label("Código:"),
            new Label("Nome:"),
            new Label("Categoria:"),
            new Label("Estado:")
        )){
            l.setAlignment(Pos.CENTER_LEFT);
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: #A6B7B7;");
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
            l.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: #A69595;");
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
        ArrayList<Boolean> forbid = new ArrayList<Boolean>();
        for(int i = 0; i < 4; i++){
            forbid.add(false);
        }

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
                mainPanel.getChildren().clear();
                evento();
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
        nomeText.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(nomeText.getText().equals("")){
                    nomeText.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(0, false);
                }else{
                    nomeText.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(0)){
                        forbid.set(0, true);
                    }
                }
            }
        });
        cadastroGrid.add(nomeText, 1, 0, 1, 1);

        Label requestText = new Label(getLogin());
        requestText.setAlignment(Pos.CENTER);
        requestText.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-font-weight: bold; -fx-text-alignment: center;");
        requestText.setWrapText(true);
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
        c.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(c.getValue().equals("")){
                    c.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(2, false);
                }else{
                    c.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(2)){
                        forbid.set(2, true);
                    }
                }
            }
        });
        c.setPrefWidth(300*0.6);
        cadastroGrid.add(c, 1, 2, 1, 1);

        DatePicker date = new DatePicker();
        date.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if(date.getValue().toString().equals("")){
                    date.setStyle("-fx-border-color: #e4000f;");
                    forbid.set(3, false);
                }else{
                    date.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(3)){
                        forbid.set(3, true);
                    }
                }
            }
        });
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
                Boolean allow = true;
                for(int i = 0; i < 4; i++){
                    if(!forbid.get(i)){
                        allow = false;
                    }
                }
                if(allow){
                JsonManager.cadastroEvento(new Evento(
                    nomeText.getText(),
                    requestText.getText(),
                    c.getValue(),
                    date.getValue().toString(),
                    ""
                ));
                mainPanel.getChildren().clear();
                evento();
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
                mainPanel.getChildren().clear();
                evento();
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
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: #A6B7B7;");
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
            l.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: #A69595;");
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
        recCadastro.setHeight(320);
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
                mainPanel.getChildren().clear();
                evento();
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
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: #A6B7B7;");
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
            l.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: #A69595;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.7);
            eventosGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            eventosGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            eventosGrid.add(l, 1, counter, 1, 1);
            counter++;
        }
        Button aceitar = new Button("Aceitar");
        aceitar.setPrefHeight(30);
        aceitar.setPrefWidth(120);
        aceitar.setLayoutX(65);
        aceitar.setLayoutY(319);
        aceitar.getStyleClass().add("geral");
        aceitar.setStyle("-fx-font-weight: bold; -fx-border-color: #39ff14;");
        aceitar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                req.aceito();
                mainPanel.getChildren().clear();
                evento();
            }
        });

        Button rejeitar = new Button("Rejeitar");
        rejeitar.setPrefHeight(30);
        rejeitar.setPrefWidth(120);
        rejeitar.setLayoutX(245);
        rejeitar.setLayoutY(319);
        rejeitar.getStyleClass().add("geral");
        rejeitar.setStyle("-fx-font-weight: bold; -fx-border-color: #e4000f;");
        rejeitar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                req.rejeitado();
                mainPanel.getChildren().clear();
                evento();
            }
        });
        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, eventosGrid, aceitar, rejeitar);
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
