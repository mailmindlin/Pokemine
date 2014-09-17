package com.pokemine.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.pokemine.client.model.ModelPokemonTest;
import com.pokemine.entity.EntityPokemon;

public class RenderPokemonTest extends RenderLiving {
	private static RenderPokemonTest INSTANCE = new RenderPokemonTest();
	public static RenderPokemonTest getInstance() {
		return INSTANCE;
	}
	public RenderPokemonTest() {
		super(new ModelPokemonTest(), 0.5F);
	}
	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if(entity instanceof EntityPokemon) {
			return ((EntityPokemon)entity).getTexture();
		}
		return null;
	}
}
