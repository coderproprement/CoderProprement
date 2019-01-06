package coderproprement.lpiem.com.projetcoderproprement.model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;
import coderproprement.lpiem.com.projetcoderproprement.Model.ComicCreator;
import coderproprement.lpiem.com.projetcoderproprement.Model.JSONImport;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class JSONImportTest {
    private JSONImport jsonImport;
    private Context context;

    @Before
    public void setUp(){
        HashMap<Integer, Comic> comicList = new HashMap<>();
        jsonImport = new JSONImport(comicList);
        this.context = InstrumentationRegistry.getTargetContext();
        this.comicListTestInit();
    }

    @Test
    public void equalsFunction() {
        HashMap<Integer, Comic> comicList2 = new HashMap<>();
        HashMap<Integer, Comic> comicList3 = new HashMap<>();

        JSONImport jsonImport1 = new JSONImport(comicList2);
        JSONImport jsonImport2 = new JSONImport(comicList3);

        comicList2.put(3,new Comic());
        comicList3.put(3,new Comic());

        assertEquals(jsonImport,jsonImport1);
        assertEquals(jsonImport,jsonImport1);
        assertEquals(jsonImport,jsonImport2);

        assertEquals(jsonImport1,jsonImport1);

        assertEquals(jsonImport1,jsonImport);
        Comic c = new Comic();
        jsonImport2.getComicList().put(0,c);

        assertFalse(jsonImport1.equals(jsonImport2));
        jsonImport2.getComicList().remove(0);
        assertTrue(jsonImport1.equals(jsonImport2));
    }

    @Test
    public void createDate() throws ParseException{
        String date = "2017-10-25T00:00:00-0400";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'-0400'");
        Date date1 = format.parse(date);

        assertTrue(jsonImport.createDate(date).equals(date1));
    }

    @Test
    public void checkIfCorrectFileCanBeReaden() throws JSONException {
        JSONObject okJsonObject = new JSONObject(jsonImport.loadJSONFromAsset(this.context, "json/sample-ok.json"));

        assertTrue(jsonImport.checkIfFileIsCorrect(okJsonObject) == true);
    }

    @Test(expected= JSONException.class)
    public void checkIfIncorrectFileCannotBeReaden() throws JSONException {
        JSONObject koJsonObject = new JSONObject(jsonImport.loadJSONFromAsset(this.context, "json/sample-ko.json"));

        assertTrue(jsonImport.checkIfFileIsCorrect(koJsonObject) == true);
    }

    @Test
    public void AddComicToList(){
        assertTrue(this.jsonImport.getCurrentError().equals(""));
    }

    private void comicListTestInit() {
        Comic c = new Comic();
        int id = 3;
        this.jsonImport.addComic(c,3);
    }

    @Test
    public void isElementPresentInArray(){
        Comic c = new Comic();
        Comic c2 = new Comic("Test comic",
                "A test comic",
                "ds45743dqs",
                null,
                4.03f,
                3,
                "no image url",
                null);
        assertTrue(this.jsonImport.isPresent(c)==true);
        assertFalse(this.jsonImport.isPresent(c2));
    }

    @Test
    public void getElementById(){
        Comic c = new Comic();
        assertTrue(this.jsonImport.getElementById(c)==3);
    }

    @Test
    public void removeElement(){
        Comic c = new Comic();
        Comic c2 = new Comic("Test comic",
                "A test comic",
                "ds45743dqs",
                null,
                4.03f,
                3,
                "no image url",
                null);

        this.jsonImport.removeComic(c);
        assertTrue(this.jsonImport.getCurrentError()=="");

        this.jsonImport.removeComic(c2);
        assertTrue(this.jsonImport.getCurrentError()!="");
    }

    @After
    public void tearDown() throws Exception {

    }
}