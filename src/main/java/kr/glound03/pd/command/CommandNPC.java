package kr.glound03.pd.command;

import kr.glound03.pd.data.NPCData;
import kr.glound03.pd.manager.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.util.Map;

public class CommandNPC extends CommandBase {

    @Override
    public String getName() {
        return "엔피시";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        try {
            Map<String, String> data = NPCData.getNPCData();
            switch (args[0]) {
                case "등록":
                    if (!data.containsKey(args[1])) {
                        StringBuilder stb = new StringBuilder();
                        for (String arg : args) {
                            if (!arg.equals(args[0]) && !arg.equals(args[1])) {
                                stb.append(arg).append(" ");
                            }
                        }
                        data.put(args[1], stb.toString());
                        Utils.sendSystemMessage(player, "엔피시에 /" + stb + "명령어가 등록되었습니다.");
                    } else {
                        Utils.sendWarnMessage(player, "이미 해당 NPC에 등록된 명령어가 있습니다.");
                    }
                    break;
                case "삭제":
                    if (data.remove(args[1], data.get(args[1]))) {
                        Utils.sendSystemMessage(player, "엔피시에 등록된 명령어를 삭제했습니다.");
                    } else {
                        Utils.sendWarnMessage(player, "해당 NPC에 등록된 명령어가 없습니다.");
                    }
                    break;
                case "확인":
                    for (String npcName : data.keySet()) {
                        String command = data.get(npcName);
                        Utils.sendSystemMessage(player, npcName + ": /" + command);
                    }
                    break;
            }
        } catch (Exception e) {
            Utils.sendWarnMessage(player, "명령어를 제대로 입력해주세요.");
        }
    }
}
