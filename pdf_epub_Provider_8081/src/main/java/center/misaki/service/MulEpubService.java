package center.misaki.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface MulEpubService {

    public void MergePdfToEpub(File[] files, String name) throws IOException;
}
