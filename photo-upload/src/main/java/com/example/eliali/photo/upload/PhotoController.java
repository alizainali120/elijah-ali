package com.example.eliali.photo.upload;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    // Can also use @Autowired to inject

    @GetMapping("/")
    public String hello() {
        return "Hello Ali";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return photoService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = photoService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = photoService.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    /*@PostMapping("/photoz")
    public Photo create(@RequestBody @Valid Photo photo) { //Include RequestBody + Use @Valid to validate variable
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(), photo);
        return photo;
    }*/

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photoService.save(file.getOriginalFilename(), file.getBytes());
    }

    /*(async function deletePhoto(id) {
        await fetch('http://localhost:8080/photoz/' + id, {
                method:"DELETE"
       })})("1");*/

}
