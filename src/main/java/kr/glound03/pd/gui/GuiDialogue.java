package kr.glound03.pd.gui;

import kr.glound03.pd.data.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.IOException;

public class GuiDialogue extends GuiScreen {

    public final String folderName;
    private int currentPage;
    private final int maxPage;

    public GuiDialogue(String folderName) {
        this.folderName = folderName;
        this.currentPage = 0;

        this.mc = Minecraft.getMinecraft();

        File dialogueFolder = new File(Data.RESOURCE_FOLDER + "\\dialogue\\" + folderName);
        File[] dialogues = dialogueFolder.listFiles(s -> s.isFile() && s.getName().endsWith(".png"));
        if (dialogues != null) {
            this.maxPage = dialogues.length - 1;
        } else {
            this.maxPage = 0;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        mc.renderEngine.bindTexture(new ResourceLocation(Data.MOD_ID, "dialogue/" + folderName + "/" + currentPage + ".png"));
        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        switch (mouseButton) {
            case 0:
                if (currentPage > 0) {
                    currentPage--;
                }
                break;
            case 1:
                if (currentPage < maxPage) {
                    currentPage++;
                } else {
                    mc.displayGuiScreen(null);
                }
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
