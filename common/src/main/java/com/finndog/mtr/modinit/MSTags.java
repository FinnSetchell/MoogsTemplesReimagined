package com.finndog.mtr.modinit;

import com.finndog.mtr.MSCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class MSTags {
    public static void initTags() {}

    public static TagKey<Structure> LARGER_LOCATE_SEARCH = TagKey.create(Registries.STRUCTURE,
            new ResourceLocation(MSCommon.MODID, "larger_locate_search"));

}
