package org.ii.kucharyna.app.activities;

import podreczna.kucharyna.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
    
	// onCreate - called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        // Tab for cookbook
        TabSpec cookbookspec = tabHost.newTabSpec("Ksi�zka Kucharska");
        cookbookspec.setIndicator("", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent cookbookIntent = new Intent(this, RecipiesList.class);
        cookbookspec.setContent(cookbookIntent);
        
        // Tab for calculator
        TabSpec calculatorspec = tabHost.newTabSpec("Kalkulator");
        calculatorspec.setIndicator("", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent calculatorIntent = new Intent(this, Calculator.class);
        calculatorspec.setContent(calculatorIntent);
        
        // Tab for shoppingList
        TabSpec shoppingListspec = tabHost.newTabSpec("Lista Zakupow");
        shoppingListspec.setIndicator("", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent shoppingListIntent = new Intent(this, ShoppingList.class);
        shoppingListspec.setContent(shoppingListIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(cookbookspec); 
        tabHost.addTab(calculatorspec);
        tabHost.addTab(shoppingListspec);
        
    }
}