package kr.glound03.pd.network;

import io.netty.buffer.ByteBuf;
import kr.glound03.pd.gui.GuiDialogue;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CPacketOpenDialogueGui implements IMessage {

    public String folderName;

    public CPacketOpenDialogueGui(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        folderName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, folderName);
    }

    public static class Handler implements IMessageHandler<CPacketOpenDialogueGui, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(CPacketOpenDialogueGui message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handler(message, ctx));
            return null;
        }

        @SideOnly(Side.CLIENT)
        private void handler(CPacketOpenDialogueGui message, MessageContext ctx) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiDialogue(message.folderName));
        }
    }

    public CPacketOpenDialogueGui() {
    }
}
