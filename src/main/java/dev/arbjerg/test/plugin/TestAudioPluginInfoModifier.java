package dev.arbjerg.test.plugin;

import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.arbjerg.lavalink.api.AudioPluginInfoModifier;
import dev.arbjerg.test.plugin.source.TestAudioPlaylist;
import dev.arbjerg.test.plugin.source.TestAudioTrack;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestAudioPluginInfoModifier implements AudioPluginInfoModifier {

	@Nullable
	@Override
	public JsonObject modifyAudioPlaylistPluginInfo(@NotNull AudioPlaylist playlist) {
		if (playlist instanceof TestAudioPlaylist) {
			return new JsonObject(Map.of(
				"test", JsonElementKt.JsonPrimitive("test")
			));
		}
		return null;
	}

	@Nullable
	@Override
	public JsonObject modifyAudioTrackPluginInfo(@NotNull AudioTrack track) {
		if (track instanceof TestAudioTrack) {
			return new JsonObject(Map.of(
				"test", JsonElementKt.JsonPrimitive("test")
			));
		}
		return null;
	}
}
