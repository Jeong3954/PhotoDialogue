package kr.glound03.pd.proxy;

import kr.glound03.pd.data.NPCData;
import kr.glound03.pd.manager.NetworkManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

public class CommonProxy {

    public void onPreInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        NetworkManager.init();
    }

    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        EntityPlayer player = event.getEntityPlayer();
        Entity entity = event.getTarget();
        World world = event.getWorld();
        MinecraftServer server = world.getMinecraftServer();
        if (server != null && event.getHand() == EnumHand.MAIN_HAND && !world.isRemote) {
            Map<String, String> data = NPCData.getNPCData();
            if (data.containsKey(entity.getName())) {
                String command = data.get(entity.getName());
                server.commandManager.executeCommand(player, command.replace("%player%", player.getName()));
            }
        }
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (!event.getItemStack().isEmpty()) {
            EntityPlayer player = event.getEntityPlayer();
            World world = event.getWorld();
            MinecraftServer server = world.getMinecraftServer();
            ItemStack stack = event.getItemStack();
            if (!world.isRemote && event.getHand() == EnumHand.MAIN_HAND && server != null && stack.hasTagCompound()) {
                NBTTagCompound compound = stack.getTagCompound();
                if (compound != null) {
                    if (compound.hasKey("command")) {
                        server.commandManager.executeCommand(player, compound.getString("command").replace("%player%", player.getName()));
                    }
                }
            }
        }
    }
}
