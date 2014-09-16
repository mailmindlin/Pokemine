package com.pokemine.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.pokemine.CommonProxy;
import com.pokemine.item.PokeBall.PokeballFlavor;

public class EntityPokeball extends EntityThrowable {
	static PokeballFlavor tFlavor=null;
	public static void preloadFlavor(PokeballFlavor nFlavor) {
		tFlavor=nFlavor;
	}
	public static EntityPokeball makeForFlavor(PokeballFlavor flavor, World w, EntityLivingBase e){
		switch(flavor) {
		case ULTRA_BALL:
			return new EPUltra(w,e);
		case MASTER_BALL:
			return new EPMaster(w,e);
		case SAFARI_BALL:
			return new EPSafari(w,e);
		case POKEBALL:
		default:
			return new EPClassic(w,e);
		}
	}
	protected PokeballFlavor flavor;

	public EntityPokeball(World world) {
		super(world);
		this.flavor=this.getFlavor();
		System.err.println("Flavor: "+this.flavor.toString());
		/*
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (int i = 1; i < Math.min(0, stackTrace.length); i++) {
			System.err.println(stackTrace[i].toString());
		}*/
	}

	public EntityPokeball(World world, EntityLivingBase e) {
		super(world, e);
		this.flavor=getFlavor();
	}

	protected EntityPokeball(World world, double dx,double dy, double dz) {
		super(world, dx, dy, dz);
		this.flavor=getFlavor();
	}
	public EntityPokeball(World w, EntityLivingBase e, PokeballFlavor f) {
		this(w,e);
		this.flavor=f;
	}
	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition pos) {
		if (pos.entityHit != null) {
			if (pos.entityHit instanceof EntityPokemon
					&& (((EntityPokemon) pos.entityHit).attemptCatch(0))) {
				pos.entityHit.setDead();
			}
			pos.entityHit.attackEntityFrom(
					DamageSource.causeThrownDamage(this, this.getThrower()),
					1.0F);
		}
		this.setDead();
	}

	public IIcon getTexture() {
		return this.flavor.getIcon();
	}
	public PokeballFlavor getFlavor() {
		return CommonProxy.USE_MULTIENTITY?EntityPokeball.tFlavor:PokeballFlavor.POKEBALL;
	}
	public static class EPClassic extends EntityPokeball {
		public EPClassic(World w, EntityLivingBase e) {
			super(w,e);
		}
		public EPClassic(World world) {
			super(world);
		}
		@Override
		public PokeballFlavor getFlavor() {
			return PokeballFlavor.POKEBALL;
		}
	}
	public static class EPUltra extends EntityPokeball {
		public EPUltra(World w, EntityLivingBase e) {
			super(w,e);
		}
		public EPUltra(World world) {
			super(world);
		}
		@Override
		public PokeballFlavor getFlavor() {
			return PokeballFlavor.ULTRA_BALL;
		}
	}
	public static class EPMaster extends EntityPokeball {
		public EPMaster(World w, EntityLivingBase e) {
			super(w,e);
		}
		public EPMaster(World world) {
			super(world);
		}
		@Override
		public PokeballFlavor getFlavor() {
			return PokeballFlavor.ULTRA_BALL;
		}
	}
	public static class EPSafari extends EntityPokeball {
		public EPSafari(World w, EntityLivingBase e) {
			super(w,e);
		}
		public EPSafari(World world) {
			super(world);
		}
		@Override
		public PokeballFlavor getFlavor() {
			return PokeballFlavor.ULTRA_BALL;
		}
	}
}
