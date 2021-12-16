package center.misaki.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface MergePdfService {
    public String MergePdf(File[] files, String username) throws IOException;
}
