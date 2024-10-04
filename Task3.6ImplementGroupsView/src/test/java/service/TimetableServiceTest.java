package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yevhenpiven.bootstrapproject.entity.Timetable;
import com.yevhenpiven.bootstrapproject.repository.TimetableRepository;
import com.yevhenpiven.bootstrapproject.service.TimetableService;

@ExtendWith(MockitoExtension.class)
class TimetableServiceTest {

    private static final Integer TIMETABLE_ID = 1;
    private static final LocalDate DATE = LocalDate.of(2024, 9, 30);
    private static final LocalTime START_TIME = LocalTime.of(9, 0);
    private static final LocalTime END_TIME = LocalTime.of(10, 30);

    @Mock
    private TimetableRepository timetableRepository;

    @InjectMocks
    private TimetableService timetableService;

    @Test
    void testFindAll() {
        Timetable timetable1 = new Timetable(DATE, START_TIME, END_TIME);
        Timetable timetable2 = new Timetable(DATE.plusDays(1), START_TIME, END_TIME);

        when(timetableRepository.findAll()).thenReturn(Arrays.asList(timetable1, timetable2));

        List<Timetable> timetables = timetableService.findAll();
        assertEquals(2, timetables.size());
        assertEquals(DATE, timetables.get(0).getDate());
    }

    @Test
    void testFindById() {
        Timetable timetable = new Timetable(DATE, START_TIME, END_TIME);

        when(timetableRepository.findById(TIMETABLE_ID)).thenReturn(Optional.of(timetable));

        Optional<Timetable> foundTimetable = timetableService.findById(TIMETABLE_ID);
        assertTrue(foundTimetable.isPresent());
        assertEquals(DATE, foundTimetable.get().getDate());
    }

    @Test
    void testSave() {
        Timetable timetable = new Timetable(DATE, START_TIME, END_TIME);

        when(timetableRepository.save(timetable)).thenReturn(timetable);

        Timetable savedTimetable = timetableService.save(timetable);
        assertNotNull(savedTimetable);
        assertEquals(DATE, savedTimetable.getDate());
    }

    @Test
    void testDeleteById() {
        doNothing().when(timetableRepository).deleteById(TIMETABLE_ID);

        timetableService.deleteById(TIMETABLE_ID);
        verify(timetableRepository, times(1)).deleteById(TIMETABLE_ID);
    }
}
