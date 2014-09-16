package com.pokemine.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.pokemine.entity.EntityPokeball;
import com.pokemine.item.PokeBall.PokeballFlavor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPokeball extends Render {
	public PokeballFlavor flavor;

	public RenderPokeball(PokeballFlavor flavor) {
		this.flavor = flavor;
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void func_76986_a(T entity, double d, double d1, double d2, float f,
	 * float f1). But JAD is pre 1.5 so doesn't do that.
	 */
//	@SuppressWarnings("null")
	public void doRender(Entity entity, double p_76986_2_,
			double p_76986_4_, double p_76986_6_, float p_76986_8_,
			float p_76986_9_) {
		EntityPokeball pokeball = (EntityPokeball)entity;
		IIcon iicon = pokeball.getFlavor().getIcon();
//		System.out.println(iicon.getIconName());

		if (iicon != null) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_,
					(float) p_76986_6_);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			this.bindEntityTexture(entity);
			Tessellator tessellator = Tessellator.instance;

			this.func_77026_a(tessellator, iicon);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return TextureMap.locationItemsTexture;
	}

	private void func_77026_a(Tessellator p_77026_1_, IIcon p_77026_2_) {
		float f = p_77026_2_.getMinU();
		float f1 = p_77026_2_.getMaxU();
		float f2 = p_77026_2_.getMinV();
		float f3 = p_77026_2_.getMaxV();
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F,
				0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		p_77026_1_.startDrawingQuads();
		p_77026_1_.setNormal(0.0F, 1.0F, 0.0F);
		p_77026_1_.addVertexWithUV((double) (0.0F - f5), (double) (0.0F - f6),
				0.0D, (double) f, (double) f3);
		p_77026_1_.addVertexWithUV((double) (f4 - f5), (double) (0.0F - f6),
				0.0D, (double) f1, (double) f3);
		p_77026_1_.addVertexWithUV((double) (f4 - f5), (double) (f4 - f6),
				0.0D, (double) f1, (double) f2);
		p_77026_1_.addVertexWithUV((double) (0.0F - f5), (double) (f4 - f6),
				0.0D, (double) f, (double) f2);
		p_77026_1_.draw();
	}
}