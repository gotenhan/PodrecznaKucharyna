package org.ii.kucharyna.presenter;

import java.util.ArrayList;
import java.util.List;

import org.ii.kucharyna.persistance.KucharynaRepository;
import org.ii.kucharyna.persistance.communication.MongoLabCommunicator;
import org.ii.kucharyna.persistance.models.RecipeModel;
import org.ii.kucharyna.presenter.views.IRecipeListView;

public class RecipeListPresenter {
  private KucharynaRepository repository;
  private IRecipeListView view;
  private List<String> nameList;
  
  public RecipeListPresenter(IRecipeListView view, KucharynaRepository repository){
    if(repository == null)
      throw new NullPointerException("RecipeListPresenter#constructor: repository cannot be null");
    if(view == null)
      throw new NullPointerException("RecipeListPresenter#constructor: view cannot be null");
    this.repository = repository;
    this.view = view;
    
    updateNameList();
  }
  
  public RecipeListPresenter(IRecipeListView view){
    if(view == null)
      throw new NullPointerException("RecipeListPresenter#constructor: view cannot be null");
    this.repository = new KucharynaRepository(new MongoLabCommunicator());
    this.view = view;
    
    updateNameList();
  }
  
  private void updateNameList() {
    if(nameList == null)
      nameList = new ArrayList<String>();
    nameList.clear();
    for(RecipeModel model: repository.getRecipes()){
      String name = model.getName();
      nameList.add(name != null ? name : "brak nazwy");
    }
  }

  public void refreshView(){
    updateNameList();
    this.view.setRecipeNamesList(this.nameList);
  }

}
