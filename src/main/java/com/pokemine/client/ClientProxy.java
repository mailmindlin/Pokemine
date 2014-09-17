package com.pokemine.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;

import com.pokemine.CommonProxy;
import com.pokemine.client.render.RenderPokeball;
import com.pokemine.entity.EntityPokeball;
import com.pokemine.item.PokeBall;
import com.pokemine.item.PokeBall.PokeballFlavor;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public synchronized void register_preinit() {
		PokeTab.INSTANCE._load();
		super.register_preinit();
	}

	@Override
	public synchronized void registerRenderers() {
		if(CommonProxy.USE_MULTIENTITY) {
		RenderingRegistry.registerEntityRenderingHandler(EntityPokeball.class,
				new RenderPokeball(PokeballFlavor.POKEBALL));
		}/* else {
			RenderingRegistry.registerEntityRenderingHandler(EntityPokeball.EPClassic.class,new RenderPokeball(PokeballFlavor.POKEBALL));
			RenderingRegistry.registerEntityRenderingHandler(EntityPokeball.EPUltra.class,new RenderPokeball(PokeballFlavor.ULTRA_BALL));
			RenderingRegistry.registerEntityRenderingHandler(EntityPokeball.EPMaster.class,new RenderPokeball(PokeballFlavor.MASTER_BALL));
			RenderingRegistry.registerEntityRenderingHandler(EntityPokeball.EPSafari.class,new RenderPokeball(PokeballFlavor.SAFARI_BALL));
		}*/
	}

	@Override
	public boolean banCheck() {
		String userName = Minecraft.getMinecraft().getSession().getUsername();
		System.out.println("This player's username is... " + userName + "!");
		System.out.println("Now, have they been good? Let's take a look...");
		if (userName.equalsIgnoreCase("shobu9")) { // You can use the OR
													// operator, or an array,
													// for multiple usernames.
			return true;
		} else {
			return false;
		}
	}
}
