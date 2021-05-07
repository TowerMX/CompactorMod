package com.heroes.compactormod.common.blocks;

import com.heroes.compactormod.common.entities.CompactorTileEntity;
import com.heroes.compactormod.core.init.TileEntityTypesInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class CompactorBlock extends Block /* implements IForgeBlock */ {

	public CompactorBlock() {
		super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(3.5f, 3.5f)
				.sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0).requiresCorrectToolForDrops());
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.COMPACTOR_TILE_ENTITY_TYPE.get().create();
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (worldIn.isClientSide()) {
			TileEntity te = worldIn.getBlockEntity(pos);
			if (te instanceof CompactorTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (CompactorTileEntity) te, pos);
			}
		}
		return super.use(state, worldIn, pos, player, handIn, hit);
	}

}
