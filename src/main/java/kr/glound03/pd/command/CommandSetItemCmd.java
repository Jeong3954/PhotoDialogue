package kr.glound03.pd.command;

import kr.glound03.pd.manager.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class CommandSetItemCmd extends CommandBase {

    @Override
    public String getName() {
        return "아이템설정";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        try {
            ItemStack stack = player.getHeldItemMainhand();
            if (!stack.isEmpty()) {
                NBTTagCompound compound;
                if (stack.hasTagCompound()) {
                    compound = stack.getTagCompound();
                } else {
                    compound = new NBTTagCompound();
                }

                if (compound != null) {
                    StringBuilder stb = new StringBuilder();
                    for (String arg : args) {
                        stb.append(arg).append(" ");
                    }
                    compound.setString("command", stb.toString());
                    stack.setTagCompound(compound);
                    Utils.sendSystemMessage(player, "들고있는 아이템에 /" + stb + "명령어가 설정되었습니다.");
                }
            } else {
                Utils.sendWarnMessage(player, "설정할 아이템을 들어주세요.");
            }
        } catch (Exception e) {
            Utils.sendWarnMessage(player, "명령어를 제대로 입력해주세요.");
        }
    }
}
