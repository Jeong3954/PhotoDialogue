package kr.glound03.pd.data;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.minecraftforge.fml.common.Loader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class NPCData {

    private static final String configPath = Loader.instance().getConfigDir().getPath() + "/npc.json";
    private static final Gson gson = new Gson();
    private static Map<String, String> NPC = new HashMap<>();

    public static void saveData() {
        try {
            Writer writer = new FileWriter(configPath);
            gson.toJson(NPC, writer);
            writer.close();
            System.out.println("NPC 데이터 저장 완료");
            NPC.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadData() {
        try {
            File config = new File(configPath);
            if (!config.exists()) {
                config.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(config));
            if (br.readLine() != null) {
                Type type = new TypeToken<Map<String, String>>() {
                }.getType();
                NPC = gson.fromJson(new FileReader(configPath), type);
                System.out.println("NPC 데이터 로드 완료");
            } else {
                System.out.println("NPC 데이터가 비어있습니다.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getNPCData() {
        return NPC;
    }
}