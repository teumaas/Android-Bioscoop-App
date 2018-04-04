package nl.avans.informatica.bioscoopapp.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import nl.avans.informatica.bioscoopapp.R;
import nl.avans.informatica.bioscoopapp.domain.MovieReview;

public class ReviewAdapter extends BaseAdapter {

    private static final String TAG = ReviewAdapter.class.getSimpleName();

    private Context mContext;
    private LayoutInflater mInflator;
    private ArrayList reviewsArrayList;

    public ReviewAdapter(Context context, LayoutInflater layoutInflater, ArrayList<MovieReview> reviewsArrayList) {
        mContext = context;
        mInflator = layoutInflater;
        this.reviewsArrayList = reviewsArrayList;
    }

    @Override
    public int getCount() {
        int size = reviewsArrayList.size();
        return size;
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "getItem " + position);
        return reviewsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i(TAG, "getView " + position);

        ViewHolder viewHolder;

        // Create new of gebruik een al bestaande (recycled by Android)


        if (convertView == null) {

            // Als convertView nog niet bestaat maken we een nieuwe aan.
            convertView = mInflator.inflate(R.layout.costum_review_listview, null);

            viewHolder = new ViewHolder();
            viewHolder.review = (TextView) convertView.findViewById(R.id.review);
            viewHolder.reviewDate = (TextView) convertView.findViewById(R.id.reviewDate);

            // Koppel de view aan de viewHolder
            convertView.setTag(viewHolder);
        } else {
            // Als de convertView wel bestaat gebruiken we die.
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MovieReview review = (MovieReview) reviewsArrayList.get(position);
        viewHolder.review.setText(review.getContent()); //ToDo moet vanuit de DB worden opgehaald.
        viewHolder.reviewDate.setText(review.getDate()); //ToDo moet vanuit de DB worden opgehaald.

        return convertView;
    }

    private static class ViewHolder { //wordt alleen in deze class gebruikt daarom geen public class gemaakt
        protected TextView review;
        protected TextView reviewDate;
    }
}

