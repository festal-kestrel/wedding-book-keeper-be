package com.kestrel.weddingbookkeeper.external.storage;

import java.io.InputStream;

public interface FileService {

    String upload(InputStream inputStream, String directory);
}
