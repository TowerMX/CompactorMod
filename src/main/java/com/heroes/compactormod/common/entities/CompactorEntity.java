package com.heroes.compactormod.common.entities;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CompactorEntity extends TileEntity implements ITickableTileEntity{

	private static int TICKS = 100; // Cantidad de ticks a los que mirar el inventario
	private int tickCounter = 1; // Contador de ticks
	
	public CompactorEntity(TileEntityType<?> entityTipe) {
		super(entityTipe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		if(tickCounter++==CompactorEntity.TICKS) {
			
		}
		
	}

//	Dónde se suscribe? Creo que en CompactorMod, pero no estoy seguro.
//	
//	@SubscribeEvent
//	public static void registerTE(RegistryEvent.Register<TileEntityType<?>> evt) {
//	  TileEntityType<?> type = TileEntityType.Builder.create(factory, BlockInit.BLOCKS).build(null);
//	  type.setRegistryName("mymod", "myte");
//	  evt.getRegistry().register(type);
//	}
	
}
