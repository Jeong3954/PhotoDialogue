package kr.glound03.pd;

import kr.glound03.pd.command.CommandNPC;
import kr.glound03.pd.command.CommandOpenDialogueGui;
import kr.glound03.pd.command.CommandOpenPhotoGui;
import kr.glound03.pd.command.CommandSetItemCmd;
import kr.glound03.pd.data.Data;
import kr.glound03.pd.data.NPCData;
import kr.glound03.pd.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(
        modid = Data.MOD_ID,
        name = Data.MOD_NAME,
        version = Data.MOD_VERSION
)
public class PhotoDialogue
{

    @SidedProxy(clientSide = "kr.glound03.pd.proxy.ClientProxy", serverSide = "kr.glound03.pd.proxy.CommonProxy", modId = Data.MOD_ID)
    public static CommonProxy proxy;

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        proxy.onPreInit(event);
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandOpenPhotoGui());
        event.registerServerCommand(new CommandOpenDialogueGui());
        event.registerServerCommand(new CommandSetItemCmd());
        event.registerServerCommand(new CommandNPC());

        NPCData.loadData();
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        NPCData.saveData();
    }
}
