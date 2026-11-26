package com.ls.straightwindcharges;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StraightWindcharges implements ModInitializer {
    public static final String MOD_ID = "sw";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Straight Windcharges initialized");
    }
}
