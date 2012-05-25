package org.ii.kucharyna.app.activities;

import java.util.List;

import org.ii.kucharyna.app.BigBadStaticThing;
import org.ii.kucharyna.presenter.RecipePresenter;
import org.ii.kucharyna.presenter.views.IRecipeView;

import podreczna.kucharyna.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddingRecipe extends Activity implements IRecipeView {
  private RecipePresenter presenter;
  List<String> tags;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.addingrecipe);

    Intent i = getIntent();
    Bundle b = i.getExtras();
    int modelNr;
    if (b != null)
      modelNr = b.containsKey("position") ? b.getInt("position") : -1;
    else
      modelNr = -1;
    Log.i("AddingRecipe", "position: " + Integer.toString(modelNr));
    presenter = new RecipePresenter(this, modelNr, BigBadStaticThing.repository);
    presenter.refreshView();
    
    Button deleteButton = (Button) findViewById(R.id.deleting);
    deleteButton.setOnClickListener(deleteListener);

    Button saveButton = (Button) findViewById(R.id.saving);
    saveButton.setOnClickListener(saveListener);

  }

  private OnClickListener deleteListener = new OnClickListener() {
    public void onClick(View v) {
      AlertDialog.Builder alertDialog = new AlertDialog.Builder(
          AddingRecipe.this);
      alertDialog.setTitle("Anuluj");
      alertDialog.setMessage("Na pewno chcesz anulować?");

      // Setting Icon to Dialog
      alertDialog.setIcon(R.drawable.delete);

      alertDialog.setPositiveButton("TAK",
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              // do something when the button is clicked
              Intent mIntent = new Intent();
              setResult(RESULT_OK, mIntent);
              finish();
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
      presenter.updateModel();
      finish();
    }
  };

  @Override
  public void setRecipeName(String name) {
    final String newName = name;
    runOnUiThread(new Runnable() {
      public void run() {
        EditText text = (EditText) findViewById(R.id.recipeName);
        text.setText(newName);
      }
    });
  }

  @Override
  public void setRecipeDescription(String description) {
    final String newDescr = description;
    runOnUiThread(new Runnable() {
      public void run() {
        EditText text = (EditText) findViewById(R.id.description);
        text.setText(newDescr);
      }
    });
  }

  @Override
  public void setRecipeInstructions(String instructions) {
    final String newInstr = instructions;
    runOnUiThread(new Runnable() {
      public void run() {
        EditText text = (EditText) findViewById(R.id.preparationDescription);
        text.setText(newInstr);
      }
    });
  }

  @Override
  public void setRecipeTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public String getRecipeName() {
    EditText text = (EditText) findViewById(R.id.recipeName);
    return text.getText().toString();
  }

  @Override
  public String getRecipeInstructions() {
    EditText text = (EditText) findViewById(R.id.preparationDescription);
    return text.getText().toString();
  }

  @Override
  public String getRecipeDescription() {
    EditText text = (EditText) findViewById(R.id.description);
    return text.getText().toString();
  }

  @Override
  public List<String> getRecipeTags() {
    return tags;
  }
}