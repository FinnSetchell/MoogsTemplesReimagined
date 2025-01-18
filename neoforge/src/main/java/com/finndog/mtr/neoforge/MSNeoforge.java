package com.finndog.mtr.neoforge;

import com.finndog.mtr.MSCommon;
import com.finndog.mtr.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mtr.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mtr.events.lifecycle.ServerGoingToStopEvent;
import com.finndog.mtr.events.lifecycle.SetupEvent;
import com.finndog.mtr.modinit.registry.neoforge.ResourcefulRegistriesImpl;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;


@Mod(MSCommon.MODID)
public class MSNeoforge {

    public MSNeoforge(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(EventPriority.NORMAL, ResourcefulRegistriesImpl::onRegisterForgeRegistries);

        MSCommon.init();

        modEventBus.addListener(MSNeoforge::onSetup);

        IEventBus eventBus = NeoForge.EVENT_BUS;
        eventBus.addListener(MSNeoforge::onServerStarting);
        eventBus.addListener(MSNeoforge::onServerStopping);
        eventBus.addListener(MSNeoforge::onAddReloadListeners);
    }

    private static void onSetup(FMLCommonSetupEvent event) {
        SetupEvent.EVENT.invoke(new SetupEvent(event::enqueueWork));
    }

    private static void onServerStarting(ServerAboutToStartEvent event) {
        ServerGoingToStartEvent.EVENT.invoke(new ServerGoingToStartEvent(event.getServer()));
    }

    private static void onServerStopping(ServerStoppingEvent event) {
        ServerGoingToStopEvent.EVENT.invoke(ServerGoingToStopEvent.INSTANCE);
    }

    private static void onAddReloadListeners(AddReloadListenerEvent event) {
        RegisterReloadListenerEvent.EVENT.invoke(new RegisterReloadListenerEvent((id, listener) -> event.addListener(listener)));
    }

}
