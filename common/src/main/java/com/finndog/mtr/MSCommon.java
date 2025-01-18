package com.finndog.mtr;

import com.finndog.mtr.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mtr.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mtr.events.lifecycle.ServerGoingToStopEvent;
import com.finndog.mtr.events.lifecycle.SetupEvent;
import com.finndog.mtr.modinit.MSPlacements;
import com.finndog.mtr.modinit.MSProcessors;
import com.finndog.mtr.modinit.MSStructurePieces;
import com.finndog.mtr.modinit.MSStructurePlacementType;
import com.finndog.mtr.modinit.MSStructures;
import com.finndog.mtr.modinit.MSTags;
import com.finndog.mtr.utils.AsyncLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MSCommon {
    public static final String MODID = "mtr";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        MSTags.initTags();

        MSStructures.STRUCTURE_TYPE.init();
        MSPlacements.PLACEMENT_MODIFIER.init();
        MSProcessors.STRUCTURE_PROCESSOR.init();
        MSStructurePieces.STRUCTURE_PIECE.init();
        MSStructurePieces.STRUCTURE_POOL_ELEMENT.init();
        MSStructurePlacementType.STRUCTURE_PLACEMENT_TYPE.init();

        SetupEvent.EVENT.addListener(MSCommon::setup);
        RegisterReloadListenerEvent.EVENT.addListener(MSCommon::registerDatapackListener);
        ServerGoingToStartEvent.EVENT.addListener(MSCommon::serverAboutToStart);
        ServerGoingToStopEvent.EVENT.addListener(MSCommon::onServerStopping);
    }

    private static void setup(final SetupEvent event) {
    }

    private static void serverAboutToStart(final ServerGoingToStartEvent event) {

        AsyncLocator.handleServerAboutToStartEvent();
    }

    private static void onServerStopping(final ServerGoingToStopEvent event) {
        AsyncLocator.handleServerStoppingEvent();
    }

    public static void registerDatapackListener(final RegisterReloadListenerEvent event) {
    }
}
