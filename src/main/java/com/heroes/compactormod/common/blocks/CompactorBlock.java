package com.heroes.compactormod.common.blocks;

import com.heroes.compactormod.common.entities.CompactorEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.HopperBlock;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;

public class CompactorBlock extends Block /*implements IForgeBlock*/ {


	public CompactorBlock(Properties properties) {
		super(properties);
	}

//	@Override
//	public boolean hasTileEntity(BlockState state) {
//		return true;
//	}
//
//	@Override
//	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//		// TODO Auto-generated method stub
//		return new CompactorEntity(/*equisde*/);
//	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		
	}

	@Override
	public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_,
			PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
		// TODO Auto-generated method stub
		return super.use(p_225533_1_, p_225533_2_, p_225533_3_, p_225533_4_, p_225533_5_, p_225533_6_);
	}
	
}
