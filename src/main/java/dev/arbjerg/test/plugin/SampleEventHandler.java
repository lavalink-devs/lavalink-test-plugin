package dev.arbjerg.test.plugin;

import dev.arbjerg.lavalink.api.IPlayer;
import dev.arbjerg.lavalink.api.ISocketContext;
import dev.arbjerg.lavalink.api.PluginEventHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleEventHandler extends PluginEventHandler {

    private static final Logger log = LoggerFactory.getLogger(SampleEventHandler.class);

    public SampleEventHandler() {
        log.info("SampleEventHandler created!");
    }

    @Override
    public void onWebSocketOpen(ISocketContext context, boolean resumed) {
        log.info("Websocket opened!");
    }

    @Override
    public void onDestroyPlayer(@NotNull ISocketContext context, @NotNull IPlayer player) {
        log.info("Player destroyed!");
    }

    @Override
    public void onNewPlayer(@NotNull ISocketContext context, @NotNull IPlayer player) {
        log.info("New player!");
    }

    @Override
    public void onSocketContextDestroyed(@NotNull ISocketContext context) {
        log.info("Socket context destroyed!");
    }

    @Override
    public void onSocketContextPaused(@NotNull ISocketContext context) {
        log.info("Socket context paused!");
    }

    @Override
    public void onWebSocketMessageOut(@NotNull ISocketContext context, @NotNull String message) {
        log.info("Socket message out!");
    }
}
