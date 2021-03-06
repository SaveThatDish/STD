package com.example.savethatdish;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends Activity {
	public static final String CONSUMER_KEY = "PYJ9fp4Zs357x8GKEcc2OA";
	public static String CONSUMER_SECRET = "Svw5yWnPK26_WYOrbkcvsC4PMNU";
	public static final String TOKEN = "_o14KmTq969arSh-BdJBIHIBLanS3h2J";
	public static final String TOKEN_SECRET = "MLFvYopfLQVy8YWpN7WObb8u_EA";
	
	private static List<String> results = new ArrayList<String>();
	private EditText searchText, locationText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		ImageButton imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
		imageButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				results.clear();
				searchText = (EditText)findViewById(R.id.editTextSearchKeyword);
				locationText = (EditText)findViewById(R.id.editTextSearchLocation);
				String query = searchText.getText().toString();
				String locationQuery = locationText.getText().toString();
				new SearchTask().execute(query, locationQuery);
			}
		});
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
	
	
	public static List<String> returnResults() {
		return results;
	}
	
	public class SearchTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... arg0) {
			try {
				
				Yelp yelp = new Yelp(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
			    String response = yelp.search(arg0[0], "0", arg0[1]);  // hardcoded sort and location params for now
			      
			    JSONObject obj = new JSONObject(response);
			    JSONArray businesses = obj.getJSONArray("businesses");
			    
			    int n = businesses.length();
			    for(int i = 0; i < n; i++) {
			    	results.add(businesses.getJSONObject(i).getString("name"));
			    }
			    
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(Void result) {
			Intent results = new Intent(SearchActivity.this, ResultsActivity.class);
			startActivity(results);
		}
	
	}
}