package managesys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import managesys.model.Category;
import managesys.model.Format;
import managesys.repository.BookRepository;

@Service("masterLogic")
public class MasterService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public List<Category> findAllCategories() {
        return Category.findAll(bookRepository);
    }

    @Transactional
    public void registerCategory(Category category) {
        if (!isExistCategory(category)) {
            category.save(bookRepository);
        }
    }

    @Transactional
    public Category getCategory(int id) {
        return Category.findById(bookRepository, id);
    }

    private boolean isExistCategory(Category category) {
        Category c = Category.findByName(bookRepository, category.getName());
        return Optional.ofNullable(c).isPresent();
    }

    @Transactional
    public List<Format> findAllFormats() {
        return Format.findAll(bookRepository);
    }

    @Transactional
    public void registerFormat(Format format) {
        if (!isExistFormat(format)) {
            format.save(bookRepository);
        }
    }

    @Transactional
    public Format getFormat(int id) {
        return Format.findById(bookRepository, id);
    }

    private boolean isExistFormat(Format format) {
        Format f = Format.findByName(bookRepository, format.getName());
        return Optional.ofNullable(f).isPresent();
    }
}
