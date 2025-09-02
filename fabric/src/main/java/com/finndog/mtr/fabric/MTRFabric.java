package com.finndog.mtr.fabric;

import com.finndog.mtr.MTRCommon;
import net.fabricmc.api.ModInitializer;

public class MTRFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MTRCommon.init();
    }
}
