package com.pokemine.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.pokemine.client.PokeTab;
import com.pokemine.entity.EntityPokemon;
import com.pokemine.entity.EntityPokemonTest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PokemonSpawnEgg extends Item {
	public static final String NAME = "pokemonSE";
	public static final PokemonSpawnEgg INSTANCE = new PokemonSpawnEgg();
	public PokemonSpawnEgg() {
		super();
        this.setMaxDamage(0);
        setCreativeTab((CreativeTabs) PokeTab.INSTANCE);
		setMaxStackSize(64);
		setUnlocalizedName(NAME);
		this.iconString="pokemine:pokeball";
	}
	@Override
	public IIcon getIconFromDamage(int dmg) {
		return this.itemIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return "Spawn a Pokemon. Any pokemon.";
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.itemIcon = register.registerIcon(this.getIconString());
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			--itemstack.stackSize;
		}
		if (player.isSneaking())return itemstack;
		world.playSoundAtEntity(player, "random.bow", 0.5F,
				0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote) {
			EntityPokemon pokemon = new EntityPokemonTest(world);
			world.spawnEntityInWorld(pokemon);
		}

		return itemstack;
	}
}
