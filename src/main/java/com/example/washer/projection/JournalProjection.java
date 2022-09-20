package com.example.washer.projection;

import com.example.washer.dto.Change;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface JournalProjection {
    UUID getId();

    @Value("#{@journalService.allChanges(target.changes)}")
    ChangeProjection getChanges();
}
