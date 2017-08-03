package com.example.com.sampleflickermappointer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


   Button btnjson;
    GridView gridView;
    int[] calc={1,2,3,4,5,6,7,8,9,11,12,13,14,15,17,18,19,20,21,22,23};
    TextView textView;
    LinearLayout lincalc;
    static ArrayList<Model> modelArrayList =new ArrayList<>();
    ProgressDialog progressDialog;
     static  MainActivity mainActivity;
    static Boolean load=false;
    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionDetector=new ConnectionDetector(MainActivity.this);

        btnjson=(Button)findViewById(R.id.btn2);
        gridView=(GridView)findViewById(R.id.gridview);
        textView=(TextView)findViewById(R.id.tvresult);
        lincalc=(LinearLayout)findViewById(R.id.lincalc);

        numbergrid numbergrid=new numbergrid();
        gridView.setAdapter(numbergrid);

        mainActivity=MainActivity.this;


        btnjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(connectionDetector.isConnectingToInternet()) {

                    if (modelArrayList.isEmpty()) {
                        new Getdata().execute();
                    } else {
                        startActivity(new Intent(MainActivity.this, MapsActivity.class));
                    }
                }else{

                    Toast.makeText(MainActivity.this,"No internet",Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


    public class Getdata extends AsyncTask<Void, ArrayList<Model>, ArrayList<Model>> {

        HttpHandler handler = new HttpHandler();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Loading");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Please Wait");
            progressDialog.show();

        }

        @Override
        protected ArrayList<Model> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String st;

            try {
                st = handler.urltojson("https://api.flickr.com/services/rest/?method=flickr.photos.search&licence=1,2,4,7&has_geo=1&extras=original_format,description,geo,date_upload,owner_name&format=json&nojsoncallback=1&api_key=ccc0bafafb2e761c02f4b2a5feb5ba88");

                if (st != null) {

                    JSONObject jObject = new JSONObject(st);
                    //Log.e("resulktt",""+jObject.get("status"));
                    JSONObject results = jObject.getJSONObject("photos");
                    JSONArray jsonArray = results.getJSONArray("photo");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObject2 = jsonArray.getJSONObject(i);
                        Model model = new Model();
                        model.setLatitude(jObject2.getString("latitude"));
                        model.setLongitude(jObject2.getString("longitude"));
                        model.setOwnername(jObject2.getString("ownername"));
                        model.setTitle(jObject2.getString("title"));
                        modelArrayList.add(model);


                    }


                } else {

                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

                    progressDialog.dismiss();
                    startActivity(new Intent(MainActivity.this, MapsActivity.class));

        }
    }




    public class  numbergrid extends BaseAdapter{


        @Override
        public int getCount() {
            return calc.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            Button button=new Button(MainActivity.this);
            button.setText(""+calc[i]);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Double i1 = calc[i] + 12.61;

                    textView.setText("Result=" + i1);


                }
            });

            return button;
        }
    }


}
