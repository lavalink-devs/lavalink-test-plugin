package dev.arbjerg.test.plugin.source;

import com.sedmelluq.discord.lavaplayer.container.mp3.Mp3AudioTrack;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.io.NonSeekableInputStream;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.sedmelluq.discord.lavaplayer.track.DelegatedAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.LocalAudioTrackExecutor;

public class TestAudioTrack extends DelegatedAudioTrack {

	private final TestAudioSourceManager sourceManager;

	public TestAudioTrack(AudioTrackInfo trackInfo, TestAudioSourceManager sourceManager) {
		super(trackInfo);
		this.sourceManager = sourceManager;
	}

	@Override
	public void process(LocalAudioTrackExecutor executor) throws Exception {
		var file = TestAudioTrack.class.getClassLoader().getResourceAsStream("test/" + this.trackInfo.identifier);
		try (var stream = new NonSeekableInputStream(file)) {
			processDelegate(new Mp3AudioTrack(this.trackInfo, stream), executor);
		}
	}

	@Override
	protected AudioTrack makeShallowClone() {
		return new TestAudioTrack(this.trackInfo, this.sourceManager);
	}

	@Override
	public AudioSourceManager getSourceManager() {
		return this.sourceManager;
	}
}
