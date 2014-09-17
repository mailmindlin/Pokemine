package com.pokemine.item;

import java.io.FileNotFoundException;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.pokemine.CommonProxy;
import com.pokemine.client.PokeTab;
import com.pokemine.entity.EntityPokeball;
import com.pokemine.entity.EntityPokemon;
import com.pokemine.pokemon.battle.Battle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PokeBall extends Item {
	public static final String NAME = "pokeball";
	public static final PokeBall INSTANCE = new PokeBall();

	protected PokeBall() {
		super();
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        setCreativeTab((CreativeTabs) PokeTab.INSTANCE);
		setMaxStackSize(64);
		setUnlocalizedName(NAME);
	}

	@Override
	public String getUnlocalizedName(ItemStack p_77667_1_) {
		int i = MathHelper.clamp_int(p_77667_1_.getItemDamage(), 0, 15);
		return PokeballFlavor.getIndex(i).name;
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
			EntityPokeball pokeball;
			PokeballFlavor flavor = PokeballFlavor.getIndex(itemstack.getItemDamage());
			if(CommonProxy.USE_MULTIENTITY) {
				EntityPokeball.preloadFlavor(flavor);
				pokeball = new EntityPokeball(world, player, flavor);
			} else {
				pokeball = EntityPokeball.makeForFlavor(flavor, world,
						player);
			}
			world.spawnEntityInWorld(pokeball);
		}

		return itemstack;
	}

	@Override
	public IIcon getIconFromDamage(int dmg) {
		IIcon ico = PokeballFlavor.getIndex(dmg).icon;
		if(ico==null)ico=super.getIconFromDamage(dmg);
		return ico;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		try {
			String name = PokeballFlavor.getIndex(stack.getItemDamage()).name
					+ " " + Item.getIdFromItem(PokeBall.INSTANCE) + ":"
					+ stack.getItemDamage();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.getItemStackDisplayName(stack);
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		// You can also take a more direct approach and do each one individual
		// but I prefer the lazy / right way
		for (int i = 0; i < 26; ++i) {
			list.add(new ItemStack(item, 1, i));
		}
		setMaxDamage(0);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for (int i = 0; i < 26; i++) {
			PokeballFlavor tmp = PokeballFlavor.getIndex(i);
			try {
				System.out.println("Registering texture: "+tmp.texture);
				tmp.icon = register.registerIcon(tmp.texture);
				throw new FileNotFoundException();
			} catch (FileNotFoundException e) {

			}
		}
	}

	public static PokeballFlavor[] arr = new PokeballFlavor[26];

	public static enum PokeballFlavor {
		POKEBALL(0, "Poke Ball", "Classic"),
		GREAT_BALL(1, "great"),
		ULTRA_BALL(2, "ultra"),
		MASTER_BALL(3, "master"),
		SAFARI_BALL(4, "safari"),
		LEVEL_BALL(5, "level"),
		LURE_BALL(6, "lure"),
		MOON_BALL(7, "moon"),
		FREIND_BALL(8, "freind"),
		LOVE_BALL(9, "love"),
		HEAVY_BALL(10, "heavy"),
		FAST_BALL(11, "fast"),
		SPORT_BALL(12, "sport"),
		PREMIER_BALL(13, "premier"),
		REPEAT_BALL(14, "repeat"),
		TIMER_BALL(15, "timer"),
		NEST_BALL(16, "nest"),
		NET_BALL(17, "net"),
		DIVE_BALL(18, "dive"),
		LUXURY_BALL(19, "luxury"),
		HEAL_BALL(20, "heal"),
		QUICK_BALL(21, "quick"),
		DUSK_BALL(22, "dusk"),
		CHERISH_BALL(23, "cherish"),
		PARK_BALL(24, "park"),
		DREAM_BALL(25, "dream");
		int index;
		String name;
		String texture;
		IIcon icon;

		PokeballFlavor(int i, String pre) {
			this(i, pre.substring(0, 1).toUpperCase() + pre.substring(1)
					+ " Ball", pre.substring(0, 1).toUpperCase()
					+ pre.substring(1));
		}

		PokeballFlavor(int i, String pre, String tex) {
			this.index = i;
			this.name = pre;
			this.texture = "pokemine:pokeball" + tex;
			this.icon = null;
			arr[i] = this;
		}

		public float getCatchRate(Battle b, EntityPokemon p) {
			return 1.0F;// TODO finish & add maths
		}

		public String getTexture() {
			return this.texture;
		}

		public static PokeballFlavor getIndex(int i) {
			return arr[MathHelper.clamp_int(i,0,arr.length-1)];
		}

		public IIcon getIcon() {
			return this.icon;
		}

		public int getIndex() {
			return this.index;
		}
	}
}
