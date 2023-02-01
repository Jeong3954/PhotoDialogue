package kr.glound03.pd.manager;

import kr.glound03.pd.data.Data;
import kr.glound03.pd.network.CPacketOpenDialogueGui;
import kr.glound03.pd.network.CPacketOpenPhotoGui;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager {

    public static SimpleNetworkWrapper INSTANCE = null;
    private static int packetId;

    public static void init() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Data.MOD_ID);

        INSTANCE.registerMessage(CPacketOpenPhotoGui.Handler.class, CPacketOpenPhotoGui.class, packetId++, Side.CLIENT);
        INSTANCE.registerMessage(CPacketOpenDialogueGui.Handler.class, CPacketOpenDialogueGui.class, packetId++, Side.CLIENT);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }

    public static void sendToAll(IMessage message) {
        INSTANCE.sendToAll(message);
    }
}
