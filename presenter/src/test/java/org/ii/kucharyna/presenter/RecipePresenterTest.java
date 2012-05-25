package org.ii.kucharyna.presenter;

import java.util.List;

import junit.framework.TestCase;

import org.ii.kucharyna.persistance.models.RecipeModel;
import org.ii.kucharyna.presenter.views.IRecipeView;

public class RecipePresenterTest extends TestCase {

	private Presenter presenter;

	public RecipePresenterTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		presenter = new Presenter();
	}

	public void testGetRecipeStub() {
		RecipeModel rm = presenter.getRecipeStub();
		assertNotNull(rm);
		assertEquals("Jajecznica po studencku", rm.getName());
	}

	public void testRefreshRecipe() {
		RecipeViewMock mock = new RecipeViewMock();
		presenter.refreshRecipe(mock, 0);
		assertEquals("Jajecznica po studencku", mock.getRecipeName());
		presenter.refreshRecipe(mock, 1);
		assertEquals("Jajówa po studencku", mock.getRecipeName());
	}

	private class RecipeViewMock implements IRecipeView {
		private String name = null;
		private String description = null;
		private String instructions = null;
		private List<String> tags = null;
    public String getRecipeName() {
      return name;
    }
    public void setRecipeName(String name) {
      this.name = name;
    }
    public String getRecipeDescription() {
      return description;
    }
    public void setRecipeDescription(String description) {
      this.description = description;
    }
    public String getRecipeInstructions() {
      return instructions;
    }
    public void setRecipeInstructions(String instructions) {
      this.instructions = instructions;
    }
    public List<String> getRecipeTags() {
      return tags;
    }
    public void setRecipeTags(List<String> tags) {
      this.tags = tags;
    }

	}

}
