package com.sudwood.advancedutilities.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;
import com.sudwood.advancedutilities.AdvancedUtilities;

public class RecipeConfigHandler 
{
	
	private static File configDir;
	public static void init(File conf)
	{
		configDir = conf;
		File confPath = new File(configDir.getPath()+AdvancedUtilities.MODID+"recipeconf"+".json");
		Gson gson = new Gson();
		if(!Files.exists(confPath.toPath()))
		{
			try
			{
			Files.createFile(confPath.toPath());
			}
			catch(IOException e )
			{
				e.printStackTrace();
			}
			AddRecipe add = new AddRecipe();
			add.input.put("minecraft:dirt", 1);
			add.input.put("minecraft:sand", 1);
			add.input.put("minecraft:cobblestone", 1);
			add.input.put("minecraft:stone_pickaxe", 1);
			add.output = "minecraft:diamond";
			add.outputNum = 64;
			add.recipeType = "kiln";
			String temp = gson.toJson(add);
			try {
				   //write converted json data to a file named "CountryGSON.json"
				   FileWriter writer = new FileWriter(confPath.getPath());
				   writer.write(temp);
				   writer.close();
				  
				  } catch (IOException e) {
				   e.printStackTrace();
				  }
			return;
		}
		if(Files.exists(confPath.toPath()))
		{
			AddRecipe[] adds;
			try {
				adds = gson.fromJson(new FileReader(confPath.getPath()), AddRecipe[].class);
				System.out.println(gson.toJson(adds));
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonIOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}


