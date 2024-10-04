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

import com.yevhenpiven.bootstrapproject.entity.Group;
import com.yevhenpiven.bootstrapproject.repository.GroupRepository;
import com.yevhenpiven.bootstrapproject.service.GroupService;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    private static final Integer GROUP_ID = 1;
    private static final String GROUP_NAME_1 = "Group 1";
    private static final String GROUP_NAME_2 = "Group 2";

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @Test
    void testFindAll() {
        Group group1 = new Group(GROUP_NAME_1);
        Group group2 = new Group(GROUP_NAME_2);

        when(groupRepository.findAll()).thenReturn(Arrays.asList(group1, group2));

        List<Group> groups = groupService.findAll();
        assertEquals(2, groups.size());
        assertEquals(GROUP_NAME_1, groups.get(0).getGroupName());
    }

    @Test
    void testFindById() {
        Group group = new Group(GROUP_NAME_1);
        when(groupRepository.findById(GROUP_ID)).thenReturn(Optional.of(group));

        Optional<Group> foundGroup = groupService.findById(GROUP_ID);
        assertTrue(foundGroup.isPresent());
        assertEquals(GROUP_NAME_1, foundGroup.get().getGroupName());
    }

    @Test
    void testSave() {
        Group group = new Group(GROUP_NAME_1);
        when(groupRepository.save(group)).thenReturn(group);

        Group savedGroup = groupService.save(group);
        assertNotNull(savedGroup);
        assertEquals(GROUP_NAME_1, savedGroup.getGroupName());
    }

    @Test
    void testDeleteById() {
        doNothing().when(groupRepository).deleteById(GROUP_ID);

        groupService.deleteById(GROUP_ID);
        verify(groupRepository, times(1)).deleteById(GROUP_ID);
    }
}
