package podreczna.kucharyna;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShoppingList extends ListActivity {
	
	static final String[] SHOPPING_ITEMS = new String[] { "Chleb", "Makaron", "Mleko", "Mas³o", "Cebula", "Szynka", 
			"Kawa", "Ry¿", "Jab³ka", "Pomidory", "Zielona herbata", "Banany", "Poziomki", "Oregano", "Tymianek",
			"Maliny"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ShoppingListArrayAdapter(this, SHOPPING_ITEMS));
		getListView().setTextFilterEnabled(true);
		setContentView(R.layout.shoppinglist);
		Button ConfirmationButton = (Button) findViewById(R.id.removingAllButton);

		ConfirmationButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShoppingList.this);
				alertDialog.setTitle("Usuwanie...");
				alertDialog.setMessage("Na pewno chcesz usun¹æ wszystkie zakupy?");
	
				// Setting Icon to Dialog
				alertDialog.setIcon(R.drawable.delete);
	
				alertDialog.setPositiveButton("TAK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								// Write your code here to execute after dialog
								Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
							}
						});

				alertDialog.setNegativeButton("NIE",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int which) {
								// Write your code here to execute after dialog
								Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
								dialog.cancel();
							}
						});
	
				// Showing Alert Message
				alertDialog.show();
	
			}
		});
	}
}


