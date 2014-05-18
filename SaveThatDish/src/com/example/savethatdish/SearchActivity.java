package com.example.savethatdish;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends Activity {
	private static final String CONSUMER_KEY = "PYJ9fp4Zs357x8GKEcc2OA";
	private static String CONSUMER_SECRET = "Svw5yWnPK26_WYOrbkcvsC4PMNU";
	private static final String TOKEN = "_o14KmTq969arSh-BdJBIHIBLanS3h2J";
	private static final String TOKEN_SECRET = "MLFvYopfLQVy8YWpN7WObb8u_EA";
	
	private List<String> results = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		Button searchButton = (Button)findViewById(R.id.searchButton);
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				results.clear();
				// TODO Auto-generated method stub
				EditText searchText = (EditText)findViewById(R.id.editTextSearch);
				String query = searchText.getText().toString();
				new SearchTask().execute(query);
			}
		});

		
		
		/**listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
	
		
	}
	
	public class SearchTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... arg0) {
			try {
				
				Yelp yelp = new Yelp(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
			    String response = yelp.search(arg0[0], "0", "La Jolla");  // hardcoded sort and location params for now
			      
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
			ListView listView = (ListView)findViewById(R.id.searchResults);
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
					SearchActivity.this,
					android.R.layout.simple_list_item_1, results);
			listView.setAdapter(arrayAdapter);
		}
	
	}
}