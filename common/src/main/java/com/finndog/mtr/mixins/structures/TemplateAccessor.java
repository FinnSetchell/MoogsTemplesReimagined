package com.finndog.mtr.mixins.structures;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructureTemplate.class)
public interface TemplateAccessor {

    @Accessor("palettes")
    List<StructureTemplate.Palette> ms_getPalettes();
}
