package com.heroes.compactormod.core.init;

import com.heroes.compactormod.CompactorMod;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CompactorMod.MOD_ID);
	
	public static final RegistryObject<BlockItem> COMPACTOR = ITEMS.register("compactor",
			() -> new BlockItem(BlockInit.COMPACTOR.get(), new Item.Properties().tab(ItemGroup.TAB_REDSTONE)));

}
