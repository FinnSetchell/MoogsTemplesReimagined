package com.finndog.mtr.mixins.resources;

import com.finndog.mtr.modinit.MSConditionsRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltInRegistries.class)
public class BuiltInRegistriesMixin {

    /**
     * Creates and inits our custom registry at game startup
     * @author TelepathicGrunt
     */
    @Inject(method = "<clinit>",
            at = @At(value = "RETURN"))
    private static void ms_initCustomRegistries(CallbackInfo ci) {
        MSConditionsRegistry.MVS_JSON_CONDITIONS_REGISTRY.init();
    }
}
