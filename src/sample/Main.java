package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application{
    public static String[][] freegame = new String[30][3];
    public static String[][] temp = new String[30][3];
    public static ArrayList<String[]> steamgame = new ArrayList<String[]>();
    public static ArrayList<String[]> nowgame = new ArrayList<String[]>();
    public static ArrayList<String[]> salegame = new ArrayList<String[]>();
    public static ArrayList<String[]> bundlegame = new ArrayList<String[]>();

    public static String[] saledata = new String[3];

    public static void main(String[] args)  {
        try {
            int index;

            Document doc =  Jsoup.connect("http://itcm.co.kr/index.php?mid=game_news&category=967347").get();

            Elements gametitle = doc.select("tbody").select("tr").select("[class=\"title \"]").select("[class=\"hx\"]");
            Elements gameURL = doc.select("tbody").select("tr").select("[class=\"title \"]").select("[class=\"hx\"][href]");
            Elements gametime = doc.select("tbody").select("tr").select("[class*=list_timer]");
            List<String> steamlist = gametitle.eachText();
            List<String> steamtimelist = gametime.eachText();
            List<String> gameurllist = gameURL.eachAttr("href");

            for(int i = 0;i<steamlist.size(); i++){
                freegame[i][0] = steamlist.get(i).replace(".","");
                index = freegame[i][0].indexOf("(");
                if(index > 1)
                    freegame[i][0] = freegame[i][0].substring(0,index);
                index = freegame[i][0].indexOf("-",20);
                if(index > 1)
                    freegame[i][0] = freegame[i][0].substring(0,index);
                index = freegame[i][0].indexOf(":",20);
                if(index > 1)
                    freegame[i][0] = freegame[i][0].substring(0,index);
                freegame[i][1] = steamtimelist.get(i+1);
                freegame[i][2] = gameurllist.get(i);
            }
            int i = 1;
            for(String[] st : freegame)
            {
                System.out.println(st[0] + " " + st[1]);
            }
            int t = 0;
            for(int j = 0;j<30;j++)
            {
                if(freegame[j][0].indexOf("]") > 0 || freegame[j][0].indexOf("에픽스토어") >= 0) {

                    steamgame.add(new String[]{freegame[j][0], freegame[j][1],freegame[j][2]});
                }
            }
            for(int j = 0;j<30;j++)
            {

                if(!(freegame[j][1].indexOf("종료") >= 0))
                {
                    nowgame.add(new String[]{freegame[j][0], freegame[j][1],freegame[j][2]});
                }
            }

            Document docsale = Jsoup.connect("http://itcm.co.kr/index.php?mid=game_news&category=1070").get();

            gametitle = docsale.select("tbody").select("tr").select("[class=\"title \"]").select("[class=\"hx\"]");
            gameURL = docsale.select("tbody").select("tr").select("[class=\"title \"]").select("[class=\"hx\"][href]");
            gametime = docsale.select("tbody").select("tr").select("[class*=list_timer]");
            steamlist = gametitle.eachText();
            steamtimelist = gametime.eachText();
            gameurllist = gameURL.eachAttr("href");


            for(i = 0;i<steamlist.size(); i++){
                temp[i][0] = steamlist.get(i).replace(".","");
                index = temp[i][0].indexOf("(");
                if(index > 1)
                    temp[i][0] = temp[i][0].substring(0,index);
                index = temp[i][0].indexOf("-",20);
                if(index > 1)
                    temp[i][0] = temp[i][0].substring(0,index);
                index = temp[i][0].indexOf(":",20);
                if(index > 1)
                    temp[i][0] = temp[i][0].substring(0,index);
                temp[i][1] = steamtimelist.get(i+1);
                temp[i][2] = gameurllist.get(i);
            }
            for(String[] st : temp)
            {
                System.out.println(st[0] + " " + st[1]);
            }
            for(int j = 0;j<30;j++)
            {

                if(!(temp[j][1].indexOf("종료") >= 0))
                {
                    salegame.add(new String[]{temp[j][0], temp[j][1],temp[j][2]});
                }
            }

            Document docbundle = Jsoup.connect("http://itcm.co.kr/index.php?mid=game_news&category=2098178").get();

            gametitle = docbundle.select("tbody").select("tr").select("[class=\"title \"]").select("[class=\"hx\"]");
            gameURL = docbundle.select("tbody").select("tr").select("[class=\"title \"]").select("[class=\"hx\"][href]");
            gametime = docbundle.select("tbody").select("tr").select("[class*=list_timer]");
            steamlist = gametitle.eachText();
            steamtimelist = gametime.eachText();
            gameurllist = gameURL.eachAttr("href");


            for(i = 0;i<steamlist.size(); i++){
                temp[i][0] = steamlist.get(i).replace(".","");
                index = temp[i][0].indexOf("(");
                if(index > 1)
                    temp[i][0] = temp[i][0].substring(0,index);
                index = temp[i][0].indexOf("-",20);
                if(index > 1)
                    temp[i][0] = temp[i][0].substring(0,index);
                index = temp[i][0].indexOf(":",20);
                if(index > 1)
                    temp[i][0] = temp[i][0].substring(0,index);
                temp[i][1] = steamtimelist.get(i+1);
                temp[i][2] = gameurllist.get(i);
            }
            for(String[] st : temp)
            {
                System.out.println(st[0] + " " + st[1]);
            }
            for(int j = 0;j<30;j++)
            {

                if(!(temp[j][1].indexOf("종료") >= 0))
                {
                    bundlegame.add(new String[]{temp[j][0], temp[j][1],temp[j][2]});
                }
            }




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("채고의 게임정보");
        primaryStage.setScene(new Scene(root, 730, 480));
        primaryStage.show();
    }
}


