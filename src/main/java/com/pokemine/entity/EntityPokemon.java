package com.pokemine.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public abstract class EntityPokemon extends EntityLiving {
	protected int maxHealth;
	public EntityPokemon(World w) {
		super(w);
	}

	public abstract boolean attemptCatch(int pokeballFlavor);
	public abstract int getPokemonIndexNumber();
	public abstract String getName();
	
}
