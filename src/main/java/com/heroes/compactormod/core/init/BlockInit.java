package com.heroes.compactormod.core.init;

import com.heroes.compactormod.CompactorMod;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CompactorMod.MOD_ID);
	
	public static final RegistryObject<Block> COMPACTOR = BLOCKS.register("compactor", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(15f, 30f)));
	
}
