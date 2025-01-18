package com.finndog.mtr.modinit;

import com.finndog.mtr.MSCommon;
import com.finndog.mtr.modinit.registry.RegistryEntry;
import com.finndog.mtr.modinit.registry.ResourcefulRegistries;
import com.finndog.mtr.modinit.registry.ResourcefulRegistry;
import com.finndog.mtr.world.structures.GenericJigsawStructure;
import com.finndog.mtr.world.structures.GenericNetherJigsawStructure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;


public final class MSStructures {
    public static final ResourcefulRegistry<StructureType<?>> STRUCTURE_TYPE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_TYPE, MSCommon.MODID);

    public static RegistryEntry<StructureType<GenericJigsawStructure>> GENERIC_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("ms_generic_jigsaw_structure", () -> () -> GenericJigsawStructure.CODEC);
    public static RegistryEntry<StructureType<GenericNetherJigsawStructure>> GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("ms_generic_nether_jigsaw_structure", () -> () -> GenericNetherJigsawStructure.CODEC);
}


