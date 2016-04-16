package com.example.adapter;

    import java.text.SimpleDateFormat;
    import java.util.List;

    import android.app.Activity;
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.TextView;

    import com.android.volley.toolbox.ImageLoader;
    import com.android.volley.toolbox.NetworkImageView;
    import com.example.models.Advertisement;
    import com.example.megurinelucas.housemart.AppController;
    import com.example.megurinelucas.housemart.R;

/**
 * This class processes data & add to ListView
 *
 * @author LongVH.
 */
public class ListAdvertisementAdapter extends BaseAdapter {

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        private Activity activity;
        private LayoutInflater inflater;
        private List<Advertisement> advertisementItems;

        public ListAdvertisementAdapter(Activity activity, List<Advertisement> advertisementItems) {
            this.activity = activity;
            this.advertisementItems = advertisementItems;
        }

        @Override
        public int getCount() {
            return advertisementItems.size();
        }

        @Override
        public Object getItem(int location) {
            return advertisementItems.get(location);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (inflater == null)
                inflater = (LayoutInflater) activity
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null)
                convertView = inflater.inflate(R.layout.list_row, parent,false);

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
           NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
            //ImageView thumbNail=(ImageView) convertView.findViewById(R.id.thumbnail);

            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView address = (TextView) convertView.findViewById(R.id.address);
            TextView area = (TextView) convertView.findViewById(R.id.area);
            TextView price = (TextView) convertView.findViewById(R.id.price);
            TextView dateCreated = (TextView) convertView.findViewById(R.id.date_created);
            TextView id = (TextView) convertView.findViewById(R.id.hiddenID);

            // getting Advertisement data for the row
            Advertisement ad = advertisementItems.get(position);

            // thumbnail image
            // test link online, using volley
            thumbNail.setImageUrl("http://files.gamebanana.com/img/ico/sprays/feelsgood2.png", imageLoader);
            // using ImageView
            //thumbNail.setImageURI(Uri.parse(ad.getImage1()));

            // title
            title.setText(ad.getTitle());

            // address
            address.setText(ad.getAddress());

            // area
            area.setText("Area: "+ad.getArea()+" m2");

            // price
            price.setText(formatPrice(ad.getPrice()));

            // ID
            id.setText(ad.getId());

            //date created in format "Sat, 16 Apr 2016"
            SimpleDateFormat format=new SimpleDateFormat("EEE, dd MMM yyyy");
            dateCreated.setText("Date Created: "+format.format(ad.getDateCreate()));

            return convertView;
        }

        //Format price as "X.000.000 VND"
        private String formatPrice(String price){
            StringBuilder strBuilder=new StringBuilder(price);

            int index=strBuilder.length()-1;
            while (index>1){
                index-=2;
                if (index > 0) {
                    strBuilder.insert(index,'.');
                    index--;
                }
            }

            return strBuilder.toString()+" VND";
        }

    }

