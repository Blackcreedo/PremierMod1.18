package fr.black.pm.tileEntities.custom.lightningChanneler;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.black.pm.PremierMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class LightningChannelerScreen extends AbstractContainerScreen<LightningChannelerContainer> {
    private final ResourceLocation GUI = new ResourceLocation(PremierMod.MOD_ID, "textures/gui/lightning_channeler_gui.png");

    public LightningChannelerScreen(LightningChannelerContainer container, Inventory inventory, Component name) {
        super(container, inventory, name);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0,GUI);
        int relX = (this.width - this.imageWidth) /2;
        int relY = (this.height - this.imageHeight) /2;
        this.blit(matrixStack,relX,relY,0,0,this.imageWidth,this.imageHeight);
    }

}
