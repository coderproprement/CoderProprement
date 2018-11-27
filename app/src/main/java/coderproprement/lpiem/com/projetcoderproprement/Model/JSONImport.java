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
    private List<Comic> comicList;
    public final String OKJSONADDRESSFILE = "json/sample-ok.json";
    public final String KOJSONADDRESSFILE = "json/sample-ko.json";

    public JSONImport(List<Comic> comicList) {
        this.comicList = comicList;
    }

    public List<Comic> getComicList() {
        return comicList;
    }

    public void setComicList(List<Comic> comicList) {
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
    public HashMap<Integer, Comic> importData(Context context) {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray comicListJsonArray = obj.getJSONArray("results");
            HashMap<Integer, Comic> comicList = new HashMap<>();

            if(obj.getInt("code") == 500){
                return null;
            }else if (obj.getInt("code") == 200) {

                for (int i = 0; i < comicListJsonArray.length(); i++) {
                    Comic newComic;
                    JSONObject comicJsonObject = (JSONObject) comicListJsonArray.get(i);
                    int id = comicJsonObject.getInt("id");
                    String title = comicJsonObject.getString("title");
                    int issueNumber = comicJsonObject.getInt("issueNumber");
                    Date comicParutionDate = this.createDate(comicJsonObject.getString("date"));
                    String description = comicJsonObject.getString("description");
                    String diamondCode = comicJsonObject.getString("diamondCode");

                    Float price = BigDecimal.valueOf(comicJsonObject.getDouble("price")).floatValue();
                    int pageCount = comicJsonObject.getInt("pageCount");
                    String imagePath = comicJsonObject.getString("image");

                    JSONArray comicCreatorsList = comicJsonObject.getJSONArray("creators");
                    List<ComicCreator> comicCreatorList = new ArrayList<>();
                    if (comicCreatorsList.length() > 0) {
                        for (int j = 0; j < comicCreatorsList.length(); i++) {
                            ComicCreator newComicCreator;
                            JSONObject comicCreatorJsonObject = (JSONObject) comicCreatorsList.get(j);
                            String firstName = comicCreatorJsonObject.getString("name");
                            String role = comicCreatorJsonObject.getString("role");

                            newComicCreator = new ComicCreator(firstName, role);
                            comicCreatorList.add(newComicCreator);
                        }
                    }

                    newComic = new Comic(title, issueNumber, description, diamondCode, comicParutionDate, price, pageCount, imagePath, comicCreatorList);
                    comicList.put(id, newComic);
                }
                return comicList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("yourfilename.json");
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
        int year = Integer.parseInt(formattedDate.substring(0,3));
        int month = Integer.parseInt(formattedDate.substring(5,6));
        int day = Integer.parseInt(formattedDate.substring(8,9));

        return new Date(year,month,day);
    }
}
