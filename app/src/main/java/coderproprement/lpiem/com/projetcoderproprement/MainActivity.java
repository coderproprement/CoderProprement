package coderproprement.lpiem.com.projetcoderproprement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;
import coderproprement.lpiem.com.projetcoderproprement.Model.JSONImport;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Context context = this;
    private ListView comicListView;
    private List<Comic> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<Integer, Comic> comicList = new HashMap<>();
        //displayComicHashMap(new JSONImport(comicList).importData(context,JSONImport.OKJSONADDRESSFILE));
        comicListView = (ListView) findViewById(R.id.comicListView);

        comicList.putAll(new JSONImport(comicList).importData(context,JSONImport.OKJSONADDRESSFILE));

        //displayComicHashMap(comicList);

       list = new ArrayList<Comic>(comicList.values());
       //Log.d("ComicList",list.toString());
        
       CustomBaseAdapter adapter = new CustomBaseAdapter(this, list);
       comicListView.setAdapter(adapter);
       comicListView.setOnItemClickListener(this);

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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(context, "You have clicked on Comic " + list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(getBaseContext(),   DetailsActivity.class);
        myIntent.putExtra("title", list.get(position).getTitle());
        myIntent.putExtra("description", list.get(position).getDescription());
        SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMMM  yyyy");
        String details = format.format(list.get(position).getParutionDate());
        myIntent.putExtra("details", details);
        ArrayList creators = new ArrayList<String>();
        for(int i = 0; i<list.get(position).getComicCreatorsList().size(); i++){
            creators.add(list.get(position).getComicCreatorsList().get(i).getName());
        }
        myIntent.putExtra("creators", creators);
        startActivity(myIntent);
    }
}
