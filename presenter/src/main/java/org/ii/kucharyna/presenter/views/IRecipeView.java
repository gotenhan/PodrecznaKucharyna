package org.ii.kucharyna.presenter.views;

import java.util.List;

public interface IRecipeView {
  public void setRecipeName(String name);

  public void setRecipeDescription(String description);

  public void setRecipeInstructions(String instructions);

  public void setRecipeTags(List<String> tags);

  public String getRecipeName();

  public String getRecipeInstructions();

  public String getRecipeDescription();

  public List<String> getRecipeTags();
}
