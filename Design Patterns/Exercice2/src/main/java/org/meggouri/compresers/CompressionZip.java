package org.meggouri.compresers;

public class CompressionZip extends ImageCompressor {
    @Override
    protected int[] applyCompression(int[] data) {
        int[] compressedData = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            compressedData[i] = data[i]-1;
        }
        return compressedData;
    }
}
