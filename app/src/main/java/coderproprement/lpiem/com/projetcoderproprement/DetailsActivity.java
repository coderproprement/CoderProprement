package coderproprement.lpiem.com.projetcoderproprement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;

import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;
import coderproprement.lpiem.com.projetcoderproprement.Model.ComicCreator;


public class DetailsActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    TextView details;
    TextView credits;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        details = findViewById(R.id.details);
        credits = findViewById(R.id.credit);
        description.setMovementMethod(new ScrollingMovementMethod());

        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
        details.setText(getIntent().getStringExtra("details"));
        ArrayList<String> creatorsList = getIntent().getStringArrayListExtra("creators");
        String creators = "Auteurs: ";
        for(int i = 0; i<creatorsList.size() ; i++){
            if(i==0){
                creators =  creators +creatorsList.get(i);
            }
            else {
                creators = creators + ", " + creatorsList.get(i);
            }
        }
        credits.setText(creators);
    }



}
