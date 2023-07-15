package dev.arbjerg.test.plugin.filter;

import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import dev.arbjerg.lavalink.api.AudioFilterExtension;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class TestAudioFilterExtension implements AudioFilterExtension {

	@NotNull
	@Override
	public String getName() {
		return "test";
	}

	@Nullable
	@Override
	public FloatPcmAudioFilter build(@NotNull JsonElement data, @Nullable AudioDataFormat format, @Nullable FloatPcmAudioFilter output) {
		if (data instanceof JsonObject obj) {
			if (obj.get("maxVolume") instanceof JsonPrimitive primitive) {
				var floatVal = Float.parseFloat(primitive.getContent());
				return new TestAudioFilter(floatVal, output);
			}
		}

		return null;
	}

	@Override
	public boolean isEnabled(@NotNull JsonElement data) {
		if (data instanceof JsonObject obj) {
			if (obj.get("maxVolume") instanceof JsonPrimitive primitive) {
				float floatVal;
				try {
					floatVal = Float.parseFloat(primitive.getContent());
				} catch (NumberFormatException e) {
					return false;
				}
				return floatVal > 0;
			}
		}
		return false;
	}
}
