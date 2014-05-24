package com.example.savethatdish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultsActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		
		ListView listView = (ListView)findViewById(R.id.searchResults);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1, SearchActivity.returnResults());
		listView.setAdapter(arrayAdapter);

		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent restaurant = new Intent(this, RestaurantActivity.class);
				startActivity(restaurant);
			}
			
		});
		
	}
}