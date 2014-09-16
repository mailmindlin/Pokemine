package com.pokemine.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

import com.pokemine.item.PokeBall;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PokeTab extends CreativeTabs {
	public static final PokeTab INSTANCE = new PokeTab();
	public static final ItemStack titleStack= new ItemStack(PokeBall.INSTANCE, 1, 0);
	protected PokeTab() {
		super(CreativeTabs.getNextID(), "pokemine");
		ItemDye id;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getIconItemStack() {
		return PokeTab.titleStack;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getTabIconItem() {
		return null;
	}

	@SideOnly(Side.CLIENT)
	public int func_151243_f() {
		return 5;
	}

	public void _load() {
		return;
	}
}
