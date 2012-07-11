package com.rpgplayground.resourcetools;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioEncoder;
import android.media.MediaRecorder.OutputFormat;
import android.os.Environment;

public class AudioUtility {

	private MediaRecorder recorder = new MediaRecorder();
	private MediaPlayer player = new MediaPlayer();
	String path;

	/**
	 * Creates a new audio recording at the given path (relative to root of SD
	 * card).
	 */
	public AudioUtility(String path) {
		this.path = sanitizePath(path);
	}

	private String sanitizePath(String path) {
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		if (!path.contains(".")) {
			path += ".3gp";
		}
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ path;
	}

	/**
	 * Starts a new recording.
	 */
	public void startRecording() throws IOException {
		String state = android.os.Environment.getExternalStorageState();
		if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
			throw new IOException("SD Card is not mounted.  It is " + state
					+ ".");
		}

		// make sure the directory we plan to store the recording in exists
		File directory = new File(path).getParentFile();
		if (!directory.exists() && !directory.mkdirs()) {
			throw new IOException("Path to file could not be created.");
		}

		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setOutputFile(path);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			recorder.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recorder.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops a recording that has been previously started.
	 */
	public void stopRecording() throws IOException {
		try {
			recorder.stop();
			recorder.release();
			recorder = null;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	// public class AudioUtility {
	//
	// MediaRecorder mr = new MediaRecorder();;
	// MediaPlayer mp = new MediaPlayer();
	//
	// public AudioUtility() {
	// mr.setAudioSource(MediaRecorder.AudioSource.MIC);
	// mr.setOutputFormat(OutputFormat.DEFAULT);
	// mr.setOutputFile("/sdcard/TestRecording.zqa");
	// mr.setAudioEncoder(AudioEncoder.DEFAULT);
	// try {
	// mr.prepare();
	// } catch (IllegalStateException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// public void startRecordingAudio() {
	// try {
	// mr.start();
	// } catch (IllegalStateException e) {
	// // TOpublic class AudioRecorder {
	//
	// final MediaRecorder recorder = new MediaRecorder();
	// final String path;
	//
	// /**
	// * Creates a new audio recording at the given path (relative to root of SD
	// card).
	// */
	// public AudioRecorder(String path) {
	// this.path = sanitizePath(path);
	// }
	//
	// private String sanitizePath(String path) {
	// if (!path.startsWith("/")) {
	// path = "/" + path;
	// }
	// if (!path.contains(".")) {
	// path += ".3gp";
	// }
	// return Environment.getExternalStorageDirectory().getAbsolutePath() +
	// path;
	// }
	//
	// /**
	// * Starts a new recording.
	// */
	// public void start() throws IOException {
	// String state = androi catch (IOException e) {
	// TODO Auto-generated catch block
	// e.printStackTrace();
	// }d.os.Environment.getExternalStorageState();
	// if(!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
	// throw new IOException("SD Card is not mounted.  It is " + state + ".");
	// }
	//
	// // make sure the directory we plan to store the recording in exists
	// File directory = new File(path).getParentFile();
	// if (!directory.exists() && !directory.mkdirs()) {
	// throw new IOException("Path to file could not be created.");
	// }
	//
	// recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	// recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	// recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	// recorder.setOutputFile(path);
	// recorder.prepare();
	// recorder.start();
	// }
	//
	// /**
	// * Stops a recording that has been previously started.
	// */
	// public void stop() throws IOException {
	// recorder.stop();
	// recorder.release();
	// }
	//
	// }DO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// public void stopRecordingAudio() {
	// try {
	// mr.stop();
	// mr.release();
	// } catch (IllegalStateException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	
	public void playRecording() throws IOException {

		player.setDataSource(path);
		player.prepare();
		player.start();

	}
	
	public void stopPlayingRecording() throws IOException {

		player.stop();
		player.release();
		player = null;

	}
}
