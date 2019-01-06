package coderproprement.lpiem.com.projetcoderproprement;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class JsonErrorDisplayer {
    private Context context;

    public JsonErrorDisplayer(Context context) {
        this.context = context;
    }

    public void showErrorMessageWithToast() {
        Toast.makeText(context, R.string.jsonErrorMessage, Toast.LENGTH_SHORT).show();
    }

    public void showErrorMessageWithSnackbar(){
        View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        View view = rootView.findViewById(R.id.comicListView);

        Snackbar.make(view,R.string.jsonErrorMessage,Snackbar.LENGTH_SHORT).show();
    }
}
