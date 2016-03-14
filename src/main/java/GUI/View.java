package GUI;

import genetic_algortithm_API.elementary_parts.city.City;
import genetic_algortithm_API.genetic_algorithm.implementations_of_GA.CanonicalGeneticAlgorithm;
import genetic_algortithm_API.genetic_algorithm.implementations_of_GA.ElitistGeneticAlgorithm;
import genetic_algortithm_API.genetic_algorithm.interface_of_GA.GeneticAlgorithm;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing.SinglePointCrossing;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing.TwoPointsCrossing;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.mutation.GreedyMutation;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.mutation.SinglePointMutation;
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
import javafx.scene.control.*;
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


    private static int counter = 0;
    private Button startButton, nextButton;

    private int quantityOfCities, sizeOfPopulation, iterations;
    private double probability;

    private String selectionType, crossingType, mutationType, typeOfGeneratingCities;

    private ComboBox<String> typeOfSelection, typeOfCrossing, typeOfMutation;
    private ToggleGroup toggleGroup;
    private RadioButton initRandomCities, initCities;

    private TextField numberOfCities, numberOfPopulation, numberOfIterations, mutationProbability;


    // starting point
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;


        nextButton = new Button("Далі");
        Button exitButton = new Button("Вихід");
        Label label = new Label("Введіть параметри алгоритму :");

        setupMainScene();

        exitButton.setOnAction(e -> Platform.exit());
        nextButton.setOnAction(e -> {

            try {
                quantityOfCities = Integer.parseInt(numberOfCities.getText());
                probability = Double.parseDouble(mutationProbability.getText());
                iterations = Integer.parseInt(numberOfIterations.getText());
                sizeOfPopulation = Integer.parseInt(numberOfPopulation.getText());

                if (selectionType == null || crossingType == null || mutationType == null || typeOfGeneratingCities == null
                        || quantityOfCities < 3 || probability > 1) {
                    throw new Exception();
                }

                if (typeOfGeneratingCities.equals("Random")) {
                    setupRandomCities();
                } else {
//                    primaryStage.setScene(mainScene);
                    drawCities(canvas, graphicsContext);
                }

                primaryStage.setScene(mainScene);
            } catch (Exception exception) {
                Alert initAlert = new Alert(Alert.AlertType.WARNING);
                initAlert.setTitle("Попередження");
                initAlert.setHeaderText("Погані вхідні параметри! ");
                initAlert.setContentText("Перевірте введені дані.");

                initAlert.showAndWait();
            }
//
//            setupRandomCities();
//

            System.out.println(quantityOfCities);
            System.out.println(probability);
            System.out.println(iterations);
            System.out.println(sizeOfPopulation);
            System.out.println(selectionType);
            System.out.println(crossingType);
            System.out.println(mutationType);
            System.out.println(typeOfGeneratingCities);


        });


        BorderPane initSceneRoot = new BorderPane();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setPadding(new Insets(20));
        grid.setVgap(20);

        HBox buttonsPane = new HBox();

        setupButtonsPane(buttonsPane, exitButton, nextButton);


        initSceneRoot.setTop(label);
        initSceneRoot.setCenter(grid);
        initSceneRoot.setBottom(buttonsPane);

        initTextFields();
        initComboboxes();
        initRadioButtons();


        grid.getChildren().addAll(numberOfCities, numberOfPopulation, numberOfIterations, mutationProbability,
                typeOfSelection, typeOfCrossing, typeOfMutation, initRandomCities, initCities);


        initScene = new Scene(initSceneRoot, 700, 550);

        loadCSSFile("style.css");


        primaryStage.setResizable(false);
        primaryStage.setTitle("TSP assignment");
        primaryStage.setScene(initScene);
        primaryStage.show();

    }

    private void setupRandomCities() {

        generatedCities = new City[quantityOfCities];

        for (int i = 0; i < quantityOfCities; i++) {
            double x = Math.random() * 940;
            double y = Math.random() * 480;

            generatedCities[i] = new City(x + 3, y + 3, i + 1);

            graphicsContext.beginPath();
            graphicsContext.fillOval(x, y, 6, 6);
            graphicsContext.fillText(String.valueOf(i + 1), x, y); // id , x, y
            graphicsContext.stroke();

        }


    }


    private void setupButtonsPane(HBox buttonsPane, Button firstButton, Button secondButton) {


        buttonsPane.setPadding(new Insets(50));
        buttonsPane.setMargin(firstButton, new Insets(0, 300, 10, 80));
        buttonsPane.getChildren().addAll(firstButton, secondButton);
    }

    private void initTextFields() {


        numberOfCities = new TextField();
        numberOfCities.setPromptText("Кількість міст");
        numberOfCities.setFocusTraversable(false);
        GridPane.setConstraints(numberOfCities, 0, 0, 1, 1);

        numberOfPopulation = new TextField();
        numberOfPopulation.setPromptText("Велечина популяції");
        numberOfPopulation.setFocusTraversable(false);
        GridPane.setConstraints(numberOfPopulation, 0, 1);

        numberOfIterations = new TextField();
        numberOfIterations.setPromptText("Кількість ітерацій");
        numberOfIterations.setFocusTraversable(false);
        GridPane.setConstraints(numberOfIterations, 0, 2);

        mutationProbability = new TextField();
        mutationProbability.setPromptText("Ймовірність мутацій");
        mutationProbability.setFocusTraversable(false);
        GridPane.setConstraints(mutationProbability, 0, 3, 1, 1);

    }

    private void initComboboxes() {

        typeOfSelection = new ComboBox<>();
        typeOfSelection.setPromptText("Оберіть оператор відбору.");
        typeOfSelection.getItems().addAll("Елітарний відбір.");
        GridPane.setConstraints(typeOfSelection, 0, 4);

        typeOfSelection.setOnAction(e -> selectionType = typeOfSelection.getValue());

        typeOfCrossing = new ComboBox<>();
        typeOfCrossing.setPromptText("Оберіть оператор селекції.");
        typeOfCrossing.getItems().addAll("Одноточковий кросовер.", "Двоточковий кросовер.");
        GridPane.setConstraints(typeOfCrossing, 0, 5);

        typeOfCrossing.setOnAction(e -> crossingType = typeOfCrossing.getValue());

        typeOfMutation = new ComboBox<>();
        typeOfMutation.setPromptText("Оберіть оператор мутації.");
        typeOfMutation.getItems().addAll("Одноточкова мутація.", "Жадібна мутація.");
        GridPane.setConstraints(typeOfMutation, 0, 6);

        typeOfMutation.setOnAction(e -> mutationType = typeOfMutation.getValue());


    }

    private void initRadioButtons() {
        toggleGroup = new ToggleGroup();

        initRandomCities = new RadioButton("Розмістити міста випадковим чином.");
        initRandomCities.setUserData("Random");

        initCities = new RadioButton("Самостійно розмістити міста.");
        initCities.setUserData("Custom");

        initRandomCities.setToggleGroup(toggleGroup);
        initCities.setToggleGroup(toggleGroup);

        GridPane.setConstraints(initRandomCities, 0, 7);
        GridPane.setConstraints(initCities, 0, 8);

        toggleGroup.selectedToggleProperty().addListener(e -> {
            typeOfGeneratingCities = (String) toggleGroup.getSelectedToggle().getUserData();
        });
    }


    private void loadCSSFile(String fileName) throws MalformedURLException {
        File file = new File(fileName);
        URL url = file.toURI().toURL();
        initScene.getStylesheets().add(url.toExternalForm());
    }

    private void setupMainScene() {

        Button exitButton = new Button("Вихід");
        startButton = new Button("Старт");

        List<double[]> citiesCoordinates = new ArrayList<>();

        canvas = new Canvas(940, 480);
        graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);

//        drawCities(canvas, graphicsContext, citiesCoordinates);
//        drawRelationsBetweenCities(startButton, graphicsContext, citiesCoordinates);


        exitButton.setPrefSize(70, 35);
        startButton.setPrefSize(70, 35);

        exitButton.setOnAction(e -> Platform.exit());
        // rewrite
        startButton.setOnAction(e -> {
            ElitistGeneticAlgorithm ga = null;


            try {
                if (crossingType.equals("Одноточковий кросовер.") && mutationType.equals("Одноточкова мутація.")) {
                    ga = new ElitistGeneticAlgorithm(getSizeOfPopulation(), getProbability(),
                            getIterations(), getCities(), new SinglePointCrossing(), new SinglePointMutation());

                }
                if (crossingType.equals("Одноточковий кросовер.") && mutationType.equals("Жадібна мутація.")) {
                    ga = new ElitistGeneticAlgorithm(getSizeOfPopulation(), getProbability(),
                            getIterations(), getCities(), new SinglePointCrossing(), new GreedyMutation());

                }
                if (crossingType.equals("Двоточковий кросовер.") && mutationType.equals("Одноточкова мутація.")) {

                    ga = new ElitistGeneticAlgorithm(getSizeOfPopulation(), getProbability(),
                            getIterations(), getCities(), new TwoPointsCrossing(), new SinglePointMutation());
                }
                if (crossingType.equals("Двоточковий кросовер.") && mutationType.equals("Жадібна мутація.")) {
                    ga = new ElitistGeneticAlgorithm(getSizeOfPopulation(), getProbability(),
                            getIterations(), getCities(), new TwoPointsCrossing(), new GreedyMutation());
                }


            } catch (Exception e1) {
                e1.printStackTrace();
            }
            drawRelationsBetweenCities(ga);


        });

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


    private void drawCities(Canvas canvas, GraphicsContext graphicsContext) {

        generatedCities = new City[quantityOfCities];


        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    if (counter == quantityOfCities) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Помилка");
                        alert.setHeaderText("Забагато міст.");
                        alert.setContentText("Всі міста вже сформовані, тисність старт!");

                        alert.showAndWait();

                    } else {
                        graphicsContext.beginPath();
                        graphicsContext.fillOval(event.getX(), event.getY(), 6, 6);
                        graphicsContext.fillText(String.valueOf(counter + 1), event.getX(), event.getY());
                        generatedCities[counter] = new City(event.getX() + 3, event.getY() + 3, counter + 1);
                        counter++;
                        graphicsContext.stroke();

                    }
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

    private void drawRelationsBetweenCities(ElitistGeneticAlgorithm ga) {

        int[] answer = ga.getResult().getPhenotype();


        for (int i = 0; i < answer.length; i++) {

            if (i == answer.length - 1) {
                graphicsContext.beginPath();
//                    graphicsContext.moveTo(generatedCities[answer[i] - 1].getX(), generatedCities[answer[i] - 1].getY());
                graphicsContext.moveTo(generatedCities[answer[i] - 1].getX(), generatedCities[answer[i] - 1].getY());
                graphicsContext.lineTo(generatedCities[answer[0] - 1].getX(), generatedCities[answer[0] - 1].getY());
                graphicsContext.stroke();
            } else {
                graphicsContext.beginPath();
                graphicsContext.moveTo(generatedCities[answer[i] - 1].getX(), generatedCities[answer[i] - 1].getY());
                graphicsContext.lineTo(generatedCities[answer[i + 1] - 1].getX(), generatedCities[answer[i + 1] - 1].getY());
                graphicsContext.stroke();
            }

        }

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

    public Button getStartButton() {
        return startButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public Stage getWindow() {
        return window;
    }

    public int getSizeOfPopulation() {
        return sizeOfPopulation;
    }

    public int getIterations() {
        return iterations;
    }

    public double getProbability() {
        return probability;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public String getCrossingType() {
        return crossingType;
    }

    public String getMutationType() {
        return mutationType;
    }
}

