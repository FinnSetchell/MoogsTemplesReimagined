package com.finndog.mtr.misc.structurepiececounter;

import com.finndog.mtr.MSCommon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructurePieceCountsManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
    public final static StructurePieceCountsManager STRUCTURE_PIECE_COUNTS_MANAGER = new StructurePieceCountsManager();

    private Map<ResourceLocation, List<StructurePieceCountsObj>> StructureToPieceCountsObjs = new HashMap<>();
    private final Map<ResourceLocation, Map<ResourceLocation, RequiredPieceNeeds>> cachedRequirePiecesMap = new HashMap<>();
    private final Map<ResourceLocation, Map<ResourceLocation, Integer>> cachedMaxCountPiecesMap = new HashMap<>();

    public StructurePieceCountsManager() {
        super(GSON, "rs_pieces_spawn_counts");
    }

    @MethodsReturnNonnullByDefault
    private List<StructurePieceCountsObj> getStructurePieceCountsObjs(ResourceLocation fileIdentifier, JsonElement jsonElement) throws Exception {
        List<StructurePieceCountsObj> piecesSpawnCounts = GSON.fromJson(jsonElement.getAsJsonObject().get("pieces_spawn_counts"), new TypeToken<List<StructurePieceCountsObj>>() {}.getType());
        for(int i = piecesSpawnCounts.size() - 1; i >= 0; i--) {
            StructurePieceCountsObj entry = piecesSpawnCounts.get(i);
            if(entry.alwaysSpawnThisMany != null && entry.neverSpawnMoreThanThisMany != null && entry.alwaysSpawnThisMany > entry.neverSpawnMoreThanThisMany) {
                throw new Exception("Moog's Temples Reimagined Error: Found " + entry.nbtPieceName + " entry has alwaysSpawnThisMany greater than neverSpawnMoreThanThisMany which is invalid.");
            }
        }
        return piecesSpawnCounts;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
        Map<ResourceLocation, List<StructurePieceCountsObj>> mapBuilder = new HashMap<>();
        loader.forEach((fileIdentifier, jsonElement) -> {
            try {
                mapBuilder.put(fileIdentifier, getStructurePieceCountsObjs(fileIdentifier, jsonElement));
            }
            catch (Exception e) {
                MSCommon.LOGGER.error("Moog's Temples Reimagined Error: Couldn't parse rs_pieces_spawn_counts file {} - JSON looks like: {}", fileIdentifier, jsonElement, e);
            }
        });
        this.StructureToPieceCountsObjs = mapBuilder;
        cachedRequirePiecesMap.clear();
        StructurePieceCountsAdditionsMerger.performCountsAdditionsDetectionAndMerger(manager);
    }

    public void parseAndAddCountsJSONObj(ResourceLocation structureRL, List<JsonElement> jsonElements) {
        jsonElements.forEach(jsonElement -> {
            try {
                this.StructureToPieceCountsObjs.computeIfAbsent(structureRL, rl -> new ArrayList<>()).addAll(getStructurePieceCountsObjs(structureRL, jsonElement));
            }
            catch (Exception e) {
                MSCommon.LOGGER.error("Moog's Temples Reimagined Error: Couldn't parse rs_pieces_spawn_counts file {} - JSON looks like: {}", structureRL, jsonElement, e);
            }
        });
    }

    @Nullable
    public Map<ResourceLocation, RequiredPieceNeeds> getRequirePieces(ResourceLocation structureRL) {
        // check to make sure we do have entries for this structure
        if(!this.StructureToPieceCountsObjs.containsKey(structureRL))
            return null;

        // if cached, return cached map
        if(cachedRequirePiecesMap.containsKey(structureRL)) {
            return cachedRequirePiecesMap.get(structureRL);
        }
        // otherwise, compute the required pieces map to return and cache
        else {
            Map<ResourceLocation, RequiredPieceNeeds> requirePiecesMap = new HashMap<>();
            List<StructurePieceCountsObj> structurePieceCountsObjs = this.StructureToPieceCountsObjs.get(structureRL);
            if(structurePieceCountsObjs != null) {
                structurePieceCountsObjs.forEach(entry -> {
                    if (entry.alwaysSpawnThisMany != null)
                        requirePiecesMap.put(new ResourceLocation(entry.nbtPieceName), new RequiredPieceNeeds(entry.alwaysSpawnThisMany, entry.minimumDistanceFromCenterPiece != null ? entry.minimumDistanceFromCenterPiece : 0));
                });
            }
            cachedRequirePiecesMap.put(structureRL, requirePiecesMap);
            return requirePiecesMap;
        }
    }

    @MethodsReturnNonnullByDefault
    public Map<ResourceLocation, Integer> getMaximumCountForPieces(ResourceLocation structureRL) {
        // if cached, return cached map
        if(cachedMaxCountPiecesMap.containsKey(structureRL)) {
            return cachedMaxCountPiecesMap.get(structureRL);
        }
        // otherwise, compute the max count pieces map to return and cache
        else {
            Map<ResourceLocation, Integer> maxCountPiecesMap = new HashMap<>();
            List<StructurePieceCountsObj> structurePieceCountsObjs = this.StructureToPieceCountsObjs.get(structureRL);
            if(structurePieceCountsObjs != null) {
                structurePieceCountsObjs.forEach(entry -> {
                    if(entry.neverSpawnMoreThanThisMany != null)
                        maxCountPiecesMap.put(new ResourceLocation(entry.nbtPieceName), entry.neverSpawnMoreThanThisMany);
                });
            }
            cachedMaxCountPiecesMap.put(structureRL, maxCountPiecesMap);
            return maxCountPiecesMap;
        }
    }

    public record RequiredPieceNeeds(int maxLimit, int minDistanceFromCenter) {
        public int getRequiredAmount() {
            return maxLimit;
        }

        public int getMinDistanceFromCenter() {
            return minDistanceFromCenter;
        }
    }
}
