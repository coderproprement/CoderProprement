package coderproprement.lpiem.com.projetcoderproprement;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<Comic> comicItemList;

    public CustomBaseAdapter(Context context, List<Comic> comicItemList) {
        this.context = context;
        this.comicItemList = comicItemList;
    }

    private class ViewHolder {
        ImageView comicIcon;
        TextView comicTitle;
        TextView comicDate;
        TextView comicPageNumber;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.custom_listview_cell, null);
            holder = new ViewHolder();
            holder.comicTitle = (TextView) convertView.findViewById(R.id.comicTitle);
            holder.comicDate = (TextView) convertView.findViewById(R.id.comicReleaseDate);
            holder.comicPageNumber = (TextView) convertView.findViewById(R.id.comicPageNumber);
            holder.comicIcon = (ImageView) convertView.findViewById(R.id.comicIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Comic comicItem = (Comic) getItem(position);

        holder.comicTitle.setText(comicItem.getTitle());
        holder.comicDate.setText(comicItem.getParutionDate().toString());
        holder.comicPageNumber.setText(comicItem.getPageCount());
        holder.comicIcon.setImageURI(Uri.parse(comicItem.getImageUrl()));

        return convertView;
    }

    @Override
    public int getCount() {
        return comicItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return comicItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return comicItemList.indexOf(getItem(position));
    }

}
