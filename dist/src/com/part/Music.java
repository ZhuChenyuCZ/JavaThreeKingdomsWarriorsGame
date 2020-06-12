package com.part;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.io.File;

public class Music implements Runnable {
    private AudioInputStream audioInputStream;
    private boolean isDaemon, isRunning;
    public Music(String path, boolean isDaemon) {
        try {
           audioInputStream = AudioSystem.getAudioInputStream(new File(path));
           this.isDaemon = isDaemon;
           this.isRunning = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

     @Override
    public void run() {
        if(isDaemon){
            while (true){
                playMusic();
            }
        }
        else {
            playMusic();
        }
    }

    public void playMusic(){
        try {
            audioInputStream.mark(0x3f3f3f3f);
            AudioFormat audioFormat = audioInputStream.getFormat();
            SourceDataLine sourceDataLine;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            int cnt = 0;
            byte[] readByte = new byte[1024];
            while (cnt != -1){
                cnt = audioInputStream.read(readByte, 0, 1024);
                if(cnt >= 0)
                    sourceDataLine.write(readByte, 0, cnt);
            }
            audioInputStream.reset();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
