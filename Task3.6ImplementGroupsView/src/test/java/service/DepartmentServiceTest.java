package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yevhenpiven.bootstrapproject.entity.Department;
import com.yevhenpiven.bootstrapproject.repository.DepartmentRepository;
import com.yevhenpiven.bootstrapproject.service.DepartmentService;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    private static final int DEPARTMENT_ID = 1;
    private static final String DEPARTMENT_NAME_1 = "Department 1";
    private static final String DEPARTMENT_NAME_2 = "Department 2";

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void testFindAll() {
        Department department1 = new Department(DEPARTMENT_NAME_1);
        Department department2 = new Department(DEPARTMENT_NAME_2);

        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1, department2));

        List<Department> departments = departmentService.findAll();
        assertEquals(2, departments.size());
        assertEquals(DEPARTMENT_NAME_1, departments.get(0).getDepartmentName());
    }

    @Test
    void testFindById() {
        Department department = new Department(DEPARTMENT_NAME_1);
        when(departmentRepository.findById(DEPARTMENT_ID)).thenReturn(Optional.of(department));

        Optional<Department> foundDepartment = departmentService.findById(DEPARTMENT_ID);
        assertTrue(foundDepartment.isPresent());
        assertEquals(DEPARTMENT_NAME_1, foundDepartment.get().getDepartmentName());
    }

    @Test
    void testSave() {
        Department department = new Department(DEPARTMENT_NAME_1);
        when(departmentRepository.save(department)).thenReturn(department);

        Department savedDepartment = departmentService.save(department);
        assertNotNull(savedDepartment);
        assertEquals(DEPARTMENT_NAME_1, savedDepartment.getDepartmentName());
    }

    @Test
    void testDeleteById() {
        doNothing().when(departmentRepository).deleteById(DEPARTMENT_ID);

        departmentService.deleteById(DEPARTMENT_ID);
        verify(departmentRepository, times(1)).deleteById(DEPARTMENT_ID);
    }
}
