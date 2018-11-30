package coderproprement.lpiem.com.projetcoderproprement;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;
import coderproprement.lpiem.com.projetcoderproprement.Model.JSONImport;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        HashMap<Integer, Comic> comicList = new HashMap<>();
        JSONImport jsonImport = new JSONImport(comicList);
        comicList = jsonImport.importData(context,JSONImport.OKJSONADDRESSFILE);
        displayComicHashMap(comicList);
    }

    private void displayComicHashMap(HashMap<Integer, Comic> comicList){
        for(Map.Entry<Integer, Comic> entry : comicList.entrySet()) {
            Integer key = entry.getKey();
            Comic value = entry.getValue();

            Log.d("Comic n°"+key,value.toString());
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainActivity that = (MainActivity) o;
        return Objects.equals(context, that.context);
    }

    @Override
    public int hashCode() {

        return Objects.hash(context);
    }
}
