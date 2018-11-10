package com.sudwood.advancedutilities.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sudwood.advancedutilities.client.gui.GuiCompressor;
import com.sudwood.advancedutilities.recipes.CompressRecipes;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CompressRecipeHandler extends TemplateRecipeHandler 
{

	@Override
	public String getRecipeName() 
	{
		return "Compressing";
	}

	@Override
	public String getGuiTexture() 
	{
		return new ResourceLocation("advancedutilities","textures/gui/compressorgui.png").toString();
	}
	
	 public class SmeltingPair extends CachedRecipe
	    {
	        public SmeltingPair(ItemStack ingred, ItemStack result) {
	            this.ingred = new PositionedStack(ingred, 51, 24);
	            this.result = new PositionedStack(result, 111, 24);
	        }

	        public List<PositionedStack> getIngredients() {
	            return getCycledIngredients(cycleticks / 48, Arrays.asList(ingred));
	        }

	        public PositionedStack getResult() {
	            return result;
	        }

	        public PositionedStack getOtherStack() {
	            return null;
	        }

	        PositionedStack ingred;
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
	        transferRects.add(new RecipeTransferRect(new Rectangle(75, 23, 24, 18), "compressing"));
	    }

	    @Override
	    public Class<? extends GuiContainer> getGuiClass() {
	        return GuiCompressor.class;
	    }

	    @Override
	    public TemplateRecipeHandler newInstance() {
	        return super.newInstance();
	    }

	    @Override
	    public void loadCraftingRecipes(String outputId, Object... results) {
	        if (outputId.equals("compressing") && getClass() == CompressRecipeHandler.class) {//don't want subclasses getting a hold of this
	            Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) CompressRecipes.getRecipes();
	            for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
	                arecipes.add(new SmeltingPair(recipe.getValue(), recipe.getKey()));
	        } else
	            super.loadCraftingRecipes(outputId, results);
	    }

	    @Override
	    public void loadCraftingRecipes(ItemStack result) {
	        Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>)  CompressRecipes.getRecipes();
	        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
	            if (NEIServerUtils.areStacksSameType(recipe.getKey(), result))
	                arecipes.add(new SmeltingPair(recipe.getValue(), recipe.getKey()));
	    }

	    @Override
	    public void loadUsageRecipes(String inputId, Object... ingredients) {
	        if (getClass() == CompressRecipeHandler.class && inputId == ("crushing"))//don't want subclasses getting a hold of this
	            loadCraftingRecipes("compressing");
	        else
	            super.loadUsageRecipes(inputId, ingredients);
	    }

	    @Override
	    public void loadUsageRecipes(ItemStack ingredient) {
	        Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>)  CompressRecipes.getRecipes();
	        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
	            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getValue(), ingredient)) {
	                SmeltingPair arecipe = new SmeltingPair(recipe.getValue(), recipe.getKey());
	                arecipe.setIngredientPermutation(Arrays.asList(arecipe.ingred), ingredient);
	                arecipes.add(arecipe);
	            }
	    }


	    @Override
	    public void drawExtras(int recipe) {
	        drawProgressBar(75, 23, 176, 14, 24, 16, 48, 0);
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
	        return "compressing";
	    }

}
