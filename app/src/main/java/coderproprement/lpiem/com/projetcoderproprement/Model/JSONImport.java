package coderproprement.lpiem.com.projetcoderproprement.Model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JSONImport implements JSONImportInterface{
    private HashMap<Integer, Comic> comicList;
    public final static String OKJSONADDRESSFILE = "json/sample-ok.json";
    public final static String KOJSONADDRESSFILE = "json/sample-ko.json";

    private String currentError = "";

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

    public String getCurrentError() {
        return currentError;
    }

    public JSONImport(HashMap<Integer, Comic> comicList) {
        this.comicList = comicList;
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
        return Objects.equals(comicList, that.comicList) &&
                Objects.equals(currentError, that.currentError) &&
                Objects.equals(context, that.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comicList, currentError, context);
    }

    @Override
    public String toString() {
        return "JSONImport{" +
                "comicList=" + comicList +
                ", currentError='" + currentError + '\'' +
                ", context=" + context +
                '}';
    }

    @Override
    public HashMap<Integer, Comic> importData(Context context,String filePath) {
        try {
            HashMap<Integer, Comic> comicList = new HashMap<>();

            if(this.checkIfFileIsCorrect(new JSONObject(loadJSONFromAsset(context, filePath))) == false){
                return null;
            }else if (this.checkIfFileIsCorrect(new JSONObject(loadJSONFromAsset(context, filePath))) == true) {
                for (int i = 0; i < new JSONObject(loadJSONFromAsset(context, filePath)).getJSONArray("results").length(); i++) {
                    addComicToList(new JSONObject(loadJSONFromAsset(context, filePath)).getJSONArray("results"), comicList, i);
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
     * Check if an element is present in the hashmap
     * @param element comic element suspected to be present
     * @return true => present | false => not present
     */
    public boolean isPresent(Comic element){
        for (Comic iteration : this.comicList.values()) {
            if(iteration.equals(element)){
                return true;
            }
        }
        return false;
    }

    /**
     * Add a comic to the hashmap
     * @param newComic new comic to be added
     * @param id the key of the comic
     */
    public void addComic(Comic newComic, int id){
        if(this.isPresent(newComic)){
            this.currentError = "L'élément est déjà existant dans la base!";
        }else{
            this.comicList.put(id, newComic);
        }
    }

    /**
     * Get the key of the comic in the hashmap
     * @param newComic object of search
     * @return {key} key of the element | If is not found => -1
     */
    public int getElementById(Comic newComic){
        for (Map.Entry<Integer, Comic> entry : this.comicList.entrySet()) {
            if(this.comicList.get(entry.getKey()).equals(newComic)){
                return entry.getKey();
            }
        }
        return -1;
    }

    public void removeComic(Comic newComic){
        if(!this.isPresent(newComic)){
            this.currentError = "L'élément est inexistant dans la base!";
        }else{
            this.comicList.remove(newComic);
        }
    }

    /**
     * Add a comic to an HashMap arrayList
     * @param comicListJsonArray jsonobject managing retrieving data from the json file
     * @param comicList list of comics
     * @param i current comic managed by the jsonObject
     * @throws JSONException JSON Exception Managment
     */
    private void addComicToList(JSONArray comicListJsonArray, HashMap<Integer, Comic> comicList, int i) throws JSONException {
        JSONObject comicJsonObject = (JSONObject) comicListJsonArray.get(i);
        String comicIdArray[] = comicJsonObject.getString("title").split("#");
        List<ComicCreator> comicCreatorList = new ArrayList<>();

        JSONArray comicCreatorsJSONList = comicJsonObject.getJSONArray("creators");
        if (comicCreatorsJSONList.length() > 0) {
            for (int j = 0; j < comicCreatorsJSONList.length(); j++) {
                addAuthorToComic((JSONObject) comicCreatorsJSONList.get(j), comicCreatorList, j);
            }
        }

        comicList.put(comicJsonObject.getInt("id"),
                    new Comic(comicIdArray[0],
                    comicJsonObject.getString("description"),
                    comicJsonObject.getString("diamondCode"),
                    this.createDate(comicJsonObject.getString("date")),
                    BigDecimal.valueOf(comicJsonObject.getDouble("price")).floatValue(),
                    comicJsonObject.getInt("pageCount"),
                    comicJsonObject.getString("image"),
                    comicCreatorList));
    }

    /**
     * Add an author to a comic Object
     * @param comicCreatorsJSONObject json instance managing data from file
     * @param comicCreatorList list of creators
     * @param j counter
     * @throws JSONException default JSONException
     */
    private void addAuthorToComic(JSONObject comicCreatorsJSONObject, List<ComicCreator> comicCreatorList, int j) throws JSONException {
        ComicCreator comicCreator = new ComicCreator(comicCreatorsJSONObject.getString("name"), comicCreatorsJSONObject.getString("role"));
        comicCreatorList.add(j,comicCreator);
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
        try {
            InputStream is = context.getAssets().open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public Date createDate(String formattedDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'-0400'");
        Date date = null;
        try {
            date = format.parse(formattedDate);
            System.out.println(date);
            Log.d("MyDateFormatted",date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
