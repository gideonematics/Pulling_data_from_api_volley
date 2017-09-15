package iconium.com.pulling_data_from_api_volley;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ogie on 9/14/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Developer> {

    private static List<Developer> developerData;
    private static int resource;
    private static Context context;



    public ListViewAdapter(@NonNull Context _context, @LayoutRes int _resource, @NonNull List<Developer> _developerData) {
        super(_context, _resource, _developerData);

        developerData = _developerData;
        resource = _resource;
        context = _context;


    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        final Developer  developer = getItem(position);

        //inflate new view if view is null
        if(convertView == null) {
            view = (View) LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);
        }else{
            view = (LinearLayout) convertView;
        }

        //update textview with developer's user name
        ((TextView) view.findViewById(R.id.devsUserName))
                .setText(developerData.get(position).getLogin());
        ImageView imageView = ((ImageView)view.findViewById(R.id.devsAvater));

        //update imageview with image from server
        Picasso.with(context)
                .load(developerData.get(position).getAvatarUrl())
                .fit()
                .placeholder(R.drawable.profile_avatar_placeholder_large)
                .error(R.drawable.profile_avatar_placeholder_large)
                .into(imageView);


        //show details of developer if imageview is clicked
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent developerProfileActivityIntent = new Intent(context,DeveloperProfileDisplay.class);
                developerProfileActivityIntent.putExtra("dev", (Parcelable) developer);
                context.startActivity(developerProfileActivityIntent);



            }
        });



        return view;
    }









}
