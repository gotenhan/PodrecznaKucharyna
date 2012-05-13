package org.ii.kucharyna;

import java.util.ArrayList;

import org.ii.kucharyna.presenter.MainPresenter;
import org.ii.kucharyna.presenter.views.IRecipeView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AppActivity extends Activity implements IRecipeView{
    /** Called when the activity is first created. */
	private MainPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.presenter = new MainPresenter();
    }

	public void setRecipeName(String recipeName) {
		final String _recipeName = recipeName;
		this.runOnUiThread(new Runnable(){
			public void run(){
				TextView recipeNameTextView = (TextView)findViewById(R.id.recipe_name);
				recipeNameTextView.setText(_recipeName);
			}
		});
	}

	public void setRecipeDescription(String description) {
		// TODO Auto-generated method stub
		
	}

	public void setRecipeInstructions(String instructions) {
		// TODO Auto-generated method stub
		
	}

	public void setRecipeTags(ArrayList<String> tags) {
		// TODO Auto-generated method stub
		
	}
    
}