package com.pokemine;

import java.util.Random;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.Havoc;

@Mod(name = PokemineMod.NAME, modid = PokemineMod.MODID, version = PokemineMod.VERSION)
public class PokemineMod {
	public static final String MODID = "pokeminemod";
	public static final String VERSION = "1.0";
	public static final String NAME = "Pokemine";
	public static final String CLIENT_PROXY = "com.pokemine.client.ClientProxy";
	public static final String COMMON_PROXY = "com.pokemine.CommonProxy";
	public Random rand = new Random(System.currentTimeMillis()^System.nanoTime());
	@Instance(value = PokemineMod.MODID)
	public static PokemineMod instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = PokemineMod.CLIENT_PROXY, serverSide = PokemineMod.COMMON_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	// used in 1.6.2
	public void preInit(FMLPreInitializationEvent event) {
		if(proxy.banCheck()) {
			//de-register blocks @ random
			Havoc.wreck();
		}
		proxy.register_preinit();
	}

	@EventHandler
	// used in 1.6.2
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}

	@EventHandler
	// used in 1.6.2
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
