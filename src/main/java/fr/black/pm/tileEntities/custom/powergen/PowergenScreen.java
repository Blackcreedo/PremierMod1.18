package fr.black.pm.tileEntities.custom.powergen;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.black.pm.PremierMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PowergenScreen extends AbstractContainerScreen<PowergenContainer> {

    private final ResourceLocation GUI = new ResourceLocation(PremierMod.MOD_ID, "textures/gui/powergen_gui.png");

    public PowergenScreen(PowergenContainer container, Inventory inventory, Component name) {
        super(container, inventory, name);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack,mouseX,mouseY);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        drawString(stack, Minecraft.getInstance().font, "Energy" + menu.getEnergy(), 10, 10, 0xffffff);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0,GUI);
        int relX = (this.width - this.imageWidth) /2;
        int relY = (this.height - this.imageHeight) /2;
        this.blit(stack,relX,relY,0,0,this.imageWidth,this.imageHeight);
    }
}
