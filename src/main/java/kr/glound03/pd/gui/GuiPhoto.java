package kr.glound03.pd.gui;

import kr.glound03.pd.data.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiPhoto extends GuiScreen {

    public final String photoName;

    public GuiPhoto(String photoName) {
        this.photoName = photoName;
        this.mc = Minecraft.getMinecraft();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        mc.renderEngine.bindTexture(new ResourceLocation(Data.MOD_ID, "photo/" + photoName + ".png"));
        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
