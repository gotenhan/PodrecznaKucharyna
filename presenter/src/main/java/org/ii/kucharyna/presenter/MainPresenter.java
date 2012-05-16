package org.ii.kucharyna.presenter;

import java.util.ArrayList;
import java.util.Arrays;

import org.ii.kucharyna.persistance.models.RecipeModel;
import org.ii.kucharyna.presenter.views.IRecipeView;

public class MainPresenter {
	private ArrayList<RecipeModel> recipes = new ArrayList<RecipeModel>();

	RecipeModel getRecipeStub() {
		RecipeModel model = new RecipeModel();
		model.setName("Jajecznica po studencku");
		model.setDescription("Jajecznica po studencku jest popularna wśród "
				+ "biednych studenciaków bo jest tania");
		model.setInstructions("1. Podejdź do lodówki"
				+ System.getProperty("line.separator") + "2. Otwórz lodówkę"
				+ System.getProperty("line.separator")
				+ "3. Podrap się po jajkach"
				+ System.getProperty("line.separator") + "4. Zamknij lodówkę");
		String[] tags = new String[] { "jajko", "jajo", "studenckie",
				"student", "tanio", "tanie", "łatwe", "proste" };
		model.setTags(new ArrayList<String>(Arrays.asList(tags)));
		return model;
	}

	public MainPresenter() {
		this.recipes.add(getRecipeStub());
		RecipeModel rm = getRecipeStub();
		rm.setName("Jajówa po studencku");
		rm.addTag("jajecznica");
		rm.removeTag("jajko");
		this.recipes.add(rm);
	}

	public void refreshRecipe(IRecipeView recipeView, int recipeId) {
		recipeView.setRecipeName(recipes.get(recipeId).getName());
	}
}
