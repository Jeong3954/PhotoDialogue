package kr.glound03.pd.network;

import io.netty.buffer.ByteBuf;
import kr.glound03.pd.gui.GuiPhoto;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CPacketOpenPhotoGui implements IMessage {

    public String photoName;

    public CPacketOpenPhotoGui(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        photoName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, photoName);
    }

    public static class Handler implements IMessageHandler<CPacketOpenPhotoGui, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(CPacketOpenPhotoGui message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handler(message, ctx));
            return null;
        }

        @SideOnly(Side.CLIENT)
        private void handler(CPacketOpenPhotoGui message, MessageContext ctx) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiPhoto(message.photoName));
        }
    }

    public CPacketOpenPhotoGui() {
    }
}
