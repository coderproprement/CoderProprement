package coderproprement.lpiem.com.projetcoderproprement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;
import coderproprement.lpiem.com.projetcoderproprement.Model.ComicCreator;


public class DetailsActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    TextView details;
    TextView credits;
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        details = findViewById(R.id.details);
        credits = findViewById(R.id.credit);
        description.setMovementMethod(new ScrollingMovementMethod());
        image = findViewById(R.id.image);

        Intent intent = getIntent();

        title.setText("Titre: "+ intent.getStringExtra("title"));
        description.setText("Synopsis: \n" + intent.getStringExtra("description"));
        details.setText(intent.getStringExtra("details"));

        ArrayList<String> creatorsList = intent.getStringArrayListExtra("creators");
        String creators = "Auteurs: ";

        for(int i = 0; i<creatorsList.size() ; i++){
            if(i == 0){
                creators =  creators +creatorsList.get(i);
            }
            else {
                creators = creators + ", " + creatorsList.get(i);
            }
        }

        credits.setText(creators);

        Glide.with(getApplicationContext())
                .load(intent.getStringExtra("img"))
                .into(image);
    }



}
