package campbell.ca.hw;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {
    TextView errorTxt;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getTextViews(this.findViewById(android.R.id.content));
        String location = getIntent().getExtras().getString("DATA1");
        if(location == null || location.isEmpty()){
            input.setText(R.string.nodata);
            input.setTextColor(Color.RED);
        } else {
            Log.d("INTDATA1","DATA1"+location);
            input.setText(location);
        }
    }

    public void showMap(View v) {

        getTextViews(v);
        String country = input.getText().toString();
        if(country == null || country.isEmpty()){
            Log.d("Map Activity","No Country");
            errorTxt.setText(R.string.nodata);
            errorTxt.setTextColor(Color.RED);
        } else {
            Uri geoLocation = Uri.parse("geo:0,0?q=" + Uri.encode(country));

            Intent geoIntent = new Intent(Intent.ACTION_VIEW);

            geoIntent.setData(geoLocation);
            if (geoIntent.resolveActivity(getPackageManager()) != null) {
                Log.d("Map Activity","Launching Maps");
                startActivity(geoIntent);
            } else {
                errorTxt.setText(R.string.error_no_geo);
                errorTxt.setTextColor(Color.RED);
                Log.d("Map Activty",getResources().getString(R.string.error_no_geo));
            }
        }
    }

    public void searchWeb(View view){
        Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        String country = input.getText().toString();
        if(country == null || country.isEmpty()) {
            Log.d("Map Activity","No Country");
            errorTxt.setText(R.string.nodata);
            errorTxt.setTextColor(Color.RED);
        } else {
            searchIntent.putExtra(SearchManager.QUERY, country);
            if (searchIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(searchIntent);
            } else {
                errorTxt.setText(R.string.error_no_search);
                errorTxt.setTextColor(Color.RED);
                Log.d("Map Activty", getResources().getString(R.string.error_no_search));
            }
        }
    }

    private void getTextViews(View view){
        input = (EditText)findViewById(R.id.input);
        errorTxt = (TextView)findViewById(R.id.errorView);
    }
}
