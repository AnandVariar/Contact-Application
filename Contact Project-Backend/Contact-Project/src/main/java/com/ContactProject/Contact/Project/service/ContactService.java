package com.ContactProject.Contact.Project.service;

import com.ContactProject.Contact.Project.repo.ContactRepo;
import com.ContactProject.Contact.Project.domain.Contact;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.ContactProject.Contact.Project.Constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepo contactRepo;

    public Page<Contact> getAllContacts(int page, int size) {
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Contact getContact(String id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found..."));
    }

    public Contact createContact(Contact contact) {
        return contactRepo.save(contact);
    }

    public void deleteContact(String id) {
        Contact contact = contactRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found..."));

        // Delete associated photo
        String photoUrl = contact.getPhotoUrl();
        if (photoUrl != null && !photoUrl.isEmpty()) {
            deletePhoto(contact.getId(), photoUrl);
        }

        contactRepo.delete(contact);
    }

    public String uploadPhoto(String id, MultipartFile file) {
        Contact contact = getContact(id);
        String photoUrl = photoFunction.apply(id, file);
        contact.setPhotoUrl(photoUrl);
        contactRepo.save(contact);
        return photoUrl;
    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorage = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorage)) {
                Files.createDirectories(fileStorage);
            }
            Files.copy(image.getInputStream(), fileStorage.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/contacts/image/" + filename).toUriString();
        } catch (Exception e) {
            throw new RuntimeException("Unable to save the Image");
        }
    };

    // Method to delete a photo file
    private void deletePhoto(String id, String photoUrl) {
        try {
            Path filePath = Paths.get(PHOTO_DIRECTORY).resolve(id + fileExtension.apply(photoUrl)).normalize();
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete the Image");
        }
    }
}
