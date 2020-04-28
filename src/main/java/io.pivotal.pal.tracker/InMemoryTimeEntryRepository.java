package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements  TimeEntryRepository{

    HashMap<Long,TimeEntry> repository = new HashMap();
    long counter = 1L;

    public InMemoryTimeEntryRepository() {

    }

    @Override
    public TimeEntry create(TimeEntry any) {
        any.setId(counter++);
        repository.put(any.getId(),any);
        return repository.get(any.getId());
    }

    @Override
    public TimeEntry find(long timeEntryId)
    {
        return repository.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {

        return new ArrayList<>(repository.values());
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        if (find(eq) == null) return null;
        TimeEntry updatedEntry = new TimeEntry(eq, any.getProjectId(),
                any.getUserId(),
                any.getDate(),
                any.getHours()
        );
        repository.replace(eq, updatedEntry);
        return updatedEntry;
    }
    @Override
    public void delete(long timeEntryId) {
        repository.remove(timeEntryId);
    }
}
