package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;




public class Controller implements Initializable{

    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
    int type = 0;

    @FXML
    public TableView<TableRowDataModel> Game;
    @FXML
    private TableColumn<TableRowDataModel, String> GameTitle;
    @FXML
    private TableColumn<TableRowDataModel, String> GameTime;
    @FXML
    public Button allcheck;
    @FXML
    public Button believe;
    @FXML
    public Button salegamebutton;
    @FXML
    public Button bundlegamebutton;
    @FXML
    public Button nowfreebutton;

    ObservableList<TableRowDataModel> Gamelist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("FXML Load Complete");
        GameTitle.setCellValueFactory(cellData -> cellData.getValue().TitleProperty());
        GameTime.setCellValueFactory(cellData -> cellData.getValue().TimeProperty());
        Game.setItems(Gamelist);
    }

    @FXML
    public void Double() {
        Game.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String urltemp = null;
                if (event.getClickCount() > 1) {
                    if(type == 0) {
                        urltemp = Main.freegame[Game.getSelectionModel().getFocusedIndex()][2];
                    }
                    else if(type == 1)
                    {
                        urltemp = Main.steamgame.get(Game.getSelectionModel().getFocusedIndex())[2];
                    }
                    else if(type == 2)
                    {
                        urltemp = Main.nowgame.get(Game.getSelectionModel().getFocusedIndex())[2];
                    }
                    else if(type == 3)
                    {
                        urltemp = Main.salegame.get(Game.getSelectionModel().getFocusedIndex())[2];
                    }
                    else if(type == 4)
                    {
                        urltemp = Main.bundlegame.get(Game.getSelectionModel().getFocusedIndex())[2];
                    }
                    try
                    {
                        Desktop.getDesktop().browse(new URI("http://itcm.co.kr" + urltemp));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (URISyntaxException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @FXML
    public void AllFreeGame(){
        type = 0;
        Gamelist.clear();
        for(int i=0;i<30;i++)
            Gamelist.add(new TableRowDataModel(new SimpleStringProperty(Main.freegame[i][0]),new SimpleStringProperty(Main.freegame[i][1])));
        //GameTitle.setItems(new List<String>);
    }

    @FXML
    public void SteamGame(){
        type = 1;
        Gamelist.clear();
        for(int i = 0;i<Main.steamgame.size();i++)
        {
            Gamelist.add(new TableRowDataModel(new SimpleStringProperty(Main.steamgame.get(i)[0]),new SimpleStringProperty(Main.steamgame.get(i)[1])));
        }
    }

    @FXML
    public void NowGame(){
        type = 2;
        Gamelist.clear();
        for(int i = 0;i<Main.nowgame.size();i++)
        {
            Gamelist.add(new TableRowDataModel(new SimpleStringProperty(Main.nowgame.get(i)[0]),new SimpleStringProperty(Main.nowgame.get(i)[1])));
        }
    }

    @FXML
    public void SaleGameCheck(){
        type = 3;
        Gamelist.clear();
        for(int i = 0;i<Main.salegame.size();i++)
        {
            Gamelist.add(new TableRowDataModel(new SimpleStringProperty(Main.salegame.get(i)[0]),new SimpleStringProperty(Main.salegame.get(i)[1])));
        }
    }

    @FXML
    public void BundleGameCheck(){
        type = 4;
        Gamelist.clear();
        for(int i = 0;i<Main.bundlegame.size();i++)
        {
            Gamelist.add(new TableRowDataModel(new SimpleStringProperty(Main.bundlegame.get(i)[0]),new SimpleStringProperty(Main.bundlegame.get(i)[1])));
        }
    }



}


class TableRowDataModel {
    private StringProperty Title;
    private StringProperty Time;

    public TableRowDataModel(StringProperty Title, StringProperty Time) {
        this.Title = Title;
        this.Time= Time;
    }

    public StringProperty TitleProperty() {
        return Title;
    }
    public StringProperty TimeProperty() {
        return Time;
    }
}