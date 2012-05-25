package org.ii.kucharyna.app.activities;

import java.util.List;

import org.ii.kucharyna.app.BigBadStaticThing;
import org.ii.kucharyna.presenter.RecipeListPresenter;
import org.ii.kucharyna.presenter.views.IRecipeListView;

import podreczna.kucharyna.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class RecipiesList extends ListActivity implements IRecipeListView{

	private String[] recipeList;
	private RecipeListPresenter presenter;
	private RecipiesListArrayAdapter recipiesListArrayAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		presenter = new RecipeListPresenter(this, BigBadStaticThing.repository);
		presenter.refreshView();
		
		recipiesListArrayAdapter = new RecipiesListArrayAdapter(this, recipeList);
    setListAdapter(recipiesListArrayAdapter);
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
		b.putInt("position", position);
		Intent i = new Intent(getApplicationContext(), AddingRecipe.class);
		i.putExtras(b);
		startActivity(i);
	}

  @Override
  public void setRecipeNamesList(List<String> nameList) {
    this.recipeList = (String[]) nameList.toArray(new String[nameList.size()]);
    updateRecipiesListAdapter();
  }

  private void updateRecipiesListAdapter() {
    this.recipiesListArrayAdapter = new RecipiesListArrayAdapter(this, this.recipeList);
    runOnUiThread(new Runnable(){
      public void run(){
        setListAdapter(RecipiesList.this.recipiesListArrayAdapter);
        RecipiesList.this.recipiesListArrayAdapter.notifyDataSetChanged();
      }
    });
  };
  
  @Override
  public void onResume(){
    super.onResume();
    if(presenter != null)
      presenter.refreshView();
  }
}