package com.finndog.mtr;

import com.finndog.moogs_structures.MoogsStructuresCommon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MTRCommon {
    public static final String MODID = "mtr";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        try {
            // Verify MoogsStructureLib is available
            String libVersion = MoogsStructuresCommon.MODID;
            LOGGER.info("Moogs Temples Reimagined initialized!");
            LOGGER.info("Using MoogsStructureLib version: {}", libVersion);
            
            // Add any additional initialization logic here
            // For example, registering structures, loot tables, etc.
            
        } catch (NoClassDefFoundError | ExceptionInInitializerError e) {
            LOGGER.error("MoogsStructureLib is not available! This mod requires MoogsStructureLib to function.");
            LOGGER.error("Please ensure MoogsStructureLib is installed and up to date.");
            throw new RuntimeException("Missing required dependency: MoogsStructureLib", e);
        }
    }
}
