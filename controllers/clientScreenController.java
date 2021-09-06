package controllers;

import src.*;

import java.util.Arrays;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.collections.FXCollections;

public class clientScreenController {
    String state = "";
    String login = "";

    @FXML
    private Label homeButton;

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
        System.out.println(mainPanel.getPrefWidth());
        Label panelTitle = this.paneLabel("Home");
        Membro membro;
        if(getLogin().equals("")){
            membro = new Membro("", "", "");
        }else{
            membro = JsonManager.getMembro(getLogin());
        }
        int counter = 0;
        Label user = new Label("Usuário: " + getLogin() + " ");
        user.setLayoutX(0);
        user.setLayoutY(40);
        user.setPrefWidth(mainPanel.getPrefWidth());
        user.setAlignment(Pos.CENTER_RIGHT);
        user.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        Label tblTitle = new Label("Livro alugado");
        tblTitle.setLayoutX(0);
        tblTitle.setLayoutY(132);
        tblTitle.setPrefWidth(mainPanel.getPrefWidth());
        tblTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-alignment: center");
        if(membro.alugado == null){
            Label noMaterial = new Label("Você não alugou nenhum livro");
            noMaterial.setLayoutX(0);
            noMaterial.setLayoutY(170);
            noMaterial.setPrefWidth(mainPanel.getPrefWidth());
            noMaterial.setStyle("-fx-font-size: 18px; -fx-alignment: center");
            mainPanel.getChildren().addAll(panelTitle, user, tblTitle, noMaterial);
        }else{
            MaterialAlugado alugado = membro.alugado;
            TableView<MaterialAlugado> materialAlugado = new TableView<>();
            materialAlugado.setLayoutX(11);
            materialAlugado.setLayoutY(170);
            materialAlugado.setPrefWidth(mainPanel.getPrefWidth() - 20);
            materialAlugado.setPrefHeight(50);
            materialAlugado.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            for(TableColumn<MaterialAlugado, String> c:  Arrays.asList(
                new TableColumn<MaterialAlugado, String>("Código"), 
                new TableColumn<MaterialAlugado, String>("Data do Aluguel"))
            ){
                c.setStyle("-fx-alignment: CENTER;");
                switch(counter){
                    case 0:
                        c.setCellValueFactory(new PropertyValueFactory<>("code"));
                        break;
                    case 1:
                        c.setCellValueFactory(new PropertyValueFactory<>("dataAluguel"));
                        break;
                }
                counter++;
                materialAlugado.getColumns().add(c);
            }
            materialAlugado.setItems(
                FXCollections.observableArrayList(
                    alugado
                )
            );
            mainPanel.getChildren().addAll(panelTitle, user, tblTitle, materialAlugado);
        }
    }

    public void acervo(){
        System.out.println(this.login);
        int counter = 0;
        Label panelTitle = this.paneLabel("Acervo");

        TableView<Material> acervoTable = new TableView<>();
        acervoTable.setPrefWidth(mainPanel.getWidth() - 20);
        acervoTable.setPrefHeight(mainPanel.getHeight() - 60);
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

        mainPanel.getChildren().addAll(panelTitle, acervoTable);
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
        recCadastro.setHeight(320);
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
        acervoGrid.setPrefHeight(200);
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
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: #A69595;");
            l.setPrefHeight(200*0.15);
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
            l.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: #A6B7B7;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            l.setPrefHeight(220*0.15);
            l.setPrefWidth(300*0.7);
            acervoGrid.getRowConstraints().add(new RowConstraints(220*0.25));
            acervoGrid.getRowConstraints().get(counter).setValignment(VPos.CENTER);
            acervoGrid.add(l, 1, counter, 1, 1);
            counter++;
        }

        Button alugar = new Button("Alugar");
        alugar.setPrefHeight(30);
        alugar.setPrefWidth(160);
        alugar.setLayoutX(151);
        alugar.setLayoutY(330);
        alugar.getStyleClass().add("geral");
        if(material.estado.equals("Alugado") || material.estado.equals("Atrasado") || JsonManager.getMembro(getLogin()).alugado == null){
            alugar.setStyle("-fx-font-weight: bold; -fx-border-color: #e4000f;");
        }else{
            alugar.setStyle("-fx-font-weight: bold; -fx-border-color: #39ff14;");
        }
        
        alugar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if(!material.estado.equals("Alugado") && !material.estado.equals("Atrasado") && JsonManager.getMembro(getLogin()).alugado == null){
                    JsonManager.alugarMaterial(material, login);
                    mainPanel.getChildren().clear();
                    acervo();
                }
            }
        });

        mainPanel.getChildren().addAll(recFundo, recCadastro, x, title, acervoGrid, alugar);
    }

    public void evento(){
        int counter = 0;
        Label panelTitle = this.paneLabel("Eventos");

        TableView<Evento> eventTable = new TableView<>();
        eventTable.setPrefWidth(mainPanel.getWidth() - 20);
        eventTable.setPrefHeight(270);
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

        Button propor = new Button("Propor evento");
        propor.setPrefHeight(30);
        propor.setPrefWidth(160);
        propor.setLayoutX(142);
        propor.setLayoutY(343);
        propor.getStyleClass().add("geral");
        propor.setStyle("-fx-font-weight: bold;");
        propor.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                cadastroRequisicao();
                System.out.println(mainPanel.getChildren().size());
            }
        });

        mainPanel.getChildren().addAll(panelTitle, eventTable, propor);
    }

    public void cadastroRequisicao(){
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

        Label title = new Label("Requisição de Evento");
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
                    forbid.set(1, false);
                }else{
                    c.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(1)){
                        forbid.set(1, true);
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
                    forbid.set(2, false);
                }else{
                    date.setStyle("-fx-border-color: #39ff14;");
                    if(!forbid.get(2)){
                        forbid.set(2, true);
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
                for(int i = 0; i < 3; i++){
                    if(!forbid.get(i)){
                        allow = false;
                    }
                }
                if(allow){
                JsonManager.cadastroReq(new Requisicao(
                    nomeText.getText(),
                    getLogin(),
                    c.getValue(),
                    date.getValue().toString(),
                    ""
                ));
                mainPanel.getChildren().clear();
                evento();

                }else{
                    cadastrar.setStyle("-fx-border-color: #e4000f;");
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
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: left; -fx-background-color: #A69595;");
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
            l.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-text-alignment: center; -fx-background-color: #A6B7B7;");
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