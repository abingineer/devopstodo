package com.example.demo_todo.strategey.concrete;

import com.example.demo_todo.strategey.Compressor;

public class JpegCompressor implements Compressor {

    @Override
    public void compress(String filename) {
        System.out.println("Compression using JPEG");
    }
}
