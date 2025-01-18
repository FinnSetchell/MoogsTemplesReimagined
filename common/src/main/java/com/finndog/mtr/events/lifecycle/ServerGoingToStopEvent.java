package com.finndog.mtr.events.lifecycle;


import com.finndog.mtr.events.base.EventHandler;

public class ServerGoingToStopEvent {

    public static final ServerGoingToStopEvent INSTANCE = new ServerGoingToStopEvent();

    public static final EventHandler<ServerGoingToStopEvent> EVENT = new EventHandler<>();

    private ServerGoingToStopEvent() {}
}
