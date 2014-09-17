package com.pokemine.entity;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.pokemine.PokemineMod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.registry.EntityRegistry;

public abstract class EntityPokemon extends EntityLiving {
	static ConcurrentHashMap<Integer, EntityPokemon> Pokemon = new ConcurrentHashMap<Integer, EntityPokemon>();
	protected int maxHealth;
	protected EntityPokemon(World w) {
		super(w);
		Pokemon.put(this.getPokemonIndexNumber(), this);
	}
	
	protected EntityPokemon() {
		this(null);
	}

	public abstract boolean attemptCatch(int pokeballFlavor);
	public abstract int getPokemonIndexNumber();
	public abstract String getName();
	public abstract Render getRenderer();
	public abstract ResourceLocation getTexture();
	public void preInit(boolean client) {
		EntityRegistry.registerModEntity(this.getClass(), "pokemon"+this.getName(), (int) this.getMaxHealth(), PokemineMod.instance, 80, 3, true);
		if(client)RenderingRegistry.registerEntityRenderingHandler(this.getClass(), this.getRenderer());
	}
}
