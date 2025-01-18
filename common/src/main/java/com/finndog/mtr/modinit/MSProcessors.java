package com.finndog.mtr.modinit;

import com.finndog.mtr.MSCommon;
import com.finndog.mtr.modinit.registry.RegistryEntry;
import com.finndog.mtr.modinit.registry.ResourcefulRegistries;
import com.finndog.mtr.modinit.registry.ResourcefulRegistry;
import com.finndog.mtr.world.processors.WaterloggingFixProcessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class MSProcessors {
    public static final ResourcefulRegistry<StructureProcessorType<?>> STRUCTURE_PROCESSOR = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PROCESSOR, MSCommon.MODID);

    public static final RegistryEntry<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);
}
