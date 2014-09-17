package com.pokemine.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.pokemine.client.render.RenderPokemonTest;

public class EntityPokemonTest extends EntityPokemon{
	public EntityPokemonTest(World w) {
		super(w);
	}

	public EntityPokemonTest() {
		super();
	}

	@Override
	public boolean attemptCatch(int pokeballFlavor) {
		return false;
	}

	@Override
	public int getPokemonIndexNumber() {
		return 0;
	}

	@Override
	public String getName() {
		return "Test_pokeman";
	}
	@Override
	public Render getRenderer() {
		return RenderPokemonTest.getInstance();
	}
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("pokeminemod:TP");
	}
}
