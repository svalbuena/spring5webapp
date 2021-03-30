package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(final AuthorRepository authorRepository,
                         final BookRepository bookRepository,
                         final PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        final Publisher oreilly = new Publisher("O'Reilly", "My Street 1", "New York", "NY", "12-34-56");
        publisherRepository.save(oreilly);

        final Author eric = new Author("Eric", "Evans");
        final Book ddd = new Book("Domain Driven Design", "12345");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(oreilly);
        oreilly.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(oreilly);

        final Author rod = new Author("Rod", "Johnson");
        final Book noEjb = new Book("Some EJB Book", "54321");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);
        noEjb.setPublisher(oreilly);
        oreilly.getBooks().add(noEjb);
        authorRepository.save(rod);
        bookRepository.save(noEjb);
        publisherRepository.save(oreilly);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + oreilly.getBooks().size());
    }
}
