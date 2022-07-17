package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class HelloController {
    @FXML
    private Label label1, storyLine, numCrowns, numGems, nameLbl, beforeLbl, afterLbl, heroNameLbl, avCount, troupeLbl, currentName, currentStats,
            avNameLbl, attackLbl, speedLbl, defenseLbl, resistanceLbl, lvlLbl, gWonLbl, cWonLbl, resLbl, cLvlLbl, costLbl;

    @FXML
    private TextField enterInfo, lvlUp;

    @FXML
    private Button newBut, resBut, summonBut, battleBut1, battleBut2, battleBut3, battleBut4, battleBut, lvl1But, lvl2But, lvl3But,
            lvl4But, lvl5But, lvl6But, lvl7But, lvl8But, lvl9But, computerTurnBtn, buyBtn;

    @FXML
    private AnchorPane storyPane, mainPane, summonPane, explanationPane, summonHeroPane, alliesPane, mapPane, levelPane, battlePane,
    clickedPane, resPane, levelUpPane;

    @FXML
    private ImageView starImg, avatarImg, weaponImg, summonImg1, summonImg2, summonImg3, summonImg4, summonImg5, img00, img01,
    img02, img03, img04, img05, img06, img07, img08, img09, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19,
    img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31, img32, img33, img34, img35, img36, img37,
    img38, img39, rate00, rate01, rate02, rate03, rate04, rate05, rate06, rate07, rate08, rate09, rate10, rate11, rate12, rate13, rate14,
    rate15, rate16, rate17, rate18, rate19, rate20, rate21, rate22, rate23, rate24, rate25, rate26, rate27, rate28, rate29, rate30, rate31,
    rate32, rate33, rate34, rate35, rate36, rate37, rate38, rate39, type00, type01, type02, type03, type04, type05, type06, type07, type08,
    type09, type10, type11, type12, type13, type14, type15, type16, type17, type18, type19, type20, type21, type22, type23, type24, type25,
    type26, type27, type28, type29, type30, type31, type32, type33, type34, type35, type36, type37, type38, type39, slot0, slot1,
    slot2, slot3, leftArrow, rightArrow, currentAv, currentRate, currentType, avClicked, typeClicked, type1Img, type2Img, type3Img, type4Img;

    @FXML
    private Circle circle1, circle2, circle3, circle4, circle5;

    @FXML
    private GridPane battleGrid;

    @FXML
    private ProgressBar hpBar;

    @FXML
    private ListView eventList;

    Effect shade = new Lighting();
    Node shaded;
    String name;
    int gems = 25;
    int crowns = 100;
    int fiveStar = (int)(Math.random()*3)+12;
    int fourStar = (int)(Math.random()*3)+29;
    int threeStar = 60 - fiveStar - fourStar;
    ArrayList<String> imgNames = new ArrayList<>();
    ArrayList<Weapon> allWeapons = new ArrayList<>();
    ArrayList<Hero> allHeroes = new ArrayList<>();
    ArrayList<Hero> myHeroes = new ArrayList<>();
    ArrayList<String> girlNames  = new ArrayList<>();
    ArrayList<String> boyNames  = new ArrayList<>();
    ArrayList<String>  types = new ArrayList<>();
    ImageView[] avImgs;
    ImageView[] starImgs;
    ImageView[] typeImgs;
    ImageView[] troupeSlots;
    Hero[][] troupes = new Hero[5][4];
    Effect light = new Glow();
    Button[] battleButs;
    Button[] lvlButs;
    boolean[][] levelUnlocked;
    Button[][] btn = new Button[9][7];
    ImageView[][] images = new ImageView[9][7];
    int[][] terrainGrid = new int[9][7];
    int[][] avatarGrid = new int[9][7];
    boolean[][] canMove = new boolean[9][7];
    boolean[][] canAttack = new boolean[9][7];
    boolean[][] canHeal = new boolean[9][7];
    ArrayList<Hero> enemyUnits;
    ArrayList<Hero> troupeUnits;
    boolean[] turnOver = new boolean[4];
    ImageView[] enemyTypes;
    boolean first = true;

    public HelloController(){
        allHeroes.clear();
        myHeroes.clear();
        girlNames.clear();
        boyNames.clear();
        types.clear();
        Collections.addAll(girlNames, "Florithe", "Shardi", "Emera", "Eloria", "Brie", "Lira", "Tesfira", "Kayara",
                            "Olenor", "Ligh", "Raani", "Raelle", "Peri", "Palisia", "Lilyi", "Artemis", "Astoria", "Bellatrix",
                            "Fleur", "Arya", "Nymphadora", "Pansy", "Cersei", "Shae", "Fiora", "Celica", "Athena", "Sonya", "Lilina", "Thea");
        Collections.addAll(boyNames, "Brunaulf", "Hari", "Parth", "Angar", "Balun", "Hadwyn", "Alde", "Jyn", "Alathic",
                            "Dray", "Hywell", "Caloman", "Draco", "Emmett", "Tywin", "Xion", "Jai", "Callum", "Askr", "Kondor",
                            "Alfonse", "Conrad", "Sigurd", "Astram", "Abel", "Cain", "Kris", "Python", "Roy", "Raven");

        Collections.addAll(types, "red", "blue", "green", "gray");

        imgNames.clear();
        for (int i = 1; i <= 30; i++) {
            imgNames.add("boy" + i + ".png");
            imgNames.add("girl" + i + ".png");
        }

        newWeapons();
        newHeroes(fiveStar, 65, 55, 5, true);
        newHeroes(fourStar, 55, 47, 4, true);
        newHeroes(threeStar, 48, 40, 3, true);
    }

    @FXML
    public void shadeBut(MouseEvent e){
        shaded = e.getPickResult().getIntersectedNode();
        shaded.setEffect(shade);
    }

    @FXML
    public void unshadeBut(MouseEvent e){
        shaded.setEffect(null);
    }

    @FXML
    public void newGame() {
        enemyUnits = new ArrayList<>(4);
        troupeUnits = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            enemyUnits.add(null);
            troupeUnits.add(null);
        }
        System.out.println(enemyUnits);
        System.out.println(troupeUnits);
        levelUnlocked = new boolean[][]{{true, false, false, false, false, false, false, false, false},
                                        {true, false, false, false, false, false, false, false, false},
                                        {true, false, false, false, false, false, false, false, false},
                                        {true, false, false, false, false, false, false, false, false}};

        battleButs = new Button[]{battleBut1, battleBut2, battleBut3, battleBut4};
        lvlButs = new Button[]{lvl1But, lvl2But, lvl3But, lvl4But, lvl5But, lvl6But, lvl7But, lvl8But, lvl9But};

        avImgs = new ImageView[]{img00, img01, img02, img03, img04, img05, img06, img07, img08, img09, img10, img11, img12, img13, img14,
                img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31,
                img32, img33, img34, img35, img36, img37, img38, img39};

        starImgs = new ImageView[]{rate00, rate01, rate02, rate03, rate04, rate05, rate06, rate07, rate08, rate09, rate10, rate11, rate12, rate13, rate14,
                rate15, rate16, rate17, rate18, rate19, rate20, rate21, rate22, rate23, rate24, rate25, rate26, rate27, rate28, rate29, rate30, rate31,
                rate32, rate33, rate34, rate35, rate36, rate37, rate38, rate39};

        typeImgs = new ImageView[]{type00, type01, type02, type03, type04, type05, type06, type07, type08, type09, type10, type11, type12,
                type13, type14, type15, type16, type17, type18, type19, type20, type21, type22, type23, type24, type25, type26, type27, type28,
                type29, type30, type31, type32, type33, type34, type35, type36, type37, type38, type39};

        troupeSlots = new ImageView[]{slot0, slot1, slot2, slot3};

        enemyTypes = new ImageView[]{type1Img, type2Img, type3Img, type4Img};

        for (ImageView e: typeImgs) {
            e.setEffect(light);
        }

        storyPane.setVisible(true);

        //setting up gridpane for battle
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {

                //Initializing 2D buttons with values i,j
                btn[i][j] = new Button();
                btn[i][j].setStyle("-fx-background-color:#d3d3d3");

                btn[i][j].setMinWidth(50);
                btn[i][j].setMinHeight(49);

                btn[i][j].setMaxWidth(49);
                btn[i][j].setMaxHeight(49);
                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        move(t);
                    }
                });
                //Paramters:  object, columns, rows
                battleGrid.add(btn[i][j], j, i);
//                gameGrid[i][j] = 0;

                //initializing images
                images[i][j] = new ImageView();
                images[i][j].setFitHeight(51);
                images[i][j].setFitWidth(51);
                images[i][j].setId("grid" + i + j);
                images[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        updateInfoPane(t);
                    }
                });

//                FileInputStream input = null;
//                try {
//                    input = new FileInputStream("src/main/resources/mountain.png");
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                images[i][j].setImage(new Image(input));
                battleGrid.add(images[i][j], j, i);
            }
        }

        storyPane.setVisible(true);
    }

    int part = 0;
    public void nextPart() {
        String[] lineArray = {"I am Frey, the commander of the Union of Good. The union once consisted of 5 kingdoms: Alynthi, Amaris, Oryn, Lichelle, and Nethilor.",
                              "To our despair, a few centuries ago, the empire of Nethilor was overtaken by an evil faction: the Union of Evil.",
                              "The Union of Evil stole the Divine Relic from the Kingdom of Alynthi. The Divine Relic is an artifact that is used in order to summon heroes.",
                              "Every century, a summoner who had the power to wield the Divine Relic would appear as long as the Relic was in good hands.",
                              "Despite their utmost efforts, they failed to summon heroes using the Divine Relic. This led to the Union of Evil turning to vicious methods.",
                              "They began capturing the people of our kingdoms and sacrificing their spirits in order to gain the spirit of a hero.",
                              "Summoner, it is your duty to save the 5 kingdoms and restore peace among our land."};
        if(part == 0){
            name = enterInfo.getText();
            enterInfo.setVisible(false);
        }
        storyLine.setText(lineArray[part]);
        part++;
        if(part == lineArray.length){
            storyPane.setVisible(false);
            setMainPane();
        }
    }

    public void setMainPane(){
        battlePane.setVisible(false);
        resPane.setVisible(false);
        mapPane.setVisible(false);
        summonPane.setVisible(false);
        alliesPane.setVisible(false);
        mainPane.setVisible(true);
        numGems.setText(String.valueOf(gems));
        numCrowns.setText(String.valueOf(crowns));
        nameLbl.setText(name);
    }

    ArrayList<Hero> chosenHeroes = new ArrayList<>();
    public void setSummonPane(){
        ImageView[] summonImgs = {summonImg1, summonImg2, summonImg3, summonImg4, summonImg5};
        Circle[] circles = {circle1, circle2, circle3, circle4, circle5};

        summonCount = 0;
        chosenHeroes.clear();
        mapPane.setVisible(false);
        summonPane.setVisible(true);
        summonBut.setDisable(true);
        alliesPane.setVisible(false);

        if(summonCount == 0){
            beforeLbl.setText(String.valueOf(gems));
            afterLbl.setText(String.valueOf(gems-5));
        } else if (summonCount == 1 || summonCount == 2){
            beforeLbl.setText(String.valueOf(gems));
            afterLbl.setText(String.valueOf(gems-4));
        } else {
            beforeLbl.setText(String.valueOf(gems));
            afterLbl.setText(String.valueOf(gems-3));
        }
        //5% for 5-star / 55% for 4-star / 40% for 3-star
        //summons 5 times in order to create full summoning circle
        //if a chosen hero is already chosen then it will choose again until there is a new hero
        for (int i = 0; i < 5; i++) {
            int chance = randomNumber(1, 100);
            int range;
            if(chance <= 5){
                range = randomNumber(0, fiveStar-1);
                while(chosenHeroes.contains(allHeroes.get(range))){
                    range = randomNumber(0, fiveStar-1);
                }
                chosenHeroes.add(allHeroes.get(range));
            } else if(chance <= 60){
                range = randomNumber(fiveStar, fourStar+fiveStar-1);
                while(chosenHeroes.contains(allHeroes.get(range))){
                    range = randomNumber(fiveStar, fourStar+fiveStar-1);
                }
                chosenHeroes.add(allHeroes.get(range));
            } else {
                range = randomNumber(60-threeStar, 59);
                while(chosenHeroes.contains(allHeroes.get(range))){
                    range = randomNumber(60-threeStar, 59);
                }
                chosenHeroes.add(allHeroes.get(range));
            }
        }

        //sets colors to circles
        for (int i = 0; i < 5; i++) {
            if(chosenHeroes.get(i).getWeapon().getType().equals("red")){
                circles[i].setFill(Color.ORANGERED);
            } else if (chosenHeroes.get(i).getWeapon().getType().equals("blue")){
                circles[i].setFill(Color.LIGHTBLUE);
            } else if (chosenHeroes.get(i).getWeapon().getType().equals("green")){
                circles[i].setFill(Color.LIGHTGREEN);
            } else {
                circles[i].setFill(Color.LIGHTGREY);
            }
        }

        //sets all images to invisible
        for (int i = 0; i < 5; i++) {
            summonImgs[i].setVisible(false);
        }
    }

    public void setAlliesPane(){
        mapPane.setVisible(false);
        alliesPane.setVisible(true);
        for (ImageView a: starImgs) {
            a.setVisible(false);
        }
        for (ImageView a: typeImgs) {
            a.setVisible(false);
        }

        for (int i = 0; i < myHeroes.size(); i++) {
            try{
                avImgs[i].setImage(myHeroes.get(i).getImage());
                FileInputStream input2 = new FileInputStream(myHeroes.get(i).getTierImg());
                starImgs[i].setImage(new Image(input2));
                starImgs[i].setVisible(true);
                FileInputStream input3 = new FileInputStream(myHeroes.get(i).getWeapon().getImage());
                typeImgs[i].setImage(new Image(input3));
                typeImgs[i].setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        avCount.setText(myHeroes.size() + "/60");
        displayAvInfo(myHeroes.get(0));
    }

    public void setLevelUpPane(){
        levelUpPane.setVisible(true);
        cLvlLbl.setText("Current Level: " + example.getLevel());
        lvlUp.clear();
        costLbl.setText("Cost: " + 0 + " Crowns");
    }

    int cost;
    public void updateCost(){
        cost = (int)(Math.pow((example.getLevel() + Integer.parseInt(lvlUp.getText())), 1.8)) - (int)(Math.pow(example.getLevel(), 1.8));
        cost *= 100;
        costLbl.setText("Cost: " + cost + " Crowns");
    }

    public void purchaseLvl(){
        if(crowns >= cost){
            for (int i = 0; i < Integer.parseInt(lvlUp.getText()); i++) {
                levelUp(example);
            }
            crowns -= cost;
            setLevelUpPane();
        }
    }

    public void setMapPane(){
        mapPane.setVisible(true);
    }

    int whichMap;
    public void setLevelPane(ActionEvent t){
        whichMap = Integer.parseInt(t.getSource().toString().substring(19, 20))-1;
        battleBut.setDisable(true);
        levelPane.setVisible(true);
//        for (int i = 0; i < 9; i++) {
//            lvlButs[i].setDisable(true);
//            if(levelUnlocked[whichMap][i]){
//                lvlButs[i].setDisable(false);
//            }
//        }
        for (int i = 0; i < 4; i++) {
            enemyTypes[i].setVisible(false);
        }
    }

    int level;
    public void handleBattleBut(ActionEvent t){
        level = Integer.parseInt(t.getSource().toString().substring(13, 14));
        generateEnemy(level);
        for (int i = 0; i < 4; i++) {
            enemyTypes[i].setVisible(true);
            try {
                FileInputStream input2 = new FileInputStream(enemyUnits.get(i).getWeapon().getImage());
                enemyTypes[i].setImage(new Image(input2));
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        battleBut.setDisable(false);
    }

    public void setBattlePane(){
        battlePane.setVisible(true);
        clickedPane.setVisible(false);
        eventList.getItems().clear();
        setBattleGrid();
        updateGrid();
    }

    boolean isBarrier = false;
    public void setBattleGrid(){
        for (int i = 0; i < turnOver.length; i++) {
            turnOver[i] = false;
        }

        first = true;
        int kingdom = whichMap;
        Image mountain = null;
        int[][] endPoints = new int[2][2];

        boolean notRepeat = true;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/mountain.png");
            mountain = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image wall = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/wall.png");
            wall = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image river = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/blue.png");
            river = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image tree = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/tree.png");
            tree = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image lava = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/lava.png");
            lava = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<int[]> bridgeLocs = new ArrayList<>();

        //erasing grid
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                terrainGrid[i][j] = 0;
            }
        }

        //alynthi kingdom - grass + mountains + walls
        //amaris kingdom - trees
        //oryn kingdom - rivers + bridges
        //gantrick kingdom - pits of lava
        //grass - #7ec850, 1: mountain, 2: wall
        if(kingdom <= 1){
            isBarrier = true;
            ArrayList<int[]> accLocs = new ArrayList<>();
            //determining which side the mountains will start at
            int start = randomNumber(1, 4);
            int x;
            int y;
            switch (start) {
                //top wall
                case 1:
                    x = 0;
                    y = randomNumber(1, 5);
                    break;
                //bottom wall
                case 2:
                    x = 8;
                    y = randomNumber(1, 5);
                    break;
                //right wall
                case 3:
                    x = randomNumber(1, 7);
                    y = 6;
                    break;
                //left wall
                case 4:
                    x = randomNumber(1, 7);
                    y = 0;
                    break;
                //never going to run only here so error doesnt pop up.
                default:
                    x = 0;
                    y = 0;
                    break;
            }
            terrainGrid[x][y] = 1;
            endPoints[0][0] = x;
            endPoints[0][1] = y;

            int pX = x;
            int pY = y;
            int count = 0;
            //randomly places mountains next to each other
            do {
                ArrayList<int[]> possLocs = new ArrayList<>();

                if(start != 3 && start != 4){
                    //whether or not the next mountain will be above or below
                    if(pX > 0 && terrainGrid[pX - 1][pY] != 1 && pX-1 != x && countAround(pX-1, pY) < 2 && (pX-1 != 0 || count >= 8)){
                        possLocs.add(new int[]{pX - 1, pY});
                    } else if (pX < terrainGrid.length-1 && terrainGrid[pX + 1][pY] != 1 && pX+1 != x && countAround(pX+1, pY) < 2 && (pX+1 != terrainGrid.length-1 || count >= 8)){
                        possLocs.add(new int[]{pX + 1, pY});
                    }
                }
                if(start != 1 && start != 2){
                    //whether or not the next mountain will be left or right
                    if(pY > 0 && terrainGrid[pX][pY-1] != 1 && pY-1 != y && countAround(pX, pY-1) < 2 && (pY-1 != 0 || count >= 8)){
                        possLocs.add(new int[]{pX, pY-1});
                    } else if (pY < terrainGrid[0].length-1 && terrainGrid[pX][pY+1] != 1 && pY+1 != y && countAround(pX, pY+1) < 2 && (pY+1 != terrainGrid[0].length-1 || count >= 8)){
                        possLocs.add(new int[]{pX, pY+1});
                    }
                }
                start = 0;
                if(possLocs.size()>0){
                    //this automatically chooses a random location for the next obstacle from an array of possible locations
                    int[] chosen = possLocs.get(randomNumber(0, possLocs.size()-1));
                    accLocs.add(chosen);
                    terrainGrid[chosen[0]][chosen[1]] = 1;
                    pX = chosen[0];
                    pY = chosen[1];
                    endPoints[1][0] = pX;
                    endPoints[1][1] = pY;
                    count ++;
                    notRepeat = true;
                } else {
                    //if the wall isnt complete then the grid automatically regenerates through recursion
                    setBattleGrid();
                    notRepeat = false;
                    break;
                }
            } while(pX != 0 && pX != terrainGrid.length-1 && pY != 0 && pY != terrainGrid[0].length-1);

            if(notRepeat){
                //this piece of code randomly chooses 2-3 walls
                if (kingdom == 0){
                    int num = randomNumber(3, accLocs.size()-3);
                    for (int i = num-1; i <= num+randomNumber(0, 1); i++) {
                        int[] chosen = accLocs.get(i);
                        terrainGrid[chosen[0]][chosen[1]] = 2;
                    }
                } else if (kingdom == 1) {
                    for (int i = 0; i < randomNumber(2, 3); i++) {
                        bridgeLocs.add(accLocs.get(randomNumber(3, accLocs.size()-3)));
                        terrainGrid[bridgeLocs.get(bridgeLocs.size()-1)[0]][bridgeLocs.get(bridgeLocs.size()-1)[1]] = 0;
                    }
                }
            }
        }

        if(kingdom == 2){
            int num = randomNumber(7, 15);
            for (int i = 0; i < num; i++) {
                terrainGrid[randomNumber(0, 8)][randomNumber(0, 6)] = 1;
            }
        } else if (kingdom == 3){
            int num = randomNumber(2, 4);
            for (int i = 0; i < num; i++) {
                int x = randomNumber(0, 6);
                int y = randomNumber(0, 4);
                terrainGrid[x][y] = 1;
                for (int j = 0; j < randomNumber(0, 2); j++) {
                    for (int k = 0; k < randomNumber(0, 2); k++) {
                        terrainGrid[x+j][y+k] = 1;
                    }
                }
            }
        }
        //sets the grid to assigned color and images
        if(notRepeat){
            for (int i = 0; i < btn.length; i++) {
                for (int j = 0; j < btn[0].length; j++) {
                    if(kingdom == 0 || kingdom == 2){
                        btn[i][j].setStyle("-fx-background-color:#74b378;");
                        if(Math.random() > .8){
                            btn[i][j].setStyle("-fx-background-color:#629c66;");
                        }
                        if(Math.random() < .2){
                            btn[i][j].setStyle("-fx-background-color:#518254;");
                        }
                    } else if (kingdom == 1){
                        btn[i][j].setStyle("-fx-background-color:#c2b280;");
                        for (int k = 0; k < bridgeLocs.size(); k++) {
                            btn[bridgeLocs.get(k)[0]][bridgeLocs.get(k)[1]].setStyle("-fx-background-color:#808080;");
                        }
                    } else if (kingdom == 3){
                        btn[i][j].setStyle("-fx-background-color:#808080;");
                    }
                    if(terrainGrid[i][j] == 0){
                        images[i][j].setImage(null);
                    } else if(terrainGrid[i][j] == 1){
                        if(kingdom == 0){
                            images[i][j].setImage(mountain);
                        } else if (kingdom == 1){
                            images[i][j].setImage(river);
                        } else if (kingdom == 2){
                            images[i][j].setImage(tree);
                        } else if (kingdom == 3){
                            images[i][j].setImage(lava);
                        }
                    } else if(terrainGrid[i][j] == 2) {
                        images[i][j].setImage(wall);
                    }
                }
            }

            troupeUnits.clear();
            for (int i = 0; i < 4; i++) {
                troupeUnits.add(troupes[current][i]);

            }
            for (int i = 0; i < 4; i++) {
                troupeUnits.get(i).setHp(troupeUnits.get(i).getTotalHp());
            }
            spawnIcons(endPoints);
            updateGrid();
        }
    }

    //counting how many of a certain obstacle are around the object
    public int countAround(int x, int y){
        int count = 0;
        for (int i = x-1; i <= x+1 && x < terrainGrid.length-1 && x > 0; i++) {
            if(terrainGrid[i][y] == 1){
                count ++;
            }
        }
        for (int i = y-1; i <= y+1 && y < terrainGrid[0].length-1 && y > 0; i++) {
            if(terrainGrid[x][i] == 1){
                count ++;
            }
        }
        return count;
    }

    public void generateEnemy(int level){
        enemyUnits.clear();
        switch (level){
            case 1:
                newHeroes(4, 45, 37, 3, false);
                break;
            case 2:
                newHeroes(3, 46, 38, 3, false);
                newHeroes(1, 53, 44, 4, false);
                break;
            case 3:
                newHeroes(2, 47, 39, 3, false);
                newHeroes(2, 54, 45, 4, false);
                break;
            case 4:
                newHeroes(1, 49, 41, 3, false);
                newHeroes(3, 55, 47, 4, false);
                break;
            case 5:
                newHeroes(4, 57, 49, 4, false);
                break;
            case 6:
                newHeroes(3, 60, 52, 4, false);
                newHeroes(1, 65, 55, 5, false);
                break;
            case 7:
                newHeroes(2, 63, 55, 4, false);
                newHeroes(2, 67, 57, 5, false);
                break;
            case 8:
                newHeroes(1, 67, 59, 4, false);
                newHeroes(3, 71, 61, 5, false);
                break;
            case 9:
                newHeroes(4, 80, 70, 5, false);
                break;
        }

        for (int i = 0; i < enemyUnits.size(); i++) {
            for (int j = 0; j < level*2; j++) {
                if(level != 1){
                    levelUp(enemyUnits.get(i));
                }
            }
            enemyUnits.get(i).setHp(enemyUnits.get(i).getTotalHp());
        }
    }

    public void summonHelp(){
        explanationPane.setVisible(true);
    }

    public void getOut(){
        levelUpPane.setVisible(false);
        explanationPane.setVisible(false);
        summonHeroPane.setVisible(false);
        levelPane.setVisible(false);
    }

    public void newWeapons(){
        //Red Weapons: Sword, Dagger, Tome, Bow
        String[] redWeapons = {"Sword", "Dagger", "Tome", "Bow"};
        //Blue Weapons: Lance, Dagger, Tome, Bow
        String[] blueWeapons = {"Lance", "Dagger", "Tome", "Bow"};
        //Green Weapons: Axe, Dagger, Tome, Bow
        String[] greenWeapons = {"Axe", "Dagger", "Tome", "Bow"};
        //Gray Weapons: Staff, Dagger, Tome, Bow
        String[] grayWeapons = {"Staff", "Dagger", "Tome", "Bow"};

        //This created 6 different mights stats for each type of weapon
        for (int i = 0; i < 16; i++) {
            for(int j = 0; j < 6; j++){
                if(i/4==0){
                    //0-23
                    allWeapons.add(new Weapon(redWeapons[i%4], "red", j+randomNumber(6, 11)));
                } else if(i/4==1){
                    //24-47
                    allWeapons.add(new Weapon(blueWeapons[i%4], "blue", j+randomNumber(6, 11)));
                } else if(i/4==2){
                    //48-71
                    allWeapons.add(new Weapon(greenWeapons[i%4], "green", j+randomNumber(6, 11)));
                } else {
                    //72-95
                    allWeapons.add(new Weapon(grayWeapons[i%4], "gray", j+randomNumber(6, 11)));
                }
            }
        }

        for(Weapon e: allWeapons){
            System.out.println(e.getType() + e.getName() + e.getMight());
        }
    }

    //creates all the heroes, still need to add weapons
    public void newHeroes(int num, int max, int min, int tier, boolean hero) {
        System.out.println(allHeroes.size() + " to " + (num + allHeroes.size()));
        ArrayList<String> already = new ArrayList<>();
        int start;
        int end;
        int[] dist = new int[4];
        if(hero){
            //separate variable since for loop is editing allHeroes array
            start = allHeroes.size();
            end = num + allHeroes.size();
            //determining the distribution of what type weapons among tier hero.
            for (int i = 0; i < 4; i++) {
                dist[i] = num/4;
            }
            for (int i = 0; i < num % 4; i++) {
                dist[randomNumber(0, 3)] += 1;
            }

        } else {
            //if the newHeroes method is generating enemy units
            start = 0;
            end = num;
            //can only have a maximum of three of the same color in the enemy team.
            for (int i = 0; i < 4; i++) {
                int rand = randomNumber(0, 3);
                while(dist[rand] > 2){
                    rand = randomNumber(0, 3);
                }
                dist[rand] += 1;
            }
        }

        //creates the heroes and determines the stats
        for (int i = start; i < end; i++) {
            int[] val = {randomNumber(18, 22), randomNumber(6, 11), randomNumber(6, 11), randomNumber(6, 11), randomNumber(6, 11)};
            int total = val[0] + val[1] + val[2] + val[3] + val[4];

            //decreases so the max value is less than or equal to max
            while (total > max) {
                int where = randomNumber(0, 4);
                int amt = randomNumber(1, 5);
                if (val[where] >= amt) {
                    val[where] -= amt;
                    total -= amt;
                }
            }

            //increases so the total value is greater than or equal to min
            while (total < min) {
                int amt = randomNumber(1, 5);
                val[randomNumber(0, 4)] += amt;
                total += amt;
            }

            //gets random name and removes it from name array
            if(hero){
                String x;
                if (i % 2 == 0) {
                    x = boyNames.get(randomNumber(0, boyNames.size() - 1));
                    boyNames.remove(x);
//                System.out.println("boy " + i);
                } else {
                    x = girlNames.get(randomNumber(0, girlNames.size() - 1));
                    girlNames.remove(x);
//                System.out.println("girl " + i);
                }
                allHeroes.add(new Hero(x, "src/main/resources/Avatars/" + imgNames.get(i), tier, val[0], val[1], val[2], val[3], val[4]));
            } else {
                enemyUnits.add(new Hero("Enemy " + (i+1), "src/main/resources/enemy.png", tier, val[0], val[1], val[2], val[3], val[4]));
            }

            //assigns weapon to newly created heroes
            boolean notFound = true;
            Weapon possible;
            while (notFound) {
                possible = allWeapons.get(randomNumber(0, allWeapons.size() - 1));
                //ensures that might of weapon matches the tier of hero
                if ((tier == 5 && possible.getMight() >= 13) || (tier == 4 && possible.getMight() >= 10 && possible.getMight() <= 13) || (tier == 3 && possible.getMight() >= 6 && possible.getMight() <= 10)) {
                    //ensures there are no more than two repeats if creating hero or three repeats if creating enemy
                    if (dist[types.indexOf(possible.getType())] != 0 && ((Collections.frequency(already, possible.getType() + possible.getName()) < 2) && hero) || ((Collections.frequency(already, possible.getType() + possible.getName()) < 2) && !hero)){
                        already.add(possible.getType() + possible.getName());
                        dist[types.indexOf(possible.getType())]--;
                        if(hero){
                            allHeroes.get(allHeroes.size() - 1).setWeapon(possible);
                        } else {
                            enemyUnits.get(enemyUnits.size() - 1).setWeapon(possible);
                        }
                        notFound = false;
                    }
                }
            }
        }
    }

    Hero chosen;
    public void circleClicked(MouseEvent t){
        Circle[] circles = {circle1, circle2, circle3, circle4, circle5};

        for (Circle e: circles) {
            e.setStrokeWidth(1);
            e.setStroke(Color.BLACK);
        }

        if(!circles[Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(6)) - 1].getFill().equals(Color.BLACK)){
            circles[Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(6)) - 1].setStrokeWidth(5);
            circles[Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(6)) - 1].setStroke(Color.DARKGOLDENROD);
        }

        summonBut.setDisable(false);
        chosen = chosenHeroes.get(Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(6)) - 1);
    }

    int summonCount = 0;
    public void summonHeroes(){
        //setting up the summonHero anchor pane
        Circle[] circles = {circle1, circle2, circle3, circle4, circle5};
        ImageView[] summonImgs = {summonImg1, summonImg2, summonImg3, summonImg4, summonImg5};

        summonHeroPane.setVisible(true);
        myHeroes.add(chosen);
        heroNameLbl.setText(myHeroes.get(myHeroes.size()-1).getName());
        try{
            avatarImg.setImage(myHeroes.get(myHeroes.size()-1).getImage());
            FileInputStream input2 = new FileInputStream(myHeroes.get(myHeroes.size()-1).getTierImg());
            starImg.setImage(new Image(input2));
            FileInputStream input3 = new FileInputStream(myHeroes.get(myHeroes.size()-1).getWeapon().getImage());
            weaponImg.setImage(new Image(input3));
            summonImgs[chosenHeroes.indexOf(chosen)].setVisible(true);
            summonImgs[chosenHeroes.indexOf(chosen)].setImage(myHeroes.get(myHeroes.size()-1).getImage());
            circles[chosenHeroes.indexOf(chosen)].setFill(Color.BLACK);
            circles[chosenHeroes.indexOf(chosen)].setStrokeWidth(1);
            circles[chosenHeroes.indexOf(chosen)].setStroke(Color.BLACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gems -= (Integer.parseInt(beforeLbl.getText()) - Integer.parseInt(afterLbl.getText()));
        summonCount ++;

        if(summonCount == 0){
            beforeLbl.setText(String.valueOf(gems));
            afterLbl.setText(String.valueOf(gems-5));
        } else if (summonCount == 1 || summonCount == 2){
            beforeLbl.setText(String.valueOf(gems));
            afterLbl.setText(String.valueOf(gems-4));
        } else {
            beforeLbl.setText(String.valueOf(gems));
            afterLbl.setText(String.valueOf(gems-3));
        }
    }

    int current = 0;
    ImageView[] exchange = new ImageView[2];
    public void exchangeIcons(MouseEvent t){
        int which = 0;
        t.getPickResult().getIntersectedNode().setEffect(shade);
        //if the image clicked is from the slot images then it goes in 1 index if it is from the
        //gridpane it goes in the 0 index essentially the purpose is to differentiate between the two
        if(t.getPickResult().getIntersectedNode().getId().startsWith("img")){
            exchange[0] = avImgs[Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(3))];
            for (int i = 0; i < myHeroes.size(); i++) {
                if (myHeroes.get(i).getImage().equals(exchange[0].getImage())) {
                    displayAvInfo(myHeroes.get(i));
                }
            }
        } else {
            which = Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(4));
            exchange[1] = troupeSlots[which];
            for (int i = 0; i < myHeroes.size(); i++) {
                if (myHeroes.get(i).getImage().equals(exchange[1].getImage())) {
                    displayAvInfo(myHeroes.get(i));
                }
            }
        }

        System.out.println("before place 1:" + exchange[0]);
        System.out.println("before place 2:" + exchange[1]);
        //this if statement runs when the slots are empty for that specific team so if you click on the hero it will automatically
        //go in the first empty slot.
        if(exchange[0] != null && (troupes[current][0] == null || troupes[current][1] == null || troupes[current][2] == null || troupes[current][3] == null)){
            System.out.println("0:" + troupes[current][0]);
            System.out.println("1:" + troupes[current][1]);
            System.out.println("2:" + troupes[current][2]);
            System.out.println("3:" + troupes[current][3]);
            int start = 0;

            while(exchange[0] != null && start < 4){
                if(troupes[current][start] == null){
                    troupeSlots[start].setImage(exchange[0].getImage());
//                    troupes[current][start] = myHeroes.get(Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(4)));
                    System.out.println("before: " + troupes[current][start]);
                    System.out.println(exchange[0].getImage().getUrl());
                    for (int i = 0; i < myHeroes.size(); i++) {
//                        t.getPickResult().getIntersectedNode().
                        if(myHeroes.get(i).getImage().equals(exchange[0].getImage())){
                            troupes[current][start] = myHeroes.get(i);
                            System.out.println("after: " + troupes[current][start]);
                        }
                    }
                    exchange[0] = null;
                }
                start++;
            }
            //this if statement runs if statement all the troupe slots are already full
        } else if (exchange[0] != null && exchange[1] != null){
            System.out.println("switched");
            exchange[1].setImage(exchange[0].getImage());
            exchange[1].setEffect(null);

            for (int i = 0; i < myHeroes.size(); i++) {
                if(myHeroes.get(i).getImage().equals(exchange[0].getImage())){
                    troupes[current][which] = myHeroes.get(i);
                }
            }

            exchange[0] = null;
            exchange[1] = null;

            //runs through all images in gridpane and checks which are in the current troupe
            for (int i = 0; i < avImgs.length; i++) {
                avImgs[i].setEffect(null);
                for (Hero e : troupes[current]) {
                    if(e != null){
                        if (e.getImage().equals(avImgs[i].getImage())) {
                            System.out.println(e.getWeapon().getImage());
                            avImgs[i].setEffect(shade);
                        }
                    }
                }
            }
        }

        System.out.println(troupeUnits.size());
        troupeUnits.clear();
        for (int i = 0; i < 4; i++) {
            troupeUnits.add(i, troupes[current][i]);
        }
        System.out.println(troupeUnits);
        System.out.println("after place 1:" + exchange[0]);
        System.out.println("after place 2:" + exchange[1]);
    }

    public void changeTroupe(MouseEvent t) throws FileNotFoundException {
        //switches between the troupes
        if(t.getPickResult().getIntersectedNode().getId().startsWith("right")){
            if(current < 4){
                current++;
            } else {
                current = 0;
            }
        } else {
            if(current > 0){
                current --;
            } else {
                current = 4;
            }
        }
        troupeLbl.setText("TROUPE " + (current+1));
        //sets image for hero in the troupe
        troupeUnits.clear();
        for (int i = 0; i < 4; i++) {
            troupeUnits.add(i, troupes[current][i]);
            if(troupes[current][i] != null){
                troupeSlots[i].setImage(troupes[current][i].getImage());
            } else {
                FileInputStream input = null;
                try {
                    input = new FileInputStream("src/main/resources/emptySlot.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                troupeSlots[i].setImage(new Image(input));
            }
        }


        reorder(1);

        //shades images that are in current troupe
        for (int i = 0; i < avImgs.length; i++) {
            avImgs[i].setEffect(null);
            for (Hero e : troupes[current]) {
                if(e != null){
                    if (e.getImage().equals(avImgs[i].getImage())) {
                        avImgs[i].setEffect(shade);
                    }
                }
            }
        }
    }

    public void reorder(int x) throws FileNotFoundException {
        if(x==1){
            //this counts the number of heroes that are in the current troupe because getting the length itself will always
            //return 4
            int length = 0;
            ArrayList<Image> used = new ArrayList<>();
            for (int i = 0; i < troupes[current].length; i++) {
                if(troupes[current][i] != null){
                    length ++;
                }
            }

            //this first sets all the images to empty slot
            for (int i = 0; i < avImgs.length; i++) {
                FileInputStream input = null;
                try {
                    input = new FileInputStream("src/main/resources/emptySlot.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                avImgs[i].setImage(new Image(input));
            }

//            for (int i = 0; i < myHeroes.size(); i++) {
//                System.out.println(myHeroes.get(i).getWeapon().getImage());
//            }

            System.out.println('\n');

            for (int i = 0; i < avImgs.length; i++) {
                //all the heroes in the current troupe being shown appear first in the gridpane
                if(i >= 0 && i < length){
                    //setting images
                    used.add(troupes[current][i].getImage());
                    avImgs[i].setImage(troupes[current][i].getImage());
                    try {
                        FileInputStream input2 = new FileInputStream(troupes[current][i].getTierImg());
                        starImgs[i].setImage(new Image(input2));
                        FileInputStream input3 = new FileInputStream(troupes[current][i].getWeapon().getImage());
                        typeImgs[i].setImage(new Image(input3));
                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    }

                    System.out.println(troupes[current][i].getWeapon().getImage());
                    //after this is rest of the heroes not including the heroes alredy in the gridpane
                } else if (i >= length && i < myHeroes.size()){
                    int start = i;
                    while(Arrays.asList(troupes[current]).contains(myHeroes.get(start-length)) || used.contains(myHeroes.get(start-length).getImage())){
                        start++;
                    }
                    avImgs[i].setImage(myHeroes.get(start-length).getImage());
                    used.add(myHeroes.get(start-length).getImage());
                    try {
                        FileInputStream input2 = new FileInputStream(myHeroes.get(start-length).getTierImg());
                        starImgs[i].setImage(new Image(input2));
                        FileInputStream input3 = new FileInputStream(myHeroes.get(start-length).getWeapon().getImage());
                        typeImgs[i].setImage(new Image(input3));
                        System.out.println(myHeroes.get(start-length).getWeapon().getImage());
                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
            System.out.println('\n');
        }
    }

    Hero example;
    public void displayAvInfo(Hero x){
        example = x;
        try{
            currentAv.setImage(x.getImage());
            FileInputStream input2 = new FileInputStream(x.getTierImg());
            currentRate.setImage(new Image(input2));
            FileInputStream input3 = new FileInputStream(x.getWeapon().getImage());
            currentType.setImage(new Image(input3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentName.setText(x.getName().toUpperCase(Locale.ROOT));
        currentStats.setText("LVL " + x.getLevel() + "\nHP: " + x.getHp() + "\nATK: " + (x.getAttack() + x.getWeapon().getMight()) + "\nSPD: " + x.getSpeed() + "\nDEF: " + x.getDefense() + "\nRES: " + x.getResistance());
    }

    ArrayList<Locations> tempLocs = new ArrayList<>();
    public void spawnIcons(int[][] endPts){
        //top to down - troupes on left and right
        if(whichMap <= 1){
            System.out.println(endPts[0][0] + " " + endPts[0][1] + " " + endPts[1][0] + " " + endPts[1][1]);
            if((endPts[0][0] == 0 || endPts[0][0] == 8) && (endPts[1][0] == 0 || endPts[1][0] == 8)){
                System.out.println("top to down");
                setInitialArray(randomNumber(0, 8), 0, randomNumber(0, 8), 6);
                //right to left - troupes on top and bottom
            } else if ((endPts[0][1] == 0 || endPts[0][1] == 6) && (endPts[1][1] == 0 || endPts[1][1] == 6)){
                System.out.println("right to left");
                setInitialArray(0, randomNumber(0, 6), 8, randomNumber(0, 6));
                //top to left - troupes on top-left and bottom
            } else if ((endPts[0][0] == 0 || endPts[0][1] == 0) && (endPts[1][0] == 0 || endPts[1][1] == 0)){
                System.out.println("top to left");
                setInitialArray(0, 0, 8, 6);
                //top to right - troupes on top-right and bottom
            } else if ((endPts[0][0] == 0 || endPts[0][1] == 6) && (endPts[1][0] == 0 || endPts[1][1] == 6)){
                System.out.println("top to right");
                setInitialArray(0, 6, 8, 0);
                //bottom to left - troupes on bottom-left and top
            } else if ((endPts[0][0] == 8 || endPts[0][1] == 0) && (endPts[1][0] == 8 || endPts[1][1] == 0)) {
                System.out.println("bottom to left");
                setInitialArray(8, 0, 0, 6);
                //bottom to right - troupes on bottom-right and top
            } else if ((endPts[0][0] == 8 || endPts[0][1] == 6) && (endPts[1][0] == 8 || endPts[1][1] == 6)) {
                System.out.println("bottom to right");
                setInitialArray(8, 6, 0, 0);
            }
        } else {
            int x1;
            int y1;
            int x2;
            int y2;
            do{
                y1 = randomNumber(0, 6);
                x1 = randomNumber(0, 3);
                y2 = randomNumber(0, 6);
                x2 = randomNumber(5, 8);
            } while(terrainGrid[x1][y1] != 0 || terrainGrid[x2][y2] != 0);
            setInitialArray(x1, y1, x2, y2);
        }

        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                System.out.print(avatarGrid[i][j] + " ");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                System.out.print(terrainGrid[i][j] + " ");
            }
            System.out.println("\n");
        }

        //sets starting location for the rest of the heroes
        for (int i = 1; i < 4; i++) {
            tempLocs.clear();
            int x = i;
            //this code is running through every location around the previous avatar placed.
            while(tempLocs.size()==0 && x > 0){
                System.out.println("passed 0");
                for (int r = troupeUnits.get(x-1).getLocation().getX()-1; r <= troupeUnits.get(x-1).getLocation().getX()+1; r++) {
                    System.out.println("passed 0.5");
                    for (int c = troupeUnits.get(x-1).getLocation().getY()-1; c <= troupeUnits.get(x-1).getLocation().getY()+1; c++) {
                        System.out.println("passed 1");
                        //checking whether the coordinates are in bounds and
                        if(r < avatarGrid.length && r >= 0 && c < avatarGrid[0].length && c >= 0 && checkEmptyAround(r, c) && getDistance(r, c, troupeUnits.get(x-1).getLocation().getX(), troupeUnits.get(x-1).getLocation().getY()) == 1){
                            System.out.println("passed 2");
                            tempLocs.add(new Locations(r, c));
                        }
                    }
                }
                if (tempLocs.size() == 0){
                    x--;
                }
            }
            troupeUnits.get(i).setLocation(tempLocs.get(randomNumber(0, tempLocs.size()-1)));
            avatarGrid[troupeUnits.get(i).getLocation().getX()][troupeUnits.get(i).getLocation().getY()] = i+1;
        }

        //sets starting location for the rest of the enemies
        for (int i = 1; i < 4; i++) {
            tempLocs.clear();
            int x = i;
            //this code is running through every location around the previous enemy placed.
            while(tempLocs.size()==0 && x > 0){
                for (int r = enemyUnits.get(x-1).getLocation().getX()-2; r <= enemyUnits.get(x-1).getLocation().getX()+2; r++) {
                    for (int c = enemyUnits.get(x-1).getLocation().getY()-2; c <= enemyUnits.get(x-1).getLocation().getY()+2; c++) {
                        if(r < avatarGrid.length && r >= 0 && c < avatarGrid[0].length && c >= 0 && checkEmptyAround(r, c) && getDistance(r, c, enemyUnits.get(x-1).getLocation().getX(), enemyUnits.get(x-1).getLocation().getY()) <= 7 && getDistance(r, c, troupeUnits.get(0).getLocation().getX(), troupeUnits.get(0).getLocation().getY()) >= 4){
                            tempLocs.add(new Locations(r, c));
                        }
                    }
                }
                if (tempLocs.size() == 0){
                    x--;
                }
            }
            enemyUnits.get(i).setLocation(tempLocs.get(randomNumber(0, (tempLocs.size()-1))));
            avatarGrid[enemyUnits.get(i).getLocation().getX()][enemyUnits.get(i).getLocation().getY()] = i+5;
        }
    }

    public void setInitialArray(int num1, int num2, int num3, int num4){
        troupeUnits.get(0).setLocation(new Locations(num1, num2));
        avatarGrid[troupeUnits.get(0).getLocation().getX()][troupeUnits.get(0).getLocation().getY()] = 1;
        enemyUnits.get(0).setLocation(new Locations(num3, num4));
        avatarGrid[enemyUnits.get(0).getLocation().getX()][enemyUnits.get(0).getLocation().getY()] = 5;
    }

    public void colorGrid(){
        Image mountain = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/mountain.png");
            mountain = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image wall = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/wall.png");
            wall = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image river = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/blue.png");
            river = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image tree = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/tree.png");
            tree = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image lava = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/lava.png");
            lava = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                btn[i][j].setStyle(btn[i][j].getStyle().substring(0, 29));
                if(terrainGrid[i][j] == 0){
                    images[i][j].setImage(null);
                } else if(terrainGrid[i][j] == 1){
                    if(whichMap == 0){
                        images[i][j].setImage(mountain);
                    } else if (whichMap == 1){
                        images[i][j].setImage(river);
                    } else if (whichMap == 2){
                        images[i][j].setImage(tree);
                    } else if (whichMap == 3){
                        images[i][j].setImage(lava);
                    }
                } else if(terrainGrid[i][j] == 2){
                    images[i][j].setImage(wall);
                }
            }
        }
    }

    public void updateGrid(){
        //clearing the avatar grid in order to get rid of previous position data
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                avatarGrid[i][j] = 0;
            }
        }

        //inputting data back into avatar grid
        for (int i = 0; i < troupeUnits.size(); i++) {
            avatarGrid[troupeUnits.get(i).getLocation().getX()][troupeUnits.get(i).getLocation().getY()] = i+1;
            //shades imageview of heroes that have their turn over
            if(turnOver[i]){
                images[troupeUnits.get(i).getLocation().getX()][troupeUnits.get(i).getLocation().getY()].setEffect(shade);
            } else {
                images[troupeUnits.get(i).getLocation().getX()][troupeUnits.get(i).getLocation().getY()].setEffect(null);
            }
        }
        for (int i = 0; i < enemyUnits.size(); i++) {
            avatarGrid[enemyUnits.get(i).getLocation().getX()][enemyUnits.get(i).getLocation().getY()] = i+5;
        }

        //setting images into gridpane
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                canHeal[i][j] = false;
                if (avatarGrid[i][j] != 0){
                    if (avatarGrid[i][j] <= 4){
                        images[i][j].setImage(troupeUnits.get(avatarGrid[i][j]-1).getImage());
                    } else {
                        images[i][j].setImage(enemyUnits.get(avatarGrid[i][j]-5).getImage());
                    }
                }
            }
        }

    }

    Hero currentHero;
    public void updateInfoPane(MouseEvent t){
        int i = Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(4, 5));
        int j = Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(5));

        if (canAttack[i][j]){
            //destroys obstacles in the way
            if(terrainGrid[i][j] == 2){
                terrainGrid[i][j] = 0;
                turnOver[troupeUnits.indexOf(currentHero)] = true;
                isBarrier = false;
                //attacks enemies
            } else if (avatarGrid[i][j] >= 5){
//                if (attack(currentHero, enemyUnits.get(avatarGrid[i][j]-5)) && attack(enemyUnits.get(avatarGrid[i][j]-5), currentHero)){
//                    turnOver[troupeUnits.indexOf(currentHero)] = true;
//                    if(currentHero.getSpeed() >= enemyUnits.get(avatarGrid[i][j]-5).getSpeed()+5){
//                        attack(currentHero, enemyUnits.get(avatarGrid[i][j]-5));
//                    }
//                }

                if (attack(currentHero, enemyUnits.get(avatarGrid[i][j]-5))){
                    if (currentHero.getWeapon().getRange() == enemyUnits.get(avatarGrid[i][j]-5).getWeapon().getRange() && attack(enemyUnits.get(avatarGrid[i][j]-5), currentHero)){
                        if(currentHero.getSpeed() >= enemyUnits.get(avatarGrid[i][j]-5).getSpeed()+5){
                            attack(currentHero, enemyUnits.get(avatarGrid[i][j]-5));
                        }
                    } else if (currentHero.getWeapon().getRange() != enemyUnits.get(avatarGrid[i][j]-5).getWeapon().getRange()){
                        if(currentHero.getSpeed() >= enemyUnits.get(avatarGrid[i][j]-5).getSpeed()+5){
                            attack(currentHero, enemyUnits.get(avatarGrid[i][j]-5));
                        }
                    }
                }

                if(troupeUnits.indexOf(currentHero) >= 0){
                    turnOver[troupeUnits.indexOf(currentHero)] = true;
                }
            }
            currentHero.getWeapon().countdown();
            colorGrid();
            updateGrid();
            //heals avatar
        } else if (canHeal[i][j]){
            int recover = troupeUnits.get(avatarGrid[i][j]-1).getHp() + Math.abs(currentHero.getAttack()-currentHero.getWeapon().getMight());
            if(recover > troupeUnits.get(avatarGrid[i][j]-1).getTotalHp()){
                troupeUnits.get(avatarGrid[i][j]-1).setHp(troupeUnits.get(avatarGrid[i][j]-1).getTotalHp());
            } else {
                troupeUnits.get(avatarGrid[i][j]-1).setHp(recover);
            }
            eventList.getItems().add(0, currentHero.getName() + " healed " + troupeUnits.get(avatarGrid[i][j]-1).getName() + " by " + Math.abs(currentHero.getAttack()-currentHero.getWeapon().getMight()) + " HP");
            if(troupeUnits.indexOf(currentHero) >= 0){
                turnOver[troupeUnits.indexOf(currentHero)] = true;
            }
            currentHero.getWeapon().resetCountdown();
            colorGrid();
            updateGrid();
        }
        //sets info pane to the information of the avatar that was clicked
        if(avatarGrid[i][j] != 0 && ((avatarGrid[i][j] >= 5 && currentHero != null && !currentHero.equals(enemyUnits.get(avatarGrid[i][j]-5))) || (avatarGrid[i][j] <= 4 && currentHero != null && !currentHero.equals(troupeUnits.get(avatarGrid[i][j]-1))) || first)){
            first = false;
            clickedPane.setVisible(true);
            avClicked.setImage(images[i][j].getImage());
            if (avatarGrid[i][j] <= 4 && !turnOver[avatarGrid[i][j]-1]){
                currentHero = troupeUnits.get(avatarGrid[i][j]-1);
                setInfoPane(currentHero);
                highlightGrid(currentHero, false);
            } else if (avatarGrid[i][j] >= 5){
                currentHero = enemyUnits.get(avatarGrid[i][j]-5);
                setInfoPane(currentHero);
                colorGrid();
                updateGrid();
            } else if (avatarGrid[i][j] <= 4 && turnOver[avatarGrid[i][j]-1]){
                setInfoPane(troupeUnits.get(avatarGrid[i][j]-1));
            }
        }
        System.out.println(canMove[i][j]);
    }

    public void setInfoPane(Hero temp){
        try {
            FileInputStream input = new FileInputStream(temp.getWeapon().getImage());
            typeClicked.setImage(new Image(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        hpBar.setProgress((double)temp.getHp() / temp.getTotalHp());
        avNameLbl.setText(temp.getName().toUpperCase(Locale.ROOT));
        lvlLbl.setText("LVL: " + temp.getLevel());
        attackLbl.setText("ATK: " + (temp.getAttack() + temp.getWeapon().getMight()));
        speedLbl.setText("SPD: " + temp.getSpeed());
        defenseLbl.setText("DEF: " + temp.getDefense());
        resistanceLbl.setText("RES: " + temp.getResistance());
    }

    //this method highlights the gridspot of the avatar clicked as black and then highlights every gridspot the avatar can move to as green.
    public void highlightGrid(Hero hero, boolean attacking){
        colorGrid();
        updateGrid();

        for (int i = 0; i < canMove.length; i++) {
            for (int j = 0; j < canMove[0].length; j++) {
                canMove[i][j] = false;
                canAttack[i][j] = false;
                canHeal[i][j] = false;
            }
        }

        int x = hero.getLocation().getX();
        int y = hero.getLocation().getY();
        btn[x][y].setStyle(btn[x][y].getStyle() + "; -fx-border-width: 5; -fx-border-color: black");

        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                //any space that can be moved to within two
                if(getDistance(i, j, x, y) > 0 && getDistance(i, j, x, y) <= 2 && terrainGrid[i][j] == 0 && avatarGrid[i][j] == 0 && noObstacle(i, j, x, y) && !attacking){
                    btn[i][j].setStyle(btn[i][j].getStyle() + "; -fx-border-width: 5; -fx-border-color: #013220");
                    canMove[i][j] = true;
                    //any space that can be attacked
                } else if(getDistance(i, j, x, y) == hero.getWeapon().getRange() && (terrainGrid[i][j] == 2 || avatarGrid[i][j] > 4)) {
                    btn[i][j].setStyle(btn[i][j].getStyle() + "; -fx-border-width: 5; -fx-border-color: #8B0000");
                    canAttack[i][j] = true;
                } else if(hero.getWeapon().isHealer() && hero.getWeapon().canHeal() && getDistance(i, j, x, y) == hero.getWeapon().getRange() && (terrainGrid[i][j] == 2 || avatarGrid[i][j] < 5) && avatarGrid[i][j] != 0){
                    btn[i][j].setStyle(btn[i][j].getStyle() + "; -fx-border-width: 5; -fx-border-color: #00008B");
                    canHeal[i][j] = true;
                } else {
                    btn[i][j].setStyle(btn[i][j].getStyle().substring(0, 29));
                    turnOver[troupeUnits.indexOf(currentHero)] = true;
                }
            }
        }

        for (int i = 0; i < canMove.length; i++) {
            for (int j = 0; j < canMove[0].length; j++) {
                if(canMove[i][j]){
                    System.out.println("move: " + i + " " + j);
                } else if (canAttack[i][j]){
                    System.out.println("attack: " + i + " " + j);
                }
            }
        }
    }

    int each;
    public void move(ActionEvent t){
        System.out.println(t.getSource());
        int x = 0;
        int y = 0;

        //finding the x and y coordinates of  button clicked
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                if(t.getSource().equals(btn[i][j])){
                    x = i;
                    y = j;
                }
            }
        }

        if (canMove[x][y]){
            System.out.println("running");
            //this runs if the square clicked on can be moved to
            currentHero.setLocation(new Locations(x, y));
            if(troupeUnits.contains(currentHero)){
                avatarGrid[currentHero.getLocation().getX()][currentHero.getLocation().getY()] = troupeUnits.indexOf(currentHero) + 1;
            } else {
                avatarGrid[currentHero.getLocation().getX()][currentHero.getLocation().getY()] = enemyUnits.indexOf(currentHero) + 5;
            }
            currentHero.getWeapon().countdown();
            colorGrid();
            updateGrid();
            highlightGrid(currentHero, true);
            if(isComputerTurn()){
                computerTurnBtn.setDisable(false);
                each = 0;
            }
        }
    }

    public boolean isComputerTurn(){
        boolean yes = true;
        for (int i = 0; i < 4; i++) {
            if(!turnOver[i]){
                yes = false;
            }
        }
        return yes;
    }

    public void endTurn(){
        for (int i = 0; i < 4; i++) {
            turnOver[i] = true;
        }
        computerTurnBtn.setDisable(false);
        each = 0;
        colorGrid();
        updateGrid();
    }

    public void computerMove(){
        tempLocs.clear();

        System.out.println("enemy: " + each);

        if(each >= enemyUnits.size()){
            computerTurnBtn.setDisable(true);
            each = 0;
            for (int i = 0; i < troupeUnits.size(); i++) {
                turnOver[i] = false;
            }
            colorGrid();
            updateGrid();
            return;
        }

        int x = enemyUnits.get(each).getLocation().getX();
        int y = enemyUnits.get(each).getLocation().getY();
//        tempLocs.add(enemyUnits.get(each).getLocation());

        //check whether or not there is an target that can be attacked by the enemy unit
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                //any space that can be moved to within two
                if (getDistance(i, j, x, y) > 0 && getDistance(i, j, x, y) <= 2 && terrainGrid[i][j] == 0 && avatarGrid[i][j] == 0 && noObstacle(i, j, x, y)) {
                    tempLocs.add(new Locations(i, j));
                    System.out.println("temp loc: " + i + " " + j);
                }
            }
        }

        ArrayList<int[]> attacks = new ArrayList<>();
        boolean[] canKill;
        ArrayList<int[]> obstacles = new ArrayList<>();
        ArrayList<int[]> heals = new ArrayList<>();
        //running through every gridspot for each temporary location
        for (int a = 0; a < tempLocs.size(); a++) {
            for (int i = 0; i < btn.length; i++) {
                for (int j = 0; j < btn[0].length; j++) {
                    System.out.println(enemyUnits.get(each).getWeapon().getRange());
                    if (getDistance(tempLocs.get(a).getX(), tempLocs.get(a).getY(), i, j) == enemyUnits.get(each).getWeapon().getRange()) {
                        //if there is a hero unit weapon range distance away adds it to the attacks arraylist
                        if(avatarGrid[i][j] <= 4 && avatarGrid[i][j] != 0){
                            attacks.add(new int[]{a, avatarGrid[i][j] - 1});
                            System.out.println("attacks " + i + " " + j);
                            //if there is a wall weapon range distance away adds it to the obstacle arraylisy
                        } else if (terrainGrid[i][j] == 2){
                            obstacles.add(new int[]{a, i, j});
                            //what you can heal weapon range distacne away is added to heal array
                        } else if (enemyUnits.get(each).getWeapon().isHealer() && enemyUnits.get(each).getWeapon().canHeal() && avatarGrid[i][j] >= 5 && avatarGrid[i][j] != 0){
                            heals.add(new int[]{a, avatarGrid[i][j] - 5});
                        }
                    }
                }
            }
        }

        if(heals.size() > 0){
            double max = 0;
            int which = 0;

            for (int i = 0; i < heals.size(); i++) {
                double hpNeed = ((double)(enemyUnits.get(heals.get(i)[1]).getTotalHp()) - enemyUnits.get(heals.get(i)[1]).getHp())/enemyUnits.get(heals.get(i)[1]).getTotalHp();
                if(hpNeed > max){
                    max = hpNeed;
                    which = i;
                }
            }

            if(max > 0.25){
                int recover = enemyUnits.get(heals.get(which)[1]).getHp() + Math.abs(enemyUnits.get(each).getAttack()-enemyUnits.get(each).getWeapon().getMight());
                if(recover > enemyUnits.get(heals.get(which)[1]).getTotalHp()){
                    enemyUnits.get(heals.get(which)[1]).setHp(enemyUnits.get(heals.get(which)[1]).getTotalHp());
                } else {
                    enemyUnits.get(heals.get(which)[1]).setHp(recover);
                }
                eventList.getItems().add(0, enemyUnits.get(each).getName() + " healed " + enemyUnits.get(heals.get(which)[1]).getName() + " by " + Math.abs(enemyUnits.get(each).getAttack()-enemyUnits.get(each).getWeapon().getMight()) + " HP");
                enemyUnits.get(each).getWeapon().resetCountdown();
                enemyUnits.get(each).setLocation(tempLocs.get(heals.get(which)[0]));
                System.out.println(enemyUnits.get(each).getName() + "moving towards healing");
                attacks.clear();
                colorGrid();
                updateGrid();
            }
        }

        if(attacks.size() > 0){
            System.out.println("possible attacks is greater than 0");

            canKill = new boolean[attacks.size()];
            int max = 0;
            int which = 0;
            boolean anyKills = false;
            //finds out if any of the possible moves will kill the opponent
            for (int i = 0; i < attacks.size(); i++) {
                int num = calcDamage(enemyUnits.get(each), troupeUnits.get(attacks.get(i)[1]));
                //if the enemy will be able to attack the hero twice
                if(enemyUnits.get(each).getSpeed() >= troupeUnits.get(attacks.get(which)[1]).getSpeed()+5){
                    if(troupeUnits.get(attacks.get(which)[1]).getHp() > num*2){
                        anyKills = true;
                        canKill[i] = true;
                    }
                } else {
                    if(troupeUnits.get(attacks.get(which)[1]).getHp() > num){
                        anyKills = true;
                        canKill[i] = true;
                    }
                }
            }

            //if there are several possible kills, kills the one with the highest attack stat
            //otherwise just kills whichever hero
            if(anyKills){
                for (int i = 0; i < canKill.length; i++) {
                    if(canKill[i] && troupeUnits.get(attacks.get(i)[1]).getAttack() > max){
                        max = troupeUnits.get(attacks.get(i)[1]).getAttack();
                        which = i;
                    }
                }
            } else {
                //if there are no kills then it will kind the hero it can deal the most damage to and attack it
                for (int i = 0; i < attacks.size(); i++) {
                    int num = calcDamage(enemyUnits.get(each), troupeUnits.get(attacks.get(i)[1]));
                    if(num > max){
                        max = num;
                        which = i;
                    }
                }
            }

            //sets location to that gridspot and simulates the attack
            enemyUnits.get(each).setLocation(tempLocs.get(attacks.get(which)[0]));
            //because of the double and statement this code will only run if the result of the previous clause returns true.
            //this prevents errors such as null because the hero doesn't exist at the index given.
            if (attack(enemyUnits.get(each), troupeUnits.get(attacks.get(which)[1]))){
                if (enemyUnits.get(each).getWeapon().getRange() == troupeUnits.get(attacks.get(which)[1]).getWeapon().getRange() && attack(troupeUnits.get(attacks.get(which)[1]), enemyUnits.get(each))){
                    if(enemyUnits.get(each).getSpeed() >= troupeUnits.get(attacks.get(which)[1]).getSpeed()+5){
                        attack(enemyUnits.get(each), troupeUnits.get(attacks.get(which)[1]));
                    }
                } else if (enemyUnits.get(each).getWeapon().getRange() != troupeUnits.get(attacks.get(which)[1]).getWeapon().getRange()){
                    if(enemyUnits.get(each).getSpeed() >= troupeUnits.get(attacks.get(which)[1]).getSpeed()+5){
                        attack(enemyUnits.get(each), troupeUnits.get(attacks.get(which)[1]));
                    }
                }
            }

            enemyUnits.get(each).getWeapon().countdown();
            colorGrid();
            updateGrid();
        } else if (obstacles.size() > 0){
            //this runs through all the possible obstacles destroyed and finds the one that is furthest away from the hero
            int max = 0;
            int which = 0;
            for (int i = 0; i < obstacles.size(); i++) {
                int sum = 0;
                for (int j = 0; j < troupeUnits.size(); j++) {
                    sum += getDistance(obstacles.get(i)[1], obstacles.get(i)[2], troupeUnits.get(j).getLocation().getX(), troupeUnits.get(j).getLocation().getY());
                }
                if(sum > max){
                    max = sum;
                    which = i;
                }
            }

            enemyUnits.get(each).setLocation(tempLocs.get(obstacles.get(which)[0]));
            terrainGrid[obstacles.get(which)[1]][obstacles.get(which)[2]] = 0;
            isBarrier = false;
            enemyUnits.get(each).getWeapon().countdown();
            colorGrid();
            updateGrid();
        } else {
            int min = 15;
            int which = 0;
            ArrayList<Locations> closest = new ArrayList<>();
            enemyUnits.get(each).getWeapon().countdown();
            //if there is a obstacle on the map but is farther away than 3 gridspots then the enemy moves to the grid spot that is closest to the obstacle
            if(isBarrier){
                for (int a = 0; a < tempLocs.size(); a++) {
                    for (int i = 0; i < btn.length; i++) {
                        for (int j = 0; j < btn[0].length; j++) {
                            if(terrainGrid[i][j] == 2){
                                int dist = getDistance(tempLocs.get(a).getX(), tempLocs.get(a).getY(), i, j);
                                if(dist < min){
                                    min = dist;
                                    which = a;
                                }
                            }
                        }
                    }
                }

                closest.add(tempLocs.get(which));

                //if there is multiple barriers same distance away
                for (int a = 0; a < tempLocs.size(); a++) {
                    for (int i = 0; i < btn.length; i++) {
                        for (int j = 0; j < btn[0].length; j++) {
                            if (terrainGrid[i][j] == 2 && getDistance(tempLocs.get(a).getX(), tempLocs.get(a).getY(), i, j) == min) {
                                closest.add(tempLocs.get(a));
                            }
                        }
                    }
                }

                enemyUnits.get(each).setLocation(closest.get(randomNumber(0, closest.size()-1)));
                colorGrid();
                updateGrid();
            } else {
                //this part of the function will direct the enemy unit towards the least amount of hp left and closest
                int[] total = new int[troupeUnits.size()];
                int minNum = total[0];
                int whichNum = 0;
                for (int i = 0; i < troupeUnits.size(); i++) {
                    int num = calcDamage(enemyUnits.get(each), troupeUnits.get(i));
                    total[i] = troupeUnits.get(i).getHp() - num;
                    int num2 = findPathDist(enemyUnits.get(each).getLocation(), troupeUnits.get(i).getLocation());
                    if(num2 == 0){
                        computerMove();
                        return;
                    }
                    total[i] += num2;
                    if(total[i] < minNum){
                        minNum = total[i];
                        whichNum = i;
                    }
                }

                int minDist = findPathDist(enemyUnits.get(each).getLocation(), troupeUnits.get(whichNum).getLocation());
                int whichLoc = 0;
                for (int i = 0; i < tempLocs.size(); i++) {
                    int dist = findPathDist(tempLocs.get(i), troupeUnits.get(whichNum).getLocation());
                    if(dist < minDist){
                        minDist = dist;
                        whichLoc = i;
                    }
                }

                enemyUnits.get(each).setLocation(tempLocs.get(whichLoc));
                colorGrid();
                updateGrid();
            }
        }
        each++;
    }

    public boolean attack(Hero a, Hero b){
        System.out.println(a.getName() + " attacked " + b.getName());

        boolean cont = true;
        if(b.getHp() > calcDamage(a, b)){
            b.setHp(b.getHp()-calcDamage(a, b));
            eventList.getItems().add(0, a.getName() + " dealt " + calcDamage(a, b) + " damage to " + b.getName());
            eventList.getItems().add(0, b.getName() + " now has an hp of " + b.getHp());
        } else {
            eventList.getItems().add(0, a.getName() + " killed " + b.getName());
            if(troupeUnits.contains(b)){
                troupeUnits.remove(b);
                for (int i = troupeUnits.size()-1; i < 4; i++) {
                    turnOver[i] = true;
                }
            } else if(enemyUnits.contains(b)){
                enemyUnits.remove(b);
                eventList.getItems().add(0, a.getName() + " leveled up");
                levelUp(a);
            }
            checkGameOver();
            cont = false;
        }
        return cont;
    }

    //calculates the damage done
    public int calcDamage(Hero a, Hero b){
        double wt = 1;
        double eff = 1;
        double res = 1;
        //weapon triangle
        //Red Weapons: 20%+ Green, 20%- Blue
        //Blue Weapons: 20%+ Red, 20%- Green
        //Green Weapons: 20%+ Blue, 20%- Red
        //Gray Weapons: Same To Every Weapon
        if(a.getWeapon().getType().equals("red")){
            if(b.getWeapon().getType().equals("green")){
                wt = 1.2;
            } else if (b.getWeapon().getType().equals("blue")){
                wt = 0.8;
            }
        } else if(a.getWeapon().getType().equals("blue")){
            if(b.getWeapon().getType().equals("red")){
                wt = 1.2;
            } else if (b.getWeapon().getType().equals("green")){
                wt = 0.8;
            }
        } else if(a.getWeapon().getType().equals("green")){
            if(b.getWeapon().getType().equals("blue")){
                wt = 1.2;
            } else if (b.getWeapon().getType().equals("red")){
                wt = 0.8;
            }
        }

        if(b.getWeapon().getName().equals("Staff")){
            eff = 0.5;
        }

        if(whichMap == 0 && (b.getWeapon().getName().equals("Lance") || b.getWeapon().getName().equals("Sword") || b.getWeapon().getName().equals("Axw"))){
            res = 1.2;
        } else if (whichMap == 1 && b.getWeapon().getName().equals("Dagger")){
            res = 1.2;
        } else if (whichMap == 2 && b.getWeapon().getName().equals("Bow")){
            res = 1.2;
        } else if (whichMap == 3 && b.getWeapon().getName().equals("Tome")){
            res = 1.2;
        }


        int calc;

        if(a.getWeapon().getName().equals("Tome") || a.getWeapon().getName().equals("Staff")){
             calc = (int)(Math.round((a.getAttack()+a.getWeapon().getMight()) * wt * eff - b.getResistance()*res));
        } else {
            calc =  (int)(Math.round((a.getAttack()+a.getWeapon().getMight()) * wt * eff - b.getDefense()*res));
        }

        if(a.equals(b)){
            calc = 0;
        }

        //cannto deal negative damage
        if(calc < 0){
            calc = 0;
        }
        return calc;
    }

    //i learned this through watching a bfs algorithim video on youtube but i discluded the concept of queing because it was
    //not necessary in my case.
    public int findPathDist(Locations start, Locations end){
        ArrayList<ArrayList<Locations>> places = new ArrayList<>();
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        boolean notFound = true;
        places.add(new ArrayList<Locations>());
        places.get(0).add(start);
        int count = 0;

        while(notFound) {
            count ++;
            int length = places.get(0).size();
            places.add(new ArrayList<Locations>());

            for (int j = 0; j < length; j++) {
                for (int i = 0; i < 4; i++) {
                    //looks at grid spots above below left and right
                    int r = places.get(0).get(j).getX() + dr[i];
                    int c = places.get(0).get(j).getY() + dc[i];

                    //checks if grid spot if empty and in bounds
                    if (r >= 0 && r < btn.length && c >= 0 && c < btn[0].length && terrainGrid[r][c] == 0) {
                        boolean put = true;
                        //checks whether or not the grid spot is already in the new array
                        for (int k = 0; k < places.get(1).size(); k++) {
                            if(places.get(1).get(k).getX() == r && places.get(1).get(k).getY() == c){
                                put = false;
                                System.out.println("equals in 1");
                            }
                        }
                        //checks for old array
                        for (int k = 0; k < places.get(0).size(); k++) {
                            if(places.get(0).get(k).getX() == r && places.get(0).get(k).getY() == c){
                                put = false;
                                System.out.println("equals in 0");
                            }
                        }
                        //if not in both it adds it to the new array
                        if(put){
                            places.get(1).add(new Locations(r, c));
                            System.out.println(places.get(1).get(places.get(1).size()-1));
                        }
                    }
                }
            }

            //checks if any of the locations in new array are the end location
            for (Locations found : places.get(1)) {
                if(found.getX() == end.getX() && found.getY() == end.getY()){
                    notFound = false;
                }
            }
            places.remove(0);

            if(count >= 100){
                isBarrier = true;
                return 0;
            }
        }

        return places.get(0).size();
    }

    public void forfeit(){
        troupeUnits.clear();
        checkGameOver();
    }

    public void checkGameOver(){
        int gemsWon = 0;
        int crownsWon = 0;

        if(troupeUnits.size() == 0){
            resPane.setVisible(true);
            resLbl.setText("YOU LOST");
        } else if (enemyUnits.size() == 0){
            resPane.setVisible(true);
            resLbl.setText("YOU WON");
            gemsWon = level + randomNumber(-1, 2);
            if(gemsWon == 0){
                gemsWon++;
            }
            gems += gemsWon;
            crownsWon = troupeUnits.size() * 200 * level + randomNumber(0, level*100);
            crowns += crownsWon;
        }

        gWonLbl.setText(String.valueOf(gemsWon));
        cWonLbl.setText(String.valueOf(crownsWon));
    }

    public void levelUp(Hero x){
        int[] val = {randomNumber(0, 1), randomNumber(0, 1), randomNumber(0, 1), randomNumber(0, 1), randomNumber(0, 1)};
        x.setTotalHp(x.getTotalHp()+val[0]);
        x.setAttack(x.getAttack()+val[1]);
        x.setDefense(x.getDefense()+val[1]);
        x.setSpeed(x.getSpeed()+val[1]);
        x.setResistance(x.getResistance()+val[1]);
        x.setLevel(x.getLevel()+1);
    }

    //checks if gridspot is empty
    public boolean checkEmptyAround(int i, int j){
        return terrainGrid[i][j] == 0 && avatarGrid[i][j] == 0;
    }

    //gets the distance between two points in terms of the number of spaces one has to move horizontally and vertically
    //in order to reach the destinacion
    public int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x2-x1) + Math.abs(y2-y1);
    }

    //if there is a distance of two between the two points then this checks whether or not there is an obstacle between
    //those two points if the space with a distance of 2 is diagonal then it checks through switching the coordinates around and if
    //the new coordinates are one away from the original.
    public boolean noObstacle(int x1, int y1, int x2, int y2){
        if(getDistance(x1, y1, x2, y2) == 2){
            return terrainGrid[x2-(x2-x1)/2][y2-(y2-y1)/2] == 0 || (terrainGrid[x1][y2] == 0 && getDistance(x1, y2, x1, y1) == 1) || (terrainGrid[x2][y1] == 0 && getDistance(x2, y1, x1, y1) == 1);
        }
        return true;
    }

    public int randomNumber(int a, int b) {
        double x = Math.floor(Math.random() * (b - a + 1) + a);
        return (int) x;
    }
}