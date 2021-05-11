package com.heroes.compactormod.common.blocks;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.heroes.compactormod.common.entities.CompactorTileEntity;
import com.heroes.compactormod.common.gui.CompactorGui;
import com.heroes.compactormod.common.procedures.CompactorTryProcedure;

import io.netty.buffer.Unpooled;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class CompactorBlock extends Block {

	public CompactorBlock() {
		super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(3.5f, 3.5f)
				.sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0).requiresCorrectToolForDrops());
	}

	/* POST FRANKENSTEIN */
	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}
	
	@Override
	public void onPlace(BlockState state1, World world, BlockPos pos, BlockState state2,
			boolean flag) {
		super.onPlace(state1, world, pos, state2, flag);
		world.getBlockTicks().scheduleTick(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), this, 10);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.tick(state, world, pos, random);
		CompactorTryProcedure.executeProcedure((IWorld) world, pos.getX(), pos.getY(), pos.getZ());
		world.getBlockTicks().scheduleTick(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), this, 10);
	}

//		Creo que es comentable. Creo que es de servidor y eso nosotros no lo tocamos?
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity,
			Hand hand, BlockRayTraceResult hit) {
		super.use(state, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (entity instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) entity, new INamedContainerProvider() {
				@Override
				public ITextComponent getDisplayName() {
					return new StringTextComponent("Compactor");
				}

				@Override
				public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
					return new CompactorGui.GuiContainerMod(id, inventory,
							new PacketBuffer(Unpooled.buffer()).writeBlockPos(new BlockPos(x, y, z)));
				}
			}, new BlockPos(x, y, z));
		}
		return ActionResultType.SUCCESS;
	}
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof INamedContainerProvider ? (INamedContainerProvider) tileEntity : null;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new CompactorTileEntity();
	}

//		Creo que es comentable. Creo que es de servidor y eso nosotros no lo tocamos?

//		@Override
//		public boolean eventReceived(BlockState state, World world, BlockPos pos, int eventID, int eventParam) {
//			super.eventReceived(state, world, pos, eventID, eventParam);
//			TileEntity tileentity = world.getTileEntity(pos);
//			return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
//		}

	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = world.getBlockEntity(pos);
			if (tileentity instanceof CompactorTileEntity) {
				InventoryHelper.dropContents(world, pos, (CompactorTileEntity) tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}
	
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	public int getComparatorInputOverride(BlockState blockState, World world, BlockPos pos) {
		TileEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof CompactorTileEntity)
			return Container.getRedstoneSignalFromContainer((CompactorTileEntity) tileentity);
		else
			return 0;
	}
}
