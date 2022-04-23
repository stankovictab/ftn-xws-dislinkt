package dislinkt.postservice.Service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dislinkt.postservice.Entity.Image;
import dislinkt.postservice.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public String addImage(String title, MultipartFile file) throws IOException { 
        Image image = new Image(title); 
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        
        image = imageRepository.insert(image); 
        
        return image.getId(); 
    }

    public Image getImage(String id) { 
        return imageRepository.findById(id).get(); 
    }
    
    
}
