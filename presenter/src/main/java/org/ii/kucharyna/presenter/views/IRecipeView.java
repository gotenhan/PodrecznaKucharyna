package org.ii.kucharyna.presenter.views;

import java.util.ArrayList;

public interface IRecipeView {
	public void setRecipeName(String name);

	public void setRecipeDescription(String description);

	public void setRecipeInstructions(String instructions);

	public void setRecipeTags(ArrayList<String> tags);
}
