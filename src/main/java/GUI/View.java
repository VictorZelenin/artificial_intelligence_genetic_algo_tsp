package GUI;

import genetic_algortithm_API.elementary_parts.city.City;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class View extends Application {

    private Stage window;
    private Scene initScene, mainScene;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private City[] generatedCities;

    private int quantityOfCities;

    private TextField numberOfCities, numberOfPopulation, numberOfIterations, mutationProbability;
//    private Label citiesLabel, populationLabel, iterationsLabel, mutationProbLabel;


//    public static void main(String[] args) {
//        launch(args);
//    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;


        Button nextButton = new Button("Далі");
        Button exitButton = new Button("Вихід");
        Label label = new Label("Введіть параметри алгоритму :");

        setupMainScene();

        exitButton.setOnAction(e -> Platform.exit());
        nextButton.setOnAction(e -> {
            primaryStage.setScene(mainScene);
            quantityOfCities = Integer.parseInt(numberOfCities.getText());

            setupRandomCities();

            System.out.println(quantityOfCities);

        });


        BorderPane initSceneRoot = new BorderPane();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setVgap(20);

        HBox buttonsPane = new HBox();

        setupButtonsPane(buttonsPane, exitButton, nextButton);


        initSceneRoot.setTop(label);
        initSceneRoot.setCenter(grid);
        initSceneRoot.setBottom(buttonsPane);

        initTextFields();


        grid.getChildren().addAll(numberOfCities, numberOfPopulation, numberOfIterations, mutationProbability);


        initScene = new Scene(initSceneRoot, 700, 550);

        loadCSSFile("style.css");


        primaryStage.setResizable(false);
        primaryStage.setTitle("TSP assignment");
        primaryStage.setScene(initScene);
        primaryStage.show();

    }

    private City[] setupRandomCities() {

        generatedCities = new City[quantityOfCities];

        for (int i = 0; i < quantityOfCities; i++) {
            double x = Math.random() * 940;
            double y = Math.random() * 480;

            new City(x, y, i + 1);

            graphicsContext.beginPath();
            graphicsContext.fillOval(x, y, 6, 6);
            graphicsContext.fillText(String.valueOf(i + 1), x, y); // id , x, y
            graphicsContext.stroke();

        }

        return generatedCities;
    }


    private void setupButtonsPane(HBox buttonsPane, Button firstButton, Button secondButton) {


        buttonsPane.setPadding(new Insets(50));
        buttonsPane.setMargin(firstButton, new Insets(0, 300, 10, 80));
        buttonsPane.getChildren().addAll(firstButton, secondButton);
    }

    private void initTextFields() {

//        Label citiesLabel = new Label("Кількість міст :");
        numberOfCities = new TextField();
        numberOfCities.setPromptText("Кількість міст");
//        GridPane.setConstraints(citiesLabel, 0, 0, 1, 1);
        GridPane.setConstraints(numberOfCities, 0, 0, 1, 1);

        numberOfPopulation = new TextField();
        numberOfPopulation.setPromptText("Велечина популяції");
        GridPane.setConstraints(numberOfPopulation, 0, 1);

        numberOfIterations = new TextField();
        numberOfIterations.setPromptText("Кількість ітерацій");
        GridPane.setConstraints(numberOfIterations, 0, 2);

        mutationProbability = new TextField();
        mutationProbability.setPromptText("Ймовірність мутацій");
        GridPane.setConstraints(mutationProbability, 0, 3, 1, 1);

    }


    private void loadCSSFile(String fileName) throws MalformedURLException {
        File file = new File(fileName);
        URL url = file.toURI().toURL();
        initScene.getStylesheets().add(url.toExternalForm());
    }

    private void setupMainScene() {

        Button exitButton = new Button("Вихід");
        Button startButton = new Button("Старт");

        List<double[]> citiesCoordinates = new ArrayList<>();

        canvas = new Canvas(940, 480);
        graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);

        drawCities(canvas, graphicsContext, citiesCoordinates);
        drawRelationsBetweenCities(startButton, graphicsContext, citiesCoordinates);


        exitButton.setPrefSize(70, 35);
        startButton.setPrefSize(70, 35);

        exitButton.setOnAction(e -> Platform.exit());

        BorderPane root = new BorderPane();

        HBox buttonsPane = new HBox();

        buttonsPane.setPadding(new Insets(50));
        buttonsPane.setMargin(exitButton, new Insets(0, 500, 10, 100));
        buttonsPane.getChildren().addAll(exitButton, startButton);


        root.setBottom(buttonsPane);
        root.setCenter(canvas);


        mainScene = new Scene(root, 950, 580);


    }

    private void initDraw(GraphicsContext gc) {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle

        gc.setFill(Color.CORAL);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(0.5);

    }

    private void drawCities(Canvas canvas, GraphicsContext graphicsContext, List<double[]> citiesCoordinates) {

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    graphicsContext.beginPath();
                    graphicsContext.fillOval(event.getX(), event.getY(), 8, 8);
                    citiesCoordinates.add(new double[]{event.getX() + 4, event.getY() + 4});
                    graphicsContext.stroke();
                });

    }

    private void drawRelationsBetweenCities(Button startButton, GraphicsContext graphicsContext, List<double[]> cities) {
        startButton.setOnAction(event -> {
            for (int i = 0; i < cities.size() - 1; i++) {

                graphicsContext.beginPath();
                graphicsContext.moveTo(cities.get(i)[0], cities.get(i)[1]);
                graphicsContext.lineTo(cities.get(i + 1)[0], cities.get(i + 1)[1]);
                graphicsContext.stroke();
            }
        });

    }


    public City[] getCities() {
        return generatedCities;
    }

    public int getQuantityOfCities() {
        return quantityOfCities;
    }

    public int getNumberOfPopulation() {
        return Integer.parseInt(numberOfPopulation.getText());
    }

    public int getNumberOfIterations() {
        return Integer.parseInt(numberOfIterations.getText());
    }

    public double getMutationProbability() {
        return Double.parseDouble(mutationProbability.getText());
    }
}
