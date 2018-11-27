package coderproprement.lpiem.com.projetcoderproprement.Model;

import android.content.Context;

import java.util.Date;
import java.util.HashMap;

public interface JSONImportInterface {
    HashMap<Integer, Comic> importData(Context context);

    Date createDate(String jsonFormattedDate);

    String loadJSONFromAsset(Context context);
}
