package pl.shop.bike.update.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class CloudinaryService {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "do2tszehh",
            "api_key", "813687346468584",
            "api_secret", "YFmKU16es3kmCiPiYCS3gnp85FA",
            "secure", true));

    public String uploadPhoto(Part part) throws IOException {
        Map<String, String> uploadParams = new HashMap<>();
        File file = convertMultipartFile(part);
        FileUtils.copyInputStreamToFile(part.getInputStream(), file);
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        return uploadResult.get("url").toString();
    }

    private File convertMultipartFile(Part part) throws IOException {
        File convFile = new File(part.getName());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(part.getInputStream().readAllBytes());
        fos.close();

        return convFile;
    }
}
