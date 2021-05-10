/*
package com.heroes.compactormod.core.init;

import com.heroes.compactormod.CompactorMod;
import com.heroes.compactormod.common.entities.CompactorTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, CompactorMod.MOD_ID);

	public static final RegistryObject<TileEntityType<CompactorTileEntity>> COMPACTOR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("compactor", () -> TileEntityType.Builder
					.create(CompactorTileEntity::new, BlockInit.COMPACTOR.get()).build(null));
}
*/