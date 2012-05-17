package podreczna.kucharyna;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class ShowingRecipe extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.showingrecipe);
 
        TextView txtProduct = (TextView) findViewById(R.id.recipeName);
 
        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("nazwa");
        // displaying selected product name
        txtProduct.setText(product);
 
        Button backButton = (Button) findViewById(R.id.goingBack);
        backButton.setOnClickListener(backListener);
        
        Button editButton = (Button) findViewById(R.id.editing);
        editButton.setOnClickListener(editListener);
    }
    
    private OnClickListener backListener = new OnClickListener() {
        public void onClick(View v) {
        	// do something when the button is clicked
        	Intent j = new Intent(getApplicationContext(), MainActivity.class);
    	    // sending data to new activity
    	    startActivity(j);
        }
    };
    
    private OnClickListener editListener = new OnClickListener() {
        public void onClick(View v) {
        	// do something when the button is clicked
        	Intent j = new Intent(getApplicationContext(), AddingRecipe.class);
    	    // sending data to new activity
    	    startActivity(j);
        }
    };
}