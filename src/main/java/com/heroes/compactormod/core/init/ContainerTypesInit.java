package com.heroes.compactormod.core.init;

import com.heroes.compactormod.CompactorMod;
import com.heroes.compactormod.container.CompactorContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypesInit {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, CompactorMod.MOD_ID);

	public static final RegistryObject<ContainerType<CompactorContainer>> COMPACTOR_CONTAINER_TYPE = CONTAINER_TYPES
			.register("compactor", () -> IForgeContainerType.create(CompactorContainer::new));

}
