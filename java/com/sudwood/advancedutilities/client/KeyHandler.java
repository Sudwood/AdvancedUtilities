package com.sudwood.advancedutilities.client;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class KeyHandler {
public static KeyBinding run = new KeyBinding("Running Shoes", Keyboard.KEY_B, "Advanced Utilities");
public static KeyBinding jetpackToggle = new KeyBinding("Toggle Jetpack", Keyboard.KEY_F, "Advanced Utilities");
public static KeyBinding drinkQuickPotion = new KeyBinding("Drink Quick Potion", Keyboard.KEY_E, "Advanced Utilities");
public static KeyBinding skills = new KeyBinding("Skill Menu", Keyboard.KEY_K, "Advanced Utilities");
public static KeyBinding hoverToggle = new KeyBinding("Toggle Jetpack Hover", Keyboard.KEY_G,"Advanced Utilities");
public static KeyBinding buableToggle = new KeyBinding("Toggle Bauble Effects", Keyboard.KEY_Z, "Advanced Utilities");

	public KeyHandler() {
		ClientRegistry.registerKeyBinding(run);
		ClientRegistry.registerKeyBinding(jetpackToggle);
		ClientRegistry.registerKeyBinding(drinkQuickPotion);
		ClientRegistry.registerKeyBinding(skills);
		ClientRegistry.registerKeyBinding(hoverToggle);
		ClientRegistry.registerKeyBinding(buableToggle);
	}

}
