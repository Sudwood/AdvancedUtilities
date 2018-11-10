package com.sudwood.advancedutilities.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sudwood.advancedutilities.client.gui.GuiSteelOven;
import com.sudwood.advancedutilities.recipes.SteelOvenRecipes;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SteelOvenRecipeHandler extends TemplateRecipeHandler
{

	@Override
	public String getRecipeName() 
	{
		return "Steel Oven";
	}

	@Override
	public String getGuiTexture() 
	{
		return new ResourceLocation("advancedutilities","textures/gui/steelovengui.png").toString();
	}
	
	 public class SmeltingPent extends CachedRecipe
	    {
	        public SmeltingPent(ItemStack ingred1,ItemStack ingred2,ItemStack ingred3,ItemStack ingred4,ItemStack ingred5, ItemStack result) {
	            this.ingred1 = new PositionedStack(ingred1, 27, 25);
	            this.ingred2 = new PositionedStack(ingred2, 45, 7);
	            this.ingred3 = new PositionedStack(ingred3, 9, 43);
	            this.ingred4 = new PositionedStack(ingred4, 45, 43);
	            this.ingred5 = new PositionedStack(ingred5, 9, 7);
	            this.result = new PositionedStack(result, 103, 25);
	        }

	        public List<PositionedStack> getIngredients() {
	        	List<PositionedStack> ing = new ArrayList<PositionedStack>();
	        	ing.add(ingred1);
	        	ing.add(ingred2);
	        	ing.add(ingred3);
	        	ing.add(ingred4);
	        	ing.add(ingred5);
	            return ing;
	        }

	        public PositionedStack getResult() {
	            return result;
	        }

	        public PositionedStack getOtherStack() {
	            return null;
	        }

	        PositionedStack ingred1;
	        PositionedStack result;
	        PositionedStack ingred2;
	        PositionedStack ingred3;
	        PositionedStack ingred4;
	        PositionedStack ingred5;
	    }
	 
	 @Override
	    public void loadTransferRects() {
	        transferRects.add(new RecipeTransferRect(new Rectangle(68, 24, 24, 18), "steeloven"));
	    }

	    @Override
	    public Class<? extends GuiContainer> getGuiClass() {
	        return GuiSteelOven.class;
	    }

	    @Override
	    public TemplateRecipeHandler newInstance() {
	        return super.newInstance();
	    }


	    @Override
	    public void loadCraftingRecipes(String outputId, Object... results) {
	        if (outputId.equals("steeloven") && getClass() == SteelOvenRecipeHandler.class) {//don't want subclasses getting a hold of this
	        	Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>>> recipes = SteelOvenRecipes.getRecipes();
	        	for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
					{
						for(Entry<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
						{
							for(Entry<ItemStack,  Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
							{
								for(Entry<ItemStack, ItemStack> ingr45: ingr345.getValue().entrySet())
								{
									arecipes.add(new SmeltingPent(ingr12345.getKey(),ingr2345.getKey(),ingr345.getKey(),ingr45.getKey(),ingr45.getValue(), recipe.getKey()));
								}
								
							}
						}
					}
				}
	        } else
	            super.loadCraftingRecipes(outputId, results);
	    }
	    @Override
	    public void loadCraftingRecipes(ItemStack result) 
	    {
	    	Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>>> recipes = SteelOvenRecipes.getRecipes();
        	for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
					{
						for(Entry<ItemStack,  Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
						{
							for(Entry<ItemStack, ItemStack> ingr45: ingr345.getValue().entrySet())
							{
								if(NEIServerUtils.areStacksSameType(result, recipe.getKey()))
								arecipes.add(new SmeltingPent(ingr12345.getKey(),ingr2345.getKey(),ingr345.getKey(),ingr45.getKey(),ingr45.getValue(), recipe.getKey()));
							}
							
						}
					}
				}
			}
	                //arecipes.add(new SmeltingQuad();
	    }
	    
	    @Override
	    public void loadUsageRecipes(String inputId, Object... ingredients) {
	        if (getClass() == SteelOvenRecipeHandler.class && inputId == ("steeloven"))//don't want subclasses getting a hold of this
	            loadCraftingRecipes("steeloven");
	        else
	            super.loadUsageRecipes(inputId, ingredients);
	    }

	    @Override
	    public void loadUsageRecipes(ItemStack ingredient) 
	    {
	    	Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>>> recipes = SteelOvenRecipes.getRecipes();
        	for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack,  Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
					{
						for(Entry<ItemStack,  Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
						{
							for(Entry<ItemStack, ItemStack> ingr45: ingr345.getValue().entrySet())
							{
								if(NEIServerUtils.areStacksSameType(ingredient, ingr12345.getKey() )|| NEIServerUtils.areStacksSameType(ingredient, ingr2345.getKey()) 
										|| NEIServerUtils.areStacksSameType(ingredient, ingr345.getKey()) || NEIServerUtils.areStacksSameType(ingredient, ingr45.getKey())  || NEIServerUtils.areStacksSameType(ingredient, ingr45.getValue()))
								arecipes.add(new SmeltingPent(ingr12345.getKey(),ingr2345.getKey(),ingr345.getKey(),ingr45.getKey(),ingr45.getValue(), recipe.getKey()));
							}
						}
					}
				}
			}
	    }



	    @Override
	    public void drawExtras(int recipe) {
	        drawProgressBar(68, 24, 176, 14, 24, 16, 48, 0);
	    }


	    @Override
	    public String getOverlayIdentifier() {
	        return "steeloven";
	    }

}
