package com.xxxyjade17.spiruracore;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static Config INSTANCE;
    private static final Logger LOGGER = SpiruraCore.getLogger();
    private static final Gson gson = new Gson();
    private static JsonObject config;

    private static Map<Integer,String> RankNameMap =new HashMap<>();
    private static Map<Integer,Map<Integer,Integer>> RequiredExperienceMap =new HashMap<>();
    private static Map<Integer,Map<Integer,Integer>> IncreasedExperienceMap =new HashMap<>();
    private static Map<Integer,Map<Integer,Boolean>> ShackleMap = new HashMap<>();
    private static Map<Integer,Map<Integer,Float>> BreakRateMap = new HashMap<>();
    private static Map<Integer,Map<Integer,Float>> RateIncreaseMap = new HashMap<>();

    private static int ticks_per_increase;

    private Config() {
        try {
            loadConfig();
            loadRankName();
            loadExperience();
            loadShackle();
        }catch (IOException e){
            LOGGER.error(getLogMessage("file.failed","config.json"),e);
        }
    }

    public static Config getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    private void loadConfig() throws IOException {
        Path path = Path.of("config/spiruracore/config.json");
        Files.createDirectories(path.getParent());
        if (Files.notExists(path)) {
            try (InputStream inputStream = Config.class.getResourceAsStream("/"+path)) {
                if (inputStream != null) {
                    Files.copy(inputStream, path);
                } else {
                    LOGGER.warn(getLogMessage("file.empty","config.json"));
                    return;
                }
            }
        }
        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        config = gson.fromJson(reader, JsonObject.class);

        ticks_per_increase = config.get("ticks_per_increase").getAsInt();
    }

    private void loadRankName() throws IOException {
        Path path = Path.of("config/spiruracore/rankname.json");
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if (Files.notExists(path)) {
            try (InputStream inputStream = Config.class.getResourceAsStream("/" + path)) {
                if (inputStream != null) {
                    Files.copy(inputStream, path);
                } else {
                    LOGGER.warn(getLogMessage("file.empty","rankname.json"));
                    return;
                }
            }
        }
        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        JsonObject ranks = gson.fromJson(reader, JsonObject.class);
        Map<Integer, String> rankNameMap = new HashMap<>();
        for (int i = 1; i <= ranks.size(); i++) {
            String name = ranks.get("rank"+ i).getAsString();
            rankNameMap.put(i,name);
        }
        RankNameMap=rankNameMap;
    }

    private void loadExperience() throws IOException {
        Path path = Path.of("config/spiruracore/experience.json");
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if (Files.notExists(path)) {
            try (InputStream inputStream = Config.class.getResourceAsStream("/" + path)) {
                if (inputStream != null) {
                    Files.copy(inputStream, path);
                } else {
                    LOGGER.warn(getLogMessage("file.empty","experience.json"));
                    return;
                }
            }
        }
        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

        JsonObject experience = gson.fromJson(reader, JsonObject.class);
        Map<Integer,Map<Integer,Integer>> requiredExperienceMap=new HashMap<>();
        Map<Integer,Map<Integer,Integer>> increasedExperienceMap=new HashMap<>();
        for (int i = 1; i <= experience.size(); i++) {
            JsonObject rank = experience.get("rank"+ i).getAsJsonObject();
            Map<Integer,Integer> requiredExperience = new HashMap<>();
            Map<Integer,Integer> increasedExperience = new HashMap<>();
            for (int j = 1; j <= rank.size(); j++) {
                JsonObject level = rank.get("level"+ j).getAsJsonObject();
                int require = level.get("require").getAsInt();
                int increase = level.get("increase").getAsInt();
                requiredExperience.put(j,require);
                increasedExperience.put(j,increase);
            }
            requiredExperienceMap.put(i,requiredExperience);
            IncreasedExperienceMap.put(i,increasedExperience);
        }
        RequiredExperienceMap=requiredExperienceMap;
        IncreasedExperienceMap =increasedExperienceMap;
    }

    private void loadShackle() throws IOException {
        Path path = Path.of("config/spiruracore/shackle.json");
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if (Files.notExists(path)) {
            try (InputStream inputStream = Config.class.getResourceAsStream("/" + path)) {
                if (inputStream != null) {
                    Files.copy(inputStream, path);
                } else {
                    LOGGER.warn(getLogMessage("file.empty","shackle.json"));
                    return;
                }
            }
        }
        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        JsonObject shackleConfig = gson.fromJson(reader, JsonObject.class);
        Map<Integer,Map<Integer,Boolean>> shackleMap=new HashMap<>();
        Map<Integer,Map<Integer,Float>> breakRateMap=new HashMap<>();
        Map<Integer,Map<Integer,Float>> rateIncreaseMap=new HashMap<>();
        for (int i = 1; i <= shackleConfig.size(); i++) {
            JsonObject rank = shackleConfig.get("rank"+ i).getAsJsonObject();
            Map<Integer,Boolean> shackle = new HashMap<>();
            Map<Integer,Float> breakRate = new HashMap<>();
            Map<Integer,Float> rateIncrease = new HashMap<>();
            for (int j = 1; j <= rank.size(); j++) {
                JsonObject level = rank.get("level"+ j).getAsJsonObject();
                boolean Shackle=level.get("shackle").getAsBoolean();
                float BreakRate=level.get("break_rate").getAsFloat();
                float RateIncrease=level.get("rate_increase").getAsFloat();
                shackle.put(j,Shackle);
                breakRate.put(j,BreakRate);
                rateIncrease.put(j,RateIncrease);
            }
            shackleMap.put(i,shackle);
            breakRateMap.put(i,breakRate);
            rateIncreaseMap.put(i,rateIncrease);
        }
        ShackleMap=shackleMap;
        BreakRateMap=breakRateMap;
        RateIncreaseMap=rateIncreaseMap;
    }

    public String getRankName(int rank){
        return RankNameMap.get(rank);
    }

    public int getRequiredExperience(int rank,int level){
        return RequiredExperienceMap.get(rank).get(level);
    }

    public int getIncreasedExperience(int rank,int level){
        return IncreasedExperienceMap.get(rank).get(level);
    }

    public boolean getShackle(int rank,int level){
        return ShackleMap.get(rank).get(level);
    }

    public float getBreakRate(int rank,int level){
        return BreakRateMap.get(rank).get(level);
    }

    public float getRateIncrease(int rank,int level){
        return RateIncreaseMap.get(rank).get(level);
    }

    public String getLogMessage(String key) {
        return switch (key) {
            case "config.failed" -> "AttributeCore配置文件加载失败:";
            case "file.empty" -> "{}文件为空！";
            case "file.failed" -> "{}文件加载失败:";
            default -> key;
        };
    }

    public String getLogMessage(String key,Object... args){
        return String.format(getLogMessage(key),args);
    }

    public Object getConfigInfo(String key){
        return switch (key) {
            case "ticks_per_increase" -> ticks_per_increase;
            default -> key;
        };
    }
}
