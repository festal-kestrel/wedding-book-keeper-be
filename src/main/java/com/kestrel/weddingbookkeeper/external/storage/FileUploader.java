package com.kestrel.weddingbookkeeper.external.storage;

import java.io.InputStream;

public interface FileUploader {

    String upload(InputStream inputStream, String directory);
}
