package com.heroes.compactormod.client.screen;

import com.heroes.compactormod.CompactorMod;
import com.heroes.compactormod.container.CompactorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiContainerEvent.DrawForeground;

@OnlyIn(Dist.CLIENT)
public class CompactorScreen extends ContainerScreen<CompactorContainer> {
	
	private static final ResourceLocation COMPACTOR_GUI = new ResourceLocation(CompactorMod.MOD_ID, "textures/gui/compactor.png");

	public CompactorScreen(CompactorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.leftPos = 0;
		this.topPos = 0;
		this.width = 176;
		this.height = 166;
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseY, mouseX, mouseY);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}
	
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
		this.font.func_243248_b(matrixStack, this.playerInventory.getDisplayName(), (float) this.playerInventoryTitleX, (float) this.playerInventoryTitleY, 4210752);
	}
	
	@SuppressWarnings("deprecation")
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(COMPACTOR_GUI);
		int x = (this.width - this.getXSize()) / 2;
		int y = (this.height - this.getYSize()) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.getXSize(), this.getYSize());
		
	}
}