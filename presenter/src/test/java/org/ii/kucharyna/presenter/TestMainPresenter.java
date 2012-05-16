package org.ii.kucharyna.presenter;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.ii.kucharyna.persistance.models.RecipeModel;
import org.ii.kucharyna.presenter.views.IRecipeView;

public class TestMainPresenter extends TestCase {

	private MainPresenter presenter;

	public TestMainPresenter(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		presenter = new MainPresenter();
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

		public void setRecipeName(String name) {
			this.name = name;
		}

		public String getRecipeName() {
			return name;
		}

		public void setRecipeDescription(String description) {

		}

		public void setRecipeInstructions(String instructions) {

		}

		public void setRecipeTags(ArrayList<String> tags) {

		}

	}

}
