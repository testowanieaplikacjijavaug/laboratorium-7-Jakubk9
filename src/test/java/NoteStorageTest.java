import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class NoteStorageTest {
    private NotesStorage notesStorage;
    private NotesService notesService;

    @BeforeEach
    public void setUp() {
        notesStorage = new MockNotesStorage();
        notesService = NotesServiceImpl.createWith(notesStorage);
    }

    @AfterEach
    public void tearDown() {
        notesStorage = null;
        notesService = null;
    }

    @Test
    public void addTest() {
        Note note = Note.of("Kuba", 4.0f);

        notesService.add(note);

        assertFalse(notesStorage.isEmpty());
    }

    @Test
    public void avgTest() {
        Note note1 = Note.of("Kuba", 3.0f);
        Note note2 = Note.of("Kuba", 5.0f);

        notesService.add(note1);
        notesService.add(note2);

        float avg = notesService.averageOf("Kuba");

        assertEquals(4.0f,avg,0.00001);
    }

    @Test
    public void avgWithTwoDifferentPeopleTest() {
        Note note1 = Note.of("Kuba", 3.0f);
        Note note2 = Note.of("Kuba", 4.0f);
        Note note3 = Note.of("Mariusz", 5.0f);
        Note note4 = Note.of("Mariusz", 5.0f);

        notesService.add(note1);
        notesService.add(note2);
        notesService.add(note3);
        notesService.add(note4);

        float avg = notesService.averageOf("Kuba");

        assertEquals(3.5f,avg,0.00001);
    }

    @Test
    public void clearTest() {
        Note note = Note.of("Kuba", 4.0f);

        notesService.add(note);

        notesService.clear();

        assertTrue(notesStorage.isEmpty());
    }

    @Test
    public void givenNullNameTest() {
        assertThatThrownBy(() -> {
            notesService.averageOf(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być null");
    }

    @Test
    public void givenEmptyNameTest() {
        assertThatThrownBy(() -> {
            notesService.averageOf("");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być puste");
    }

    @Test
    public void givenNameFullOfWhiteSpacesTest() {
        assertThatThrownBy(() -> {
            notesService.averageOf("                ");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być puste");
    }
}
