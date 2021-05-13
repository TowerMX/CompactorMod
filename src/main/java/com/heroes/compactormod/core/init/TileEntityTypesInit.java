
package com.heroes.compactormod.core.init;

import com.heroes.compactormod.CompactorMod;
import com.heroes.compactormod.common.blocks.CompactorBlock;
import com.heroes.compactormod.common.entities.CompactorTileEntity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {
	
	
	private static final String ENTITY_NAME = "compactor_te";

	private static final Block block = BlockInit.block;
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, CompactorMod.MOD_ID);
	
	public static final RegistryObject<TileEntityType<CompactorTileEntity>> COMPACTOR_TE = TILE_ENTITIES.register("compactor_te", TileEntityType.Builder.of(CompactorTileEntity::new, BlockInit.COMPACTOR.get()).build(null));
	
}
	