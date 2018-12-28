package coderproprement.lpiem.com.projetcoderproprement;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;
import coderproprement.lpiem.com.projetcoderproprement.Model.JSONImport;

public class MainActivity extends AppCompatActivity {
    private Context context = this;
    private ListView comicListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<Integer, Comic> comicList = new HashMap<>();
        //displayComicHashMap(new JSONImport(comicList).importData(context,JSONImport.OKJSONADDRESSFILE));
        comicListView = (ListView) findViewById(R.id.comicListView);

        comicList.putAll(new JSONImport(comicList).importData(context,JSONImport.OKJSONADDRESSFILE));

        //displayComicHashMap(comicList);

       List<Comic> list = new ArrayList<Comic>(comicList.values());
       //Log.d("ComicList",list.toString());
        
       CustomBaseAdapter adapter = new CustomBaseAdapter(this, list);
       comicListView.setAdapter(adapter);

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
}
