package com.example.eliali.photo.upload;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@Component same as @Service
@Service
public class PhotoService {

    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));
    }};

    public Collection<Photo> get() {
        return db.values();
    }


    public Photo get(String id) {
        return db.get(id);
    }

    public Photo save(String fileName, byte[] data){
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setData(data);
        db.put(photo.getId(), photo);
        return photo;
    }

    public Photo remove(String id) {
        return db.remove(id);
    }

}
