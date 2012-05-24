/*package org.ii.kucharyna.app.badbadbad;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import org.ii.kucharyna.persistance.KucharynaRepository;
import org.ii.kucharyna.persistance.communication.MongoLabCommunicator;
import org.ii.kucharyna.presenter.RecipeListPresenter;
import org.ii.kucharyna.presenter.RecipePresenter;
import org.ii.kucharyna.presenter.views.IRecipeListView;
import org.ii.kucharyna.presenter.views.IRecipeView;

// TODO exchange BadBadBadStaticThing with something better
public class BadBadBadStaticThing {
  static MongoLabCommunicator communicator = new MongoLabCommunicator();
  static KucharynaRepository repository = new KucharynaRepository(BadBadBadStaticThing.communicator);
  
  static {
    try{
      BadBadBadStaticThing.repository.fillRecipes();
    }catch(Exception e){
      StringWriter writer = new StringWriter();
      e.printStackTrace(new PrintWriter(writer));
      Logger.getLogger("BadBadBadStaticThing").severe(writer.toString());
    }
  }
  
  public static RecipeListPresenter getRecipeListPresenter(IRecipeListView view){
    return new RecipeListPresenter(view, BadBadBadStaticThing.repository);
  }
  
  public static RecipePresenter getRecipePresenter(IRecipeView view, int index){
    return new RecipePresenter(view, index, BadBadBadStaticThing.repository);
  }

}
*/