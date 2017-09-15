package iconium.com.pulling_data_from_api_volley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static String URL = "https://api.github.com/search/users?q=location:lagos+language:java";
    private static Gson gson = new Gson();
    private static ApiDevelopersPayLoadResolver apiDevelopersPayLoadResolver;
    private static ArrayList<Developer> devsTemp = new ArrayList<>();
    private static ProgressDialog dialog;
    ListView listView;
    JSONArray array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = (ListView) findViewById(R.id.developersListView);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        //create new requestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);



        ApiRequestNegotiator apiRequest = new ApiRequestNegotiator(
                Request.Method.GET,
                URL,
                ApiDevelopersPayLoadResolver.class,
                null,

                new Response.Listener<ApiDevelopersPayLoadResolver>() {
                    @Override
                    public void onResponse(ApiDevelopersPayLoadResolver _payLoad) {
                        devsTemp.clear();
                        apiDevelopersPayLoadResolver = _payLoad;

                        for (Developer d : apiDevelopersPayLoadResolver.getItems()) {
                            devsTemp.add(d);
                        }

                        // create instance of the adapter
                        final ListViewAdapter listViewAdapter = new ListViewAdapter(MainActivity.this, R.layout.developer_card, devsTemp);

                        //bind adapter to listview
                        listView.setAdapter(listViewAdapter);

                        //notify adapter about new data
                        listViewAdapter.notifyDataSetChanged();

                        dialog.dismiss();


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }


        );


        requestQueue.add(apiRequest);

    }



}
