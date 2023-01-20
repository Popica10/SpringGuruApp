package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Publisher publisher = new Publisher("Popica publishing", "Barnutiu", "Zalau", "Romania", "something");

        ddd.setPublisher(publisher);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        publisher.getBooks().add(noEJB);

        publisherRepository.save(publisher);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap " + "\n" + "Number of Books:" + bookRepository.count());
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("Number of publishers: ");
        publisherRepository.findAll().forEach(System.out::println);
        System.out.println("The number of books for publiser: " + publisher.getAddress() + " is: " + publisher.getBooks().size());
        System.out.println("The books: " + publisher.getBooks());
    }
}
