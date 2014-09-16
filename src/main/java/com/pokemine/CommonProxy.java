package com.pokemine;

import com.pokemine.entity.EntityPokeball;
import com.pokemine.item.PokeBall;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	public static boolean USE_MULTIENTITY = true;

	public synchronized void register_preinit() {
		registerItems();
		registerRecipes();
		registerEntities();
		registerRenderers();
	}

	public synchronized void registerItems() {
		System.out.println("Registering item: " + PokeBall.NAME);
		GameRegistry.registerItem(PokeBall.INSTANCE, PokeBall.NAME,
				PokemineMod.MODID);
	}

	public void registerRenderers() {

	}

	public synchronized void registerRecipes() {

	}

	public synchronized void registerEntities() {
		if (CommonProxy.USE_MULTIENTITY) {
			EntityRegistry.registerModEntity(EntityPokeball.class, "pokeball",
					1, PokemineMod.instance, 80, 3, true);
		} else {
			EntityRegistry.registerModEntity(EntityPokeball.EPClassic.class,"pokeballClassic", 1, PokemineMod.instance, 80, 3, true);
			EntityRegistry.registerModEntity(EntityPokeball.EPUltra.class,"pokeballUltra", 1, PokemineMod.instance, 80, 3, true);
			EntityRegistry.registerModEntity(EntityPokeball.EPMaster.class,"pokeballMaster", 1, PokemineMod.instance, 80, 3, true);
			EntityRegistry.registerModEntity(EntityPokeball.EPSafari.class,"pokeballSafari", 1, PokemineMod.instance, 80, 3, true);
		}
	}

	public boolean banCheck() {
		return false;
	}
}