package org.ii.kucharyna.presenter;

import org.ii.kucharyna.persistance.KucharynaRepository;
import org.ii.kucharyna.persistance.models.RecipeModel;
import org.ii.kucharyna.presenter.views.IRecipeView;

public class RecipePresenter {
  private KucharynaRepository repository;
  private RecipeModel model;
  private IRecipeView view;
  private int modelNr;

  public RecipePresenter(IRecipeView view, int modelNr,
      KucharynaRepository repository) {
    if(repository == null)
      throw new NullPointerException("RecipePresenter#constructor: repository cannot be null");
    if(view == null)
      throw new NullPointerException("RecipePresenter#constructor: view cannot be null");
    this.repository = repository;
    this.modelNr = modelNr;
    if(this.modelNr>=0)
      this.model = this.repository.getRecipe(this.modelNr);
    else
      this.model = new RecipeModel();
    this.view = view;
  }
  
  public void updateModel(){
    this.model.setName(this.view.getRecipeName());
    this.model.setDescription(this.view.getRecipeDescription());
    this.model.setInstructions(this.view.getRecipeInstructions());
    this.model.setTags(this.view.getRecipeTags());
    
    // TODO add security check if model is still in the repository
    if(this.modelNr >= 0)
      repository.update(this.model, this.modelNr);
    else
      modelNr = repository.addRecipe(this.model);
  }
  
  public void refreshView(){
    this.view.setRecipeName(this.model.getName());
    this.view.setRecipeDescription(this.model.getDescription());
    this.view.setRecipeInstructions(this.model.getInstructions());
    this.view.setRecipeTags(this.model.getTags()); // TODO check that view cannot directly change tags list
  }
}
