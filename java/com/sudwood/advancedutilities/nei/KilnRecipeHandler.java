package com.sudwood.advancedutilities.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.client.gui.GuiKiln;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemIngot;
import com.sudwood.advancedutilities.nei.CrushRecipeHandler.SmeltingPair;
import com.sudwood.advancedutilities.recipes.CrushRecipes;
import com.sudwood.advancedutilities.recipes.KilnRecipes;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class KilnRecipeHandler extends TemplateRecipeHandler
{

	@Override
	public String getRecipeName() 
	{
		return "Kiln";
	}

	@Override
	public String getGuiTexture() 
	{
		return new ResourceLocation("advancedutilities","textures/gui/kilngui.png").toString();
	}
	
	 public class SmeltingQuad extends CachedRecipe
	    {
	        public SmeltingQuad(ItemStack ingred1,ItemStack ingred2,ItemStack ingred3,ItemStack ingred4,ItemStack result) {
	            ingred1.stackSize = 1;
	            ingred2.stackSize = 1;
	            ingred3.stackSize = 1;
	            ingred4.stackSize = 1;
	            this.ingred1 = new PositionedStack(ingred1, 33, 0);
	            this.ingred2 = new PositionedStack(ingred2, 11, 22);
	            this.ingred3 = new PositionedStack(ingred3, 33, 22);
	            this.ingred4 = new PositionedStack(ingred4, 54, 22);
	            this.result = new PositionedStack(result, 110, 23);
	        }

	        public List<PositionedStack> getIngredients() {
	        	List<PositionedStack> ing = new ArrayList<PositionedStack>();
	        	ing.add(ingred1);
	        	ing.add(ingred2);
	        	ing.add(ingred3);
	        	ing.add(ingred4);
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
	    }
	 
	 @Override
	    public void loadTransferRects() {
	        transferRects.add(new RecipeTransferRect(new Rectangle(75, 23, 24, 18), "kiln"));
	    }

	    @Override
	    public Class<? extends GuiContainer> getGuiClass() {
	        return GuiKiln.class;
	    }

	    @Override
	    public TemplateRecipeHandler newInstance() {
	        return super.newInstance();
	    }


	    @Override
	    public void loadCraftingRecipes(String outputId, Object... results) {
	        if (outputId.equals("kiln") && getClass() == KilnRecipeHandler.class) {//don't want subclasses getting a hold of this
	        	Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipes = KilnRecipes.getRecipes();
	        	for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
					{
						for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
						{
							for(Entry<ItemStack, ItemStack> ingr34: ingr234.getValue().entrySet())
							{
								arecipes.add(new SmeltingQuad(ingr1234.getKey(),ingr234.getKey(),ingr34.getKey(),ingr34.getValue(), recipe.getKey()));
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
	    	Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipes = KilnRecipes.getRecipes();
        	for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
					{
						for(Entry<ItemStack, ItemStack> ingr34: ingr234.getValue().entrySet())
						{
							if(NEIServerUtils.areStacksSameType(result, recipe.getKey()))
							arecipes.add(new SmeltingQuad(ingr1234.getKey(),ingr234.getKey(),ingr34.getKey(),ingr34.getValue(), recipe.getKey()));
						}
					}
				}
			}
	                //arecipes.add(new SmeltingQuad();
	    }
	    
	    @Override
	    public void loadUsageRecipes(String inputId, Object... ingredients) {
	        if (getClass() == KilnRecipeHandler.class && inputId == ("kiln"))//don't want subclasses getting a hold of this
	            loadCraftingRecipes("kiln");
	        else
	            super.loadUsageRecipes(inputId, ingredients);
	    }

	    @Override
	    public void loadUsageRecipes(ItemStack ingredient) 
	    {
	    	Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipes = KilnRecipes.getRecipes();
        	for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
					{
						for(Entry<ItemStack, ItemStack> ingr34: ingr234.getValue().entrySet())
						{
							if(NEIServerUtils.areStacksSameType(ingredient, ingr1234.getKey() )|| NEIServerUtils.areStacksSameType(ingredient, ingr234.getKey()) 
									|| NEIServerUtils.areStacksSameType(ingredient, ingr34.getKey())  || NEIServerUtils.areStacksSameType(ingredient, ingr34.getValue()))
							arecipes.add(new SmeltingQuad(ingr1234.getKey(),ingr234.getKey(),ingr34.getKey(),ingr34.getValue(), recipe.getKey()));
						}
					}
				}
			}
	    }



	    @Override
	    public void drawExtras(int recipe) {
	        drawProgressBar(75, 23, 176, 14, 24, 16, 48, 0);
	    }


	    @Override
	    public String getOverlayIdentifier() {
	        return "kiln";
	    }

}
