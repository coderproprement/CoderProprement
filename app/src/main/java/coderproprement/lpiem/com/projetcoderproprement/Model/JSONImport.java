package coderproprement.lpiem.com.projetcoderproprement.Model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class JSONImport implements JSONImportInterface{
    private HashMap<Integer, Comic> comicList;
    public final static String OKJSONADDRESSFILE = "json/sample-ok.json";
    public final static String KOJSONADDRESSFILE = "json/sample-ko.json";
    private Context context;

    private String currentError = "";

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getCurrentError() {
        return currentError;
    }

    public void setCurrentError(String currentError) {
        this.currentError = currentError;
    }

    public JSONImport(HashMap<Integer, Comic> comicList, Context context) {
        this.comicList = comicList;
        this.context = context;
    }

    public HashMap<Integer, Comic> getComicList() {
        return comicList;
    }

    public void setComicList(HashMap<Integer, Comic> comicList) {
        this.comicList = comicList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONImport that = (JSONImport) o;
        return Objects.equals(comicList, that.comicList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comicList);
    }

    @Override
    public HashMap<Integer, Comic> importData(Context context,String filePath) {
        try {
            JSONArray comicListJsonArray = new JSONObject(loadJSONFromAsset(context, filePath)).getJSONArray("results");
            HashMap<Integer, Comic> comicList = new HashMap<>();

            if(this.checkIfFileIsCorrect(new JSONObject(loadJSONFromAsset(context, filePath))) == false){
                return null;
            }else if (this.checkIfFileIsCorrect(new JSONObject(loadJSONFromAsset(context, filePath))) == true) {
                for (int i = 0; i < comicListJsonArray.length(); i++) {
                    addComicToList(comicListJsonArray, comicList, i);
                }
                return comicList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Add a comic to an HashMap arrayList
     * @param comicListJsonArray jsonobject managing retrieving data from the json file
     * @param comicList list of comics
     * @param i current comic managed by the jsonObject
     * @throws JSONException JSON Exception Managment
     */
    private void addComicToList(JSONArray comicListJsonArray, HashMap<Integer, Comic> comicList, int i) throws JSONException {
        Comic newComic;
        JSONObject comicJsonObject = (JSONObject) comicListJsonArray.get(i);
        int id = comicJsonObject.getInt("id");
        String comicIdArray[] = comicJsonObject.getString("title").split("#");
        String title = comicIdArray[0];
        Date comicParutionDate = this.createDate(comicJsonObject.getString("date"));
        String description = comicJsonObject.getString("description");
        String diamondCode = comicJsonObject.getString("diamondCode");

        Float price = BigDecimal.valueOf(comicJsonObject.getDouble("price")).floatValue();
        int pageCount = comicJsonObject.getInt("pageCount");
        String imagePath = comicJsonObject.getString("image");

        JSONArray comicCreatorsJSONList = comicJsonObject.getJSONArray("creators");
        List<ComicCreator> comicCreatorList = new ArrayList<>();
        if (comicCreatorsJSONList.length() > 0) {
            for (int j = 0; j < comicCreatorsJSONList.length(); j++) {
                addAuthorToComic(comicCreatorsJSONList, comicCreatorList, j);
            }
        }

        newComic = new Comic(title, description, diamondCode, comicParutionDate, price, pageCount, imagePath, comicCreatorList);
        comicList.put(id, newComic);
    }

    private void addAuthorToComic(JSONArray comicCreatorsJSONList, List<ComicCreator> comicCreatorList, int j) throws JSONException {
        ComicCreator newComicCreator;
        JSONObject comicCreatorJsonObject = (JSONObject) comicCreatorsJSONList.get(j);
        String firstName = comicCreatorJsonObject.getString("name");
        String role = comicCreatorJsonObject.getString("role");

        newComicCreator = new ComicCreator(firstName, role);
        comicCreatorList.add(newComicCreator);
    }

    /**
     * Check if the current file can be read by the program
     * @param jsonObject the json object which represent the content of the file
     * @return {true} no problem || {false} can't be readen
     * @throws JSONException the execptions for the JSON Managment
     */
    public boolean checkIfFileIsCorrect(JSONObject jsonObject) throws JSONException {
        if(jsonObject.getInt("code") == 500){
            this.currentError = "The JSON file can not be read by the program. Please check your file.";
            return false;
        }else if(jsonObject.getInt("code") == 200){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String loadJSONFromAsset(Context context,String filePath) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public Date createDate(String formattedDate){
        int year = Integer.parseInt(formattedDate.substring(0,4));
        int month = Integer.parseInt(formattedDate.substring(5,7));
        int day = Integer.parseInt(formattedDate.substring(8,10));

        return new Date(year,month,day);
    }
}
