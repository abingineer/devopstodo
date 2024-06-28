package com.example.demo_todo.strategey;


public class ImageStore {
    public void store(String fileName, Compressor compressor) {
         compressor.compress(fileName);
    }
}
