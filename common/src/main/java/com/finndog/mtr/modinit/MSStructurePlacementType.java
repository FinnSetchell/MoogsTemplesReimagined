package com.finndog.mtr.modinit;

import com.finndog.mtr.MSCommon;
import com.finndog.mtr.modinit.registry.RegistryEntry;
import com.finndog.mtr.modinit.registry.ResourcefulRegistries;
import com.finndog.mtr.modinit.registry.ResourcefulRegistry;
import com.finndog.mtr.world.structures.placements.AdvancedRandomSpread;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;


public final class MSStructurePlacementType {
    public static final ResourcefulRegistry<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PLACEMENT, MSCommon.MODID);

    public static final RegistryEntry<StructurePlacementType<AdvancedRandomSpread>> ADVANCED_RANDOM_SPREAD = STRUCTURE_PLACEMENT_TYPE.register("advanced_random_spread", () -> () -> AdvancedRandomSpread.CODEC);
}
