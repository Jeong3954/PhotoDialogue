package kr.glound03.pd.command;

import kr.glound03.pd.manager.NetworkManager;
import kr.glound03.pd.manager.Utils;
import kr.glound03.pd.network.CPacketOpenDialogueGui;
import kr.glound03.pd.network.CPacketOpenPhotoGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandOpenDialogueGui extends CommandBase {

    @Override
    public String getName() {
        return "대화창";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        try {
            if (args[0].equals("전체")) {
                NetworkManager.sendToAll(new CPacketOpenPhotoGui(args[1]));
            } else {
                EntityPlayerMP target = getPlayer(server, sender, args[0]);
                NetworkManager.sendTo(new CPacketOpenDialogueGui(args[1]), target);
            }
        } catch (Exception e) {
            Utils.sendWarnMessage(player, "명령어를 제대로 입력해주세요.");
        }
    }
}
