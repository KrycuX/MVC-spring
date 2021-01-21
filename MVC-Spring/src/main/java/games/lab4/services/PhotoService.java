package games.lab4.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PhotoService {
    void saveFile(MultipartFile multipartFile) throws IOException;
}
