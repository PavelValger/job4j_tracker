package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MemTrackerMockTest {

    @Test
    public void whenMockExecuteEditActionTrue() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        UserAction rep = new EditAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Edit item ==="
                + ln + "Заявка изменена успешно" + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenMockExecuteEditActionFalse() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        UserAction rep = new EditAction(out);
        Input input = mock(Input.class);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Edit item ==="
                + ln + "Ошибка замены заявки" + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Replaced item");
    }

    @Test
    public void whenMockExecuteDeleteActionTrue() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item"));
        UserAction deleteAction = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Delete item ==="
                + ln + "Заявка удалена успешно" + ln);
        assertThat(tracker.findAll()).isEmpty();
    }

    @Test
    public void whenMockExecuteDeleteActionFalse() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item"));
        UserAction deleteAction = new DeleteAction(out);
        Input input = mock(Input.class);
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Delete item ==="
                + ln + "Ошибка удаления заявки" + ln);
    }

    @Test
    public void whenMockExecuteFindIdActionTrue() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("Item");
        tracker.add(item);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        UserAction findIdAction = new FindIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        findIdAction.execute(input, tracker);
        var date = item.getCreated();
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find item by id ==="
                + ln + "id: 1, name: Item, created: " + formatter.format(date) + ln);
    }

    @Test
    public void whenMockExecuteFindIdActionFalse() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item"));
        UserAction findIdAction = new FindIdAction(out);
        Input input = mock(Input.class);
        findIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find item by id ==="
                + ln + "Заявка с введенным id: 0 не найдена" + ln);
    }

    @Test
    public void whenMockExecuteFindNameActionTrue() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("Item");
        tracker.add(item);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        UserAction findNameAction = new FindNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Item");
        findNameAction.execute(input, tracker);
        var date = item.getCreated();
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find items by name ==="
                + ln + "id: 1, name: Item, created: " + formatter.format(date) + ln);
    }

    @Test
    public void whenMockExecuteFindNameActionFalseThenException() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item"));
        UserAction findNameAction = new FindNameAction(out);
        Input input = mock(Input.class);
        assertThatThrownBy(() -> findNameAction.execute(input, tracker))
                .isInstanceOf(NullPointerException.class);
    }
}