package views;

import java.util.Arrays;

import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import src.Leitor;

public class adminScreenController {
    String state = "homeButton";
    int position = 0;

    @FXML
    private Label homeButton;

    @FXML
    private Label cadastroButton;

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
        Label panelTitle = this.paneLabel("Home");

        Label welcome = new Label("Seja bem-vindo/a :)");
        welcome.setLayoutX(139);
        welcome.setLayoutY(50);
        welcome.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Line line = new Line();
        line.setStartX(152);
        line.setStartY(89);
        line.setEndX(152);
        line.setEndY(89);

        Label tblTitle = new Label("Livros alugados");
        tblTitle.setLayoutX(152);
        tblTitle.setLayoutY(89);
        tblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label evTitle = new Label("Eventos");
        evTitle.setLayoutX(186);
        evTitle.setLayoutY(238);
        evTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TableView<Alugador> tbl = new TableView<>();
        tbl.setPrefWidth(425);
        tbl.setPrefHeight(111);
        tbl.setLayoutX(11);
        tbl.setLayoutY(109);
        tbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Alugador, String> colunaLeitor = new TableColumn<>("Leitor");
        TableColumn<Alugador, String>  colunaLivro = new TableColumn<>("Livro");
        TableColumn<Alugador, String>  colunaEstado = new TableColumn<>("Estado");

        colunaLeitor.setCellValueFactory(new PropertyValueFactory<>("leitor"));
        colunaLivro.setCellValueFactory(new PropertyValueFactory<>("livro"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tbl.getColumns().add(colunaLeitor);
        tbl.getColumns().add(colunaLivro);
        tbl.getColumns().add(colunaEstado);

        // tbl.setItems(listaAlug());
        tbl.getItems().addAll(
            new Alugador("Genivaldo", "A volta dos que não foram", "Disponível"),
            new Alugador("Jurema", "Pooh 2", "Atrasado"),
            new Alugador("Jurema", "Pooh 2", "Atrasado"),
            new Alugador("Jurema", "Pooh 2", "Atrasado"),
            new Alugador("Jurema", "Pooh 2", "Atrasado")
        );

        TableView<Evento> evs = new TableView<>();
        evs.setPrefWidth(425);
        evs.setPrefHeight(111);
        evs.setLayoutX(11);
        evs.setLayoutY(258);
        evs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Evento, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Evento, String>  colunaCategoria = new TableColumn<>("Categoria");
        TableColumn<Evento, String>  colunaData = new TableColumn<>("Data");

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));

        evs.getColumns().add(colunaNome);
        evs.getColumns().add(colunaCategoria);
        evs.getColumns().add(colunaData);

        // tbl.setItems(listaAlug());
        evs.getItems().addAll(
            new Evento("Seminário sobre surdez", "Seminário", "12.03.12"),
            new Evento("Seminário sobre surdez", "Seminário", "12.03.12"),
            new Evento("Seminário sobre surdez", "Seminário", "12.03.12"),
            new Evento("Seminário sobre surdez", "Seminário", "12.03.12"),
            new Evento("Seminário sobre surdez", "Seminário", "12.03.12"),
            new Evento("Seminário sobre surdez", "Seminário", "12.03.12")
        );
        mainPanel.getChildren().addAll(panelTitle, welcome, line, tblTitle, evTitle, tbl, evs);
    }

    public void cadastro(){
        Label panelTitle = this.paneLabel("Cadastro");

        Line line = new Line();
        line.setStartX(152);
        line.setStartY(89);
        line.setEndX(152);
        line.setEndY(89);

        Label nomeLabel = new Label("Nome");
        nomeLabel.setPrefWidth(86);
        nomeLabel.setAlignment(Pos.CENTER);
        TextField nome = new TextField();
        nome.getStyleClass().add("geral");

        Label loginLabel = new Label("Login");
        loginLabel.setPrefWidth(86);
        loginLabel.setAlignment(Pos.CENTER);
        TextField login = new TextField();
        login.getStyleClass().add("geral");

        Label senhaLabel = new Label("Senha");
        senhaLabel.setPrefWidth(86);
        senhaLabel.setAlignment(Pos.CENTER);
        PasswordField senha = new PasswordField();
        senha.getStyleClass().add("geral");

        Label senha2Label = new Label("Confirme a senha");
        senha2Label.setPrefWidth(86);
        senha2Label.setAlignment(Pos.CENTER);
        senha2Label.setWrapText(true);
        senha2Label.setStyle("-fx-text-alignment: center;");
        PasswordField senha2 = new PasswordField();
        senha2.getStyleClass().add("geral");

        Label categoriaLabel = new Label("Categoria");
        categoriaLabel.setPrefWidth(86);
        categoriaLabel.setAlignment(Pos.CENTER);
        ChoiceBox<String> categoria = new ChoiceBox<>();
        categoria.getItems().addAll("Bibliotecário","Leitor");
        categoria.getStyleClass().add("geral");
        categoria.setPrefWidth(314);
        categoria.setStyle("-fx-text-alignment:center;");

        GridPane grid = new GridPane();
        grid.setLayoutX(22);
        grid.setLayoutY(59);
        grid.setPrefWidth(400);
        grid.setPrefHeight(215);
        
        grid.add(nomeLabel, 0, 0, 1, 1);
        grid.add(loginLabel, 0, 1, 1, 1);
        grid.add(senhaLabel, 0, 2, 1, 1);
        grid.add(senha2Label, 0, 3, 1, 1);
        grid.add(categoriaLabel, 0, 4, 1, 1);
        grid.add(nome, 1, 0, 1, 1);
        grid.add(login, 1, 1, 1, 1);
        grid.add(senha, 1, 2, 1, 1);
        grid.add(senha2, 1, 3, 1, 1);
        grid.add(categoria, 1, 4, 1, 1);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(30);
        c1.setHalignment(HPos.CENTER);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(70);
        c2.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().addAll(c1, c2);
        RowConstraints r = new RowConstraints();
        int numRows = grid.getRowCount();
        r.setPercentHeight(100 / grid.getRowCount());
        r.setValignment(VPos.CENTER);
        for(int i = 0; i < numRows; i++){
            grid.getRowConstraints().add(r);
        }

        Rectangle rec = new Rectangle();
        rec.setLayoutX(14);
        rec.setLayoutY(52);
        rec.setWidth(421);
        rec.setHeight(308);
        rec.getStyleClass().add("rec");

        Button reg = new Button("Registrar");
        reg.setLayoutX(162);
        reg.setLayoutY(302);
        reg.setPrefWidth(129);
        reg.setPrefHeight(35);
        reg.getStyleClass().addAll("but", "geral");

        mainPanel.getChildren().addAll(panelTitle, rec, grid, reg);

    }

    public void membros(){
        double gridWidth = mainPanel.getWidth() - 20;
        double mainPanelHeight = mainPanel.getHeight();
        Label panelTitle = this.paneLabel("Membros");
        int counter = 0;

        GridPane mainGrid = new GridPane();
        mainGrid.setPrefWidth(gridWidth);
        mainGrid.setLayoutX(10);
        mainGrid.setLayoutY(50);
        mainGrid.setStyle("-fx-background-color: #464A50;");
        mainGrid.getRowConstraints().addAll(
            new RowConstraints(20), 
            new RowConstraints(mainPanelHeight-90));
        mainGrid.getColumnConstraints().addAll(
            new ColumnConstraints(gridWidth/4),
            new ColumnConstraints(gridWidth/4),
            new ColumnConstraints(gridWidth/2));
        for (Label l: Arrays.asList( new Label("Nome"), new Label("Login"), new Label("Livros atrasados"))) {
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-text-fill: #FFF8E7; -fx-font-weight: bold; -fx-font-size: 13px;");
            l.setPrefHeight(30);
            mainGrid.add(l, counter, 0, 1, 1);
            counter++;
        }
        for(ColumnConstraints c: mainGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        ScrollPane scroll = new ScrollPane();
        scroll.pannableProperty().set(true);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
        
        GridPane membersGrid = new GridPane();
        membersGrid.getStyleClass().add("grid");
        membersGrid.setStyle("-fx-grid-lines-visible: true; -fx-background-color: #A6A6A6");
        membersGrid.getColumnConstraints().addAll(
            new ColumnConstraints(gridWidth/4),
            new ColumnConstraints(gridWidth/4),
            new ColumnConstraints(gridWidth/2));
        for(ColumnConstraints c: membersGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }
        for (int i = 0; i < 20; i++) {
            Label l1 = new Label(Integer.toString(4*i));
            Label l2 = new Label(Integer.toString(4*i + 1));
            Label l4 = new Label(Integer.toString(4*i + 2));
            Label l3 = new Label("A Linguagem de Programação Go");
            for (Label l: Arrays.asList( l1, l2, l3, l4 )) {
                // l.setPrefHeight(40);
                l.setWrapText(true);
                // l.setAlignment(Pos.CENTER);
                l.setStyle("-fx-font-size: 12px; -fx-background-color: violet; -fx-text-alignment: center;");
            }
            
            GridPane bookGrid = new GridPane();
            bookGrid.setPrefWidth(gridWidth/2);
            bookGrid.setStyle("-fx-background-color: turquoise; -fx-grid-lines-visible: true;");
            ColumnConstraints c1 = new ColumnConstraints();
            c1.setPercentWidth(60);
            c1.setHalignment(HPos.CENTER);
            ColumnConstraints c2 = new ColumnConstraints();
            c2.setPercentWidth(40);
            c2.setHalignment(HPos.CENTER);
            bookGrid.getColumnConstraints().addAll(c1, c2);
            for(ColumnConstraints c: bookGrid.getColumnConstraints()){
                c.setHalignment(HPos.CENTER);
            }            
            bookGrid.add(l3, 0, i, 1, 1);
            bookGrid.add(l4, 1, i, 1, 1);
            membersGrid.add(l1, 0, i, 1, 1);
            membersGrid.add(l2, 1, i, 1, 1);
            membersGrid.add(bookGrid, 2, i, 1, 1);
        }

        

        scroll.setContent(membersGrid);
        mainGrid.add(scroll, 0, 1, 3, 1);
        mainPanel.getChildren().addAll(panelTitle, mainGrid);
    }

    public void acervo(){
        double gridWidth = mainPanel.getWidth() - 20;
        Label panelTitle = this.paneLabel("Acervo");
        int counter = 0;

        GridPane mainAcervoGrid = new GridPane();
        mainAcervoGrid.setPrefWidth(gridWidth);
        mainAcervoGrid.setPrefHeight(270);
        
        mainAcervoGrid.setLayoutX(10);
        mainAcervoGrid.setLayoutY(50);
        mainAcervoGrid.setStyle("-fx-background-color: #464A50;");
        mainAcervoGrid.getRowConstraints().addAll(
            new RowConstraints(20), 
            new RowConstraints(250)
        );
        mainAcervoGrid.getColumnConstraints().addAll(
            new ColumnConstraints(gridWidth/6),
            new ColumnConstraints(gridWidth/2),
            new ColumnConstraints(gridWidth/3)
        );
        for (Label l: Arrays.asList( 
            new Label("Código"), 
            new Label("Nome"), 
            new Label("Categoria"))
        ){
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-text-fill: #FFF8E7; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: center;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            mainAcervoGrid.add(l, counter, 0, 1, 1);
            counter++;
        }
        for(ColumnConstraints c: mainAcervoGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        ScrollPane mainScroll = new ScrollPane();
        mainScroll.pannableProperty().set(true);
        mainScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        mainScroll.setVbarPolicy(ScrollBarPolicy.NEVER);

        GridPane books = new GridPane();
        books.setPrefHeight(270);
        books.setStyle("-fx-grid-lines-visible: true; -fx-background-color: #A6A6A6");
        books.getColumnConstraints().addAll(
            new ColumnConstraints(gridWidth/6),
            new ColumnConstraints(gridWidth/2),
            new ColumnConstraints(gridWidth/3));
        for(ColumnConstraints c: books.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }
        mainScroll.setContent(books);
        mainAcervoGrid.add(mainScroll, 0, 1, 3, 1);

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
                cadastroMaterial();
                System.out.println(mainPanel.getChildren().size());
            }
        });

        mainPanel.getChildren().addAll(panelTitle, mainAcervoGrid, cadastro);
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

    public void evento(){
        double gridWidth = mainPanel.getWidth() - 20;
        Label panelTitle = this.paneLabel("Eventos");
        int counter = 0;

        GridPane mainEventsGrid = new GridPane();
        mainEventsGrid.setPrefWidth(gridWidth);
        mainEventsGrid.setPrefHeight(150);
        
        mainEventsGrid.setLayoutX(10);
        mainEventsGrid.setLayoutY(50);
        mainEventsGrid.setStyle("-fx-background-color: #464A50;");
        mainEventsGrid.getRowConstraints().addAll(
            new RowConstraints(20), 
            new RowConstraints(130)
        );
        mainEventsGrid.getColumnConstraints().addAll(
            new ColumnConstraints(gridWidth/2),
            new ColumnConstraints(gridWidth/3),
            new ColumnConstraints(gridWidth/6)
        );
        for (Label l: Arrays.asList( 
            new Label("Nome"), 
            new Label("Categoria"), 
            new Label("Data"))
        ){
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-text-fill: #FFF8E7; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: center;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            mainEventsGrid.add(l, counter, 0, 1, 1);
            counter++;
        }
        for(ColumnConstraints c: mainEventsGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }

        ScrollPane mainScroll = new ScrollPane();
        mainScroll.pannableProperty().set(true);
        mainScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        mainScroll.setVbarPolicy(ScrollBarPolicy.NEVER);

        GridPane currentEvents = new GridPane();
        currentEvents.setPrefHeight(130);
        currentEvents.setStyle("-fx-grid-lines-visible: true; -fx-background-color: #A6A6A6");
        currentEvents.getColumnConstraints().addAll(
            new ColumnConstraints(gridWidth/2),
            new ColumnConstraints(gridWidth/3),
            new ColumnConstraints(gridWidth/6));
        for(ColumnConstraints c: currentEvents.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }
        mainScroll.setContent(currentEvents);
        mainEventsGrid.add(mainScroll, 0, 1, 3, 1);

        // Requisições
        GridPane mainRequestGrid = new GridPane();
        mainRequestGrid.setPrefWidth(gridWidth);
        mainRequestGrid.setMaxWidth(gridWidth);
        mainRequestGrid.setPrefHeight(100);
        mainRequestGrid.setLayoutX(10);
        mainRequestGrid.setLayoutY(220);
        mainRequestGrid.setStyle("-fx-background-color: #464A50;");
        mainRequestGrid.getRowConstraints().addAll(
            new RowConstraints(20), 
            new RowConstraints(80));
        mainRequestGrid.getColumnConstraints().addAll(
            new ColumnConstraints(gridWidth/3),
            new ColumnConstraints(gridWidth/2),
            new ColumnConstraints(gridWidth/6));
        counter = 0;
        for (Label l: Arrays.asList( 
            new Label("Requisitante"), 
            new Label("Nome"), 
            new Label("Categoria")
            ))
        {
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-text-fill: #FFF8E7; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: center;");
            l.setPrefHeight(20);
            l.setWrapText(true);
            mainRequestGrid.add(l, counter, 0, 1, 1);
            counter++;
            System.out.println(counter);
        }
        for(ColumnConstraints c: mainRequestGrid.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }
        ScrollPane requestScroll = new ScrollPane();
        requestScroll.pannableProperty().set(true);
        requestScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        requestScroll.setVbarPolicy(ScrollBarPolicy.NEVER);

        GridPane eventsRequests = new GridPane();
        eventsRequests.setPrefHeight(80);
        eventsRequests.setStyle("-fx-grid-lines-visible: true; -fx-background-color: #A6A6A6");
        eventsRequests.getColumnConstraints().addAll(
            new ColumnConstraints(gridWidth/3),
            new ColumnConstraints(gridWidth/2),
            new ColumnConstraints(gridWidth/6));
        for(ColumnConstraints c: eventsRequests.getColumnConstraints()){
            c.setHalignment(HPos.CENTER);
        }
        requestScroll.setContent(eventsRequests);
        mainRequestGrid.add(requestScroll, 0, 1, 3, 1);

        Label l1 = new Label("1");
        Label l2 = new Label("2");
        Label l3 = new Label("3");

        RowConstraints r = new RowConstraints();
        r.setFillHeight(true);
        currentEvents.getRowConstraints().add(r);

        currentEvents.add(l1,0,0,1,1);
        currentEvents.add(l2,1,0,1,1);
        currentEvents.add(l3,2,0,1,1);

        Label l4 = new Label("4");
        Label l5 = new Label("5");
        Label l6 = new Label("6");

        eventsRequests.add(l4,0,0,1,1);
        eventsRequests.add(l5,1,0,1,1);
        eventsRequests.add(l6,2,0,1,1);

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

        mainPanel.getChildren().addAll(panelTitle, mainEventsGrid, mainRequestGrid, cadastro);
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
            new Label("Descrição"),
            new Label("Data")
            )
        ){
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 11px; -fx-text-alignment: center;");
            l.setWrapText(true);
            cadastroGrid.getRowConstraints().add(new RowConstraints(220*0.2));
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

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
        @Override 
        public void handle(MouseEvent e) { 
            System.out.println("Apertei o botão");  
        } 
    }; 

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
                    // a.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                    break;
                case "cadastroButton":
                    this.cadastro();
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
