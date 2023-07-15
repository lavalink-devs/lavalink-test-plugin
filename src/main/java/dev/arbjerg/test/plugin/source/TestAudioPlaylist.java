package dev.arbjerg.test.plugin.source;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.BasicAudioPlaylist;

import java.util.List;

public class TestAudioPlaylist extends BasicAudioPlaylist {

	public TestAudioPlaylist(String name, List<AudioTrack> tracks, AudioTrack selectedTrack, boolean isSearchResult) {
		super(name, tracks, selectedTrack, isSearchResult);
	}

}
