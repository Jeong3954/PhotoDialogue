package kr.glound03.pd.proxy;

import kr.glound03.pd.data.Data;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ClientProxy extends CommonProxy {

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        super.onPreInit(event);

        Data.RESOURCE_FOLDER = event.getModConfigurationDirectory().getParent() + "\\resources\\" + Data.MOD_ID;
        if (new File(Data.RESOURCE_FOLDER + "\\dialogue").mkdirs()) {
            System.out.println("리소스 \"dialogue\" 폴더가 생성되었습니다.");
        } else {
            System.out.println("리소스 폴더가 이미 존재합니다.");
        }

        if (new File(Data.RESOURCE_FOLDER + "\\photo").mkdirs()) {
            System.out.println("리소스 \"photo\" 폴더가 생성되었습니다.");
        } else {
            System.out.println("리소스 폴더가 이미 존재합니다.");
        }
    }
}
