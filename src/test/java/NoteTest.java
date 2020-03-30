import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    public static Note note;

    @Test
    public void getNameTest() {
        note = Note.of("Kuba",4);
        assertEquals(note.getName(), "Kuba");
    }

    @Test
    public void getNoteTest() {
        note = Note.of("Kuba",4);
        assertEquals(note.getNote(),4);
    }

    @Test
    public void givenNullNameTest() {
        assertThatThrownBy(() -> {
            Note.of(null, 4);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być null");
    }

    @Test
    public void givenEmptyNameTest() {
        assertThatThrownBy(() -> {
            Note.of("", 4);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być puste");
    }

    @Test
    public void givenOutOfRangeMarkTest() {
        assertThatThrownBy(() -> {
            Note.of("Kuba", 1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Niewłaściwa ocena");
    }

    @Test
    public void givenOutOfRangeMarkAnotherArgumentTest() {
        assertThatThrownBy(() -> {
            Note.of("Kuba", 7);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Niewłaściwa ocena");
    }
}
