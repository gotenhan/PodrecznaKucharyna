package org.ii.kucharyna.app.activities;

import podreczna.kucharyna.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddingRecipe extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.addingrecipe);

		Button deleteButton = (Button) findViewById(R.id.deleting);
		deleteButton.setOnClickListener(deleteListener);

		Button saveButton = (Button) findViewById(R.id.saving);
		saveButton.setOnClickListener(saveListener);

	}

	private OnClickListener deleteListener = new OnClickListener() {
		public void onClick(View v) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					AddingRecipe.this);
			alertDialog.setTitle("Usuwanie...");
			alertDialog.setMessage("Na pewno chcesz usun�� przepis?");

			// Setting Icon to Dialog
			alertDialog.setIcon(R.drawable.delete);

			alertDialog.setPositiveButton("TAK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// do something when the button is clicked
							Intent i = new Intent(getApplicationContext(),
									MainActivity.class);
							// sending data to new activity
							startActivity(i);
						}
					});

			alertDialog.setNegativeButton("NIE",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Write your code here to execute after dialog
							dialog.cancel();
						}
					});

			// Showing Alert Message
			alertDialog.show();
		}
	};

	private OnClickListener saveListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
}