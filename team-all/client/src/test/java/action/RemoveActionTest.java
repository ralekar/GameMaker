package action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.SpriteList;
import view.GameMakerView;
import model.SpriteModel;

public class RemoveActionTest {
	private SpriteModel actualModel1,actualModel2,expectedModel1,expectedModel2;
	private RemoveAction action;
	
	private List<SpriteModel> toBeRemovedSpriteModelsList;
	private List<SpriteModel> actualToBeRemovedSpriteModelsList;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoAction() {
		
		actualModel1 = new SpriteModel(100, 100, 5, 5,30,30, "","");
		actualModel2 = new SpriteModel(150, 200, 10, 0,35,35, "","");
		action = new RemoveAction();
		toBeRemovedSpriteModelsList = new ArrayList<SpriteModel>();

		/*
		 * the returned list of to be removed models should contains exactly 2 elements and each element should be the same 
		 * as that entered by doAction
		 */
		actualToBeRemovedSpriteModelsList = SpriteList.getInstance().getToBeRemovedSpriteModels();
		actualToBeRemovedSpriteModelsList.clear();
		/*
		 * doAction for RemoveAction adds the model in its argument to the list models to be removed
		 * Here 2 models should be added.
		 */
		action.doAction(actualModel1);
		action.doAction(actualModel2);
		
		
		int n = actualToBeRemovedSpriteModelsList.size();
		if(n==2)
			assertTrue(true);
		else
			assertTrue(false);
		
		expectedModel1 = actualToBeRemovedSpriteModelsList.get(0);
		expectedModel2 = actualToBeRemovedSpriteModelsList.get(1);
		
		assertEquals(expectedModel1,actualModel1);
		assertEquals(expectedModel2,actualModel2);
		
	}

}
