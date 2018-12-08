package coderproprement.lpiem.com.projetcoderproprement.model;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import coderproprement.lpiem.com.projetcoderproprement.MainActivity;
import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;
import coderproprement.lpiem.com.projetcoderproprement.Model.JSONImport;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class JSONImportTest {
    private MainActivity mainActivity;
    private JSONImport jsonImport;

    @Before
    public void setUp(){
        mainActivity = new MainActivity();
        HashMap<Integer, Comic> comicList = new HashMap<>();
        jsonImport = new JSONImport(comicList,jsonImport.getContext());
    }

    @Test
    public void equalsFunction() {
        HashMap<Integer, Comic> comicList2 = new HashMap<>();
        HashMap<Integer, Comic> comicList3 = new HashMap<>();

        JSONImport jsonImport1 = new JSONImport(comicList2,jsonImport.getContext());
        JSONImport jsonImport2 = new JSONImport(comicList3,jsonImport.getContext());

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
    public void createDate() {
        String date = "2017-10-25T00:00:00-0400";
        Date expectedResult = new Date(2017,10,25);
        assertTrue(jsonImport.createDate(date).equals(expectedResult));
    }

    @Test
    private void checkIfFileIsCorrect() throws JSONException {
        Context context = jsonImport.getContext();
        JSONObject okJsonObject = new JSONObject(jsonImport.loadJSONFromAsset(context, "json/sample-ok.json"));
        JSONObject koJsonObject = new JSONObject(jsonImport.loadJSONFromAsset(context, "json/sample-ok.json"));

        assertTrue(jsonImport.checkIfFileIsCorrect(okJsonObject) == true);
        assertFalse(jsonImport.checkIfFileIsCorrect(koJsonObject) == true);
        assertTrue(jsonImport.checkIfFileIsCorrect(koJsonObject) == false);
    }
}