package com.finndog.mtr.modinit;

import com.finndog.mtr.MSCommon;
import com.finndog.mtr.modinit.registry.RegistryEntry;
import com.finndog.mtr.modinit.registry.ResourcefulRegistries;
import com.finndog.mtr.modinit.registry.ResourcefulRegistry;
import com.finndog.mtr.world.placements.MinusEightPlacement;
import com.finndog.mtr.world.placements.SnapToLowerNonAirPlacement;
import com.finndog.mtr.world.placements.UnlimitedCountPlacement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public final class MSPlacements {
	public static final ResourcefulRegistry<PlacementModifierType<?>> PLACEMENT_MODIFIER = ResourcefulRegistries.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, MSCommon.MODID);

	public static final RegistryEntry<PlacementModifierType<MinusEightPlacement>> MINUS_EIGHT_PLACEMENT = PLACEMENT_MODIFIER.register("minus_eight_placement", () -> () -> MinusEightPlacement.CODEC);
	public static final RegistryEntry<PlacementModifierType<UnlimitedCountPlacement>> UNLIMITED_COUNT = PLACEMENT_MODIFIER.register("unlimited_count", () -> () -> UnlimitedCountPlacement.CODEC);
	public static final RegistryEntry<PlacementModifierType<SnapToLowerNonAirPlacement>> SNAP_TO_LOWER_NON_AIR_PLACEMENT = PLACEMENT_MODIFIER.register("snap_to_lower_non_air_placement", () -> () -> SnapToLowerNonAirPlacement.CODEC);
}
