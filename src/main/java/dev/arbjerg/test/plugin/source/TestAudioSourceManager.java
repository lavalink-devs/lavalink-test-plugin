package dev.arbjerg.test.plugin.source;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.Units;
import com.sedmelluq.discord.lavaplayer.track.AudioItem;
import com.sedmelluq.discord.lavaplayer.track.AudioReference;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class TestAudioSourceManager implements AudioSourceManager {

	private static final Set<String> FILES = Set.of("test1.mp3", "test2.mp3", "test3.mp3", "test4.mp3");
	private static final Logger log = LoggerFactory.getLogger(TestAudioSourceManager.class);
	public static final String TEST_PREFIX = "test:";

	public TestAudioSourceManager() {
		log.info("Loading Test audio source manager...");
	}

	@Override
	public String getSourceName() {
		return "test";
	}

	@Override
	public AudioItem loadItem(AudioPlayerManager manager, AudioReference reference) {
		if (reference.identifier.startsWith(TEST_PREFIX)) {
			var name = reference.identifier.substring(TEST_PREFIX.length()).trim();
			if (name.equals("all")) {
				var tracks = new ArrayList<AudioTrack>();
				for (var file : FILES) {
					tracks.add(new TestAudioTrack(new AudioTrackInfo(
						file,
						"Test author",
						Units.CONTENT_LENGTH_UNKNOWN,
						file,
						false,
						null
					), this));
				}
				return new TestAudioPlaylist("Test Tracks", tracks, null, false);
			}

			if (!FILES.contains(name)) {
				return null;
			}
			return new TestAudioTrack(new AudioTrackInfo(
				name,
				"Test author",
				Units.CONTENT_LENGTH_UNKNOWN,
				name,
				false,
				null
			), this);
		}
		return null;
	}

	@Override
	public boolean isTrackEncodable(AudioTrack track) {
		return true;
	}

	@Override
	public void encodeTrack(AudioTrack track, DataOutput output) {
	}

	@Override
	public AudioTrack decodeTrack(AudioTrackInfo trackInfo, DataInput input) {
		return new TestAudioTrack(trackInfo, this);
	}

	@Override
	public void shutdown() {
	}
}
