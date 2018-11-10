package com.sudwood.advancedutilities.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sudwood.advancedutilities.client.gui.GuiSteamCrusher;
import com.sudwood.advancedutilities.recipes.CrushRecipes;
import com.sudwood.advancedutilities.recipes.SmeltryRecipes;

import java.util.Map.Entry;

import codechicken.nei.ItemList;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.FurnaceRecipeHandler.FuelPair;
import codechicken.nei.recipe.FurnaceRecipeHandler.SmeltingPair;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;

public class SmeltryRecipeHandler extends TemplateRecipeHandler 
{

	@Override
	public String getRecipeName() 
	{
		return "Smeltry";
	}

	@Override
	public String getGuiTexture() 
	{
		return new ResourceLocation("advancedutilities","textures/gui/smeltrygui.png").toString();
	}
	
	 public class SmeltingTrio extends CachedRecipe
	    {
	        public SmeltingTrio(ItemStack melt, ItemStack mould, ItemStack result) {
	            mould.stackSize =1;
	            this.melt = new PositionedStack(melt, 76, -4);
	            this.mould = new PositionedStack(mould, 76, 19);
	            this.result = new PositionedStack(result, 77, 46);
	        }

	        public List<PositionedStack> getIngredients() {
	        	List<PositionedStack> ing = new ArrayList<PositionedStack>();
	        	ing.add(melt);
	        	ing.add(mould);
	            return ing;
	        }

	        public PositionedStack getResult() {
	            return result;
	        }

	        public PositionedStack getOtherStack() {
	            return null;
	        }

	        PositionedStack melt;
	        PositionedStack mould;
	        PositionedStack result;
	    }

	    public static class FuelPair
	    {
	        public FuelPair(ItemStack ingred, int burnTime) {
	            this.stack = new PositionedStack(ingred, 51, 42, false);
	            this.burnTime = burnTime;
	        }

	        public PositionedStack stack;
	        public int burnTime;
	    }

	    public static ArrayList<FuelPair> afuels;
	    public static HashSet<Block> efuels;

	    @Override
	    public void loadTransferRects() {
	        transferRects.add(new RecipeTransferRect(new Rectangle(33, 23, 24, 18), "smeltry"));
	    }

	    @Override
	    public Class<? extends GuiContainer> getGuiClass() {
	        return GuiSteamCrusher.class;
	    }

	    @Override
	    public TemplateRecipeHandler newInstance() {
	        return super.newInstance();
	    }

	    @Override
	    public void loadCraftingRecipes(String outputId, Object... results) {
	        if (outputId.equals("smeltry") && getClass() == SmeltryRecipeHandler.class) 
	        {
	            Map<ItemStack, Map<ItemStack, ItemStack>> recipes = (Map<ItemStack,Map<ItemStack, ItemStack>>) SmeltryRecipes.getRecipes();
	            for (Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
	            {
	            	
	            	ItemStack melt;
	            	ItemStack mould;
	            	ItemStack result = recipe.getKey();;
	            	Map<ItemStack, ItemStack> castings = (Map<ItemStack, ItemStack>)recipe.getValue();
	            	for(Entry<ItemStack,ItemStack> cast: castings.entrySet())
	            	{
	            		melt = cast.getKey();
	            		mould = cast.getValue();
	            		arecipes.add(new SmeltingTrio(melt, mould, result));
	            	}
	                //arecipes.add(new SmeltingTrio(recipe.getKey(), recipe.getValue()));
	            }
	        } 
	        else
	            super.loadCraftingRecipes(outputId, results);
	    }

	    @Override
	    public void loadCraftingRecipes(ItemStack result) 
	    {
	    	Map<ItemStack, Map<ItemStack, ItemStack>> recipes = (Map<ItemStack,Map<ItemStack, ItemStack>>) SmeltryRecipes.getRecipes();
	    	ItemStack melt;
        	ItemStack mould;
	    	if(recipes.containsKey(result))
	    	{
	    		Map<ItemStack, ItemStack> ingr = recipes.get(result);
            	Map<ItemStack, ItemStack> castings = ingr;
            	for(Entry<ItemStack,ItemStack> cast: castings.entrySet())
            	{
            		melt = cast.getKey();
            		mould = cast.getValue();
            		arecipes.add(new SmeltingTrio(melt, mould, result));
            	}
	    		
	    	}
	    	else
	    	{
	             for (Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
	             {
	            	 Map<ItemStack, ItemStack> castings = (Map<ItemStack, ItemStack>)recipe.getValue();
		            	for(Entry<ItemStack,ItemStack> cast: castings.entrySet())
		            	{
		            		melt = cast.getKey();
		            		mould = cast.getValue();
		            		if(NEIServerUtils.areStacksSameTypeCrafting(recipe.getKey(), result))
		            			arecipes.add(new SmeltingTrio(melt, mould, recipe.getKey()));
		            	}
	             }
	    	}
	    	 /*Map<ItemStack, Map<ItemStack, ItemStack>> recipes = (Map<ItemStack,Map<ItemStack, ItemStack>>) SmeltryRecipes.getRecipes();
	            for (Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
	            {
	            	
	            	ItemStack melt;
	            	ItemStack mould;
	            	Map<ItemStack, ItemStack> castings = (Map<ItemStack, ItemStack>)recipe.getValue();
	            	for(Entry<ItemStack,ItemStack> cast: castings.entrySet())
	            	{
	            		mould = cast.getKey();
	            		if(NEIServerUtils.areStacksSameType(cast.getValue(), result))
	            			arecipes.add(new SmeltingTrio(melt, mould, result));
	            	}
	                //arecipes.add(new SmeltingTrio(recipe.getKey(), recipe.getValue()));
	            }*/
	    }

	    @Override
	    public void loadUsageRecipes(String inputId, Object... ingredients) {
	        if (getClass() == SmeltryRecipeHandler.class && inputId == ("smeltry"))//don't want subclasses getting a hold of this
	            loadCraftingRecipes("smeltry");
	        else
	            super.loadUsageRecipes(inputId, ingredients);
	    }

	    @Override
	    public void loadUsageRecipes(ItemStack ingredient) 
	    {

	        Map<ItemStack, Map<ItemStack, ItemStack>> recipes = (Map<ItemStack,Map<ItemStack, ItemStack>>) SmeltryRecipes.getRecipes();
            for (Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
            {
            	
            	if(recipe.getValue().containsKey(ingredient))
            	{
	            	ItemStack mould;
	            	Map<ItemStack, ItemStack> castings = (Map<ItemStack, ItemStack>)recipe.getValue();
	            	for(Entry<ItemStack,ItemStack> cast: castings.entrySet())
	            	{
	            		mould = cast.getValue();
	            		if(NEIServerUtils.areStacksSameTypeCrafting(cast.getKey(), ingredient))
	            			arecipes.add(new SmeltingTrio(ingredient, mould, recipe.getKey()));
	            	}
            	}
            	else if(recipe.getValue().containsValue(ingredient))
            	{
            			ItemStack melt;
    	            	ItemStack mould;
    	            	Map<ItemStack, ItemStack> castings = (Map<ItemStack, ItemStack>)recipe.getValue();
    	            	for(Entry<ItemStack,ItemStack> cast: castings.entrySet())
    	            	{
    	            		melt = cast.getKey();
    	            		if(NEIServerUtils.areStacksSameTypeCrafting(cast.getValue(), ingredient))
    	            			arecipes.add(new SmeltingTrio(melt, ingredient, cast.getValue()));
    	            	}
                	}
            	}
                //arecipes.add(new SmeltingTrio(recipe.getKey(), recipe.getValue()));
         }


	    @Override
	    public void drawExtras(int recipe) {
	    }

	    private static Set<Item> excludedFuels() {
	        Set<Item> efuels = new HashSet<Item>();
	        efuels.add(Item.getItemFromBlock(Blocks.brown_mushroom));
	        efuels.add(Item.getItemFromBlock(Blocks.red_mushroom));
	        efuels.add(Item.getItemFromBlock(Blocks.standing_sign));
	        efuels.add(Item.getItemFromBlock(Blocks.wall_sign));
	        efuels.add(Item.getItemFromBlock(Blocks.wooden_door));
	        efuels.add(Item.getItemFromBlock(Blocks.trapped_chest));
	        return efuels;
	    }

	   

	    @Override
	    public String getOverlayIdentifier() {
	        return "smeltry";
	    }

}
