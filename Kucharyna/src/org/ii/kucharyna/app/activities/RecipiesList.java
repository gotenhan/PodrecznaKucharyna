package org.ii.kucharyna.app.activities;

import podreczna.kucharyna.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class RecipiesList extends ListActivity {

	static final String[] RECEPIES = new String[] { "Spaghetti bolognese",
			"Zupa pomidorowa", "Zupa og�rkowa", "Ros�", "Barszcz",
			"Kanapka z serem", "Kanapka z szynk�", "Krem z pora",
			"Kotlet schabowy", "Sa�atka owocowa", "Tiramisu", "Sur�wka z pora",
			"Nale�niki z twarogiem", "Omlet" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new RecipiesListArrayAdapter(this, RECEPIES));
		getListView().setTextFilterEnabled(true);
		setContentView(R.layout.recipieslist);

		Button newButton = (Button) findViewById(R.id.addingRecipe);
		newButton.setOnClickListener(buttonListener);
	}

	private OnClickListener buttonListener = new OnClickListener() {
		public void onClick(View v) {
			// do something when the button is clicked
			Intent i = new Intent(getApplicationContext(), AddingRecipe.class);
			// sending data to new activity
			startActivity(i);
		}
	};

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Bundle b = new Bundle();
		b.putString("nazwa", (String) this.getListAdapter().getItem(position));
		Intent i = new Intent(getApplicationContext(), ShowingRecipe.class);
		i.putExtras(b);
		startActivity(i);
	};
}