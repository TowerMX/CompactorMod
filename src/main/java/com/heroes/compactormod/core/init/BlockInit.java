package com.heroes.compactormod.core.init;

import com.heroes.compactormod.CompactorMod;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.audio.Sound;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CompactorMod.MOD_ID);
	
	public static final RegistryObject<Block> COMPACTOR = BLOCKS.register("compactor",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
					.strength(3.5f, 3.5f)
					.sound(SoundType.STONE)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(0)
					.requiresCorrectToolForDrops()));
	
}
