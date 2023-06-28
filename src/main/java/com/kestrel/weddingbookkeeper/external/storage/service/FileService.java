package com.kestrel.weddingbookkeeper.external.storage.service;

import java.io.InputStream;

public interface FileService {

    String upload(InputStream inputStream, String directory);
}
