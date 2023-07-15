package dev.arbjerg.test.plugin.filter;

import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;

class TestAudioFilter implements FloatPcmAudioFilter {

	private final float maxVolume;
	private final FloatPcmAudioFilter downstream;

	public TestAudioFilter(float maxVolume, FloatPcmAudioFilter downstream) {
		this.maxVolume = maxVolume;
		this.downstream = downstream;
	}

	@Override
	public void process(float[][] input, int offset, int length) throws InterruptedException {
		for (var i = 0; i < input.length; ++i) {
			for (var ii = 0; ii < length; ++ii) {
				if (input[i][offset + ii] > maxVolume) {
					input[i][offset + ii] = maxVolume;
				}
			}
		}
		this.downstream.process(input, offset, length);
	}

	@Override
	public void seekPerformed(long requestedTime, long providedTime) {
	}

	@Override
	public void flush() {
	}

	@Override
	public void close() {
	}
}