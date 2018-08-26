package com.boldseas.porscheshop.common.utils;

import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author yefei
 */
public class FileToMultipartFileUtils implements MultipartFile {
    private final String name;

    private String originalFilename;

    private String contentType;

    private final byte[] content;

    public FileToMultipartFileUtils(String name, byte[] content) {
        this(name, "", null, content);
    }

    public FileToMultipartFileUtils(String name, InputStream contentStream) throws IOException {
        this(name, "", null, FileCopyUtils.copyToByteArray(contentStream));
    }

    public FileToMultipartFileUtils(String name, String originalFilename, String contentType, byte[] content) {
        Assert.hasLength(name, "Name must not be null");
        this.name = name;
        this.originalFilename = (originalFilename != null ? originalFilename : "");
        this.contentType = contentType;
        this.content = (content != null ? content : new byte[0]);
    }

    public FileToMultipartFileUtils(String name, String originalFilename, String contentType, InputStream contentStream)
            throws IOException {

        this(name, originalFilename, contentType, FileCopyUtils.copyToByteArray(contentStream));
    }

    public FileToMultipartFileUtils(String originalFilename,  OutputStream contentStream) {
        this("attachment", originalFilename, "multipart/form-data", ((ByteArrayOutputStream) contentStream).toByteArray());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOriginalFilename() {
        return this.originalFilename;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return (this.content.length == 0);
    }

    @Override
    public long getSize() {
        return this.content.length;
    }

    @Override
    public byte[] getBytes() {
        return this.content;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        FileCopyUtils.copy(this.content, dest);
    }

}